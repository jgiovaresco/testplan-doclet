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

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a test case.
 * @author Julien Giovaresco
 */
public class TestCase implements Comparable<TestCase>
{
	// ------------------------- private members -------------------------

	/** The name of this test case. */
	private String m_id;

	/** The title describing this test case. */
	private String m_title;

	/** The prerequisite required for this test case. */
	private String m_prerequisite;

	/** The input data of this test case. */
	private String m_input;

	/** The expected result for this test case. */
	private String m_result;

	// ------------------------- constructors -------------------------

	/** Empty constructor. */
	public TestCase()
	{
		// RAF
	}

	/**
	 * Constructor.
	 * @param p_name The test case name.
	 */
	public TestCase(String p_name)
	{
		super();
		m_id = p_name;
	}

	// ------------------------- public methods -------------------------

	/**
	 * {@inheritDoc}
	 * @see Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(TestCase p_testcase)
	{
		return m_id.compareTo(p_testcase.getId());
	}

	/**
	 * {@inheritDoc}
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TestCase [nm_id=" + m_id + ",\nm_title=" + m_title + ",\nm_prerequisite=" + m_prerequisite + ",\nm_input=" + m_input +
				",\nm_result=" + m_result + "]";
	}

	// ------------------------- public accessors -------------------------

	/**
	 * Returns the name.
	 * @return The name.
	 * @see TestCase#m_id
	 */
	public String getId()
	{
		return m_id;
	}

	/**
	 * Sets the name.
	 * @param p_id The name
	 * @see TestCase#m_id
	 */
	public void setId(String p_id)
	{
		m_id = p_id;
	}

	/**
	 * Returns the title.
	 * @return The title.
	 * @see TestCase#m_title
	 */
	public String getTitle()
	{
		return m_title;
	}

	/**
	 * Sets the title.
	 * @param p_title The title
	 * @see TestCase#m_title
	 */
	public void setTitle(String p_title)
	{
		m_title = p_title;
	}

	/**
	 * Returns the prerequisite.
	 * @return The prerequisite.
	 * @see TestCase#m_prerequisite
	 */
	public String getPrerequisite()
	{
		return m_prerequisite;
	}

	/**
	 * Sets the prerequisite.
	 * @param p_prerequisite The prerequisite
	 * @see TestCase#m_prerequisite
	 */
	public void setPrerequisite(String p_prerequisite)
	{
		m_prerequisite = p_prerequisite;
	}

	/**
	 * Returns the input.
	 * @return The input.
	 * @see TestCase#m_input
	 */
	public String getInput()
	{
		return m_input;
	}

	/**
	 * Sets the input.
	 * @param p_input The input
	 * @see TestCase#m_input
	 */
	public void setInput(String p_input)
	{
		m_input = p_input;
	}

	/**
	 * Returns the result.
	 * @return The result.
	 * @see TestCase#m_result
	 */
	public String getResult()
	{
		return m_result;
	}

	/**
	 * Sets the result.
	 * @param p_result The result
	 * @see TestCase#m_result
	 */
	public void setResult(String p_result)
	{
		m_result = p_result;
	}
}
