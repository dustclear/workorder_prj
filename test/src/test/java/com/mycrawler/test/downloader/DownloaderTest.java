package com.mycrawler.test.downloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

/**
 * Unit test for simple App.
 */
public class DownloaderTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DownloaderTest(String testName)
    {
        super(testName);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(DownloaderTest.class);
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testDownload()
    {
//        Downloader downloader = Downloader.getInstance();
        String url = "http://cznic.dl.sourceforge.net/project/htmlunit/htmlunit/2.15/htmlunit-2.15-bin.zip";
        HttpDownloadMission downloadMission = new HttpDownloadMission(url, "htmlunit.zip");
        Thread myThread = 
        new Thread(downloadMission);
        myThread.start();
        
        existUntilDownloadFinish(myThread);
        

//        downloader.startDownload(url, "gtx.mkv");
//        downloader.exitDownloader();
    }
    
    private void existUntilDownloadFinish(Thread myThread)
    {
        Thread tc = Thread.currentThread();    
        synchronized (tc) {    
            while (myThread.isAlive()) {    
                try
                {
                    tc.wait(200000l);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }    
            }    
            tc.notify();    
        }    
    }
}
