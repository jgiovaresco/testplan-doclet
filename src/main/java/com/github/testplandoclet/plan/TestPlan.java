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

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines the test plan.
 * @author Julien Giovaresco
 */
public class TestPlan
{
   // ------------------------- private contantes -------------------------

   /** The logger. */
   private final static Logger LOGGER = LoggerFactory.getLogger(TestPlan.class);

   // ------------------------- private members -------------------------

   /** The domains of the test plan. */
   private Map<String, Domain> m_domains;

   // ------------------------- constructors -------------------------

   /**
    * Empty constructor.
    */
   public TestPlan()
   {
      m_domains = new TreeMap<String, Domain>();
   }

   // ------------------------- public methods -------------------------

   /**
    * Adds a requirement to the test plan.
    * @param p_requirement The requirement to add.
    */
   public void add(Requirement p_requirement)
   {
      Domain domaine = null;
      Service service = null;

      LOGGER.debug("Adding requirement {} to the test plan", p_requirement);

      domaine = m_domains.get(p_requirement.getDomain());
      if (null == domaine)
      {
         LOGGER.debug("Creates new domain {} to the test plan", p_requirement.getDomain());
         domaine = new Domain(p_requirement.getDomain());
         m_domains.put(p_requirement.getDomain(), domaine);
      }

      service = domaine.getService(p_requirement.getService());
      if (null == service)
      {
         LOGGER.debug("Creates new service {} for the domain {}", p_requirement.getService(), domaine.getName());
         service = new Service(p_requirement.getService());
         domaine.addService(service);
      }

      service.addRequirement(p_requirement);
   }

   /**
    * Adds a testcase to the testplan.
    * @param p_testcase The testcase to add.
    */
   public void add(TestCase p_testcase)
   {
      Domain domaine = null;
      Service service = null;

      LOGGER.debug("Adding test case {} to the test plan", p_testcase);

      domaine = m_domains.get(p_testcase.getDomain());
      if (null == domaine)
      {
         LOGGER.debug("Creates new domain {} to the test plan", p_testcase.getDomain());
         domaine = new Domain(p_testcase.getDomain());
         m_domains.put(p_testcase.getDomain(), domaine);
      }

      service = domaine.getService(p_testcase.getService());
      if (null == service)
      {
         LOGGER.debug("Creates new service {} for the domain {}", p_testcase.getService(), domaine.getName());
         service = new Service(p_testcase.getService());
         domaine.addService(service);
      }

      service.addTestcase(p_testcase);
   }

   /**
    * Returns the domains of the test plan.
    * @return The domains of the test plan.
    */
   public Collection<Domain> getDomains()
   {
      return Collections.unmodifiableCollection(m_domains.values());
   }

   /**
    * Returns the domain by its name.
    * @param p_Name The domain's name to get.
    * @return The {@link Domain}.
    */
   public Domain getDomain(String p_Name)
   {
      return m_domains.get(p_Name);
   }
}
