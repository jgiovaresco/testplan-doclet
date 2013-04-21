/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.egiov.testplandoclet.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.egiov.testplandoclet.HtmlTestPlanDoclet;

/**
 * Defines the test plan.
 * 
 * @author Julien Giovaresco
 */
public class TestPlan {
	// ------------------------- private contantes -------------------------

	/** The logger. */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(HtmlTestPlanDoclet.class);

	// ------------------------- private members -------------------------

	/** The test cases of the test plan. */
	private Map<String, TestCase> m_testcases;

	/** The services' testcases. */
	private Map<String, List<String>> m_serviceTestCases;

	/** The requirements. */
	private Map<String, Requirement> m_requirements;

	/** The services' requirements. */
	private Map<String, List<String>> m_serviceRequirements;
	
	/** The requirements' cover. */
	private Map<String, List<String>> m_requirementsCover;

	// ------------------------- constructors -------------------------

	/**
	 * Empty constructor.
	 */
	public TestPlan() {
		m_testcases = new TreeMap<String, TestCase>();
		m_serviceTestCases = new TreeMap<String, List<String>>();
		m_requirements = new TreeMap<String, Requirement>();
		m_serviceRequirements = new TreeMap<String, List<String>>();
		m_requirementsCover = new TreeMap<String, List<String>>();
	}

	// ------------------------- public methods -------------------------

	/**
	 * Adds requirements to the test plan.
	 * 
	 * @param p_requirements
	 *            The requirements to add.
	 */
	public void add(List<Requirement> p_requirements) {
		String service = null;
		List<String> requirements = null;
		
		for (Requirement requirement : p_requirements) {
			LOGGER.debug("Adding requirement {} to the test plan", requirement);
			m_requirements.put(requirement.getCode(), requirement);
		
			service = requirement.getService();
			LOGGER.debug("Updating the services' requirements for {}", service);
			requirements = m_serviceRequirements.get(service);
			if (null == requirements) {
				LOGGER.debug("No requirement for the service {}", service);
				requirements = new ArrayList<String>();
				m_serviceRequirements.put(service, requirements);
			}
			requirements.add(requirement.getCode());
		}
	}

	/**
	 * Adds a testcase to the testplan.
	 * 
	 * @param p_testcase
	 *            The testcase to add.
	 */
	public void add(TestCase p_testcase) {
		String testedService = null;
		String testcaseName = null;
		List<String> testcases = null;

		testcaseName = p_testcase.getName();
		testedService = p_testcase.getTestedService();

		LOGGER.debug("Adding testcase {} for the service {} to the test plan",
				testcaseName, testedService);
		m_testcases.put(testcaseName, p_testcase);

		// 
		
		testcases = m_serviceTestCases.get(testedService);
		LOGGER.debug("Updating the services' cover for {}", testedService);
		if (null == testcases) {
			LOGGER.debug("No testcase for the service {}", testedService);
			testcases = new ArrayList<String>();
			m_serviceTestCases.put(testedService, testcases);
		}
		testcases.add(testcaseName);

		//
		
		for (String requirement : p_testcase.getRequirements()) {
			LOGGER.debug("Updating the requirements' cover for {}", requirement);
			testcases = m_requirementsCover.get(requirement);
			if (null == testcases) {
				LOGGER.debug("No testcase for the requirement {}", requirement);
				testcases = new ArrayList<String>();
				m_requirementsCover.put(requirement, testcases);
			}
			testcases.add(testcaseName);
		}
	}

	/**
	 * Returns the list of tested services.
	 * 
	 * @return The list of tested services.
	 */
	public Collection<String> getTestedServices() {
		return Collections.unmodifiableSet(m_serviceTestCases.keySet());
	}

	/**
	 * Return testcases of a service.
	 * 
	 * @param p_service
	 *            The service
	 * @return The testcases of a service.
	 */
	public List<TestCase> getTestCases(String p_service) {
		List<TestCase> testcases = null;

		testcases = new ArrayList<TestCase>();

		LOGGER.debug("Gets testcases for the service {}", p_service);
		for (String testcaseName : m_serviceTestCases.get(p_service)) {
			testcases.add(m_testcases.get(testcaseName));
		}

		return testcases;
	}
	
	/**
	 * Return testcases which cover the requirement passed in parameter.
	 * 
	 * @param p_requirement
	 *            The requirement.
	 * @return The testcases which cover the requirement passed in parameter.
	 */
	public List<TestCase> getTestCases(Requirement p_requirement) {
		List<TestCase> testcases = null;
		
		testcases = new ArrayList<TestCase>();
		
		LOGGER.debug("Gets testcases which cover the requirement {}", p_requirement.getCode());
		for (String testcaseName : m_requirementsCover.get(p_requirement.getCode())) {
			testcases.add(m_testcases.get(testcaseName));
		}
		
		return testcases;
	}

	/**
	 * Returns requirements of a service.
	 * 
	 * @param p_service
	 *            The service.
	 * @return The requirements of a service.
	 */
	public List<Requirement> getRequirements(String p_service) {
		List<Requirement> requirements = null;

		requirements = new ArrayList<Requirement>();

		LOGGER.debug("Gets requirements for the service {}", p_service);
		for (String requirementCode : m_serviceRequirements.get(p_service)) {
			requirements.add(m_requirements.get(requirementCode));
		}
		
		return requirements;
	}

	/**
	 * Returns a requirement thanks to its code.
	 * 
	 * @return The requirement.
	 */
	public Requirement getRequirement(String p_code) {
		return m_requirements.get(p_code);
	}
}
