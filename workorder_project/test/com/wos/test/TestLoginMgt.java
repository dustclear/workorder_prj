package com.wos.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.wos.common.WosHelper;
import com.wos.mgt.InstallDocMgt;
import com.wos.mgt.LoginMgt;

public class TestLoginMgt
{
    private static LoginMgt service;
    
    @BeforeClass
    public static void init()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-*.xml");
        service = (LoginMgt)context.getBean("login");
    }
    
    @Test
    public void testLogin() { 
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("cname", "admin");
        map.put("cpwd", "123");
        String loginInfoText = WosHelper.getInstance().toJsonText(map, null);
        
        String resStr=service.login(loginInfoText);
        System.out.println(loginInfoText); //{"cpwd":"123","cname":"admin"}
        System.out.println(resStr);    //{"cguid":"1","cname":"admin","cpwd":"202cb962ac59075b964b07152d234b70","crealname":"超级用户","cemp":"735323088900373639","corgnid":"1","ienable":1,"cusertype":"1","cpwdtype":"1","iconcurrent":1,"ifirstlogin":0,"cauthtype":"1","iifloginsys":1}
    }
    
}
