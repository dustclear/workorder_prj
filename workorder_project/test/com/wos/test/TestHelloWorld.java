package com.wos.test;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.wos.client.stub.HelloWorld;
import com.wos.client.stub.HelloWorldImplService;
import com.wos.pojo.AddressType;


public class TestHelloWorld
{
    
    @Test
    public void test()
    {
        HelloWorld service = new HelloWorldImplService().getHelloWorldImplPort();
        System.out.println(service.sayHi("226"));
        Gson gson = new Gson();
        System.out.println("format: " +gson.fromJson(service.sayHi(""), AddressType.class).getCname());
        Assert.assertNotNull(service);
    }
    
}
