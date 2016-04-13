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
package com.hack23.cia.service.impl.action.application;

import java.util.List;
import java.util.UUID;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.model.internal.application.user.impl.UserAccount_;
import com.hack23.cia.model.internal.application.user.impl.UserType;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.action.application.CreateApplicationSessionRequest;
import com.hack23.cia.service.api.action.application.LoginRequest;
import com.hack23.cia.service.api.action.application.LoginResponse;
import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.service.api.action.application.RegisterUserRequest;
import com.hack23.cia.service.api.action.application.RegisterUserResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse;
import com.hack23.cia.service.api.action.common.ServiceResponse.ServiceResult;
import com.hack23.cia.service.impl.AbstractServiceFunctionalIntegrationTest;

/**
 * The Class LogoutServiceITest.
 */
@PerfTest(threads = 10, duration = 3000, warmUp = 1500)
@Required(max = 200, average = 10, percentile95 = 15, throughput = 50000)
public final class LogoutServiceITest extends AbstractServiceFunctionalIntegrationTest {

	/** The i. */
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Service logout request success test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@PerfTest(threads = 4, duration = 3000, warmUp = 1500)
	@Required(max = 1000, average = 300, percentile95 = 350, throughput = 10)
	public void serviceLogoutRequestSuccessTest() throws Exception {
		final CreateApplicationSessionRequest createApplicationSesstion = createApplicationSesstionWithRoleAnonymous();


		final RegisterUserRequest serviceRequest = new RegisterUserRequest();
		serviceRequest.setCountry("Sweden");
		serviceRequest.setUsername(UUID.randomUUID().toString());
		serviceRequest.setEmail(serviceRequest.getUsername() + "@email.com");
		serviceRequest.setUserpassword("userpassword");
		serviceRequest.setUserType(UserType.PRIVATE);
		serviceRequest.setSessionId(createApplicationSesstion.getSessionId());

		final RegisterUserResponse response = (RegisterUserResponse) applicationManager.service(serviceRequest);
		assertNotNull(EXPECT_A_RESULT, response);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, response.getResult());

		final DataContainer<UserAccount, Long> dataContainer = applicationManager.getDataContainer(UserAccount.class);
		final List<UserAccount> allBy = dataContainer.getAllBy(UserAccount_.username, serviceRequest.getUsername());
		assertEquals(1, allBy.size());



		final LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(serviceRequest.getEmail());
		loginRequest.setSessionId(serviceRequest.getSessionId());
		loginRequest.setUserpassword(serviceRequest.getUserpassword());

		final LoginResponse loginResponse = (LoginResponse) applicationManager.service(loginRequest);

		assertNotNull(EXPECT_A_RESULT, loginResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, loginResponse.getResult());

		final LogoutRequest logoutRequest = new LogoutRequest();
		logoutRequest.setSessionId(serviceRequest.getSessionId());

		final ServiceResponse logoutResponse = applicationManager.service(logoutRequest);
		assertNotNull(EXPECT_A_RESULT, logoutResponse);
		assertEquals(EXPECT_SUCCESS,ServiceResult.SUCCESS, logoutResponse.getResult());

	}

}
