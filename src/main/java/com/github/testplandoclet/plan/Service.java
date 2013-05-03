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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Define a service.
 * <p>
 * A service contains
 * <ul>
 * <li>requirements</li>
 * <li>testcases</li>
 * </ul>
 * </p>
 */
public class Service implements Comparable<Service>
{
   // ------------------------- private contantes -------------------------

   /** The logger. */
   private final static Logger LOGGER = LoggerFactory.getLogger(Service.class);

   // ------------------------- private members -------------------------

   /** The service name. */
   private String m_name;

   /** The domain on which the service is attached. */
   private String m_domain;

   /** The requirements for the service. */
   private Map<String, Requirement> m_requirements;

   /** The testcases for the service. */
   private Map<String, TestCase> m_testcases;

   /** The requirements' cover for the service. */
   private Map<String, List<String>> m_requirementsCover;

   // ------------------------- constructors -------------------------

   /**
    * Constructor
    * @param p_name The service name.
    */
   public Service(String p_name)
   {
      m_name = p_name;

      m_requirements = new TreeMap<String, Requirement>();
      m_testcases = new TreeMap<String, TestCase>();
      m_requirementsCover = new TreeMap<String, List<String>>();
   }

   // ------------------------- static methods -------------------------

   // ------------------------- public methods -------------------------

   /**
    * Adds a requirement.
    * @param p_requirement The requirement to add.
    */
   public void addRequirement(Requirement p_requirement)
   {
      m_requirements.put(p_requirement.getCode(), p_requirement);
   }

   /**
    * Adds a testcase.
    * @param p_testCase The testcase to add.
    */
   public void addTestcase(TestCase p_testCase)
   {
      List<String> testcases = null;
      String testcaseName = null;

      testcaseName = p_testCase.getName();

      m_testcases.put(testcaseName, p_testCase);

      for (String requirement : p_testCase.getRequirements())
      {
         LOGGER.debug("Updating the requirements' cover for {}", requirement);
         testcases = m_requirementsCover.get(requirement);
         if (null == testcases)
         {
            LOGGER.debug("No testcase for the requirement {}", requirement);
            testcases = new ArrayList<String>();
            m_requirementsCover.put(requirement, testcases);
         }
         testcases.add(testcaseName);
      }
   }

   /**
    * Returns a requirement by its code.
    * @param p_requirement The requirement's code to get.
    * @return The requirement.
    */
   public Requirement getRequirement(String p_requirement)
   {
      return m_requirements.get(p_requirement);
   }

   /**
    * Returns a test case by its name.
    * @param p_testCase The test case's name to get.
    * @return The test case.
    */
   public TestCase getTestCase(String p_testCase)
   {
      return m_testcases.get(p_testCase);
   }

   /**
    * Return testcases which cover the requirement passed in parameter.
    * @param p_requirement The requirement.
    * @return The testcases which cover the requirement passed in parameter.
    */
   public List<TestCase> getRequirementCover(Requirement p_requirement)
   {
      List<TestCase> testcases = null;

      testcases = new ArrayList<TestCase>();

      LOGGER.debug("Gets testcases which cover the requirement {}", p_requirement.getCode());
      for (String testcaseName : m_requirementsCover.get(p_requirement.getCode()))
      {
         testcases.add(m_testcases.get(testcaseName));
      }

      return testcases;
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   @Override
   public int compareTo(Service p_service)
   {
      return m_name.compareTo(p_service.getName());
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Service [m_name=" + m_name + ", m_domain=" + m_domain + ", m_requirements=" + m_requirements + ", m_testcases=" + m_testcases
         + "]";
   }

   // ------------------------- private methods -------------------------

   // ------------------------- public accessors -------------------------

   /**
    * Returns name.
    * @return The name.
    * @see Service#m_name
    */
   public String getName()
   {
      return m_name;
   }

   /**
    * Returns domain.
    * @return The domain.
    * @see Service#m_domain
    */
   public String getDomain()
   {
      return m_domain;
   }

   /**
    * Sets domain.
    * @param p_domain The domain
    * @see Service#m_domain
    */
   public void setDomain(String p_domain)
   {
      m_domain = p_domain;
   }

   /**
    * Returns requirements.
    * @return The requirements.
    * @see Service#m_requirements
    */
   public Collection<Requirement> getRequirements()
   {
      return Collections.unmodifiableCollection(m_requirements.values());
   }

   /**
    * Returns testcases.
    * @return The testcases.
    * @see Service#m_testcases
    */
   public Collection<TestCase> getTestCases()
   {
      return Collections.unmodifiableCollection(m_testcases.values());
   }
}
