package com.xq.tmall.service;



import java.io.IOException;

public interface HDFSService {
    //    把埋点数据写入HDFS
    void LogtoHDFS(String line) throws IOException;

}

