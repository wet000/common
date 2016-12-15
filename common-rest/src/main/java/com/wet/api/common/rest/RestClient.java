package com.wet.api.common.rest;

import com.wet.api.common.model.DomainObject;

public interface RestClient<T extends DomainObject> 
{
	/**
	 * Get a {@link T} from REST web service.
	 *
	 * @return 	The found {@link T}
	 */
	public T get();
}