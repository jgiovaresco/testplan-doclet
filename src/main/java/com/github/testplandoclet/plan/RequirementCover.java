/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.github.testplandoclet.plan;

import java.util.List;

/**
 * Defines a requirement cover.
 * @author Julien Giovaresco
 */
public class RequirementCover
{
	// ------------------------- private constants -------------------------

	// ------------------------- private members -------------------------

	/** The id of the requirement. */
	private String m_requirementId = null;

	/** The test cases covering the requirement. */
	private List<String> m_testCases;

	// ------------------------- constructors -------------------------

	/**
	 * Constructor
	 * @param p_requirementId The id of the requirement.
	 * @param p_testCases The test cases covering the requirement.
	 */
	public RequirementCover(String p_requirementId, List<String> p_testCases)
	{
		super();
		this.m_requirementId = p_requirementId;
		this.m_testCases = p_testCases;
	}

	// ------------------------- public methods -------------------------

	/**
	 * {@inheritDoc}
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RequirementCover [m_requirementId=" + m_requirementId + ", m_testCases" + m_testCases + "]";
	}

	// ------------------------- private methods -------------------------

	// ------------------------- public accessors -------------------------

	/**
	 * Returns requirementId.
	 * @return The id of the requirement.
	 * @see RequirementCover#m_requirementId
	 */
	public String getRequirementId()
	{
		return m_requirementId;
	}

	/**
	 * Sets requirementId.
	 * @param p_requirementId The id of the requirement.
	 * @see RequirementCover#m_requirementId
	 */
	public void setRequirementId(String p_requirementId)
	{
		m_requirementId = p_requirementId;
	}

	/**
	 * Returns testCases.
	 * @return The test cases covering the requirement.
	 * @see RequirementCover#m_testCases
	 */
	public List<String> getTestCases()
	{
		return m_testCases;
	}

	/**
	 * Sets testCases.
	 * @param p_testCases The test cases covering the requirement.
	 * @see RequirementCover#m_testCases
	 */
	public void setTestCases(List<String> p_testCases)
	{
		m_testCases = p_testCases;
	}
}
