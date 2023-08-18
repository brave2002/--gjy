package farm;


import MR.distinct.DistinctDemo1;
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
import java.util.TreeSet;

/**
 * @author: 张鹏飞
 * @company： 软通动力信息技术股份有限公司
 * @Official： www.isoftstone.com
 */
public class Farm1Demo {

    //map端
    public static class MapTask extends Mapper<LongWritable, Text,Text,Text> {
        /**
         *香菜	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 大葱	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 香菜	2020-01-01	1.60	山西临汾批发市场	   山西	   临汾 
         * 大蒜	2020-01-01	3.60	山西临汾批发市场	   山西	   临汾
         */
        //1.统计每个省份(province)的农产品市场(market)总数    (province,market)

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String[] split = value.toString().split("\t");
            if(split.length>=6){
                String market = split[3];
                String province = split[4];
                //写出去
                context.write(new Text(province),new Text(market));
            }
        }
    }

    //reduce端   map端的输出就是reduce端输入
    public static class ReduceTask extends Reducer<Text,Text,Text, IntWritable> {
        //山西    （山西汾阳市晋阳农副产品批发市场，山西汾阳市晋阳农副产品批发市场，山西临汾批发市场）
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            //山西    （山西汾阳市晋阳农副产品批发市场，山西汾阳市晋阳农副产品批发市场，山西临汾批发市场....）
            TreeSet<String> treeSet = new TreeSet<String>();
            //treeSet   ArrayList
            for (Text value : values) {
                treeSet.add(value.toString());
            }
            //
            context.write(key,new IntWritable(treeSet.size()));
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
        job.setMapperClass(Farm1Demo.MapTask.class);
        job.setReducerClass(Farm1Demo.ReduceTask.class);
        job.setJarByClass(Farm1Demo.class);

        //提交输出参数的类型   注意只要输出参数类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //如果输出文件存在   就删除
        String outpath="hdfs://192.168.10.110:9000/bigdata/output/farm1";
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
