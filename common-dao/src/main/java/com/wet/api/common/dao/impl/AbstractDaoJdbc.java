package com.wet.api.common.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.wet.api.common.model.DomainEntity;

public abstract class AbstractDaoJdbc<T extends DomainEntity> extends AbstractDao<T>
{
	@Autowired
	protected DataSource dataSource;
	
	public AbstractDaoJdbc(Class<T> type)
	{
		super(type);
	}
	
	protected final String getTableName()
	{
		return type.getSimpleName().toLowerCase();
	}
	
	protected abstract PreparedStatement loadPreparedStatement(T object, Connection connection, String sql) throws SQLException;
}