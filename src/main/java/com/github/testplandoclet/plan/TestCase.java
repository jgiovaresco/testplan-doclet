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
package com.github.testplandoclet.plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a test case.
 * 
 * @author Julien Giovaresco
 */
public class TestCase implements Comparable<TestCase> {
	// ------------------------- private members -------------------------

	/** The domain to which requirement belongs. */
	private String m_domain = null;

	/** The service tested by this test case. */
	private String m_service;

	/** The name of this test case. */
	private String m_name;

	/** The title describing this test case. */
	private String m_title;

	/** The requirements checked by this test case. */
	private List<String> m_requirements;

	/** The prerequisite required for this test case. */
	private String m_prerequisite;

	/** The input data of this test case. */
	private String m_input;

	/** The expected result for this test case. */
	private String m_result;

	// ------------------------- constructors -------------------------

	/**
	 * Empty constructor.
	 */
	public TestCase() {
		m_requirements = new ArrayList<String>();
	}

	/**
	 * Constructor.
	 * 
	 * @param p_name
	 *            The test case name.
	 */
	public TestCase(String p_name) {
		super();
		m_name = p_name;
		m_requirements = new ArrayList<String>();
	}

	// ------------------------- public methods -------------------------

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TestCase p_testcase) {
		return m_name.compareTo(p_testcase.getName());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestCase [m_domain=" + m_domain + ",\nm_service=" + m_service
				+ ",\nm_name=" + m_name + ",\nm_title=" + m_title
				+ ",\nm_requirements=" + m_requirements + ",\nm_prerequisite="
				+ m_prerequisite + ",\nm_input=" + m_input + ",\nm_result="
				+ m_result + "]";
	}

	// ------------------------- public accessors -------------------------

	/**
	 * Returns domain.
	 * 
	 * @return The domain.
	 * @see TestCase#m_domain
	 */
	public String getDomain() {
		return m_domain;
	}

	/**
	 * Sets domain.
	 * 
	 * @param p_domain
	 *            The domain
	 * @see TestCase#m_domain
	 */
	public void setDomain(String p_domain) {
		m_domain = p_domain;
	}

	/**
	 * Returns the service.
	 * 
	 * @return The service.
	 * @see TestCase#m_service
	 */
	public String getService() {
		return m_service;
	}

	/**
	 * Sets the service.
	 * 
	 * @param p_service
	 *            The service
	 * @see TestCase#m_service
	 */
	public void setService(String p_service) {
		m_service = p_service;
	}

	/**
	 * Returns the name.
	 * 
	 * @return The name.
	 * @see TestCase#m_name
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param p_name
	 *            The name
	 * @see TestCase#m_name
	 */
	public void setName(String p_name) {
		m_name = p_name;
	}

	/**
	 * Returns the title.
	 * 
	 * @return The title.
	 * @see TestCase#m_title
	 */
	public String getTitle() {
		return m_title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param p_title
	 *            The title
	 * @see TestCase#m_title
	 */
	public void setTitle(String p_title) {
		m_title = p_title;
	}

	/**
	 * Returns the requirements.
	 * 
	 * @return The requirements.
	 * @see TestCase#m_requirements
	 */
	public List<String> getRequirements() {
		return m_requirements;
	}

	/**
	 * Sets the requirements.
	 * 
	 * @param p_requirements
	 *            The requirements
	 * @see TestCase#m_requirements
	 */
	public void setRequirements(List<String> p_requirements) {
		m_requirements = p_requirements;
	}

	/**
	 * Returns the prerequisite.
	 * 
	 * @return The prerequisite.
	 * @see TestCase#m_prerequisite
	 */
	public String getPrerequisite() {
		return m_prerequisite;
	}

	/**
	 * Sets the prerequisite.
	 * 
	 * @param p_prerequisite
	 *            The prerequisite
	 * @see TestCase#m_prerequisite
	 */
	public void setPrerequisite(String p_prerequisite) {
		m_prerequisite = p_prerequisite;
	}

	/**
	 * Returns the input.
	 * 
	 * @return The input.
	 * @see TestCase#m_input
	 */
	public String getInput() {
		return m_input;
	}

	/**
	 * Sets the input.
	 * 
	 * @param p_input
	 *            The input
	 * @see TestCase#m_input
	 */
	public void setInput(String p_input) {
		m_input = p_input;
	}

	/**
	 * Returns the result.
	 * 
	 * @return The result.
	 * @see TestCase#m_result
	 */
	public String getResult() {
		return m_result;
	}

	/**
	 * Sets the result.
	 * 
	 * @param p_result
	 *            The result
	 * @see TestCase#m_result
	 */
	public void setResult(String p_result) {
		m_result = p_result;
	}
}
