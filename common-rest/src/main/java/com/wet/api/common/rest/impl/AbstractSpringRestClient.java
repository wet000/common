package com.wet.api.common.rest.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.wet.api.common.model.DomainObject;
import com.wet.api.common.rest.RestClient;

public abstract class AbstractSpringRestClient<T extends DomainObject> implements RestClient<T>
{
	protected Class<T> type;
	protected  String baseUri;
	
	private String method;
	private Map<String, Object> params;
	private HttpEntity<String> entity;
	private RestTemplate restTemplate;
	
	protected abstract void setType();
	protected abstract void setBaseUri();
	
	@PostConstruct
	private void init()
	{
		setType();
		setBaseUri();
		
		// Setup rest template and message converters
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		
		this.restTemplate = new RestTemplate();
		this.restTemplate.setMessageConverters(messageConverters);
		
		// Initialize params and headers
		resetRestTemplate();
	}
	
	private void resetRestTemplate()
	{
		// Reset params
		params = new HashMap<String, Object>();
		
		// Set APPLICATION_JSON accept header
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    entity = new HttpEntity<String>(headers);
	}

	protected void setMethod(String method)
	{
		this.method = method;
	}

	protected void putParam(String field, Object value)
	{
		this.params.put(field, value);
	}

	protected void setBaseUri(String baseUri) 
	{
		this.baseUri = baseUri;
	}
	
	// Helper Wrapper Method
//	protected ResponseEntity<T> getResponse()
//	{
//		return getResponse(constructUri());
//	}
	
//	public ResponseEntity<T> getResponse(String uri)
//	{
//		if (this.params.isEmpty())
//		{
//			return getResponseEntity(uri, this.entity);
//		}
//		
//		ResponseEntity<T> responseEntity = getResponseEntity(uri, this.entity, this.params);
//		resetRestTemplate();
//		
//		return responseEntity;
//	}
	
	@Override
	public T get()
	{
		return get(constructUri());
	}
	
	private String constructUri()
	{
		System.out.println("Constructing URI: ");
		StringBuilder sb = new StringBuilder(baseUri);
		sb.append(this.method).append("/?");
		
		int size = params.size();
		for (Map.Entry<String, Object> entry : params.entrySet()) 
		{
			sb.append(entry.getKey()).append("={").append(entry.getKey()).append("}");
			if (--size > 0)
			{
				sb.append("&");
			}
		}
		
		return sb.toString();
	}
	
	private T get(String uri)
	{
		// In this implementation, headers are never empty because they are initialized 
		// to include an accept header of APPLICATION_JSON. But this can be abstracted 
		// in the future as more uses for RestTemplate are found. 
		// This can be a specific JSON Spring Rest Client implementation for example.
		
		if (this.params.isEmpty() && this.entity.getHeaders().isEmpty())
		{
			return getBody(uri);
		}
		else if (this.entity.getHeaders().isEmpty())
		{
			return getBody(uri, this.params);
		}
		else if (this.params.isEmpty())
		{
			return getBody(uri, this.entity);
		}
		
		T body = getBody(uri, this.entity, this.params);
		resetRestTemplate();
		
		return body;
	}
	
	private T getBody(String uri)
	{
		return this.restTemplate.getForObject(uri, this.type);
	}
	
	private T getBody(String uri, Map<String, ?> params)
	{
		return this.restTemplate.getForObject(uri, this.type, params);
	}
	
	private T getBody(String uri, HttpEntity<String> entity)
	{
		return getResponseEntity(uri, entity).getBody();
	}

	private T getBody(String uri, HttpEntity<String> entity, Map<String, ?> params)
	{
		return getResponseEntity(uri, entity, params).getBody();
	}

	private ResponseEntity<T> getResponseEntity(String uri, HttpEntity<String> entity) 
	{
		return this.restTemplate.exchange(uri, HttpMethod.POST, entity, this.type);
	}

	private ResponseEntity<T> getResponseEntity(String uri, HttpEntity<String> entity, Map<String, ?> params)
	{
		System.out.println("Uri: " + uri);
		return this.restTemplate.exchange(uri, HttpMethod.POST, entity, this.type, params);
	}
}