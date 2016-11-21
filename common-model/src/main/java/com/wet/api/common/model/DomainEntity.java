package com.wet.api.common.model;

import java.io.Serializable;
import java.util.Date;

public interface DomainEntity extends DomainObject, Serializable
{
	public Date getVersion();
	public void setVersion(Date version);
}