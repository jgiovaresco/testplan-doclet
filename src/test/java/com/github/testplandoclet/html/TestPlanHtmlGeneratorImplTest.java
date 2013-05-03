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

import junit.framework.Assert;

import org.junit.Test;


/**
 *
 */
public class TestPlanHtmlGeneratorImplTest
{
   // ------------------------- private constants -------------------------

   // ------------------------- private members -------------------------

   // ------------------------- constructors -------------------------

   // ------------------------- preparation methods -------------------------

   // ------------------------- tests methods -------------------------

   /**
    * Test method for {@link TestPlanHtmlGeneratorImpl#getTemplateDirFromTemplateFilePath(String)}
    */
   @Test
   public void testGetTemplateDirFromTemplateFilePath()
   {
      String result = null;
      
      result = TestPlanHtmlGeneratorImpl.getTemplateDirFromTemplateFilePath("file.vm");
      Assert.assertEquals(".", result);
      
      result = TestPlanHtmlGeneratorImpl.getTemplateDirFromTemplateFilePath("path/file.vm");
      Assert.assertEquals("path", result);

      result = TestPlanHtmlGeneratorImpl.getTemplateDirFromTemplateFilePath("my/path/to/the/file.vm");
      Assert.assertEquals("my/path/to/the", result);
   }
   
   /**
    * Test method for {@link TestPlanHtmlGeneratorImpl#getFileName(String)}
    */
   @Test
   public void testGetFileName()
   {
      String result = null;
      
      result = TestPlanHtmlGeneratorImpl.getFileName("file.vm");
      Assert.assertEquals("file.vm", result);
      
      result = TestPlanHtmlGeneratorImpl.getFileName("path/file.vm");
      Assert.assertEquals("file.vm", result);
      
      result = TestPlanHtmlGeneratorImpl.getFileName("my/path/to/the/file.vm");
      Assert.assertEquals("file.vm", result);
   }
   
   // ------------------------- private methods -------------------------
}
