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
package com.github.testplandoclet;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.sun.tools.javadoc.Main;

/**
 * Tests for {@link HtmlTestPlanDoclet}.
 */
public class HtmlTestPlanDocletTest {

	// ------------------------- rules -------------------------

	// ------------------------- test methods-------------------------

	@Test
	public void testKO() {
		StringWriter err = new StringWriter();
		StringWriter warn = new StringWriter();
		StringWriter notice = new StringWriter();

		String[] args = new String[] { "-sourcepath", "./sample/src/it/java",
				"-subpackages", "com", "com" };

		Main.execute("HtmlTestPlanDoclet", new PrintWriter(err),
				new PrintWriter(warn), new PrintWriter(notice),
				HtmlTestPlanDoclet.class.getName(), args);

		Assert.assertEquals(
				"HtmlTestPlanDoclet: error - Usage: javadoc -file my_filename -doclet HtmlTestPlan ...\n",
				err.toString());
	}

	@Test
	public void test() {
		final String fileName = "target/html-doc.html";

		// String[] args = new String[] { "-sourcepath", "./sample/src/it/java",
		// "-subpackages", "fr",
		// "-private" };
		String[] args = new String[] { "-debug", "-file", fileName,
				"-sourcepath", "./sample/src/it/java", "-subpackages", "com",
				"com" };

		Main.execute("HtmlTestPlanDoclet", HtmlTestPlanDoclet.class.getName(),
				args);
	}
}
