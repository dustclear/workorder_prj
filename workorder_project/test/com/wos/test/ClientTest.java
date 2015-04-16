package com.wos.test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.wos.pojo.MaintainDocument;

public class ClientTest {
	private static WosHelper _helper = WosHelper.getInstance();

	private static final Gson _gson = new GsonBuilder().setDateFormat(
			WosConstant.DATE_TIME_FORMAT).create();

	public static void main(String[] args) {
	    setProxy();
		InstallDocMgt service = new InstallDocMgtImplService()
				.getInstallDocMgtImplPort();
		((BindingProvider) service).getRequestContext().put(
				BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

//		LoginMgt service2 = new LoginMgtImplService().getLoginMgtImplPort();
//		((BindingProvider) service2).getRequestContext().put(
//				BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
//		login(service2);
		loadInstallDocument(service);

//		testGetAllTaxOrganizations(service);
//		getInstallDetailByTemplate(service);
//      getAddress(service);
//		addEnterpriseAddress(service);
//		addEnterpriseContact(service);
//		saveCellPhone(service);
//		SaveInstallDocument(service);
//		createAnEmptyInstallDocument(service);
//		createAnEmptyMaintainDocument(service);
//		loadMaintainDocument(service);
//		SaveMaintainDocument(service);
//		getEnterpriseInfoByName(service);
//		isContactInfoExist(service);
	/*	String string = "{\"isauditingqualified\":null,\"cstatus\":1,\"ccode\":\"mobile821347265985\",\"ccreater\":\"1\",\"cinstalltypeid\":null,\"enterpriseBaseInfo\":null,\"remarks\":\"无\",\"ctaxcode\":\"320500692553043\",\"carea\":\"其它\",\"ccontactid\":\"192975\",\"customersignature\":\"王伯强\",\"centerpriseid\":\"103940\",\"ceventid\":null,\"cguid\":\"072157298052947556\",\"clastupdatedate\":null,\"cinstalldate\":\"2015-01-28 17:22:39\",\"centerpriseadress\":\"飞扬\",\"isauditingeffective\":null,\"employee\":null,\"centerprisedepartment\":null,\"ccreatedate\":\"2015-01-28 17:23:07\",\"cemployeeid\":\"145246972521717801\",\"caudittime\":null,\"cunsuccessresult\":\"无\",\"ccontacttel\":\"18951125918\",\"noqualifiedreason\":null,\"cinstalltype\":\"626498449016090763\",\"ctimestamp\":null,\"ccontactname\":\"王伯强\",\"cdepartment\":\"服务部\",\"ccontactphone\":\"18951125918\",\"clastupdater\":null,\"cbelongtax\":\"165120663464490419\",\"centerprisename\":\"苏州丽航国际货运代理有限公司\",\"cauditorguid\":null,\"cinstallresult\":1}";
		InstallDocument document = _gson.fromJson(string, InstallDocument.class);*/
		
//		System.out.println(document);
//		loadOrderCodeByEventCode(service);
	}
	
	private static String loadOrderCodeByEventCode(InstallDocMgt service)
    {
	    Map<String, String> map = new HashMap<String, String>();
        map.put("ccode", "1504030647");
        String ccodeStr = _helper.toJsonText(map, null);
        String reString = service.loadOrderCodeByEventCode(ccodeStr);
        System.out.println(reString);
        return reString;
    }
	
	private static String isContactInfoExist(InstallDocMgt service)
	{
	    Map<String, String> map = new HashMap<String, String>();
        map.put("cname", "顾美芬");
        map.put("cphone1", "145293406198134209");
        map.put("ctel1", "13375157506");
        String contactStr = _helper.toJsonText(map, null);
        String reString = service.isContactInfoExist(contactStr);
        System.out.println(reString);
        return reString;
	}
	
	private static String createAnEmptyInstallDocument(InstallDocMgt service)
	{
	    String reString = service.createAnEmptyInstallDocument();
        System.out.println(reString);
        return reString;
	}
	
	private static String createAnEmptyMaintainDocument(InstallDocMgt service)
    {
        String reString = service.createAnEmptyMaintainDocument();
        System.out.println(reString);
        return reString;
    }
	
	private static String loadMaintainDocument(InstallDocMgt service)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ccode", "1312070104");
        map.put("cguid", "145293406198134209");
        String eventCode = _helper.toJsonText(map, null);
        String reString = service.loadMaintainDocumentByEventCode(eventCode);
        System.out.println(reString);
        return reString;
    }
	
