package com.uisftech.common.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自定义jpa接口
 * @author wangwei
 *
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>{

	/**
	 * 通过原生HQL语言返回Object[]对象
	 * @return
	 */
	public List<Object[]> findByHQL(String hql);

	public List<Object[]> findBySQL(String sql);

	public List<T> findBySQL(String sql,Object...args);
	@Transactional
	public int updateBySQL(String sql,Object...args);
	@Transactional
	public int updateByHql(String hql,Object...args);
}
