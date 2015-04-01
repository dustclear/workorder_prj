package com.mycrawler.test.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document.OutputSettings;

import com.mycrawler.test.downloader.common.FileUtilsEx;

public class HttpDownloaderNew
{
    public static void main(String[] args)
    {
        download();
    }
    
    public static void download()
    {
//        String url = "http://183.131.119.46/ws.cdn.baidupcs.com/file/b165c1ceda4ebb3add9a93cd8e178810?bkt=p2-nb-217&xcode=14c1b0d6f36c2453767761741bd35d29cfbb2d2078bbd50eed03e924080ece4b&fid=2687768786-250528-860575552441089&time=1427705507&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-vMS%2B0oxypGyzZW2V%2BvGlUv7%2BeCQ%3D&to=hc&fm=Nin,B,T,t&sta_dx=504&sta_cs=1157&sta_ft=mp4&sta_ct=5&newver=1&newfm=1&flow_ver=3&sl=80347212&expires=8h&rt=pr&r=691493022&mlogid=1524852030&vuk=2687768786&vbdid=3908911242&fin=%5B%E9%AB%98%E6%B8%85%E7%89%88%5DBetter.Call.Saul.S01E01.chs.eng.mp4&fn=%5B%E9%AB%98%E6%B8%85%E7%89%88%5DBetter.Call.Saul.S01E01.chs.eng.mp4&wshc_tag=0&wsts_tag=55190ea3&wsid_tag=3ad3f5ed&wsiphost=ipdbm";
        String url = "http://4dx.pc6.com/gm/wireshark_cn.zip";
        InputStream in =null;
        try
        {
            System.out.println("filename: "+getFileName(url));
            
            final File mainFile = new File("e:/myDown", URLDecoder.decode(getFileName(url), "UTF-8"));
            if (!mainFile.exists())
            {
                if (!mainFile.getParentFile().exists())
                {
                    mainFile.getParentFile().mkdirs();
                }
                
                mainFile.createNewFile();
            }
            
            
            readMethod1(url, mainFile);
//            readMethod3(url, mainFile);
            
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
        finally{
            if (in!=null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    public static void readMethod1(String url, final File mainFile) throws ClientProtocolException, IOException
    {
        
        Request.Get(url)/*.addHeader("Range", "bytes=500-50000")*/
                .connectTimeout(20000)
                .socketTimeout(20000)
                .execute().handleResponse(new ResponseHandler<String>()
                {
                    public String handleResponse(HttpResponse response)
                            throws ClientProtocolException, IOException
                    {
                        System.out.println("downloading started.....");
                        BufferedInputStream bis = new BufferedInputStream(response
                                .getEntity().getContent());
                        
                        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024*1024*5); // 5Mbytes buffer
                        
                        byte[] buffer = new byte[1024*64];//looks like a tcp package limits to 64k. 
                        int bytesRead = 0;
                        OutputStream out = new FileOutputStream(mainFile, true);
                        int max = 0;
                        while ((bytesRead=bis.read(buffer))!=-1)
                        {
                            
//                            System.out.println("----------"+bytesRead);
                            if (bytesRead>max)
                            {
                               System.out.println(max=bytesRead);
                            }
                            
                            baos.write(buffer, 0, bytesRead);
                            if (baos.size()>(1024*1024*5-1024*64))
                            {
                                baos.writeTo(out);
                                baos.reset();
                            }
                            
//                            out.write(buffer, 0, bytesRead);
                        }
                        if (baos.size()>0)
                        {
                            baos.writeTo(out);
                        }
                        
                        IOUtils.closeQuietly(bis);
                        IOUtils.closeQuietly(out);
                        IOUtils.closeQuietly(baos);
                        System.out.println("downloading finished.....");
                        return null;
                    }
                })
                ;
        
        
    }
    
    public static String getFileName(String url)
    {
        String fileName = null;
        //first, direct name;
        try
        {
            fileName = new URL(url).getFile();
            if (!StringUtil.isBlank(fileName)&&!fileName.contains("&"))
            {
                return fileName.substring(fileName.lastIndexOf("/")+1);
            }
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //sec, in attached header
        try
        {
            fileName  = Request.Head(url).execute().returnResponse().getLastHeader("Content-Disposition").getValue();
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
        fileName=fileName.substring(fileName.indexOf("\"")+1, fileName.length()-1);
        if (StringUtil.isBlank(fileName))
        {
            return "defaultFile";
        }
        try
        {
            return URLDecoder.decode(fileName, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return fileName;
    }
}
