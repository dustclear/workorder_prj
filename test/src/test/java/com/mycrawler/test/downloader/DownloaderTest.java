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
        String url = "http://ws.cdn.baidupcs.com/file/4d31a674fd6396488b1ea4cf1e31338b?bkt=p-622b76dd1a7e2fc67fb10777ede83321&xcode=f3de7b915e9646817d632fe3ce7c6cb518b2802eb49c5325a271ffda32bcb45b&fid=4230271916-250528-192735187113035&time=1428377023&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-SQ5yAbBJYdSvGaMqpk4RZA0IxIk%3D&to=hc&fm=Nan,B,T,t&sta_dx=2041&sta_cs=349&sta_ft=mkv&sta_ct=7&newver=1&newfm=1&flow_ver=3&sl=80347212&expires=1428418820&rt=sh&r=258741243&mlogid=837978182&sh=1&vuk=2687768786&vbdid=3908911242&fin=Iron%20Man.3BD%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97.mkv&fn=Iron%20Man.3BD%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97.mkv";
        HttpDownloadMission downloadMission = new HttpDownloadMission(url, "gtx.mkv");
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
