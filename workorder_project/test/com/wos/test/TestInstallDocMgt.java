package com.wos.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wos.common.WosHelper;
import com.wos.mgt.InstallDocMgt;

public class TestInstallDocMgt
{
    private static InstallDocMgt service;
    
    @BeforeClass
    public static void init()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-*.xml");
        service = (InstallDocMgt)context.getBean("installDoc");
    }
    
    @Test
    public void testLoadInstallDocumentByEventCode() { 
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("ceventid", "222");
        String eventCode = WosHelper.getInstance().toJsonText(map, null);
        
        String resStr=service.loadInstallDocumentByEventCode(eventCode);
        System.out.println(eventCode); //{"ceventid":"222"}
        System.out.println(resStr);    //{"cguid":"185054680023997827","ceventid":"222","ccode":"F00045381","centerpriseid":"20131108164948837","ctaxcode":"320503583758293","centerprisename":"苏州百家膳商贸有限公司","cbelongtax":"2004","centerpriseadress":"江苏省苏州市平江区北环东路170号","centerprisedepartment":"无","ccontactid":"177154","ccontactname":"吴伟","ccontacttel":"无","ccontactphone":"18912776261","cinstalltypeid":"465485634789821533","cinstalldate":"Dec 9, 2013 12:00:00 AM","cinstallresult":"1","cunsuccessresult":"无","cdepartment":"技服中心","carea":"姑苏区","cemployeeid":"379188785377422476","cstatus":1,"ccreater":"460144675295283761","ccreatedate":"Dec 9, 2013 8:45:20 PM","cauditorguid":"459799468148432191","caudittime":"Dec 11, 2013 1:52:55 PM","clastupdater":"459799468148432215","clastupdatedate":"Dec 10, 2013 3:46:50 PM","ctimestamp":"905840966170674524","cinstalltype":"271441751171043777","remarks":"无","isauditingqualified":1,"isauditingeffective":1,"customersignature":"吴伟"}
    }
}
