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
 * Defines a section in the test plan.
 * @author Julien Giovaresco
 */
public class Section
{
	// ------------------------- private constants -------------------------

	// ------------------------- private members -------------------------

	/** The level of the section. */
	private int m_level;

	/** The title of the section. */
	private String m_title;

	/** The description of the section. */
	private String m_description;

	/** The test cases stored in the section. */
	private List<TestCase> m_testCases;

	/** The sub sections. */
	private List<Section> m_subSections;

	// ------------------------- constructors -------------------------

	/**
	 * Constructor
	 * @param p_level The id of the requirement.
	 * @param p_title The title of the section.
	 * @param p_description The description of the section.
	 */
	public Section(int p_level, String p_title, String p_description)
	{
		super();
		this.m_level = p_level;
		this.m_title = p_title;
		this.m_description = p_description;
	}

	// ------------------------- public methods -------------------------

	// ------------------------- private methods -------------------------

	// ------------------------- public accessors -------------------------

	/**
	 * Returns level.
	 * @return The level.
	 * @see Section#m_level
	 */
	public int getLevel()
	{
		return m_level;
	}

	/**
	 * Sets level.
	 * @param p_level The level.
	 * @see Section#m_level
	 */
	public void setLevel(int p_level)
	{
		m_level = p_level;
	}

	/**
	 * Returns title.
	 * @return The title.
	 * @see Section#m_title
	 */
	public String getTitle()
	{
		return m_title;
	}

	/**
	 * Sets title.
	 * @param p_title The title.
	 * @see Section#m_title
	 */
	public void setTitle(String p_title)
	{
		m_title = p_title;
	}

	/**
	 * Returns description.
	 * @return The description.
	 * @see Section#m_description
	 */
	public String getDescription()
	{
		return m_description;
	}

	/**
	 * Sets description.
	 * @param p_description The description.
	 * @see Section#m_description
	 */
	public void setDescription(String p_description)
	{
		m_description = p_description;
	}

	/**
	 * Returns test cases.
	 * @return The test cases.
	 * @see Section#m_testCases
	 */
	public List<TestCase> getTestCases()
	{
		return m_testCases;
	}

	/**
	 * Sets test cases.
	 * @param p_testCases The test cases.
	 * @see Section#m_testCases
	 */
	public void setTestCases(List<TestCase> p_testCases)
	{
		m_testCases = p_testCases;
	}

	/**
	 * Returns subSections.
	 * @return The subSections.
	 * @see Section#m_subSections
	 */
	public List<Section> getSubSections()
	{
		return m_subSections;
	}

	/**
	 * Sets subSections.
	 * @param p_subSections The subSections.
	 * @see Section#m_subSections
	 */
	public void setSubSections(List<Section> p_subSections)
	{
		m_subSections = p_subSections;
	}
}
