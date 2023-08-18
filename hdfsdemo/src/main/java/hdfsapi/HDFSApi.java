package hdfsapi;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

public class HDFSApi {
    static FileSystem fileSystem;
    static Configuration configuration;
    /**
     * 模拟jdbc操作
     * 获取一个hadoop的连接
     */

    static {
        //只执行一次 在class类加载中执行
        try {
           configuration = new Configuration();
            configuration.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
            configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
            fileSystem=FileSystem.get(URI.create("hdfs://192.168.10.110:9000"),configuration,"gjy"        );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *创建目录
     * @param
     */
    public static void mkdir(String path){
        try {
            boolean mkdirs=fileSystem.mkdirs(new Path(path));
            if(mkdirs){
                System.out.println("创建成功！！");
            }else{
                System.out.println("创建失败！！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 删除目录或者文件
     * @param
     */
    public static void delete(String path){
        try {
             fileSystem.delete(new Path(path),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改目录或者文件  移动
     * @param
     */
    public static void alter(String oldName,String newName){
        try {
            fileSystem.rename(new Path(oldName),new Path(newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地文件上传到hdfs集群
     * @param
     */

    public static void upload(String localPath,String hdfsPath){
        try {
            fileSystem.copyFromLocalFile(new Path(localPath),new Path(hdfsPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把hdfs集群的文件下载到windows本地
     * @param
     */
    public static void download(String hdfsPath,String localPath){
        try {
            fileSystem.copyFromLocalFile(new Path(hdfsPath),new Path(localPath));
            fileSystem.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询 当前目录下的所有内容（不包括递归）
     * @param
     */
    public static void searchBefor(String path){
        try {
            FileStatus[] fileStatus=fileSystem.listStatus(new Path(path));
            for (FileStatus fs:fileStatus){
                System.out.println("文件路径："+fs.getPath());
                System.out.println("副文本："+fs.getReplication());
                System.out.println("实际大小"+fs.getLen());
                System.out.println("block快的大小"+fs.getBlockSize());
                System.out.println("时间戳"+fs.getModificationTime());
                System.out.println("-------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询 当前目录下的所有内容（包括递归）
     * @param
     */
    public static void searchAll(String path){
        try {
           RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(new Path(path),true);
            while (iterator.hasNext()){
                LocatedFileStatus fs = iterator.next();
                System.out.println("文件路径："+fs.getPath());
                System.out.println("副文本："+fs.getReplication());
                System.out.println("实际大小"+fs.getLen());
                System.out.println("block快的大小"+fs.getBlockSize());
                System.out.println("时间戳"+fs.getModificationTime());
                System.out.println("-------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取hdfs输出流
     * @param
     */
    public static void data_to_HDFS(String line){
        try {
            FSDataOutputStream os = null;
            if(fileSystem.exists(new Path("/logs/gmall/2002-09-08.log"))) {
                os = fileSystem.append(new Path("/logs/gmall/2002-09-08.log"));
            }else{
                os = fileSystem.create(new Path("/logs/gmall/2002-09-08.log"));
            }
                //该方法 缺点：每次得到的数据会覆盖上一条数据 优点：目录文件不存在，会自带创建
               //

                //该方法 缺点：m目录不存在不会自动创建   优点：新增的数据不会覆盖原来的数据
              //os = fileSystem.append(new Path("/wode/test"));

            //不用os，会乱码
            IOUtils.copyBytes(org.apache.commons.io.IOUtils.toInputStream(line+"\r\n","utf8"),os,configuration,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //

    }
    public static void main(String[] args){
        //绝对路径 hdfs: //192.168.10.110:9000/ 最好写绝对路径
        //mkdir("hdfs://192.168.10.110:9000/test1");
        //delete("/音乐");
        //alter("/test1","/wode");
        //upload("D:\\桌面\\ooda作业\\可行性研究报告.docx","/wode");
        //download("/wode/test2","D:\\桌面");
        //searchBefor("/wode");
        data_to_HDFS("100,xiaogao,女");
    }
}

