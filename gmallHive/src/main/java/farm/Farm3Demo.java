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
public class Farm3Demo {
    //map端
    public static class MapTask extends Mapper<LongWritable, Text,Text,Text> {
        /**
         *香菜	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 大葱	2020-01-01	2.80	山西汾阳市晋阳农副产品批发市场	山西	汾阳
         * 香菜	2020-01-01	1.60	山西临汾批发市场	   山西	   临汾
         * 大蒜	2020-01-01	3.60	山西临汾批发市场	   山西	   临汾
         */
        //统计每个省(province)农产品种类(name)总数，找出排名前3的省份
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //key    (province,name)
            String[] splits = value.toString().split("\t");
            //过滤
            if(splits.length>=6){
                context.write(new Text(splits[4]),new Text(splits[0]));
            }
        }
    }

    //reduce端   map端的输出就是reduce端输入
    public static class ReduceTask extends Reducer<Text,Text,Text, IntWritable> {
        ArrayList<FarmBean> beans = new ArrayList<FarmBean>();
        @Override          //山西                （大豆，大豆，茄子。。。。。）
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            TreeSet<String> treeSet = new TreeSet<String>();
            //
            for (Text value : values) {
                treeSet.add(value.toString());
            }
            //
            beans.add(new FarmBean(key.toString(),treeSet.size()));
            //需要排序  再写出    (山西，168)   （北京，200）。。。。。 排序？？？？   (key,value)
        }
        //
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            beans.sort(new Comparator<FarmBean>() {
                @Override
                public int compare(FarmBean o1, FarmBean o2) {
                    //o1-o2 升序       o2-o1  降序
                    return o2.getSum()-o1.getSum();
                }
            });
            //取前三
            for(int i=0;i<3;i++){
                context.write(new Text(beans.get(i).getProvince()),new IntWritable(beans.get(i).getSum()));
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
        job.setMapperClass(Farm3Demo.MapTask.class);
        job.setReducerClass(Farm3Demo.ReduceTask.class);
        job.setJarByClass(Farm3Demo.class);

        //提交输出参数的类型   注意只要输出参数类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(FloatWritable.class);
        job.setOutputValueClass(NullWritable.class);

        //如果输出文件存在   就删除
        String outpath="hdfs://192.168.10.110:9000/bigdata/output/farm3";
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
