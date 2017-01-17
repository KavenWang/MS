package com.uisftech.common.base;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 自定义jpa factoryBean工厂
 * @author wangwei
 *
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T,ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID>{

	@Override
	 protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
		 return new BaseRepositoryFactory(em);
	 }

	//创建一个内部类，该类不用在外部访问
	private static class BaseRepositoryFactory<T,ID extends Serializable> extends JpaRepositoryFactory{
		private final EntityManager entityManager;
		//构造函数
		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}
		//设置具体的实现类是BaseRepositoryImpl
		@SuppressWarnings("unchecked")
		@Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        //设置具体的实现类的class
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }

	}
}
