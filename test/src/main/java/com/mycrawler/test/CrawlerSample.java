package com.mycrawler.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerSample
{
    private static final String URL = "http://104.236.51.108/forum-142-1.html";
    
    public static void main(String[] args)
    {
        try
        {
            loadHtml();
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
    
    public static void loadHtml() throws ClientProtocolException, IOException
    {
        String htmlStr = loadHtml(URL);
        
        File mainFile = new File("E:/myDown", "main.html");
        if (!mainFile.exists())
        {
            if (!mainFile.getParentFile().exists())
            {
                mainFile.getParentFile().mkdirs();
            }
            
            mainFile.createNewFile();
            FileUtils.writeStringToFile(mainFile, htmlStr);
        }
        parseMainHtml(htmlStr);
    }
    
    public static void parseMainHtml(String htmlStr)
    {
        if (!StringUtil.isBlank(htmlStr))
        {
            Document htmlDoc = Jsoup.parse(htmlStr);
            Elements linkElements = htmlDoc.select("a.xst:contains(騎兵)");
            System.out.println(linkElements.size());
            for (Element element : linkElements)
            {
                System.out.println(element.text() + "----------"
                        + element.attr("href"));
                
                try
                {
                    String childHtml = loadHtml("http://104.236.51.108/"
                            + element.attr("href"));
                    File childFile = new File("E:/myDown",
                            FilenameUtils.normalize(element.text()
                                    .replaceAll("★", "")
                                    .replaceAll("㊣", "")
                                    .replaceAll("♂", "")
                                    + ".html"));
                    if (!childFile.exists())
                    {
                        if (!childFile.getParentFile().exists())
                        {
                            childFile.getParentFile().mkdirs();
                        }
                        
                        childFile.createNewFile();
                        FileUtils.writeStringToFile(childFile, childHtml);
                    }
                    
                }
                catch (ClientProtocolException e)
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
    }
    
    public static String loadHtml(String url) throws ClientProtocolException,
            IOException
    {
        Header lengthHeader = Request.Head(url).execute().returnResponse().getLastHeader("Content-Length");
        if (lengthHeader!=null)
        {
            System.out.println("-------------------------------"+lengthHeader);
        }
        else {
            System.out.println("url: "+url);
        }
        
        Content responseContent = Request.Post(url)
        		.addHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)")
                .connectTimeout(20000)
                .socketTimeout(20000)
//                .bodyForm(Form.form().add("agreed", "true").build())
                
                .execute()
                .returnContent();
        InputStream in = responseContent.asStream();
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bReader = new BufferedReader(inputStreamReader);
        StringBuffer sBuffer = new StringBuffer(2000);
        String line;
        while ((line = bReader.readLine())!=null)
        {
            sBuffer.append(line);
        }
       
        return responseContent.asString();
        
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
