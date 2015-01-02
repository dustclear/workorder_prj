package com.wos.mgt;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface LoginMgt
{
    
    public String login(@WebParam(name="loginInfoText") String loginInfoText);
    
    
}