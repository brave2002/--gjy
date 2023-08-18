package farm;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
public class Farm4Demo {
    //map端
    public static class MapTask extends Mapper<LongWritable, Text,Text,Text> {
        /**
         *香菜	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 大葱	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 香菜	2020-01-01	1.60	山西临汾批发市场	   山西	   临汾
         * 大蒜	2020-01-01	3.60	山西临汾批发市场	   山西	   临汾
         */
        //统计排名前 3 的省份共同拥有的农产品类型
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // 北京   江苏  山东
            String[] splits = value.toString().split("\t");
            //过滤
            if(splits.length>=6){
                if(splits[4].equals("北京") || splits[4].equals("江苏")||splits[4].equals("山东")){
                    //    北京-大豆（不行）     大豆-北京（可以）
                    context.write(new Text(splits[0]+"-"+splits[4]),new Text("1"));
                }
            }
        }
    }

    /**combiner  有时候我们直接使用一个reduce得不到我们想要的聚合结果，这时候，我们需要借助MR中的combiner
     * 这个方法，进行一次中间聚合，然后再输出
     * 缺点：输入与输出类型必须保持一致
     */
    //combiner端   map端的输出就是combiner端输入
    public static class CombinerTask extends Reducer<Text,Text,Text,Text> {
        @Override            //大豆-北京          （1，，，。）
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String[] split = key.toString().split("-");
            //
            context.write(new Text(split[0]),new Text("1"));
        }
    }

    /**
     * reduce端
     * @param
     * @throws Exception
     */
    public static class  ReduceTask extends Reducer<Text,Text,Text,NullWritable>{
        @Override            //菜品          （1,1,1）
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int count=0;
            for (Text value : values) {
                count++;
            }
            //判断
            if(count==3){
                context.write(key,null);
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
        job.setMapperClass(Farm4Demo.MapTask.class);
        job.setReducerClass(Farm4Demo.ReduceTask.class);
        job.setCombinerClass(Farm4Demo.CombinerTask.class);
        job.setJarByClass(Farm4Demo.class);

        //提交输出参数的类型   注意只要输出参数类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(FloatWritable.class);
        job.setOutputValueClass(NullWritable.class);

        //如果输出文件存在   就删除
        String outpath="hdfs://192.168.10.110:9000/bigdata/output/farm4";
        FileSystem fileSystem = FileSystem.get(configuration);
        //
        if(fileSystem.exists(new Path(outpath))){
            fileSystem.delete(new Path(outpath),true);
        }

        //告诉输入输出路径
        FileInputFormat.addInputPath(job,new Path("hdfs://192.168.10.110:9000/bigdata/input/farm.tsv"));
        FileOutputFormat.setOutputPath(job,new Path(outpath));

        //温馨提示一下
        boolean b = job.waitForCompletion(true);
        System.out.println(b?"老铁,没毛病！！！":"哥们,出BUG了！！！");
    }

}
