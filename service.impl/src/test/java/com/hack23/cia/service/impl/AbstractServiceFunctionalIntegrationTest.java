/*
 * Copyright 2010 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hack23.cia.model.internal.application.system.impl.ApplicationSessionType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.testfoundation.AbstractFunctionalIntegrationTest;

/**
 * The Class AbstractServiceFunctionalIntegrationTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/application-context-service.xml",
		"classpath:/META-INF/cia-service-component-agent.xml",
		"classpath:META-INF/cia-jms-broker.xml",
		"classpath:/META-INF/application-context-service-data.xml",
		"classpath:META-INF/cia-service-external-common.xml",
		"classpath:META-INF/cia-service-external-riksdagen.xml",
		"classpath:META-INF/cia-service-external-worldbank.xml",
		"classpath:META-INF/cia-service-external-vdem.xml",
		"classpath:META-INF/cia-service-external-val.xml",
"classpath:/META-INF/cia-test-context.xml" })
public abstract class AbstractServiceFunctionalIntegrationTest extends AbstractFunctionalIntegrationTest {

	/** The Constant EXPECT_A_RESULT. */
	public static final String EXPECT_A_RESULT = "Expect a result";

	/** The Constant EXPECT_SUCCESS. */
	public static final String EXPECT_SUCCESS = "Expect success";

	/** The Constant PRINCIPAL. */
	private static final String PRINCIPAL = "principal";

	/** The Constant KEY. */
	private static final String KEY = "key";

	/** The Constant ROLE_ANONYMOUS. */
	private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";


	/** The application manager. */
	@Autowired
	protected ApplicationManager applicationManager;


	/**
	 * Instantiates a new abstract service functional integration test.
	 */
	public AbstractServiceFunctionalIntegrationTest() {
		super();
	}

	@Override
	protected Connection getDatabaseConnection() throws Exception {
		return null;
	}

	/**
	 * Creates the test application session.
	 *
	 * @return the creates the application session request
	 */
	protected final CreateApplicationSessionRequest createTestApplicationSession() {
		final CreateApplicationSessionRequest serviceRequest = new CreateApplicationSessionRequest();
		serviceRequest.setIpInformation("8.8.8.8");
		serviceRequest.setLocale("en_US.UTF-8");
		serviceRequest.setOperatingSystem("LINUX");
		serviceRequest.setSessionId(UUID.randomUUID().toString());
		serviceRequest.setSessionType(ApplicationSessionType.ANONYMOUS);
		serviceRequest.setUserAgentInformation(
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:42.0) Gecko/20100101 Firefox/42.0");

		final CreateApplicationSessionResponse response = (CreateApplicationSessionResponse) applicationManager
				.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());
		return serviceRequest;
	}

	/**
	 * Creates the application sesstion with role anonymous.
	 *
	 * @return the creates the application session request
	 */
	protected final CreateApplicationSessionRequest createApplicationSesstionWithRoleAnonymous() {
		setAuthenticatedAnonymousUser();
		return createTestApplicationSession();
	}


	/**
	 * Sets the authenticated anonymous user.
	 */
	protected final void setAuthenticatedAnonymousUser() {
		final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(ROLE_ANONYMOUS));
		SecurityContextHolder.getContext()
				.setAuthentication(new AnonymousAuthenticationToken(KEY, PRINCIPAL, authorities));
	}

	/**
	 * Sets the authenticated adminuser.
	 */
	protected final void setAuthenticatedAdminuser() {
		final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
		authorities.add(new SimpleGrantedAuthority(ROLE_ANONYMOUS));

		SecurityContextHolder.getContext()
				.setAuthentication(new AnonymousAuthenticationToken(KEY, PRINCIPAL, authorities));
	}


}
