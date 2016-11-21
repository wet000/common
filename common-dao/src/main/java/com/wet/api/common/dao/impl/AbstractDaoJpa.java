package com.wet.api.common.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.wet.api.common.model.DomainEntity;

public abstract class AbstractDaoJpa<T extends DomainEntity> extends AbstractDao<T> 
{
	@PersistenceContext
	protected EntityManager entityManager;
	
	public AbstractDaoJpa(Class<T> type)
	{
		super(type);
	}
	
	@Override
	@Transactional(readOnly=true)
	public T find(long id) 
	{
		return entityManager.find(type, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<T> findAll() 
	{
		return entityManager.createQuery("select o from " + type.getName() + " o").getResultList();
	}

	@Override
	@Transactional
	public void save(T object) 
	{
		if (object.getId() == 0)
		{
			entityManager.persist(object);
		}
		else
		{
			entityManager.merge(object);
		}
	}

	@Override
	@Transactional
	public void delete(T object) 
	{
		entityManager.remove(object);
		
		// May need to merge the object into the current entity manager if the remove isn't in the same transaction
		// entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
	}
}