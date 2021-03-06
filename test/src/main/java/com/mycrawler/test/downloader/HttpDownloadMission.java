package com.mycrawler.test.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.Args;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;

public class HttpDownloadMission implements DownloadMission
{
    private static final Logger LOGGER = Logger.getLogger(HttpDownloadMission.class);
    //the download file url.
    private String url;
    
    private String fileName;
    
    //local path where the downloaded file stored. e.g. c:/download/
    private String localPath = "e:/myDown";
    
    private File downLoadFile;
    
    private long timeCost;
    
    public HttpDownloadMission(String url)
    {
        this.url = url;
        this.fileName = getFileNameFromUrlString(url);
    }
    
    public HttpDownloadMission(String url, String fileName)
    {
        this.url = url;
        this.fileName = fileName;
    }
    
    public void run()
    {
        try
        {
            File localFile = createLocalFile();
            if (localFile != null)
            {
                downloadStart();
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void downloadStart() throws ClientProtocolException, IOException
    {
        LOGGER.debug(fileName
                + " downloading started.....");
        Request.Get(url)
                .addHeader("User-Agent",
                        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)")
                /*.addHeader("Range", "bytes=500-50000")*/
                .connectTimeout(20000) //建立连接超时
                .socketTimeout(60000)  //下载数据包时间隔超时
                .execute()
                .handleResponse(new ResponseHandler<String>()
                {
                    public String handleResponse(HttpResponse response)
                            throws ClientProtocolException, IOException
                    {
                        long startTime = System.currentTimeMillis();
                        
                        BufferedInputStream bis = new BufferedInputStream(
                                response.getEntity().getContent());
                        
                        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                                1024 * 1024 * 5); // 5Mbytes buffer
                        
                        byte[] buffer = new byte[1024 * 64];//looks like a tcp package limits to 64k. 
                        int bytesRead = 0;
                        OutputStream out = new FileOutputStream(downLoadFile,
                                true);
                        int max = 0;
                        while ((bytesRead = bis.read(buffer)) != -1)
                        {
                            
                            /* if (bytesRead > max)
                             {
                                 System.out.println(max = bytesRead);
                             }*/
                            
                            baos.write(buffer, 0, bytesRead);
                            if (baos.size() > (1024 * 1024 * 5 - 1024 * 64))
                            {
                                System.out.println("persist to disk "+baos.size());
                                baos.writeTo(out);
                                baos.reset();
                            }
                            
                        }
                        if (baos.size() > 0)
                        {
                            baos.writeTo(out);
                        }
                        
                        IOUtils.closeQuietly(bis);
                        IOUtils.closeQuietly(out);
                        IOUtils.closeQuietly(baos);
                        LOGGER.debug(fileName
                                + " downloading finished.....");
                        long endTime = System.currentTimeMillis();
                        timeCost = endTime - startTime;
                        LOGGER.debug(fileName + " total time cost:"
                                + timeCost / 1000 + "." + timeCost % 1000
                                + " seconds");
                        return null;
                    }
                });
    }
    
    public void downloadStop()
    {
        
    }
    
    private File createLocalFile() throws IOException
    {
        Args.notBlank(url, "Request URI");
        
        downLoadFile = new File(localPath, fileName);
        if (downLoadFile.exists())
        {
            if (downLoadFile.length()>0)
            {
                return null;
            }
            else {
                downLoadFile.delete();
            }
            
        }
        if (!downLoadFile.getParentFile().exists())
        {
            downLoadFile.getParentFile().mkdirs();
        }
        
        downLoadFile.createNewFile();
        
        return downLoadFile;
    }
    
    //todo: have bugs, need to modify
    private String getFileNameFromUrlString(String url)
    {
        Args.notBlank(url, "Request URI");
        String tempFileName = null;
        
        try
        { //get name from attached header
        
            Header nameHeader = Request.Head(url)
                    .execute()
                    .returnResponse()
                    .getLastHeader("Content-Disposition");
            if (nameHeader != null)
            {
                tempFileName = nameHeader.getValue();
            }
            
            if (StringUtil.isBlank(tempFileName))
            {
                //get direct name;
                tempFileName = new URL(url).getFile();
            }
            
            if (!StringUtil.isBlank(tempFileName))
            {
                tempFileName = URLDecoder.decode(tempFileName.substring(tempFileName.lastIndexOf("/") + 1),
                        "UTF-8");
            }
            else
            {
                tempFileName = "defaultFile";
            }
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return tempFileName;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    public String getLocalPath()
    {
        return localPath;
    }
    
    public void setLocalPath(String localPath)
    {
        this.localPath = localPath;
    }
    
    public File getDownLoadFile()
    {
        return downLoadFile;
    }
    
    public void setDownLoadFile(File downLoadFile)
    {
        this.downLoadFile = downLoadFile;
    }
    
}
