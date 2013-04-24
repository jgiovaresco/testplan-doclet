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

import java.io.FileWriter;
import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.github.testplandoclet.Configuration;
import com.github.testplandoclet.plan.TestPlan;

/**
 * Implementation of the interface {@link TestPlanHtmlGenerator}.
 * 
 * @author Julien Giovaresco
 */
public class TestPlanHtmlGeneratorImpl implements TestPlanHtmlGenerator {
	// ------------------------- private constants -------------------------

	/** The directory containing the templates. */
	private static final String TEMPLATES_DIR = "templates/";

	// ------------------------- private members -------------------------

	// ------------------------- constructors -------------------------

	/**
	 * Constructor for {@link TestPlanHtmlGeneratorImpl}.
	 * <p>
	 * Initialize {@link Velocity}. </>
	 */
	public TestPlanHtmlGeneratorImpl() {
		Velocity.setProperty(Velocity.RESOURCE_LOADER, "classpath");
		Velocity.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				Log4JLogChute.class.getName());
		Velocity.setProperty(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER, "velocity");
		Velocity.init();
	}

	// ------------------------- public methods-------------------------

	/**
	 * {@inheritDoc}
	 * 
	 * @see fr.egiov.testplandoclet.html.TestPlanHtmlGenerator#generate(java.lang.String,
	 *      fr.egiov.testplandoclet.plan.TestPlan)
	 */
	@Override
	public void generate(String p_applicationName, TestPlan p_testplan) {
		Template t = null;
		VelocityContext context = null;
		FileWriter writer = null;

		t = Velocity.getTemplate(TEMPLATES_DIR + "html_testplan.vm", "UTF-8");

		context = new VelocityContext();
		context.put("applicatioName", p_applicationName);
		context.put("testplan", p_testplan);

		try {
			writer = new FileWriter(Configuration.getFileName());
			t.merge(context, writer);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
