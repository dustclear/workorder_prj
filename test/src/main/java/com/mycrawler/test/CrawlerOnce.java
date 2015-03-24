package com.mycrawler.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerOnce
{
    
    public static void main(String[] args)
    {
        /*List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("userName", "dustclear@163.com"));
        try
        {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
            
            HttpPost httpPost = new HttpPost("http://pan.baidu.com");
            httpPost.setEntity(entity);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        */
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
        String htmlStr = loadHtml("http://www.zhihu.com/topic/19569848/top-answers");
        
        File mainFile = new File("H:/myDown", "main.html");
        if (!mainFile.exists())
        {
            if(!mainFile.getParentFile().exists())
            {
                mainFile.getParentFile().mkdirs();
            }
            
            mainFile.createNewFile();
            FileUtils.writeStringToFile(mainFile, htmlStr);
        }
        parseHtml(htmlStr);
    }
    
    public static void parseHtml(String htmlStr)
    {
        if (!StringUtil.isBlank(htmlStr))
        {
            Document htmlDoc = Jsoup.parse(htmlStr);
            Elements linkElements = htmlDoc.select("a.question_link");
            System.out.println(linkElements.size());
            for (Element element : linkElements)
            {
                System.out.println(element.text() + "----------"
                        + element.attr("href"));
                
                try
                {
                    String childHtml = loadHtml("http://www.zhihu.com"+element.attr("href"));
                    File childFile = new File("H:/myDown", FilenameUtils.normalize(element.text().replaceAll("…", "").replaceAll("，", "")+".html"));
                    if (!childFile.exists())
                    {
                        if(!childFile.getParentFile().exists())
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
        return Request.Get(url)
                .connectTimeout(2000)
                .socketTimeout(2000)
                .execute()
                .returnContent()
                .asString();
        
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
