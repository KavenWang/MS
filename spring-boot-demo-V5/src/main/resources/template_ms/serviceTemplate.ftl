package com.hoperun.scfs.preloan.service;

import com.hoperun.scfs.preloan.dto.${tableBean.className}DTO;
import java.util.List;
/**
 *
 * @author
 * ${tableBean.description}
 */
public interface I${tableBean.className}Service {
    /**
	 * 保存${tableBean.description}
	 * @param ${tableBean.packageName}
	 */
    public void save${tableBean.className}DTO(${tableBean.className}DTO ${tableBean.packageName});


    /**
	 * 修改${tableBean.description}
	 * @param ${tableBean.packageName}
	 */
    public void update${tableBean.className}DTO(${tableBean.className}DTO ${tableBean.packageName});


    /**
	 * 刪除${tableBean.description}
	 * @param strChecked
	 */
    public void delete${tableBean.className}Items(String strChecked);

     /**
	 * 根据主鍵ID查询${tableBean.description}
	 * @param id
	 */
    public ${tableBean.className}DTO get${tableBean.className}DTOById(Long id);





}