package com.mycrawler.test.downloader;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestHttp {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        String url = "http://dl_dir.qq.com/music/clntupate/QQMusic_Setup_85_850.exe";
        String downFile = "d:\\QQMusic.exe";
        Long netFileLenght = getNetFileSize(url);
        Long localFileLenght = getLocalFileSize(downFile);

        if (localFileLenght >= netFileLenght) {
            System.out.println("已下载完成");
            return;
        }

        System.out.println("netFileLenght : " + netFileLenght + " localFileLenght : " + localFileLenght);
        final HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setIntParameter("http.socket.timeout", 5000);

        final HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Range", "bytes=" + localFileLenght + "-");

        final HttpResponse response = httpClient.execute(httpGet);
        final int code = response.getStatusLine().getStatusCode();
        final HttpEntity entity = response.getEntity();
        System.out.println(code);

        if (entity != null && code < 400) {

            File file = new File(downFile);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(localFileLenght);

            InputStream inputStream = entity.getContent();
            int b = 0;
            final byte buffer[] = new byte[1024];
            while ((b = inputStream.read(buffer)) != -1) {
                randomAccessFile.write(buffer, 0, b);
            }

            randomAccessFile.close();
            inputStream.close();
            httpClient.getConnectionManager().shutdown();
            System.out.println("下载完成");
        }
    }

    public static Long getLocalFileSize(String fileName) {
        File file = new File(fileName);
        return file.length();
    }

    public static Long getNetFileSize(String url) {
        Long count = -1L;
        final HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setIntParameter("http.socket.timeout", 5000);

        final HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            final int code = response.getStatusLine().getStatusCode();
            final HttpEntity entity = response.getEntity();
            if (entity != null && code == 200) {
                count = entity.getContentLength();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return count;
    }
}