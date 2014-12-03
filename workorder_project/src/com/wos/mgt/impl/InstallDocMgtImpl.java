package com.wos.mgt.impl;

import javax.jws.WebService;

import com.google.gson.Gson;
import com.wos.dao.mapper.AddressTypeMapper;
import com.wos.mgt.InstallDocMgt;
import com.wos.pojo.AddressType;

@WebService(endpointInterface = "com.wos.mgt.HelloWorld")
public class InstallDocMgtImpl implements InstallDocMgt
{
    private AddressTypeMapper addressType;
    
    /*public String sayHi(String text)
    {
        AddressType type = addressType.getAllAddressTypes("zcdz").get(0);
        Gson gson = new Gson();
        System.out.println("sayHi called" + type.getCname()+" "+type.getCcode());
        return gson.toJson(type);
    }*/

    public AddressTypeMapper getAddressType()
    {
        return addressType;
    }

    public void setAddressType(AddressTypeMapper addressType)
    {
        this.addressType = addressType;
    }
    
    
}