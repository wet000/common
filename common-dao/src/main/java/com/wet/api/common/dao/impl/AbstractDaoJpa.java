package com.wet.api.common.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.wet.api.common.model.DomainEntity;

public abstract class AbstractDaoJpa<T extends DomainEntity> extends AbstractDao<T> 
{	
	public AbstractDaoJpa(Class<T> type)
	{
		super(type);
	}
	
	protected abstract EntityManager getEntityManager();
	
	protected String transactionManager;
	
	@Override
	public T find(long id) 
	{
		return getEntityManager().find(type, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() 
	{
		return getEntityManager().createQuery("select o from " + type.getName() + " o").getResultList();
	}

	@Override
	public void save(T object) 
	{
		if (object.getId() == 0)
		{
			getEntityManager().persist(object);
		}
		else
		{
			getEntityManager().merge(object);
		}
	}

	@Override
	public void delete(T object) 
	{
		getEntityManager().remove(object);
		
		// May need to merge the object into the current entity manager if the remove isn't in the same transaction
		// entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
	}
}