import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class JobsByExperienceLevel {

    // ============================================================
    // MAPPER
    // ============================================================

    public static class ExperienceMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final Text experienceLevel = new Text();
        private final IntWritable one = new IntWritable(1);

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

            // Make sure required columns exist
            if (fields.length < 12) {
                return;
            }

            // Column 11 = experience_level_label
            String experience = fields[11].trim();

            if (experience.isEmpty()) {
                return;
            }

            experienceLevel.set(experience);

            context.write(experienceLevel, one);
        }
    }


    // ============================================================
    // REDUCER
    // ============================================================

    public static class ExperienceReducer
            extends Reducer<Text, IntWritable, Text, IntWritable> {

        private final IntWritable totalJobs = new IntWritable();

        @Override
        public void reduce(
                Text key,
                Iterable<IntWritable> values,
                Context context)
                throws IOException, InterruptedException {

            int sum = 0;

            for (IntWritable value : values) {
                sum += value.get();
            }

            totalJobs.set(sum);

            context.write(key, totalJobs);
        }
    }


    // ============================================================
    // DRIVER
    // ============================================================

    public static void main(String[] args)
            throws Exception {

        if (args.length != 2) {

            System.err.println(
                    "Usage: JobsByExperienceLevel <input> <output>");

            System.exit(-1);
        }

        Configuration configuration = new Configuration();

        Job job = Job.getInstance(
                configuration,
                "Job Count by Experience Level");

        job.setJarByClass(JobsByExperienceLevel.class);

        // Mapper
        job.setMapperClass(ExperienceMapper.class);

        // Reducer
        job.setReducerClass(ExperienceReducer.class);

        // Output types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Input path
        FileInputFormat.addInputPath(
                job,
                new Path(args[0]));

        // Output path
        FileOutputFormat.setOutputPath(
                job,
                new Path(args[1]));

        System.exit(
                job.waitForCompletion(true)
                        ? 0
                        : 1);
    }
}