import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class HighestSalaryByRole {

    // ============================================================
    // MAPPER
    // ============================================================

    public static class SalaryMapper
            extends Mapper<Object, Text, Text, DoubleWritable> {

        private final Text roleFamily = new Text();
        private final DoubleWritable salary = new DoubleWritable();

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

            // Dataset must contain all required columns
            if (fields.length < 16) {
                return;
            }

            try {

                // Column 6 = salary_in_usd
                double salaryInUsd = Double.parseDouble(fields[6].trim());

                // Column 15 = role_family
                String role = fields[15].trim();

                if (role.isEmpty()) {
                    return;
                }

                roleFamily.set(role);
                salary.set(salaryInUsd);

                context.write(roleFamily, salary);

            } catch (NumberFormatException e) {
                // Ignore invalid salary records
            }
        }
    }


    // ============================================================
    // REDUCER
    // ============================================================

    public static class SalaryReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private final DoubleWritable maximumSalary =
                new DoubleWritable();

        @Override
        public void reduce(
                Text key,
                Iterable<DoubleWritable> values,
                Context context)
                throws IOException, InterruptedException {

            double maxSalary = Double.MIN_VALUE;

            for (DoubleWritable value : values) {

                if (value.get() > maxSalary) {
                    maxSalary = value.get();
                }
            }

            maximumSalary.set(maxSalary);

            context.write(key, maximumSalary);
        }
    }


    // ============================================================
    // DRIVER
    // ============================================================

    public static void main(String[] args)
            throws Exception {

        if (args.length != 2) {

            System.err.println(
                    "Usage: HighestSalaryByRole <input> <output>");

            System.exit(-1);
        }

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(
                configuration,
                "Highest Salary by Job Role");

        job.setJarByClass(HighestSalaryByRole.class);

        // Mapper
        job.setMapperClass(SalaryMapper.class);

        // Reducer
        job.setReducerClass(SalaryReducer.class);

        // Output types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        // Input / Output paths
        FileInputFormat.addInputPath(
                job,
                new Path(args[0]));

        FileOutputFormat.setOutputPath(
                job,
                new Path(args[1]));

        System.exit(
                job.waitForCompletion(true)
                        ? 0
                        : 1);
    }
}