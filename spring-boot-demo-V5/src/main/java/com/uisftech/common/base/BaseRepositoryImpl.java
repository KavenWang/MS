package com.uisftech.common.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	private final EntityManager entityManager;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByHQL(String hql) {
		return entityManager.createQuery(hql).getResultList();
	}

	@Override
	public List<T> findBySQL(String sql, Object... args) {
		Query query = entityManager.createNamedQuery(sql);
		int i = 0;
		for(Object arg:args) {
            query.setParameter(++i,arg);
        }

		return query.getResultList();
	}

	@Override
	public List<Object[]> findBySQL(String sql) {
		return entityManager.createNativeQuery(sql).getResultList();
	}

	@Override
	public int updateBySQL(String sql, Object... args) {
		Query query = entityManager.createNativeQuery(sql);
		int i = 0;
		for(Object arg:args) {
            query.setParameter(++i,arg);
        }
        return query.executeUpdate();

	}

	@Override
	public int updateByHql(String hql, Object... args) {
		Query query = entityManager.createQuery(hql);
		int i = 0;
		for(Object arg:args) {
            query.setParameter(++i,arg);
        }
        return query.executeUpdate();
	}

}