	private static String loadInstallDocument(InstallDocMgt service)
	{
	    Map<String, String> map = new HashMap<String, String>();
        map.put("ccode", "1504030647");
        map.put("cguid", "460144675295283861");
        String eventCode = _helper.toJsonText(map, null);
        String reString = service.loadInstallDocumentByEventCode(eventCode);
        System.out.println(reString);
        return reString;
	}
	
	private static void login(LoginMgt service)
	{
	    Map<String, String> map2 = new HashMap<String, String>();
        map2.put("cname", "316");
        map2.put("cpwd", "123qwe");
        String loginInfoText = WosHelper.getInstance().toJsonText(map2, null);
        System.out.println(service.login(loginInfoText));
	}

	private static String getInstallDetailByTemplate(InstallDocMgt service) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("installTemplateId", "905840966170754569");
		map.put("installDocumentId", "271441751171043777");
		String installTemplateText = _helper.toJsonText(map, null);

		String resStr = service.getInstallDetailByTemplate(installTemplateText);

		Type type = new TypeToken<ArrayList<InstallDocuDetail>>() {
		}.getType();
		List<InstallDocuDetail> list = _gson.fromJson(resStr, type);

		System.out.println(resStr); // [{"cguid":"187011891516931394","cmainid":"187011891516931393","ccode":"1100791392","cname":"远程抄报软件","crelationmatid":"454832402342498298","ccontactid":"905840966170201044","cinstalldate":"Dec 10, 2013 12:00:00 AM","cismain":0,"cisstatus":1,"cservicedata":"Dec 10, 2013 12:00:00 AM","cservicestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteeenddate":"Dec 10, 2013 12:00:00 AM"}]
		return resStr;
	}
	
	
	private static void getAddress(InstallDocMgt service)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("centerpriseid", "20110415140253670");
		String addressText = _helper.toJsonText(map, null);
		
		String resStr = service.getEnterpriseAddresses(addressText);
		System.out.println(resStr);
	}
	
	private static void getEnterpriseInfoByName(InstallDocMgt service)
	{
	    Map<String, String> map = new HashMap<String, String>();
        map.put("cname", "苏州");
        String nameText = _helper.toJsonText(map, null);
        
        String resStr = service.getEnterpriseInfoByName(nameText);
        System.out.println(resStr);
	}
	
	private static void getEnterpriseInfoByTaxCode(InstallDocMgt service)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctaxcode", "000000000000000001");
        String nameText = _helper.toJsonText(map, null);
        
        String resStr = service.getEnterpriseInfoByName(nameText);
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
	
		private static void  saveCellPhone(InstallDocMgt service)
    { 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cguid", "126493");
        map.put("cphone2", 666666);
        map.put("cphone1", "111111111");
        map.put("cphone3", "333333333");
        map.put("cphone4", "444444444");
        map.put("cphone5", "7777");
        String addressText = _helper.toJsonText(map, null);
        String resStr = service.saveCellphone(addressText);
        System.out.println(resStr);
    }
	
	private static void SaveInstallDocument(InstallDocMgt service)
	{
	    String insatllDocument = loadInstallDocument(service);
	    InstallDocument document = _gson.fromJson(insatllDocument, InstallDocument.class);
	    document.setIsbill(Short.valueOf("0"));
	    
	    String installDetails = getInstallDetailByTemplate(service);
	    Type type = new TypeToken<ArrayList<InstallDocuDetail>>() {
        }.getType();
        List<InstallDocuDetail> docuDetaillist = _gson.fromJson(
                installDetails, type);
        for (InstallDocuDetail installDocuDetail : docuDetaillist)
        {
            installDocuDetail.setCmainid(document.getCguid());
        }
        
	    
	    //save install document
	    service.saveInstallDocument(_gson.toJson(document));
	    service.saveInstallDocDetail(_gson.toJson(docuDetaillist));
	}
	
	private static void SaveMaintainDocument(InstallDocMgt service)
    {
        String maintainDocText = loadMaintainDocument(service);
        MaintainDocument document = _gson.fromJson(maintainDocText, MaintainDocument.class);
        //以下三个参数不能为空，these properties should not be null
        document.setCstatus(1);
        document.setCcreater("my test");
        document.setCcreatedate(new Date());
        
        //save maintain document
        service.saveMaintainDocument(_gson.toJson(document));
    }

	private static void setProxy()
	{
	    Properties prop = System.getProperties();
        // 设置http访问要使用的代理服务器的地址
        prop.setProperty("http.proxyHost", "127.0.0.1");
        // 设置http访问要使用的代理服务器的端口
        prop.setProperty("http.proxyPort", "8087");
        // 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
//        prop.setProperty("http.nonProxyHosts", "localhost|192.168.0.*");
        // 设置安全访问使用的代理服务器地址与端口
        // 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
        prop.setProperty("https.proxyHost", "127.0.0.1");
        prop.setProperty("https.proxyPort", "8087");
        // 使用ftp代理服务器的主机、端口以及不需要使用ftp代理服务器的主机
//        prop.setProperty("ftp.proxyHost", "192.168.0.254");
//        prop.setProperty("ftp.proxyPort", "2121");
//        prop.setProperty("ftp.nonProxyHosts", "localhost|192.168.0.*");
        // socks代理服务器的地址与端口
        prop.setProperty("socksProxyHost", "127.0.0.1");
        prop.setProperty("socksProxyPort", "8087");
	}
	
    public static void testGetAllTaxOrganizations(InstallDocMgt service)
    {
//        String resStr=service.getAllTaxOrganizations();
//        System.out.println(resStr);     //[{"cguid":"165120663464490419","ccode":"szsguqgffj","cname":"苏州市姑苏区国税分局","corganizationcode":"1","cparentid":"2001","cinnercode":"jssgjswj!jssszsgjswj!szsguqgffj","ileaf":1,"ccreater":"1","ccreatedate":"Dec 9, 2013 3:11:52 PM","ctimestamp":"165120663464490418"},{"cguid":"102102","ccode":"szsdefj","cname":"苏州市第二国税分局","corganizationcode":"111","cparentid":"2001","cinnercode":"jssgjswj!jssszsgjswj!szsdefj","ileaf":1,"ccreater":"1","ccreatedate":"Dec 25, 2013 2:54:55 PM","ctimestamp":"102780964199954128","cfullname":"苏州市第二分局"},{"cguid":"920623803615892447","ccode":"00001","cname":"其他","corganizationcode":"0001","cparentid":"000000","cinnercode":"00001","ileaf":1,"ccreater":"1","ccreatedate":"Jan 17, 2014 2:16:27 PM","ctimestamp":"920623803615892446","cfullname":"其他"},{"cguid":"8329","ccode":"tcsgslfj","cname":"太仓市国税六分局","cparentid":"2032","cinnercode":"jssgjswj!jssszsgjswj!tcsgjswj!tcsgslfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"8330","ccode":"cssgsbfj","cname":"常熟市国税八分局","cparentid":"2013","cinnercode":"jssgjswj!jssszsgjswj!cssgjswj!cssgsbfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7785","ccode":"kssgsyfj","cname":"昆山市国税一分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgsyfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7786","ccode":"kssgsefj","cname":"昆山市国税二分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgsefj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7787","ccode":"kssgssfj","cname":"昆山市国税三分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgssfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7788","ccode":"kssgssfj","cname":"昆山市国税四分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgssfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7789","ccode":"kssgswfj","cname":"昆山市国税五分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgswfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"}]
        
       System.out.println(service.getAllInstallTemplates());
        
    }
}
