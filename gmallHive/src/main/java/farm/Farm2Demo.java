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
import java.util.TreeSet;

/**
 *
 */
public class Farm2Demo {

    //map端
    public static class MapTask extends Mapper<LongWritable, Text,Text,Text> {
        /**
         *香菜	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 大葱	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 香菜	2020-01-01	1.60	山西临汾批发市场	   山西	   临汾
         * 大蒜	2020-01-01	3.60	山西临汾批发市场	   山西	   临汾
         */
        //统计山东省(province)售卖蛤蜊(name)的农产品市场(market)占全省农产品市场的比例
        //求出山东省所有的农场品市场   求出山东省所有的农场品市场卖蛤蜊的
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //key  （market,name）
            String[] splits = value.toString().split("\t");
            //过滤
            if(splits.length>=6){
                //获取需要的字段
                String name = splits[0];
                String market = splits[3];
                String province = splits[4];
                //过滤山东
                if(province.equals("山东")){
                    context.write(new Text(market),new Text(name));
                }
            }
        }
    }

    //reduce端   map端的输出就是reduce端输入
    public static class ReduceTask extends Reducer<Text,Text,FloatWritable,NullWritable> {
        //山东省去重后的所有批发市场数量
        int sum=0;
        //卖蛤蜊的批发市场数量
        int count=0;
        @Override       //山东济南市堤口路果品批发市场    (大豆，茄子，黄瓜。。。。。。)
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //局部变量
            for (Text value : values) {
                System.out.println(value.toString());
                if(value.toString().equals("蛤蜊")){
                    count++;
                }
            }
            //统计的是山东省去重后的所有批发市场数量
            sum++;
            //写出去   0.2222
            // context.write(new FloatWritable(count/sum),null);
        }

        /**
         * @param context
         */
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            //reduce方法全部执行完毕之后，我最后执行一遍
            context.write(new FloatWritable(1.0f*count/sum),null);
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
        job.setMapperClass(Farm2Demo.MapTask.class);
        job.setReducerClass(Farm2Demo.ReduceTask.class);
        job.setJarByClass(Farm2Demo.class);

        //提交输出参数的类型   注意只要输出参数类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(FloatWritable.class);
        job.setOutputValueClass(NullWritable.class);

        //如果输出文件存在   就删除
        String outpath="hdfs://192.168.10.110:9000/bigdata/output/farm2";
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
