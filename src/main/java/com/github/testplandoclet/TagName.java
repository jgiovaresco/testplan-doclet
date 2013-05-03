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
package com.github.testplandoclet;

/**
 * Defines the specifics tags name of the doclet.
 * @author Julien Giovaresco
 */
public final class TagName
{
   // ------------------------- public constants -------------------------

   /**
    * Tag defining
    * <ul>
    * <li>the domain to wich the test case belongs if this tag is in the javadoc of a test class.</li>
    * <li>the domain to which the requirement is attached if this tag is in the javadoc of a constant in a class defining requirement.</li>
    * </ul>
    */
   public static final String TAG_DOMAIN = "domain";

   /**
    * Tag defining
    * <ul>
    * <li>the service tested by the test case if this tag is in the javadoc of a test class.</li>
    * <li>the service to which the requirement is attached if this tag is in the javadoc of a constant in a class defining requirement.</li>
    * </ul>
    */
   public static final String TAG_SERVICE = "service";

   /** Tag defining that the class contains the definition of the requirements. */
   public static final String TAG_REQUIREMENTS = "requirements";

   /** Tag defining the title of the test case. */
   public static final String TAG_TITLE = "title";

   /**
    * Tag defining
    * <ul>
    * <li>the requirement covered by the test case if this tag is in the javadoc of an annoted Test method.</li>
    * <li>a requirement if this tag is in javadoc on a constant in a class defining requirements.</li>
    * </ul>
    */
   public static final String TAG_REQUIREMENT = "requirement";

   /** Tag defining the prerequisite of the test case. */
   public static final String TAG_PREREQUISITE = "prerequisite";

   /** Tag defining the input data of the test case. */
   public static final String TAG_INPUT = "input";

   /** Tag defining the expected result of the test case. */
   public static final String TAG_RESULT = "result";

   // ------------------------- constructors -------------------------

   /**
    * Private constructor.
    */
   private TagName()
   {
      // NTD
   }

}
