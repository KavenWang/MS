package com.rule.${tableBean.packageName}.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import com.common.controller.BaseController;
import com.rule.${tableBean.packageName}.entity.${tableBean.className};
import com.rule.${tableBean.packageName}.service.${tableBean.className}Service;
import com.system.metatype.Dto;
import com.system.metatype.impl.BaseDto;
import com.system.tag.util.JsonHelper;
/**
 * 
 * @author  
 * ${tableBean.description}
 */
@Controller
@RequestMapping("/${tableBean.packageName}")
public class ${tableBean.className}Controller extends BaseController {

     private static Log logger=LogFactory.getLog(${tableBean.className}Controller.class);
     
     @Autowired
     private ${tableBean.className}Service ${tableBean.packageName}Service;
     
    /**
     * 初始化${tableBean.description}列表
     * @param request
     * @return
     */
    @RequestMapping(params = "reqCode=init")
	public ModelAndView init(HttpServletRequest request) {
		 
		ModelAndView modelAndView = new ModelAndView("/jsprule/${tableBean.packageName}/${tableBean.packageName}.jsp");
        return modelAndView;
	}
	<#if tableBean.isMain=='1'>
	 /**
     * ${tableBean.description}详细工作区
     * @param request
     * @return
     */
     @RequestMapping(params = "reqCode=${tableBean.packageName}WorkBench")
	public ModelAndView ${tableBean.packageName}WorkBench(HttpServletRequest request)
			throws Exception {
			ModelAndView modelAndView = new ModelAndView("/jsprule/${tableBean.packageName}/${tableBean.packageName}Workbench.jsp");
		<#list entityClassList as en>
	          <#if en.relatedField =='1'>  
	          String ${en.propertyName} = request.getParameter("${en.propertyName}");
	          modelAndView.addObject("${en.propertyName}", ${en.propertyName});
	             <#break>
	          </#if>
	     </#list>
		return modelAndView;
	}
	</#if>
	
	
	<#if tableBean.isMain=='1'>
     <#list entityClassList as en>
	      <#if en.relatedField =='1'>  
	 /**
     * 根据${en.propertyName}查看${tableBean.description}
     * @param request
     * @return
     */
     @RequestMapping(params = "reqCode=view${tableBean.className}By${en.propertyName}")
	 public ModelAndView view${tableBean.className}By${en.propertyName}(String ${en.propertyName})throws Exception {
	    ModelAndView modelAndView = new ModelAndView("/jsprule/${tableBean.packageName}/view${tableBean.packageName}.jsp");
		modelAndView.addObject("${en.propertyName}", ${en.propertyName});
        return modelAndView;
	 }
	   <#break>
	      </#if>
	     </#list>
	</#if>
	
	
	<#if tableBean.isMain=='0'>
     <#list entityClassList as en>
	      <#if en.relatedField =='1'>  
	 /**
     * 根据${en.propertyName}编辑${tableBean.description}
     * @param request
     * @return
     */
     @RequestMapping(params = "reqCode=edit${tableBean.className}By${en.propertyName}")
	 public ModelAndView edit${tableBean.className}By${en.propertyName}(String ${en.propertyName})throws Exception {
	    ModelAndView modelAndView = new ModelAndView("/jsprule/${tableBean.packageName}/edit${tableBean.packageName}.jsp");
		modelAndView.addObject("${en.propertyName}", ${en.propertyName});
        return modelAndView;
	 }
	 
	   <#break>
	      </#if>
	     </#list>
	</#if>
	
	
	 
     <#list entityClassList as en>
	      <#if en.relatedField =='1'>  
	 /**
     * 根据${en.propertyName}查询${tableBean.description}
     * @param request
     * @return
     */
     @RequestMapping(params = "reqCode=load${tableBean.className}By${en.propertyName}")
	 public ModelAndView load${tableBean.className}By${en.propertyName}(String ${en.propertyName},HttpServletResponse response)throws Exception {
	 
	    ${tableBean.className} ${tableBean.packageName}Query = new ${tableBean.className}();
	    ${tableBean.packageName}Query.set${en.methodName}(${en.propertyName});
	    ${tableBean.className} ${tableBean.packageName} = ${tableBean.packageName}Service.get${tableBean.className}(${tableBean.packageName}Query);
	    List list = new ArrayList();
		list.add(${tableBean.packageName});
		write(JsonHelper.encodeList2PageJson(list, 1, "yyyy-MM-dd"), response);
		return null;
	 }
	   <#break>
	      </#if>
	     </#list>
	 
	
	/**
     * 查看${tableBean.description}页面
     * @param request
     * @return
     */
	@RequestMapping(params = "reqCode=view${tableBean.className}")
	public ModelAndView view${tableBean.className}(String id) {
		ModelAndView modelAndView = new ModelAndView("/jsprule/${tableBean.packageName}/view${tableBean.packageName}.jsp");
		modelAndView.addObject("id", id);
        return modelAndView;
	}
    /**
     * 根据条件查询${tableBean.description}
     * @param request
     * @return
     */ 
    @RequestMapping(params = "reqCode=load${tableBean.className}ByCondition")
	public ModelAndView load${tableBean.className}ByCondition(HttpServletRequest request,String id,HttpServletResponse response) throws Exception {
		${tableBean.className} ${tableBean.packageName} = ${tableBean.packageName}Service.get${tableBean.className}ById(id);
		List list = new ArrayList();
		list.add(${tableBean.packageName});
		write(JsonHelper.encodeList2PageJson(list, 1, "yyyy-MM-dd"), response);
		return null;
	}
	
