package com.wos.test;


public class OnlyTest
{
    
    public static void main(String[] args)
    {
        /*DefaultHttpClient httpclient = new DefaultHttpClient();
        String proxyHost = "127.0.0.1";
        int proxyPort = 8087;
        HttpHost proxy = new HttpHost(proxyHost,proxyPort);
        httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
        
        URI uri;
        HttpResponse response;
        try
        {
            uri = new URIBuilder()
            .setScheme("http")
            .setHost("114.215.124.213")
            .setPort(7001)
            .setPath("/ZHYW/zh/check_dispatch_code.jsp")
            .setParameter("DISPATCH_ID", "")
            .setParameter("CODE", "")
            .setParameter("VERIFY_CODE", "")
            .build();
            
            HttpGet httpget = new HttpGet(uri);
            response = httpclient.execute(httpget);
            
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(
                        statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            }
            if (entity == null) {
                throw new ClientProtocolException("Response contains no content");
            }
            Gson gson = new GsonBuilder().create();
            ContentType contentType = ContentType.getOrDefault(entity);
            Charset charset = contentType.getCharset();
            Reader reader = new InputStreamReader(entity.getContent(), charset);
            Map<String, String> res = gson.fromJson(reader, new TypeToken<Map<String, String>>()
                    {
                    }.getType());
//            return gson.fromJson(reader, MyJsonObject.class);
            reader.close();
            System.out.println();
            
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
    }
    
}
