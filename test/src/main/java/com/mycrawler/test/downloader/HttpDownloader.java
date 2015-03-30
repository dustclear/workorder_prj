package com.mycrawler.test.downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpDownloader
{
    public static void main(String[] args)
    {
        download();
    }
    
    public static void download()
    {
//        String url = "http://219.146.68.24/ws.cdn.baidupcs.com/file/dadc13c9944da681f5d740333de6410d?bkt=p2-qd-106&xcode=d97142a56f2e64b38a8282e829434935f3a913716a9c9d63f77424e07ee197d9&fid=2687768786-250528-379138095804928&time=1427444845&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-ubysgkQqii540kjLANvZ2J3plOk%3D&to=hc&fm=Nan,B,T,t&sta_dx=499&sta_cs=259&sta_ft=mkv&sta_ct=5&newver=1&newfm=1&flow_ver=3&sl=80347212&expires=8h&rt=pr&r=251493276&mlogid=1020033313&vuk=2687768786&vbdid=3908911242&fin=%5B%E5%A4%A7%E5%AE%8B%E6%8F%90%E5%88%91%E5%AE%98%5D.Dead.Men.Do.Tell.Tales.S01E34.HDTV.720p.x264.AC3-CMCT.mkv&fn=%5B%E5%A4%A7%E5%AE%8B%E6%8F%90%E5%88%91%E5%AE%98%5D.Dead.Men.Do.Tell.Tales.S01E34.HDTV.720p.x264.AC3-CMCT.mkv&wshc_tag=0&wsts_tag=5515146e&wsid_tag=3ad3f5ed&wsiphost=ipdbm";
        String url = "http://blog.163.com/james_zhangzw/blog/static/794251472009323103232906";
        InputStream in =null;
        try
        {
            final File mainFile = new File("H:/myDown", "2345explorer.exe");
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
        
        Request.Get(url).addHeader("Range", "bytes=0-500")
                .connectTimeout(20000)
                .socketTimeout(20000)
                .execute().handleResponse(new ResponseHandler<String>()
                {
                    public String handleResponse(HttpResponse response)
                            throws ClientProtocolException, IOException
                    {
                        BufferedInputStream bis = new BufferedInputStream(response
                                .getEntity().getContent());
                        byte[] buffer = new byte[1024*5];
                        int i=0;
                        /*while ((bis.read(buffer))!=-1)
                        {
                            System.out.println("writing:"+(i++));
                            FileUtils.writeByteArrayToFile(mainFile, buffer, true);
                        }*/
                        int bytesRead = 0;
                        RandomAccessFile raf = new RandomAccessFile(mainFile, "rw");
                        int offset=0;
                        while ((bytesRead = bis.read(buffer)) != -1) {
                            raf.seek(offset);
                            raf.write(buffer, 0, bytesRead);
                            offset += bytesRead;
                            System.out.println("----------"+bytesRead);
                        }
                        bis.close();
                        return null;
                    }
                })
                ;
        
        
    }
    
  /*  public static void readMethod11(String url, final File mainFile) throws ClientProtocolException, IOException
    {
        
        InputStream in = Request.Get(url).addHeader("Range", "bytes=0-12337000")
                .connectTimeout(2000)
                .socketTimeout(2000)
                .execute().returnContent().asStream()
                ;
        
        BufferedInputStream bis = new BufferedInputStream(in);
        byte[] buffer = new byte[1024*5];
        int i=0;
        while ((bis.read(buffer))!=-1)
        {
            System.out.println("writing:"+(i++));
            FileUtils.writeByteArrayToFile(mainFile, buffer, true);
        }
        int bytesRead = 0;
        RandomAccessFile raf = new RandomAccessFile(mainFile, "rw");
        int offset=0;
        while ((bytesRead = bis.read(buffer)) != -1) {
            raf.seek(offset);
            raf.write(buffer, 0, bytesRead);
            offset += bytesRead;
            System.out.println("----------"+bytesRead);
        }
        
        
    }
    */
    
    public static void readMethod2(String url, final File mainFile) throws ClientProtocolException, IOException
    {
        CloseableHttpClient httpClient;
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
        
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Range", "bytes=500-5000");
        httpGet.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");  
        HttpResponse response = httpClient.execute(httpGet);
        BufferedInputStream bis = new BufferedInputStream(response
                .getEntity().getContent());
        
        byte[] buffer = new byte[1024*5];
        int bytesRead = 0;
        /*while ((bytesRead=bis.read(buffer))!=-1)
        {
            System.out.println("bytesRead:"+bytesRead +"  buffer.size="+buffer.length);
            System.
            FileUtils.writeByteArrayToFile(mainFile, buffer, true);
        }*/
        RandomAccessFile raf = new RandomAccessFile(mainFile, "rw");
        int offset=0;
        while ((bytesRead = bis.read(buffer)) != -1) {
            raf.seek(offset);
            raf.write(buffer, 0, bytesRead);
            offset += bytesRead;
            System.out.println("----------"+bytesRead);
        }
        
    }
    
    public static void readMethod3(String argUrl, File mainFile)
    {
        URLConnection urlConnection = null;
        try {
            URL url = new URL(argUrl);
            urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Range", "bytes=1000-1200");
            RandomAccessFile randomAccessFile = new RandomAccessFile(mainFile, "rw");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(
                    urlConnection.getInputStream());
            int offset=0;
            byte[] buffer = new byte[1024*5];
            int bytesRead = 0;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                randomAccessFile.seek(offset);
                randomAccessFile.write(buffer, 0, bytesRead);
                offset += bytesRead;
                System.out.println("----------"+bytesRead);
            }
            bufferedInputStream.close();
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
