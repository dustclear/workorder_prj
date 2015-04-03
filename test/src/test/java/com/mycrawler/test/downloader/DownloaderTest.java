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
        Downloader downloader = Downloader.getInstance();
        String url = "http://4dx.pc6.com/gm/wireshark_cn.zip";
        downloader.startDownload(url);
//        downloader.stopDownload();
        downloader.exitDownloader();
    }
    
    public void testUrl()
    {
        
    }
}
