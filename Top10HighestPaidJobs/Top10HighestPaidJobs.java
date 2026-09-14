import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Top10HighestPaidJobs {

    // ============================================================
    // MAPPER
    // ============================================================

    public static class SalaryMapper
            extends Mapper<Object, Text, Text, Text> {

        private final Text outputKey = new Text();
        private final Text outputValue = new Text();

        @Override
        public void map(
                Object key,
                Text value,
                Context context)
                throws IOException, InterruptedException {

            String line = value.toString();

            // Skip CSV header
            if (line.startsWith("work_year,")) {
                return;
            }

            String[] fields = line.split(",", -1);

            // We need at least columns 0 through 15
            if (fields.length < 16) {
                return;
            }

            try {

                // ------------------------------------------------
                // Extract required fields
                // ------------------------------------------------

                // Column 6 = salary_in_usd
                double salary = Double.parseDouble(
                        fields[6].trim());

                // Column 3 = job_title
                String jobTitle = fields[3].trim();

                // Column 11 = experience_level_label
                String experienceLevel = fields[11].trim();

                // Column 9 = company_location
                String companyLocation = fields[9].trim();

                // Column 13 = work_mode
                String workMode = fields[13].trim();


                // ------------------------------------------------
                // Key = salary
                // Value = complete job information
                // ------------------------------------------------

                outputKey.set(String.valueOf(salary));

                String jobRecord =
                        jobTitle + "\t"
                        + experienceLevel + "\t"
                        + companyLocation + "\t"
                        + workMode;

                outputValue.set(jobRecord);

                context.write(outputKey, outputValue);

            } catch (NumberFormatException e) {

                // Ignore records with invalid salary
            }
        }
    }


    // ============================================================
    // REDUCER
    // ============================================================

    public static class Top10Reducer
            extends Reducer<Text, Text, Text, Text> {

        /*
         * Priority queue keeps the 10 highest salaries.
         *
         * Smallest salary stays at the top.
         * When the queue becomes larger than 10,
         * the smallest salary is removed.
         */

        private PriorityQueue<JobRecord> top10;


        @Override
        protected void setup(Context context) {

            top10 = new PriorityQueue<>(
                    10,
                    (a, b) -> Double.compare(
                            a.salary,
                            b.salary));
        }


        @Override
        public void reduce(
                Text key,
                Iterable<Text> values,
                Context context)
                throws IOException, InterruptedException {

            double salary = Double.parseDouble(
                    key.toString());

            for (Text value : values) {

                JobRecord record =
                        new JobRecord(
                                salary,
                                value.toString());

                top10.add(record);

                if (top10.size() > 10) {
                    top10.poll();
                }
            }
        }


        @Override
        protected void cleanup(Context context)
                throws IOException, InterruptedException {

            /*
             * Convert PriorityQueue to an array
             * and sort from highest salary to lowest.
             */

            JobRecord[] records =
                    top10.toArray(
                            new JobRecord[0]);

            java.util.Arrays.sort(
                    records,
                    (a, b) -> Double.compare(
                            b.salary,
                            a.salary));


            int rank = 1;

            for (JobRecord record : records) {

                context.write(
                        new Text(
                                String.valueOf(rank)),
                        new Text(
                                record.salary
                                + "\t"
                                + record.details));

                rank++;
            }
        }
    }


    // ============================================================
    // JOB RECORD CLASS
    // ============================================================

    public static class JobRecord {

        double salary;
        String details;

        JobRecord(
                double salary,
                String details) {

            this.salary = salary;
            this.details = details;
        }
    }


    // ============================================================
    // DRIVER
    // ============================================================

    public static void main(String[] args)
            throws Exception {

        if (args.length != 2) {

            System.err.println(
                    "Usage: Top10HighestPaidJobs <input> <output>");

            System.exit(-1);
        }


        Configuration configuration =
                new Configuration();


        Job job = Job.getInstance(
                configuration,
                "Top 10 Highest-Paid Individual Jobs");


        job.setJarByClass(
                Top10HighestPaidJobs.class);


        // Mapper
        job.setMapperClass(
                SalaryMapper.class);


        // Reducer
        job.setReducerClass(
                Top10Reducer.class);


        // Mapper output
        job.setMapOutputKeyClass(
                Text.class);

        job.setMapOutputValueClass(
                Text.class);


        // Final reducer output
        job.setOutputKeyClass(
                Text.class);

        job.setOutputValueClass(
                Text.class);


        // Input
        FileInputFormat.addInputPath(
                job,
                new Path(args[0]));


        // Output
        FileOutputFormat.setOutputPath(
                job,
                new Path(args[1]));


        System.exit(
                job.waitForCompletion(true)
                        ? 0
                        : 1);
    }
}