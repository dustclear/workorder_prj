package com.wos.common;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WosHelper
{
    private static final WosHelper _instance = new WosHelper();
    
    private static final Gson _gson = new Gson();
    
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
}
