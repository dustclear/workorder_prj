package com.mycrawler.test;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName)
    {
        super(testName);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(AppTest.class);
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        /* Map<String, Object> haMap = new HashMap<String, Object>();
         haMap.put("key1", "vaule1");
         System.out.println();*/
        /*  String path = System.getProperty("user.dir");
          System.out.println(path);*/
        String url = "http://219.146.68.24/ws.cdn.baidupcs.com/file/dadc13c9944da681f5d740333de6410d?bkt=p2-qd-106&xcode=d97142a56f2e64b38a8282e829434935f3a913716a9c9d63f77424e07ee197d9&fid=2687768786-250528-379138095804928&time=1427444845&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-ubysgkQqii540kjLANvZ2J3plOk%3D&to=hc&fm=Nan,B,T,t&sta_dx=499&sta_cs=259&sta_ft=mkv&sta_ct=5&newver=1&newfm=1&flow_ver=3&sl=80347212&expires=8h&rt=pr&r=251493276&mlogid=1020033313&vuk=2687768786&vbdid=3908911242&fin=%5B%E5%A4%A7%E5%AE%8B%E6%8F%90%E5%88%91%E5%AE%98%5D.Dead.Men.Do.Tell.Tales.S01E34.HDTV.720p.x264.AC3-CMCT.mkv&fn=%5B%E5%A4%A7%E5%AE%8B%E6%8F%90%E5%88%91%E5%AE%98%5D.Dead.Men.Do.Tell.Tales.S01E34.HDTV.720p.x264.AC3-CMCT.mkv&wshc_tag=0&wsts_tag=5515146e&wsid_tag=3ad3f5ed&wsiphost=ipdbm";
        Header lengthHeader;
        try
        {
            lengthHeader = Request.Head(url).execute().returnResponse().getLastHeader("Content-Length");
            if (lengthHeader!=null)
            {
                System.out.println("-------------------------------"+lengthHeader);
            }
            else {
                System.out.println("url: "+url);
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
