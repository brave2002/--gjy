package MR.wordcount;


import org.apache.commons.io.FileUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

/**
 * 这个代码是在Windows里面执行MR
 */
public class WordCount1 {

    //map端  ()       map端输出(hadoop,1)

    /**
     * Text:指的是StringWritable
     *(LongWritable,Text) map端的输入：这俩个参数类型，永远不变
     * LongWritable：指的是偏移量
     * Text：指的是文本数据
     *
     * （Text, IntWritable）map端的输出  （hadoop,1）  根据需求一直处于变化之中
     */
    public static class MapTask extends Mapper<LongWritable,Text,Text, IntWritable>{
        //每读取一行数据，该方法就执行一次    注意   再注意
        @Override            //偏移量          //数据
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //hadoop,hadoop,spark,spark   ==>   (hadoop,1)
            String[] words = value.toString().split(",");
            //循环遍历
            for (String word : words) {
                //(hadoop,1)   (hadoop,1)   (hive,1)......
                context.write(new Text(word),new IntWritable(1));
            }
        }
    }

    //reduce端   map端的输出就是reduce端输入   （hadoop,146）
    public static class ReduceTask extends Reducer<Text,IntWritable,Text,IntWritable>{
        //每换一个key值，该方法就执行一次    注意   在注意
        @Override           //分组的那个key       (1,1,1,1,1,1....)
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count=0;
            for (IntWritable value : values) {
                count++;
            }
            //写出去
            context.write(key,new IntWritable(count));
        }
    }

    //main方法  测试提交任务
    public static void main(String[] args) throws Exception {
        //本地测试模式   job的对象去提交任务
        Job job = Job.getInstance();

        //提交我们写的这俩个内部类
        job.setMapperClass(MapTask.class);
        job.setReducerClass(ReduceTask.class);
        job.setJarByClass(WordCount1.class);

        //提交输出参数的类型   注意只要输出参数类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //如果输出文件存在   就删除
        String outpath="D:\\bigdata\\output\\wordcount1";
        File file = new File(outpath);
        if(file.exists()){
            FileUtils.deleteDirectory(file);
        }
        //告诉输入输出路径
        FileInputFormat.addInputPath(job,new Path("D:\\bigdata\\input\\wc.txt"));
        FileOutputFormat.setOutputPath(job,new Path(outpath));

        //温馨提示一下
        boolean b = job.waitForCompletion(true);
        System.out.println(b?"老铁,没毛病！！！":"哥们,出BUG了！！！");
    }

}