      /**
       * 保存${tableBean.description}
       * @param ${tableBean.packageName}
       * @param request
       * @param response
       * @return
       */
    @RequestMapping(params = "reqCode=save${tableBean.className}")
	public ModelAndView save${tableBean.className}(${tableBean.className} ${tableBean.packageName},HttpServletRequest request,HttpServletResponse response)  {
	 
		Dto outDto = new BaseDto();
    	try {
    		 ${tableBean.packageName}Service.save${tableBean.className}(${tableBean.packageName});
    		 outDto.put("msg", "数据保存成功");
			 outDto.put("success", new Boolean(true));
		} catch (Exception e) {
		     logger.error(e);
			e.printStackTrace();
			outDto.put("msg", "数据保存失败");
			outDto.put("success", new Boolean(false));
		}
  	  String jsonString = JsonHelper.encodeObject2Json(outDto);
  		try {
			write(jsonString, response);
		} catch (Exception e) {
		    logger.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新${tableBean.description}
	 * @param ${tableBean.packageName}
	 * @param request
     * @param response
	 * @return
	 */
	@RequestMapping(params = "reqCode=update${tableBean.className}")
	public ModelAndView update${tableBean.className}(${tableBean.className} ${tableBean.packageName},HttpServletRequest request,HttpServletResponse response)  {
		    try {
		       ${tableBean.packageName}Service.update${tableBean.className}(${tableBean.packageName});
		       setOkTipMsg("数据更新成功", response);
		    }catch (Exception e) {
		       try{
		          logger.error(e);
		    	  setOkTipMsg("数据更新失败", response);
		    	}catch(Exception ex){
		    		ex.printStackTrace();
		    	}
		    }
		    return null;
	}
 
	   
  	/**
  	 * 删除${tableBean.description}
  	 * @param request
     * @param response
  	 * @return
  	 */
  	@RequestMapping(params = "reqCode=delete${tableBean.className}Items")
  	public ModelAndView delete${tableBean.className}Items(HttpServletRequest request,HttpServletResponse response) {
  		String strChecked = request.getParameter("strChecked");
  		try {
  		    if(strChecked == null || "".equals(strChecked)){
  		        setOkTipMsg("请选择要删除的记录", response);
  		        return null;
  		    }
  			${tableBean.packageName}Service.delete${tableBean.className}Items(strChecked);
  		    setOkTipMsg("数据删除成功", response);
		} catch (Exception e) {
			 try{
			      logger.error(e);
		    	  setOkTipMsg("数据删除失败", response);
		    	}catch(Exception ex){
		    		ex.printStackTrace();
		    	}
		}
  		return null;
  	}
  	
  	/**
  	 * 查询${tableBean.description}
  	 * @param ${tableBean.packageName}
  	 * @param request
  	 * @param response
  	 * @return
  	 */
    @RequestMapping(params = "reqCode=query${tableBean.className}List")
	public ModelAndView query${tableBean.className}List(${tableBean.className} ${tableBean.packageName},HttpServletRequest request,HttpServletResponse response)  {
	
	   	String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		if ("".equals(start) || null == start) {
			start = "0";
		}
		if ("".equals(limit) || null == limit) {
			limit = "10";
		}
	     
		try{
		 List<${tableBean.className}> ${tableBean.packageName}List =  this.${tableBean.packageName}Service.find${tableBean.className}ListByPage(${tableBean.packageName}, Integer.parseInt(start), Integer.parseInt(limit));
		 int pageCount = this.${tableBean.packageName}Service.find${tableBean.className}Count(${tableBean.packageName});
		 String jsonString = JsonHelper.encodeList2PageJson(${tableBean.packageName}List, pageCount, "yyyy-MM-dd");
			write(jsonString, response);
		}catch(Exception e){
		    logger.error(e);
			e.printStackTrace();
		}
	    return null;
	}
  	
}
