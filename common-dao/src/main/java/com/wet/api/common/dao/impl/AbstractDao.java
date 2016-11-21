package com.wet.api.common.dao.impl;

import com.wet.api.common.dao.Dao;
import com.wet.api.common.model.DomainEntity;

public abstract class AbstractDao<T extends DomainEntity> implements Dao<T> 
{
	protected final Class<T> type;
	
	public AbstractDao(Class<T> type)
	{
		this.type = type;
	}
}