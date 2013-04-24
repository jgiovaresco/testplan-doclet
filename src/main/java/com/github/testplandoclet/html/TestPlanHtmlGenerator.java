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
package com.github.testplandoclet.html;

import com.github.testplandoclet.plan.TestPlan;

/**
 * Defines the interface for the generator of test plan in HTML.
 * 
 * @author Julien Giovaresco
 */
public interface TestPlanHtmlGenerator
{
   /**
    * Generates the test plan in a html file.
    * @param p_applicationName The name of the application.
    * @param p_testplan The test plan to generate.
    */
   void generate(String p_applicationName, TestPlan p_testplan);
}
