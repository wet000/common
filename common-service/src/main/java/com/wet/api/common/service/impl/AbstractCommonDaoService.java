package com.wet.api.common.service.impl;

import java.util.List;

import com.wet.api.common.dao.Dao;
import com.wet.api.common.model.DomainEntity;
import com.wet.api.common.service.CommonDaoService;

public abstract class AbstractCommonDaoService<T extends DomainEntity> implements CommonDaoService<T> 
{	
	protected abstract Dao<T> getDao();
	
	@Override
	public T find(long id) 
	{
		return getDao().find(id);
	}

	@Override
	public List<T> findAll() 
	{
		return getDao().findAll();
	}

	@Override
	public void save(T object) 
	{
		getDao().save(object);
	}

	@Override
	public void delete(T object) 
	{
		getDao().delete(object);
	}
}