package com.wos.mgt.impl;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.wos.common.MD5Util;
import com.wos.common.WosHelper;
import com.wos.dao.mapper.RmsUserMapper;
import com.wos.mgt.LoginMgt;
import com.wos.pojo.RmsUser;

@WebService(endpointInterface = "com.wos.mgt.LoginMgt")
public class LoginMgtImpl implements LoginMgt
{
    private RmsUserMapper userDao;
    
    private WosHelper _helper = WosHelper.getInstance();
    
    private static final Gson _gson = new Gson();
    
    @Override
    public String login(String loginInfoText)
    {
        RmsUser userInfo = _gson.fromJson(loginInfoText, RmsUser.class);
        
        return _helper.toJsonText(loginByUserInfo(userInfo), RmsUser.class);
    }
    
    private RmsUser loginByUserInfo(RmsUser userInfo)
    {
        String notEncodedPassword = userInfo.getCpwd();
        if (notEncodedPassword != null)
        {
            userInfo.setCpwd(MD5Util.GetMD5Code(notEncodedPassword));
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
                    if(StringUtils.equals(MD5Util.GetMD5Code(notEncodedPassword), userInfo.getCpwd()))
                    {
                        return userInfo;
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
