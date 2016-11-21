package com.wet.api.common.dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:/META-INF/spring/spring.xml"})
public class CommonDaoTest 
{
	protected static final String JDBC_DRIVER = org.h2.Driver.class.getName();
	protected static final String JDBC_URL = "jdbc:h2:mem:notification_test;DB_CLOSE_DELAY=-1";
	protected static final String USER = "sa";
	protected static final String PASSWORD = "";
	protected static final String SCHEMA_LOCATION = "classpath:META-INF/h2/schema.sql";
	protected static final String DATASET_LOCATION = "META-INF/h2/dataset.xml";
	protected static final Charset CHARSET = StandardCharsets.UTF_8;
	protected static final boolean CONTINUE_ON_ERROR = false;
	
}