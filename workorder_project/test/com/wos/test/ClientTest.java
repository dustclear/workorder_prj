package com.wos.test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wos.client.stub.InstallDocMgt;
import com.wos.client.stub.InstallDocMgtImplService;
import com.wos.client.stub.LoginMgt;
import com.wos.client.stub.LoginMgtImplService;
import com.wos.common.WosConstant;
import com.wos.common.WosHelper;
import com.wos.pojo.InstallDocuDetail;
import com.wos.pojo.InstallDocument;

public class ClientTest {
	private static WosHelper _helper = WosHelper.getInstance();

	private static final Gson _gson = new GsonBuilder().setDateFormat(
			WosConstant.DATE_TIME_FORMAT).create();

	public static void main(String[] args) {
		InstallDocMgt service = new InstallDocMgtImplService()
				.getInstallDocMgtImplPort();
		((BindingProvider) service).getRequestContext().put(
				BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

		LoginMgt service2 = new LoginMgtImplService().getLoginMgtImplPort();
		((BindingProvider) service2).getRequestContext().put(
				BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

		/*
		getInstallDetailByTemplate(service);
		getAddress(service);*/
//		addEnterpriseAddress(service);
		addEnterpriseContact(service);
	}
	
	private static void loadInstallDocument(InstallDocMgt service)
	{
	    Map<String, String> map = new HashMap<String, String>();
        map.put("ccode", "1312070104");
        map.put("cguid", "145293406198134209");
        String eventCode = _helper.toJsonText(map, null);
        System.out.println(service.loadInstallDocumentByEventCode(eventCode));
	}
	
	private static void login(LoginMgt service)
	{
	    Map<String, String> map2 = new HashMap<String, String>();
        map2.put("cname", "admin");
        map2.put("cpwd", "123");
        String loginInfoText = WosHelper.getInstance().toJsonText(map2, null);
        System.out.println(service.login(loginInfoText));
	}

	private static void getInstallDetailByTemplate(InstallDocMgt service) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("installTemplateId", "905840966170754569");
		map.put("installDocumentId", "271441751171043777");
		String installTemplateText = _helper.toJsonText(map, null);

		String resStr = service.getInstallDetailByTemplate(installTemplateText);

		Type type = new TypeToken<ArrayList<InstallDocuDetail>>() {
		}.getType();
		List<InstallDocuDetail> list = _gson.fromJson(resStr, type);

		System.out.println(resStr); // [{"cguid":"187011891516931394","cmainid":"187011891516931393","ccode":"1100791392","cname":"远程抄报软件","crelationmatid":"454832402342498298","ccontactid":"905840966170201044","cinstalldate":"Dec 10, 2013 12:00:00 AM","cismain":0,"cisstatus":1,"cservicedata":"Dec 10, 2013 12:00:00 AM","cservicestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteeenddate":"Dec 10, 2013 12:00:00 AM"}]
	}
	
	
	private static void getAddress(InstallDocMgt service)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("centerpriseid", "20110415140253670");
		String addressText = _helper.toJsonText(map, null);
		
		String resStr = service.getEnterpriseAddresses(addressText);
		System.out.println(resStr);
	}
	
	private static void  addEnterpriseAddress(InstallDocMgt service)
    { 
        Map<String, String> map = new HashMap<String, String>();
        map.put("centerpriseid", "192999");
        map.put("cadress", "test");
        map.put("czipcode", "21000");
        map.put("careaid", "001");
        String addressText = _helper.toJsonText(map, null);
        String resStr = service.addEnterpriseAddress(addressText);
        System.out.println(resStr);
    }
	
	private static void  addEnterpriseContact(InstallDocMgt service)
    {
	    String contacText ="{\"centerpriseid\":\"108129\",\"cisonjob\":1,\"isnew\":0,\"cname\":\"谢\",\"csex\":1,\"cage\":null,\"cphone1\":\"111222222\",\"ctel1\":\"63088756\",\"cadress\":null,\"cbirthday\":null,\"cidcard\":\"\",\"cemail\":\"\",\"cqq\":null}";
	    String resStr = service.addContactInfo(contacText);
        System.out.println(resStr);
    }
	//{"centerpriseid":"108129","cisonjob":1,"isnew":0,"cname":"谢","csex":1,"cage":null,"cphone1":"111222222","ctel1":"63088756","cadress":null,"cbirthday":null,"cidcard":"","cemail":"","cqq":null}
	
}
