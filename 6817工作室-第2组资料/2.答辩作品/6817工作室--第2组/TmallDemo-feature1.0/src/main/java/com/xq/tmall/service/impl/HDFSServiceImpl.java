package com.xq.tmall.service.impl;

import com.xq.tmall.dao.HDFSDao;
import com.xq.tmall.service.HDFSService;
import com.xq.tmall.util.TimesUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class HDFSServiceImpl implements HDFSService {
    public void LogtoHDFS(String line) throws IOException {
        FileSystem fileSystem= HDFSDao.getFileSystem();
        if(fileSystem==null){
            System.out.println("Error get filesystem--------------------------------------------------------");
            return;
        }
        Path dst=new Path("hdfs://192.168.10.100:8020/logs/shoppingLogs/"+ TimesUtil.getTimes());
//        Path dst=new Path("hdfs://172.22.228.92:8020/logs/shoppingLogs");
//        Properties properties=new Properties();
        OutputStream outputStream;

        if(fileSystem.exists(dst)){
            outputStream=fileSystem.append(dst);
        }else {
            outputStream = fileSystem.create(dst);
        }

        System.out.println("正在写入------------------------------------");
        IOUtils.copyBytes(org.apache.commons.io.IOUtils.toInputStream(line+"\r\n"),outputStream,HDFSDao.getConfiguration(),true);
        outputStream.close();
//        fileSystem.close();
        System.out.println("写入完成-------------------------------------");
    }
}

