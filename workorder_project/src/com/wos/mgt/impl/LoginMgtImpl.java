package com.wos.mgt.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wos.common.MD5Util;
import com.wos.common.WosConstant;
import com.wos.common.WosHelper;
import com.wos.dao.mapper.RmsUserMapper;
import com.wos.mgt.LoginMgt;
import com.wos.pojo.RmsUser;

@WebService(endpointInterface = "com.wos.mgt.LoginMgt")
public class LoginMgtImpl implements LoginMgt
{
    @Resource
    WebServiceContext wsContext;
    
    private RmsUserMapper userDao;
    
    private WosHelper _helper = WosHelper.getInstance();
    
    private static final Gson _gson = new GsonBuilder().serializeNulls().setDateFormat(WosConstant.DATE_TIME_FORMAT)
            .create();
    
    @Override
    public String login(String loginInfoText)
    {
        RmsUser userInfo = _gson.fromJson(loginInfoText, RmsUser.class);
        
        RmsUser result = loginByUserInfo(userInfo);
        _helper.getSession(wsContext).setAttribute(WosConstant.CURRENT_USER, result);
        
        return _helper.toJsonText(result, RmsUser.class);
    }
    
    private RmsUser loginByUserInfo(RmsUser userInfo)
    {
        String notEncodedPassword = userInfo.getCpwd();
        if (notEncodedPassword != null)
        {
            try {
				userInfo.setCpwd(MD5Util.getMd5Hash1(notEncodedPassword));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            List<RmsUser> resList = userDao.userLogin(userInfo);
            if (resList != null && resList.size() > 0)
            {
                return resList.get(0);
            }
            else
            {//try again.
                resList = userDao.findUserByName(userInfo.getCname());
                if (resList != null && resList.size() > 0)
                {
                    userInfo = resList.get(0);
                    notEncodedPassword = notEncodedPassword + "{"
                            + userInfo.getCguid() + "}";
                    try {
						if(StringUtils.equals(MD5Util.getMd5Hash2(notEncodedPassword), userInfo.getCpwd()))
						{
						    return userInfo;
						}
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                }
            }
            
        }
        return null;
    }

    public RmsUserMapper getUserDao()
    {
        return userDao;
    }

    public void setUserDao(RmsUserMapper userDao)
    {
        this.userDao = userDao;
    }
    
    
    
}
