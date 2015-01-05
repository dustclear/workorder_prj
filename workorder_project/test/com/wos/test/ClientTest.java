package com.wos.test;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.wos.client.stub.InstallDocMgt;
import com.wos.client.stub.InstallDocMgtImplService;
import com.wos.client.stub.LoginMgt;
import com.wos.client.stub.LoginMgtImplService;
import com.wos.common.WosHelper;

public class ClientTest
{
    private static WosHelper _helper = WosHelper.getInstance();
    
    public static void main(String[] args)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ccode", "1312070104");
        map.put("cguid", "145293406198134209");
        String eventCode = _helper.toJsonText(map, null);
        
        InstallDocMgt service = new InstallDocMgtImplService().getInstallDocMgtImplPort();
        ((BindingProvider)service).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true); 
        
        LoginMgt service2 = new LoginMgtImplService().getLoginMgtImplPort();
        ((BindingProvider)service2).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("cname", "admin");
        map2.put("cpwd", "123");
        String loginInfoText = WosHelper.getInstance().toJsonText(map2, null);
        service2.login(loginInfoText);
        System.out.println(service.loadInstallDocumentByEventCode(eventCode));
        try
        {
            Thread.currentThread().sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }  
        System.out.println(service.loadInstallDocumentByEventCode(eventCode));
    }
    
}
