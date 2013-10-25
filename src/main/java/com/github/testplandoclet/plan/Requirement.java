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

/**
 * Defines a requirement
 * @author Julien Giovaresco
 */
public class Requirement implements Comparable<Requirement>
{
	// ------------------------- private constants -------------------------

	// ------------------------- private members -------------------------

	/** The id of the requirement. */
	private String m_id = null;

	/** The title of the requirement. */
	private String m_title;

	/** The description of the requirement. */
	private String m_description = null;

	// ------------------------- constructors -------------------------

	/**
	 * Constructor
	 * @param p_id The id of the requirement.
	 * @param p_title The title of the requirement.
	 * @param p_description The description of the requirement.
	 */
	public Requirement(String p_id, String p_title, String p_description)
	{
		super();
		this.m_id = p_id;
		this.m_title = p_title;
		this.m_description = p_description;
	}

	// ------------------------- public methods -------------------------

	/**
	 * {@inheritDoc}
	 * @see Comparable#compareTo(Object)
	 */
	@Override
	public int compareTo(Requirement p_requirement)
	{
		return m_id.compareTo(p_requirement.getId());
	}

	/**
	 * {@inheritDoc}
	 * @see Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Requirement [m_id=" + m_id + ", m_description=" + m_description + "]";
	}

	// ------------------------- private methods -------------------------

	// ------------------------- public accessors -------------------------

	/**
	 * Returns code.
	 * @return The code.
	 * @see Requirement#m_id
	 */
	public String getId()
	{
		return m_id;
	}

	/**
	 * Sets code.
	 * @param p_id The code
	 * @see Requirement#m_id
	 */
	public void setId(String p_id)
	{
		m_id = p_id;
	}

	/**
	 * Returns description.
	 * @return The description.
	 * @see Requirement#m_description
	 */
	public String getDescription()
	{
		return m_description;
	}

	/**
	 * Sets description.
	 * @param p_description The description
	 * @see Requirement#m_description
	 */
	public void setDescription(String p_description)
	{
		m_description = p_description;
	}

	/**
	 * Returns title.
	 * @return The title
	 * @see Requirement#m_title
	 */
	public String getTitle()
	{
		return m_title;
	}

	/**
	 * Sets title.
	 * @param p_title The title
	 * @see Requirement#m_title
	 */
	public void setTitle(String p_title)
	{
		m_title = p_title;
	}
}
