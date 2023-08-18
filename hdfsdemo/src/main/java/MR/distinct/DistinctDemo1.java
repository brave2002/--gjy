package MR.distinct;


import MR.wordcount.WordCount2;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 数据去重案例
 */
public class DistinctDemo1 {

    //map端
    public static class MapTask extends Mapper<LongWritable, Text,Text,Text> {
        //1351307	广东	惠州	移动  ==>  (135  广东  惠州，移动)
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split("\t");
            if(splits.length>=7){
                // 135
                String phone = splits[0].substring(0,3);
                String province = splits[1];
                String city = splits[2];
                String operator = splits[3];
                //写出去
                context.write(new Text(phone+"\t"+province+"\t"+city),new Text(operator));
            }

        }
    }

    //reduce端   map端的输出就是reduce端输入
    public static class ReduceTask extends Reducer<Text,Text,Text,Text> {
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //(135  广东  惠州 ,{移动。。。。。})  ==》（135  广东  惠州  ，移动 ）
            for (Text value : values) {
                context.write(key,value);
                break;
            }
        }
    }

    //main方法  测试提交任务
    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME","gjy");
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.10.110:9000");
        //集群模式   job的对象去提交任务
        Job job = Job.getInstance(configuration);
        //提交我们写的这俩个内部类
        job.setMapperClass(DistinctDemo1.MapTask.class);
        job.setReducerClass(DistinctDemo1.ReduceTask.class);
        job.setJarByClass(DistinctDemo1.class);

        //提交输出参数的类型   注意只要输出参数类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //如果输出文件存在   就删除
        String outpath="hdfs://192.168.10.110:9000/bigdata/output/distinct";
        FileSystem fileSystem = FileSystem.get(configuration);
        //
        if(fileSystem.exists(new Path(outpath))){
            fileSystem.delete(new Path(outpath),true);
        }

        //告诉输入输出路径
        FileInputFormat.addInputPath(job,new Path("hdfs://192.168.10.110:9000/bigdata/input/Phone.txt"));
        FileOutputFormat.setOutputPath(job,new Path(outpath));

        //温馨提示一下
        boolean b = job.waitForCompletion(true);
        System.out.println(b?"老铁,没毛病！！！":"哥们,出BUG了！！！");
    }

}
