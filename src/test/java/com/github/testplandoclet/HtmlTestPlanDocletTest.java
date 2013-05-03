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

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.sun.tools.javadoc.Main;

/**
 * Tests for {@link HtmlTestPlanDoclet}.
 */
public class HtmlTestPlanDocletTest
{

   // ------------------------- rules -------------------------

   // ------------------------- test methods-------------------------

   @Test
   public void testKO_ApplicationMissing()
   {
      StringWriter err = new StringWriter();
      StringWriter warn = new StringWriter();
      StringWriter notice = new StringWriter();

      String[] args = new String[] { "-sourcepath", "./sample/src/it/java", "-subpackages", "com", "com" };

      Main.execute("HtmlTestPlanDoclet", new PrintWriter(err), new PrintWriter(warn), new PrintWriter(notice),
         HtmlTestPlanDoclet.class.getName(), args);

      Assert.assertEquals("HtmlTestPlanDoclet: error - -application option is mandatory.\n"
         + "HtmlTestPlanDoclet: error - Usage: javadoc -application name [-template my_template.vm] [-file my_filename] -doclet HtmlTestPlan ...\n",
         err.toString());
   }

   @Test
   public void testTemplateParDefaut()
   {
      final String fileName = "target/default_template.html";

      // String[] args = new String[] { "-sourcepath", "./sample/src/it/java",
      // "-subpackages", "fr",
      // "-private" };
      String[] args = new String[] { "-debug", "-application", "default_template", "-file", fileName, "-sourcepath", "./sample/src/it/java", "-subpackages", "com", "com" };

      Main.execute("HtmlTestPlanDoclet", HtmlTestPlanDoclet.class.getName(), args);
   }

   @Test
   public void testTemplateAutreTemplate()
   {
      final String fileName = "target/autre_template.html";

      // String[] args = new String[] { "-sourcepath", "./sample/src/it/java",
      // "-subpackages", "fr",
      // "-private" };
      String[] args =
         new String[] { "-debug", "-application", "other_template","-template", "src/test/resources/other_template.vm", "-file", fileName, "-sourcepath",
            "./sample/src/it/java", "-subpackages", "com", "com" };

      Main.execute("HtmlTestPlanDoclet", HtmlTestPlanDoclet.class.getName(), args);
   }
}
