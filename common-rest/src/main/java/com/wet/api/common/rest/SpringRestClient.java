package com.wet.api.common.rest;

import org.springframework.http.ResponseEntity;

import com.wet.api.common.model.DomainObject;

public interface SpringRestClient<T extends DomainObject> extends RestClient<T>
{
	public String getBaseUri();
	public ResponseEntity<T> getResponse(String uri);
}