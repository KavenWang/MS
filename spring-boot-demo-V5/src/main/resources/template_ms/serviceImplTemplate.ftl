package com.rule.${tableBean.packageName}.service.impl;

import org.apache.commons.lang.StringUtils;
import java.util.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rule.${tableBean.packageName}.dao.${tableBean.className}Dao;
import com.rule.${tableBean.packageName}.entity.${tableBean.className};

import com.rule.${tableBean.packageName}.service.${tableBean.className}Service;
/**
 * 
 * @author  
 * ${tableBean.description}
 */
@Service("${tableBean.packageName}Service")
@Transactional
public class ${tableBean.className}ServiceImpl implements ${tableBean.className}Service{
       
    @Autowired
    private ${tableBean.className}Dao ${tableBean.packageName}Dao;
    /**
	 * 保存${tableBean.description}
	 * @param ${tableBean.packageName}
	 */
	public void save${tableBean.className}(${tableBean.className} ${tableBean.packageName}) {
		 
		this.${tableBean.packageName}Dao.save(${tableBean.packageName});
	}
	 /**
	 * 修改${tableBean.description}
	 * @param ${tableBean.packageName}
	 */
	public void update${tableBean.className}(${tableBean.className} ${tableBean.packageName}) {
	 
		this.${tableBean.packageName}Dao.saveOrUpdate(${tableBean.packageName});
	}
	/**
	 * 根据主鍵ID查询${tableBean.description}
	 * @param id
	 */
	public ${tableBean.className} get${tableBean.className}ById(String id) {
	 
		return (${tableBean.className})this.${tableBean.packageName}Dao.get(${tableBean.className}.class,id);
	}
	/**
	 * 根据${tableBean.packageName}对象查询${tableBean.description}
	 * @param ${tableBean.packageName}Query
	 */
    public ${tableBean.className} get${tableBean.className}(${tableBean.className} ${tableBean.packageName}Query){
    
        ${tableBean.className} ${tableBean.packageName}=null;
		if(${tableBean.packageName}Query!=null){
			${tableBean.packageName} = (${tableBean.className}) ${tableBean.packageName}Dao.findObjectByObject(${tableBean.packageName}Query);
		}
		return ${tableBean.packageName};
    
    }
	/**
	 * 刪除${tableBean.description}
	 * @param strChecked
	 */
	public void delete${tableBean.className}Items(String strChecked) {
	
	   String[] arrChecked = strChecked.split(",");
	   for(int i = 0; i < arrChecked.length; i++){
			
			this.${tableBean.packageName}Dao.deleteEntityById(${tableBean.className}.class, arrChecked[i]);
			
	   } 
		
	}
	
    /**
	 *  
	 * 分页查询${tableBean.description}
	 * @param ${tableBean.packageName}
	 * @param start           
	 * @param limit
	 *           
	 * @return  
	 */
	public List<${tableBean.className}> find${tableBean.className}ListByPage(${tableBean.className} ${tableBean.packageName}, int start, int limit) {
	 
		       DetachedCriteria criteria = DetachedCriteria.forClass(${tableBean.className}.class); 
		   		  if(${tableBean.packageName}!=null){
		     <#list entityClassList as en>
                <#if en.queryField!='0'>
                  <#if en.pkField=='0'>
                     <#if en.queryField=='1'>
                        <#if en.filedType == 'varchar' || en.filedType == 'varchar2' || en.filedType == 'char' || en.filedType =='text' || en.filedType =='clob'>
                        if(StringUtils.isNotBlank(${tableBean.packageName}.get${en.methodName}())){
				            criteria.add(Restrictions.like("${en.propertyName}",${tableBean.packageName}.get${en.methodName}().trim(), MatchMode.ANYWHERE));
			            }
                        </#if>
                     </#if>
                      <#if en.queryField=='2'>
                       <#if  en.filedType == 'decimal' || en.filedType == 'double' || en.filedType == 'int' || en.filedType == 'number' || en.filedType == 'float'>
                        if(${tableBean.packageName}.get${en.methodName}()!=null&&${tableBean.packageName}.get${en.methodName}()>0){
				            criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
			            }
                       </#if>
                       <#if en.filedType =='date' || en.filedType =='datetime'>
                        if(${tableBean.packageName}.get${en.methodName}()!=null){
                           criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
                        } 
                        <#if en.filedType == 'varchar' || en.filedType == 'varchar2' || en.filedType == 'char' || en.filedType =='text' || en.filedType =='clob'>
                        if(StringUtils.isNotBlank(${tableBean.packageName}.get${en.methodName}())){
				           criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
			            }
                        </#if>
                      </#if> 
                     </#if>
                       <#if en.queryField=='3'>
                       <#if  en.filedType == 'decimal' || en.filedType == 'double' || en.filedType == 'int' || en.filedType == 'number' || en.filedType == 'float'>
                        if(${tableBean.packageName}.get${en.methodName}Start()!=null&&${tableBean.packageName}.get${en.methodName}Start()>0){
				            criteria.add(Restrictions.ge("${en.propertyName}",${tableBean.packageName}.get${en.methodName}Start()));
			            }
			            if(${tableBean.packageName}.get${en.methodName}End()!=null&&${tableBean.packageName}.get${en.methodName}End()>0){
				            criteria.add(Restrictions.le("${en.propertyName}",${tableBean.packageName}.get${en.methodName}End()));
			            }
                       </#if>
                        <#if en.filedType =='date' || en.filedType =='datetime'>
                        if(${tableBean.packageName}.get${en.methodName}Start()!=null&&${tableBean.packageName}.get${en.methodName}Start().length()>0){
				            criteria.add(Restrictions.ge("${en.propertyName}",com.common.util.DateUtil.parseDate(${tableBean.packageName}.get${en.methodName}Start(), "yyyy-MM-dd")));
			            }
			            if(${tableBean.packageName}.get${en.methodName}End()!=null&&${tableBean.packageName}.get${en.methodName}End().length()>0){
				            criteria.add(Restrictions.le("${en.propertyName}",com.common.util.DateUtil.parseDate(${tableBean.packageName}.get${en.methodName}End(), "yyyy-MM-dd")));
			            }
                       </#if>
                     </#if>
                  </#if>
                  <#else>
                  <#if tableBean.isMain=='0'>
				      <#if en.relatedField=='1'>
				       criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
				     </#if>
				  </#if>
                </#if>
             </#list>
		    		}
		List<${tableBean.className}> ${tableBean.packageName}List = this.${tableBean.packageName}Dao.findByPage(criteria, start, limit);
		return  ${tableBean.packageName}List;
	}
	
