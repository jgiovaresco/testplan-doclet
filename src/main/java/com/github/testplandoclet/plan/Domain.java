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

/**
 * Define a domain.
 * <p>
 * A domain contains severals services
 * </p>
 */
public class Domain implements Comparable<Domain>
{
   // ------------------------- private constants -------------------------

   // ------------------------- private members -------------------------

   /** The domain's name. */
   private String m_name;

   /** The services associated to the domain. */
   private Map<String, Service> m_services;

   // ------------------------- constructors -------------------------

   /**
    * Constructor
    * @param p_name The domain's name
    */
   public Domain(String p_name)
   {
      super();
      m_name = p_name;

      m_services = new TreeMap<String, Service>();
   }

   // ------------------------- static methods -------------------------

   // ------------------------- public methods -------------------------

   /**
    * Adds a service to the domain.
    * @param p_service The service to add.
    */
   public void addService(Service p_service)
   {
      m_services.put(p_service.getName(), p_service);
   }

   /**
    * Returns a service by its name.
    * @param p_serviceName The service to get.
    * @return The service.
    */
   public Service getService(String p_serviceName)
   {
      return m_services.get(p_serviceName);
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   @Override
   public int compareTo(Domain p_domain)
   {
      return m_name.compareTo(p_domain.getName());
   }

   /**
    * {@inheritDoc}
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Domain [m_name=" + m_name + ", m_services=" + m_services + "]";
   }

   // ------------------------- private methods -------------------------

   // ------------------------- public accessors -------------------------

   /**
    * Returns name.
    * @return The name.
    * @see Domain#m_name
    */
   public String getName()
   {
      return m_name;
   }

   /**
    * Returns services.
    * @return The services.
    * @see Domain#m_services
    */
   public Collection<Service> getServices()
   {
      return Collections.unmodifiableCollection(m_services.values());
   }
}
