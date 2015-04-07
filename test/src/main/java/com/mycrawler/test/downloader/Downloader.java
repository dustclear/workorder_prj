package com.mycrawler.test.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader
{
    private ExecutorService threadPool = Executors.newFixedThreadPool(5);
    
    private static class SingletonHolder {
        private static final Downloader INSTANCE = new Downloader();
    }
    
    
    
    private List<DownloadMission> missionList = new ArrayList<DownloadMission>();
    
    private Downloader(){}
    
    public static Downloader getInstance()
    {
//        Executors.newScheduledThreadPool(5);
        return SingletonHolder.INSTANCE;
        
    }
    
    
    public void startDownload(String url)
    {
        HttpDownloadMission downloadMission = new HttpDownloadMission(url);
        missionList.add(downloadMission);
        threadPool.execute(downloadMission);
        
    }
    
    public void startDownload(String url, String fileName)
    {
        HttpDownloadMission downloadMission = new HttpDownloadMission(url, fileName);
        missionList.add(downloadMission);
        threadPool.execute(downloadMission);
        
    }
    
    public void deleteMission(DownloadMission mission)
    {
        missionList.remove(mission);
    }
    
    public void exitDownloader()
    {
        System.out.println("stop task afer they are finished!");
        threadPool.shutdown();
    }
}
