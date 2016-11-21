package com.wet.api.common.dao;

import java.util.List;

import com.wet.api.common.model.DomainEntity;

public interface Dao<T extends DomainEntity> 
{
	/**
	 * Find a {@link T} by id.
	 *
	 * @param 	id Id of the {@link T} to find
	 * @return 	The found {@link T}
	 */
	public T find(long id);
	
	/**
	 * Find all the {@link T}s
	 *
	 * @return 	A list of {@link T}s
	 */
	public List<T> findAll();
	
	/**
	 * Update or create a {@link T}.
	 *
	 * @param 	object The {@link T} to create or update
	 */
	public void save(T object);
	
	/**
	 * Delete a {@link T}.
	 *
	 * @param 	object The {@link T} to delete
	 */
	public void delete(T object);
}