package MRTest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final int missige_max = 9999;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String year = line.split("\\s+")[1];
        int airTemplate = missige_max;
        if(line.split("\\s+").length > 2){
            airTemplate = Integer.parseInt(line.split("\\s+")[2]);
        }else {
            airTemplate = missige_max;
        }
        context.write(new Text(year),new IntWritable(airTemplate));

        //test wordCount
//        String[] words = line.split("\\s+");
//        for (String word : words) {
//            context.write(new Text(word),new IntWritable(1));
//        }

    }
}