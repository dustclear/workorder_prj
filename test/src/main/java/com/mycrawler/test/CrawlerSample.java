package com.mycrawler.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mycrawler.test.downloader.Downloader;

public class CrawlerSample
{
    
    private static final Logger LOGGER = Logger.getLogger(CrawlerSample.class);
    
    private static String URL = "http://104.236.51.108/forum-142-1.html";
    
    private static final String IP = "http://104.236.51.108/";
    
    Downloader downloader = Downloader.getInstance();
    
    private int seedCount = 0;
    
    private int picCount = 0;
    
    public static void main(String[] args)
    {
        new CrawlerSample().startWork();
        
    }
    
    public void startWork()
    {
        for (int i = 600; i < 2501; i++)
        {
            URL = "http://104.236.51.108/forum-142-" + i + ".html";
            String htmlStr;
			try {
				htmlStr = loadHtml(URL);
				parseMainHtml(htmlStr);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //          persistHtml("main.html", htmlStr);
            
            
            LOGGER.debug("total pics---" + picCount);
            LOGGER.debug("total seeds---" + seedCount);
        }
        downloader.exitDownloader();
    }
    
    public void parseMainHtml(String mainHtml)
    {
        if (!StringUtil.isBlank(mainHtml))
        {
            Document htmlDoc = Jsoup.parse(mainHtml);
//            Elements linkElements = htmlDoc.select("a.xst:contains(步兵)");
            Elements linkElements = htmlDoc.select("a.xst:contains(騎兵)");
            for (Element element : linkElements)
            {
                String childHtml;
                try
                {
                    childHtml = loadHtml(IP + element.attr("href"));
                    /*persistHtml(FilenameUtils.normalize(element.text()
                            .replaceAll("★", "")
                            .replaceAll("㊣", "")
                            .replaceAll("♂", "")
                            + ".html"), childHtml);*/
                    
                    parseChildHtml(childHtml);
                }
                catch (ClientProtocolException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                
            }
        }
    }
    
    private void parseChildHtml(String childHtml)
    {
        if (!StringUtil.isBlank(childHtml))
        {
            Document htmlDoc = Jsoup.parse(childHtml);
            Elements seedElements = htmlDoc.select("a:contains(.torrent)");
            Elements picElements = htmlDoc.select("img[zoomfile]");
            
//            downloadSeeds(seedElements);
            
            downloadPics(picElements);
        }
    }

	private void downloadPics(Elements picElements) {
		for (Element picElement : picElements)
		{
		    String picUrl = picElement.attr("zoomfile");
		    String picName = picElement.attr("title");
		    if (StringUtils.isNotBlank(picUrl))
		    {
		        downloader.startDownload(picUrl, picName);
		    }
		    picCount++;
		}
	}

	private void downloadSeeds(Elements seedElements) {
		for (Element seedElement : seedElements)
		{
		    String seedUrl = IP + seedElement.attr("href");
		    String seedName = seedElement.text();
		    if (StringUtils.isNotBlank(seedUrl))
		    {
		        downloader.startDownload(seedUrl, seedName);
		    }
		    seedCount++;
		}
	}
    
    public String loadHtml(String url) throws ClientProtocolException,
            IOException
    {
        LOGGER.debug("url: " + url);
        
        Content responseContent = Request.Post(url)
                .addHeader("User-Agent",
                        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)")
                .connectTimeout(20000)
                .socketTimeout(60000)
                //                .bodyForm(Form.form().add("agreed", "true").build())
                .execute()
                .returnContent();
        InputStream in = responseContent.asStream();
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bReader = new BufferedReader(inputStreamReader);
        StringBuffer sBuffer = new StringBuffer(2000);
        String line;
        while ((line = bReader.readLine()) != null)
        {
            sBuffer.append(line);
        }
        
        return responseContent.asString();
        
    }
    
    private void persistHtml(String fileName, String fileContent)
    {
        File mainFile = new File("E:/myDown", fileName);
        if (!mainFile.exists())
        {
            if (!mainFile.getParentFile().exists())
            {
                mainFile.getParentFile().mkdirs();
            }
            try
            {
                mainFile.createNewFile();
                FileUtils.writeStringToFile(mainFile, fileContent);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static String post()
    {
        return null;
    }
    
    public static String get()
    {
        return null;
    }
}
