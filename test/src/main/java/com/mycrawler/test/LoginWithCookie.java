package com.mycrawler.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class LoginWithCookie
{
    private static final Logger LOGGER = Logger.getLogger(LoginWithCookie.class);
    
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
        /*try
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
        }*/
    	loadHtmlBaidu();
        
    }
    
    public static void loadHtml() throws ClientProtocolException, IOException
    {
        LOGGER.debug("start");
                    String htmlStr = loadHtmlZhiHu("http://www.zhihu.com/login");
        
//        String htmlStr = loadHtmlBaidu("http://www.zhihu.com/login");
        
        File mainFile = new File("e:/myDown", "main.html");
        if (!mainFile.exists())
        {
            if (!mainFile.getParentFile().exists())
            {
                mainFile.getParentFile().mkdirs();
            }
            
            mainFile.createNewFile();
            FileUtils.writeStringToFile(mainFile, htmlStr);
        }
        //            parseHtml(htmlStr);
    }
    
    public static String loadHtmlZhiHu(String url)
            throws ClientProtocolException, IOException
    {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("email", "dustclear@163.com"));
        formParams.add(new BasicNameValuePair("password", "1qaz2wsx"));
        
        HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        
        Executor exec = Executor.newInstance(httpClient);
        exec.execute(Request.Post(url)
                .bodyForm(formParams)
                .addHeader("User-Agent",
                        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)")
                .connectTimeout(2000)
                .socketTimeout(20000))
                .handleResponse(new ResponseHandler<String>()
                {
                    public String handleResponse(HttpResponse response)
                            throws ClientProtocolException, IOException
                    {
                        long startTime = System.currentTimeMillis();
                        long timeCost = 0;
                        InputStream in = response.getEntity().getContent();
                        InputStreamReader inputStreamReader = new InputStreamReader(
                                in);
                        BufferedReader bReader = new BufferedReader(
                                inputStreamReader);
                        StringBuffer sBuffer = new StringBuffer(2000);
                        String line;
                        while ((line = bReader.readLine()) != null)
                        {
                            sBuffer.append(line);
                        }
                        
                        return sBuffer.toString();
                        
                    }
                });
        
        return exec.execute(Request.Get("http://www.zhihu.com")
                .addHeader("User-Agent",
                        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)")
                .connectTimeout(2000)
                .socketTimeout(2000))
                .handleResponse(new ResponseHandler<String>()
                {
                    public String handleResponse(HttpResponse response)
                            throws ClientProtocolException, IOException
                    {
                        long startTime = System.currentTimeMillis();
                        long timeCost = 0;
                        InputStream in = response.getEntity().getContent();
                        InputStreamReader inputStreamReader = new InputStreamReader(
                                in);
                        BufferedReader bReader = new BufferedReader(
                                inputStreamReader);
                        StringBuffer sBuffer = new StringBuffer(2000);
                        String line;
                        while ((line = bReader.readLine()) != null)
                        {
                            sBuffer.append(line);
                        }
                        
                        return sBuffer.toString();
                        
                    }
                });
        
        //                    .returnContent().asString();
        /*String responseContent = Request.Post(url)
                .bodyForm(formParams)
                .addHeader("User-Agent",
                    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)")
                .connectTimeout(2000)
                .socketTimeout(2000)
                .execute()
        //                    .returnContent().asString()
                .handleResponse(new ResponseHandler<String>()
                        {
                            public String handleResponse(HttpResponse response)
                                    throws ClientProtocolException, IOException
                            {
                                long startTime = System.currentTimeMillis();
                                long timeCost=0;
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
                                
                                long endTime = System.currentTimeMillis();
                                timeCost = endTime - startTime;
                                LOGGER.debug(" total time cost:"
                                        + timeCost / 1000 + "." + timeCost % 1000
                                        + " seconds");
                                return null;
                            }
                        });*/
        
        //            return null;
    }
    
    public static String loadHtmlBaidu()
            
    {
//    	System.setProperty("apache.commons.httpclient.cookiespec", CookieSpecs.DEFAULT);
    	WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
    	webClient.getOptions().setCssEnabled(false);
        try {
			HtmlPage page = webClient.getPage("http://www.115.com");
			
			final HtmlForm form = page.getForms().get(0);

		    final HtmlAnchor button = page.getAnchorByText("登录");
		    final HtmlTextInput name = form.getInputByName("account");
		    final HtmlPasswordInput password = form.getInputByName("passwd");

		    // Change the value of the text field
		    name.setValueAttribute("13914039707");
		    password.setValueAttribute("`12345");

		    // Now submit the form by clicking the button and get back the second page.
		    final Page page2 = button.openLinkInNewWindow();
		    System.out.println("start-------------------------------");
		    System.out.println(page2);
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
    
}
