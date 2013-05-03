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

   /** The domain to which requirement belongs. */
   private String m_domain = null;

   /** The service to which requirement belongs. */
   private String m_service = null;

   /** The code of the requirement. */
   private String m_code = null;

   /** The description of the requirement. */
   private String m_description = null;

   // ------------------------- constructors -------------------------

   /**
    * Constructor
    * @param p_domain The domain to which the requirement belongs.
    * @param p_service The service to which the requirement belongs.
    * @param p_code The code of the requirement.
    * @param p_description The description of the requirement.
    */
   public Requirement(String p_domain, String p_service, String p_code, String p_description)
   {
      super();
      this.m_domain = p_domain;
      this.m_service = p_service;
      this.m_code = p_code;
      this.m_description = p_description;
   }

   // ------------------------- public methods -------------------------

   /**
    * {@inheritDoc}
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   @Override
   public int compareTo(Requirement p_requirement)
   {
      return m_code.compareTo(p_requirement.getCode());
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Requirement [m_domain=" + m_domain + ", m_service=" + m_service + ", m_code=" + m_code + ", m_description=" + m_description
         + "]";
   }

   // ------------------------- private methods -------------------------

   // ------------------------- public accessors -------------------------

   /**
    * Returns domain.
    * @return The domain.
    * @see Requirement#m_domain
    */
   public String getDomain()
   {
      return m_domain;
   }

   /**
    * Sets domain.
    * @param p_domain The domain
    * @see Requirement#m_domain
    */
   public void setDomain(String p_domain)
   {
      m_domain = p_domain;
   }

   /**
    * Returns service.
    * @return The service.
    * @see Requirement#m_service
    */
   public String getService()
   {
      return m_service;
   }

   /**
    * Sets service.
    * @param p_service The service
    * @see Requirement#m_service
    */
   public void setService(String p_service)
   {
      m_service = p_service;
   }

   /**
    * Returns code.
    * @return The code.
    * @see Requirement#m_code
    */
   public String getCode()
   {
      return m_code;
   }

   /**
    * Sets code.
    * @param p_code The code
    * @see Requirement#m_code
    */
   public void setCode(String p_code)
   {
      m_code = p_code;
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
}
