package com.wos.mgt.restful.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.wos.dao.mapper.InstallTemplateMapper;
import com.wos.pojo.InstallTemplate;

@Consumes("application/json")
@Produces("application/json")
public class InstallDocRestfulMgtImpl
{
    private InstallTemplateMapper installTemplateMapper;
    
    @GET
    @Path("/getInstallTemplates")
    public List<InstallTemplate> getAllInstallTemplates()
    {
        List<InstallTemplate> templates = installTemplateMapper.loadAllInstallTemplates();
        
        return templates;
    }

    public InstallTemplateMapper getInstallTemplateMapper()
    {
        return installTemplateMapper;
    }

    public void setInstallTemplateMapper(InstallTemplateMapper installTemplateMapper)
    {
        this.installTemplateMapper = installTemplateMapper;
    }
    
    
    
}
