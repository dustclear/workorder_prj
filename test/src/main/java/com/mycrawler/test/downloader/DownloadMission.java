package com.mycrawler.test.downloader;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface DownloadMission extends Runnable
{
    void downloadStart() throws ClientProtocolException, IOException;
    
    void downloadStop();
}
