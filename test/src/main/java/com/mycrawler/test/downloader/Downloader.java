package com.mycrawler.test.downloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader
{
    public static void main(String[] args)
    {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        String url = "http://4dx.pc6.com/gm/wireshark_cn.zip";
        
        HttpDownloadMission downloadMission = new HttpDownloadMission(url);
        
        threadPool.execute(downloadMission);
    }
}
