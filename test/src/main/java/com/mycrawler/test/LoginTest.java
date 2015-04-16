package com.mycrawler.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginTest
{
    private static final Logger LOGGER = Logger.getLogger(LoginTest.class);
    private static final Properties sysPro = new Properties();
    
    public static void main(String[] args)
    {
    	
    	initProperties();
        /*List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("userName", ""));
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
    	loadHtmlBD();
//    	loadHtmlBDOther();
//        loadHtml115();
//        loadHtmlOther();
        
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
        formParams.add(new BasicNameValuePair("email", sysPro.getProperty("")));
        formParams.add(new BasicNameValuePair("password", sysPro.getProperty("")));
        
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
    
    public static String loadHtml115()
            
    {
//    	System.setProperty("apache.commons.httpclient.cookiespec", CookieSpecs.DEFAULT);
        BrowserVersion browser_115 = BrowserVersion.CHROME;
        browser_115.setApplicationVersion("/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 115Browser/5.1.6");
        browser_115.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 115Browser/5.1.6");
        
    	WebClient webClient = new WebClient(BrowserVersion.CHROME);
    	webClient.getCookieManager().setCookiesEnabled(true);//enable cookies
    	webClient.getOptions().setCssEnabled(false);
    	webClient.getOptions().setJavaScriptEnabled(true);  
    	webClient.setAjaxController(new AjaxController(){
    	    @Override
    	    public boolean processSynchron(HtmlPage page, WebRequest request, boolean async)
    	    {
    	        return true;
    	    }
    	});
    	webClient.setJavaScriptTimeout(30000);
    	webClient.getOptions().setTimeout(30000);
//    	webClient.getOptions().setUseInsecureSSL(true);
//    	webClient.getOptions().setThrowExceptionOnScriptError(false);
//    	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    	
        try {
			HtmlPage page = webClient.getPage("http://www.115.com");
			
			final HtmlForm form = page.getForms().get(0);

		    final HtmlAnchor button = page.getAnchorByText("登录");
		    final HtmlTextInput name = form.getInputByName("account");
		    final HtmlPasswordInput password = form.getInputByName("passwd");

		    // Change the value of the text field
		    name.setValueAttribute(sysPro.getProperty("user_115"));
		    password.setValueAttribute(sysPro.getProperty("pass_115"));
		    HtmlCheckBoxInput remberName = (HtmlCheckBoxInput)page.getElementById("js-remember_pwd");
            
            remberName.setChecked(true);
		    // Now submit the form by clicking the button and get back the second page.
		    HtmlPage page2 = button.click();
		    webClient.waitForBackgroundJavaScript(5000);
		    
//		    webClient.getCookieManager().clearCookies();
		    
		    System.out.println("start-------------------------------");
		    System.out.println(page2.getWebResponse().getContentAsString()); //source code
		    System.out.println("go-------------------------------");
		    Page basePage = webClient.getPage("http://115.com/");
		    webClient.waitForBackgroundJavaScript(5000);
		    Page page3 = webClient.getPage("http://115.com/?ct=offline&ac=space&_="+System.currentTimeMillis());
//		    Page page3 = webClient.getPage("http://115.com/?ac=offline_tpl&is_wl_tpl=1");
//		    webClient.waitForBackgroundJavaScript(8000);
		    System.out.println(page3.getUrl()+"=====================================////////////////");
		    
		    
		     Gson gson = new Gson();
	        Map<String, String> retMap = gson.fromJson(page3.getWebResponse().getContentAsString(),
	                new TypeToken<Map<String, String>>()
	                {}.getType());
		    
		    
		    System.out.println(page3.getWebResponse().getContentAsString());
		    
		    
		    /*final HtmlAnchor wenjian = page3.getAnchorByText("文件");
		    
		    HtmlPage page4 = wenjian.click();
            webClient.waitForBackgroundJavaScript(8000);
            System.out.println(page4.getUrl()+"=====================================////////////////");
            System.out.println(page4.getWebResponse().getContentAsString());*/
		    
		    Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		    
		    for (Cookie cookie : cookies)
            {
                System.out.println(cookie.getName()+" = "+ cookie.getValue());
            }
            
            /*HtmlPage page4 = (HtmlPage)page3.getFrames().get(3).getEnclosedPage();
            webClient.waitForBackgroundJavaScript(8000);
            System.out.println(page4.getUrl()+"=====================================////////////////");
            System.out.println(page4.getWebResponse().getContentAsString());
		    */
		    WebRequest reqSet = new WebRequest(new URL("http://115.com/lixian/?ct=lixian&ac=add_task_url"));
		    reqSet.setHttpMethod(HttpMethod.POST);

		    List reqParam = new ArrayList();

		    reqParam.add(new com.gargoylesoftware.htmlunit.util.NameValuePair("url", "magnet:?xt=urn:btih:038BB0584EA2F95747E23297FDBEAC3A8BCF255E&dn=%E9%92%A2%E9%93%81%E4%BE%A03.MP4.720x404.%E5%9B%BD%E8%AF%AD%E4%B8%AD%E5%AD%97"));

		    reqParam.add(new com.gargoylesoftware.htmlunit.util.NameValuePair("uid", "1151253"));

		    reqParam.add(new com.gargoylesoftware.htmlunit.util.NameValuePair("sign", retMap.get("sign")));
		    reqParam.add(new com.gargoylesoftware.htmlunit.util.NameValuePair("time", String.valueOf((System.currentTimeMillis()/1000))));

		    reqSet.setRequestParameters(reqParam);

		    TextPage mypage = (TextPage)webClient.getPage(reqSet);
		    webClient.waitForBackgroundJavaScript(8000);
		    System.out.println(mypage.getUrl()+"=====================================////////////////");
            System.out.println(mypage.getWebResponse().getContentAsString());
		    
		    
		    
            webClient.closeAllWindows();


			
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
    
    public static String loadHtmlBD()
    
    {
//    	System.setProperty("apache.commons.httpclient.cookiespec", CookieSpecs.DEFAULT);
    	WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
    	/*webClient.setAjaxController(new AjaxController(){
            @Override
            public boolean processSynchron(HtmlPage page, WebRequest request, boolean async)
            {
                return true;
            }
        });*/
    	webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    	webClient.getCookieManager().setCookiesEnabled(true);//enable cookies
    	webClient.getOptions().setJavaScriptEnabled(true);  
    	webClient.getOptions().setCssEnabled(false);
    	webClient.setJavaScriptTimeout(30000);
        webClient.getOptions().setTimeout(30000);
        try {
//			HtmlPage page = webClient.getPage("http://pan.baidu.com");
			HtmlPage page = webClient.getPage("http://pan.baidu.com/");
			webClient.waitForBackgroundJavaScript(5000);
			final HtmlForm form = page.getForms().get(0);

		    final HtmlSubmitInput button = form.getInputByValue("登录");
		    final HtmlTextInput name = form.getInputByName("userName");
		    final HtmlPasswordInput password = form.getInputByName("password");
		    HtmlCheckBoxInput remberName = form.getInputByName("memberPass");
		    remberName.setChecked(true);

		    // Change the value of the text field
//		    page.setFocusedElement(name);
		    name.setValueAttribute(sysPro.getProperty("user_bd"));
		    page.setFocusedElement(password);
		    webClient.waitForBackgroundJavaScript(5000);
		    password.setValueAttribute(sysPro.getProperty("pass_bd"));

		    // Now submit the form by clicking the button and get back the second page.
		    HtmlPage page2 = button.click();
		    webClient.waitForBackgroundJavaScript(8000);
		    page.refresh();
		    page2 = button.click();
		    webClient.waitForBackgroundJavaScript(8000);
		    
		    Set<Cookie> cookies = webClient.getCookieManager().getCookies();
            
            for (Cookie cookie : cookies)
            {
                System.out.println(cookie.getName()+" = "+ cookie.getValue());
            }
            
           /* List<com.gargoylesoftware.htmlunit.util.NameValuePair> headers = 
                    page2.getWebResponse().getResponseHeaders();
                for (com.gargoylesoftware.htmlunit.util.NameValuePair header : headers) {
                    System.out.println(header.getName() + " = " + header.getValue());
                }*/
		    System.out.println("start-------------------------------");
		    System.out.println(page2.asText());
		    
		    System.out.println(page2.getWebResponse().getContentAsString()); //source code
		    webClient.closeAllWindows();
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
    
    public static void loadHtmlOther()
    {
        
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
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
    
    public static String loadHtmlBDOther()
            
    {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("userName", ""));
        formParams.add(new BasicNameValuePair("password", ""));
        
        HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        
        Executor exec = Executor.newInstance(httpClient);
        try
        {
            exec.execute(Request.Post("http://pan.baidu.com")
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
                            System.out.println(sBuffer);
                            return sBuffer.toString();
                            
                        }
                    });
            
            exec.execute(Request.Get("http://pan.baidu.com")
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
                            
                            System.out.println(sBuffer);
                            return sBuffer.toString();
                            
                        }
                    });
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
        
        return null;
        
    }
    
    private static void initProperties()
    {
    	InputStream fis = null;
		try {
			fis = LoginTest.class.getResourceAsStream("/cert.properties");
			sysPro.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(fis!=null)
			{
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
}
