package com.hoperun.scfs.preloan.dto;
import java.util.Date;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import java.math.BigDecimal;
/**
 *
 * @author
 * ${tableBean.description}
 */
@SuppressWarnings("serial")
public class ${tableBean.className}DTO implements Serializable {

	private static final long serialVersionUID = 1L;
      <#list entityClassList as en>
        /** ${en.columnComment} **/
        private ${en.propertyType} ${en.propertyName};
        <#if en.dataCode!=''>
        /**${en.columnComment}**/
        private ${en.propertyType} ${en.propertyName}text;
        </#if>
        <#if en.filedType =='date' || en.filedType =='datetime'>
        private String ${en.propertyName}Str;
        <#if en.queryField == '3'>
        private String ${en.propertyName}Start;
        private String ${en.propertyName}End;
         </#if>
        </#if>
        <#if en.filedType == 'decimal' || en.filedType == 'double' || en.filedType == 'float' || en.filedType == 'int' || en.filedType == 'number'>
         <#if en.queryField == '3'>
        private ${en.propertyType} ${en.propertyName}Start;
        private ${en.propertyType} ${en.propertyName}End;
         </#if>
        </#if>
      </#list>

<#list entityClassList as en>
  <#if en.pkField=='1'>
	  public ${en.propertyType} get${en.methodName}() {
		return this.${en.propertyName};
	  }
	  public void set${en.methodName}(${en.propertyType} ${en.propertyName}){
		this.${en.propertyName} = ${en.propertyName};
	  }
	 <#else>
	  public ${en.propertyType} get${en.methodName}() {
		return this.${en.propertyName};
	  }
	  public void set${en.methodName}(${en.propertyType} ${en.propertyName}){
		this.${en.propertyName} = ${en.propertyName};
	  }
	  <#if en.dataCode!=''>
	  public ${en.propertyType} get${en.methodName}text() {
	    this.${en.propertyName}text = com.common.util.DictCache.getCodeDesc("${en.dataCode?upper_case}",this.${en.propertyName});
		return this.${en.propertyName}text;
	  }
	  public void set${en.methodName}text(${en.propertyType} ${en.propertyName}text){
		this.${en.propertyName}text = ${en.propertyName}text;
	  }
	  </#if>
	  <#if en.filedType =='date' || en.filedType =='datetime'>
	  public String get${en.methodName}Str() {
	  if(this.${en.propertyName}!=null){
	    <#if en.filedType =='date'>
	     this.${en.propertyName}Str = com.common.util.DateUtil.dateToStr(this.${en.propertyName}, "yyyy-MM-dd");
	    </#if>
	    <#if en.filedType =='datetime'>
	     this.${en.propertyName}Str = com.common.util.DateUtil.dateToStr(this.${en.propertyName}, "yyyy-MM-dd HH:mm:ss");
	    </#if>
	   }
		return this.${en.propertyName}Str;
	  }
	  public void set${en.methodName}Str(String ${en.propertyName}Str){
		this.${en.propertyName}Str = ${en.propertyName}Str;
	  }
	  <#if en.queryField == '3'>
	  public String get${en.methodName}Start() {
		return this.${en.propertyName}Start;
	  }
	  public void set${en.methodName}Start(String ${en.propertyName}Start){
		this.${en.propertyName}Start = ${en.propertyName}Start;
	  }
	  public String get${en.methodName}End() {
		return this.${en.propertyName}End;
	  }
	  public void set${en.methodName}End(String ${en.propertyName}End){
		this.${en.propertyName}End = ${en.propertyName}End;
	  }
	  </#if>
	  </#if>
	 <#if en.filedType == 'decimal' || en.filedType == 'double' || en.filedType == 'float' || en.filedType == 'int' || en.filedType == 'number'>
	  <#if en.queryField == '3'>
	  public ${en.propertyType} get${en.methodName}Start() {
		return this.${en.propertyName}Start;
	  }
	  public void set${en.methodName}Start(${en.propertyType} ${en.propertyName}Start){
		this.${en.propertyName}Start = ${en.propertyName}Start;
	  }
	  public ${en.propertyType} get${en.methodName}End() {
		return this.${en.propertyName}End;
	  }
	  public void set${en.methodName}End(${en.propertyType} ${en.propertyName}End){
		this.${en.propertyName}End = ${en.propertyName}End;
	  }
	  </#if>
	  </#if>
  </#if>
</#list>

}