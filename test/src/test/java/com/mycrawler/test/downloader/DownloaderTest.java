package com.mycrawler.test.downloader;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

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
        HttpDownloadMission downloadMission = new HttpDownloadMission(url,
                "htmlunit.zip");
        Thread myThread = new Thread(downloadMission);
        myThread.start();
        
        existUntilDownloadFinish(myThread);
        
        //        downloader.startDownload(url, "gtx.mkv");
        //        downloader.exitDownloader();
    }
    
    private void existUntilDownloadFinish(Thread myThread)
    {
        Thread tc = Thread.currentThread();
        synchronized (tc)
        {
            while (myThread.isAlive())
            {
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
    
    public void loadHtmlOther()
    {
        
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
        webClient.getCookieManager().setCookiesEnabled(true);//enable cookies
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setAjaxController(new AjaxController()
        {
            @Override
            public boolean processSynchron(HtmlPage page, WebRequest request,
                    boolean async)
            {
                return true;
            }
        });
        webClient.setJavaScriptTimeout(3000);
        webClient.getOptions().setTimeout(3000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        
        HtmlPage page;
        try
        {
            page = webClient.getPage("http://pan.baidu.com/wap/link?shareid=2289386&uk=4230271916&third=0&dir=%2F电影%2F好莱坞大片%2F钢铁侠1-3&page=1&");
            System.out.println("start-------------------------------");
            System.out.println(page.getWebResponse().getContentAsString()); //source code
            webClient.closeAllWindows();
            
        }
        catch (FailingHttpStatusCodeException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