	/**
	 *  
	 * 查询${tableBean.description}个数   
	 * @param ${tableBean.packageName}         
	 * @return 
	 */
	public int find${tableBean.className}Count(${tableBean.className} ${tableBean.packageName}) {
           DetachedCriteria criteria = DetachedCriteria.forClass(${tableBean.className}.class);
		          if(${tableBean.packageName}!=null){
		     <#list entityClassList as en>
                <#if en.queryField!='0'>
                  <#if en.pkField=='0'>
                     <#if en.queryField=='1'>
                      <#if en.filedType == 'varchar' || en.filedType == 'varchar2' || en.filedType == 'char' || en.filedType =='text' || en.filedType =='clob'>
                        if(StringUtils.isNotBlank(${tableBean.packageName}.get${en.methodName}())){
				            criteria.add(Restrictions.like("${en.propertyName}",${tableBean.packageName}.get${en.methodName}().trim(), MatchMode.ANYWHERE));
			            }
			           </#if>  
                     </#if>
                      <#if en.queryField=='2'>
                      <#if  en.filedType == 'decimal' || en.filedType == 'double' || en.filedType == 'int' || en.filedType == 'number' || en.filedType == 'float'>
                        if(${tableBean.packageName}.get${en.methodName}()!=null&&${tableBean.packageName}.get${en.methodName}()>0){
				            criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
			            }
			          </#if>  
			          <#if en.filedType =='date' || en.filedType =='datetime'>
                        if(${tableBean.packageName}.get${en.methodName}()!=null){
                           criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
                        } 
                      </#if> 
                       <#if en.filedType == 'varchar' || en.filedType == 'varchar2' || en.filedType == 'char' || en.filedType =='text' || en.filedType =='clob'>
                        if(StringUtils.isNotBlank(${tableBean.packageName}.get${en.methodName}())){
				           criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
			            }
                        </#if>
                     </#if>
                       <#if en.queryField=='3'>
                       <#if  en.filedType == 'decimal' || en.filedType == 'double' || en.filedType == 'int' || en.filedType == 'number' || en.filedType == 'float'>
                        if(${tableBean.packageName}.get${en.methodName}Start()!=null&&${tableBean.packageName}.get${en.methodName}Start()>0){
				            criteria.add(Restrictions.ge("${en.propertyName}",${tableBean.packageName}.get${en.methodName}Start()));
			            }
			            if(${tableBean.packageName}.get${en.methodName}End()!=null&&${tableBean.packageName}.get${en.methodName}End()>0){
				            criteria.add(Restrictions.le("${en.propertyName}",${tableBean.packageName}.get${en.methodName}End()));
			            }
                       </#if>
                        <#if en.filedType =='date' || en.filedType =='datetime'>
                        if(${tableBean.packageName}.get${en.methodName}Start()!=null&&${tableBean.packageName}.get${en.methodName}Start().length()>0){
				            criteria.add(Restrictions.ge("${en.propertyName}",com.common.util.DateUtil.parseDate(${tableBean.packageName}.get${en.methodName}Start(), "yyyy-MM-dd")));
			            }
			            if(${tableBean.packageName}.get${en.methodName}End()!=null&&${tableBean.packageName}.get${en.methodName}End().length()>0){
				            criteria.add(Restrictions.le("${en.propertyName}",com.common.util.DateUtil.parseDate(${tableBean.packageName}.get${en.methodName}End(), "yyyy-MM-dd")));
			            }
                       </#if>
                     </#if>
                  </#if>
                  <#else>
                  <#if tableBean.isMain=='0'>
				      <#if en.relatedField=='1'>
				       criteria.add(Restrictions.eq("${en.propertyName}",${tableBean.packageName}.get${en.methodName}()));
				     </#if>
				  </#if>
                </#if>
             </#list>
				}
		int result = this.${tableBean.packageName}Dao.getListSize(criteria);
		return result;
	}

}