package com.xq.tmall.dao;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IOUtils;

public class HDFSDao {
    static FileSystem fileSystem;
    static Configuration configuration;
    static {
//        properties = new Properties();
        try {
//            properties.load(HDFSDao.class.getClassLoader().getResourceAsStream("application.properties"));
            configuration=new Configuration();
            configuration.set("dfs.client.block.write.replace-datanode-on-failure.enable","true");
            configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy","NEVER");
//            configuration.set("dfs.client.socket-timeout", "300000");
//            configuration.set("dfs.datanode.handler.count","10");
            configuration.set("dfs.client.use.datanode.hostname","true");
//            fileSystem = FileSystem.get(URI.create(properties.getProperty("spring.hdfs.log.shopping")), configuration, "root");
//            fileSystem = FileSystem.get(URI.create("hdfs://172.22.228.92:8020/logs/shoppingLogs"), configuration, "root");
            fileSystem = FileSystem.get(URI.create("hdfs://192.168.10.100:8020/logs/shoppingLogs"), configuration, "zh");


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static Configuration getConfiguration() {
        return configuration;
    }
    public static FileSystem getFileSystem(){
        return fileSystem;
    }
//    public static Properties getProperties(){
//        return properties;
//    }



}




