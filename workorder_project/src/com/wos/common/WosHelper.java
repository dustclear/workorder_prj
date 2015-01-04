package com.wos.common;

import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class WosHelper
{
    private static final WosHelper _instance = new WosHelper();
    
    private static final Gson _gson = new GsonBuilder().setDateFormat(WosConstant.DATE_TIME_FORMAT).create();
    
    private WosHelper()
    {
    };
    
    public static WosHelper getInstance()
    {
        return _instance; 
    }
    
    public String getValueFromJsonText(String jsonText, String keyText)
    {
        Gson gson = new Gson();
        Map<String, String> retMap = gson.fromJson(jsonText,
                new TypeToken<Map<String, String>>()
                {}.getType());
        return retMap.get(keyText);
    }
    
    
    public String toJsonText(Object srcObj, Class srcClass)
    {
        if (srcClass != null)
        {
            return _gson.toJson(srcObj, srcClass);
        }
        else
        {
            return _gson.toJson(srcObj);
        }
    }
    
    public String gernerateInstallCode()
    {
        return WosConstant.PREFIX_INSTALL_CODE+generateRadomStr(12);
    }
    
    public String generatePrimaryKey()
    {
        return generateRadomStr(18);
    }
    
    private String generateRadomStr(int length)
    {
        return RandomStringUtils.randomNumeric(length);
    }
}
