package com.wet.api.common.model.impl;

import com.wet.api.common.model.DomainObject;

public class AbstractDomainObject implements DomainObject
{
	protected long id;
	
	@Override
	public long getId() 
	{
		return id;
	}

	@Override
	public void setId(long id) 
	{
		this.id = id;
	}
}