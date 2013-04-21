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
package fr.egiov.testplandoclet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;

import fr.egiov.testplandoclet.html.TestPlanHtmlGenerator;
import fr.egiov.testplandoclet.html.TestPlanHtmlGeneratorImpl;
import fr.egiov.testplandoclet.plan.Requirement;
import fr.egiov.testplandoclet.plan.TestCase;
import fr.egiov.testplandoclet.plan.TestPlan;

/**
 * This javadoc doclet creates the test plan from the javadoc of JUnit testcase
 * annoted with specifics tags.
 * 
 * @author Julien Giovaresco
 */
public class HtmlTestPlanDoclet extends Doclet {
	// ------------------------- private constants -------------------------

	/** The logger. */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(HtmlTestPlanDoclet.class);

	// ------------------------- private members -------------------------

	/** The test plan. */
	private TestPlan m_testPlan;

	// ------------------------- constructors -------------------------

	/**
	 * The constructor for {@link HtmlTestPlanDoclet}
	 */
	public HtmlTestPlanDoclet() {
		super();
		LOGGER.debug("Creating HtmlTestPlanDoclet");

		m_testPlan = new TestPlan();
	}

	// ------------------------- static methods -------------------------

	/**
	 * Doclet main method.
	 * 
	 * @param p_root
	 *            The root of the javadoc information.
	 * @return true if the javadoc generation was successfull.
	 */
	public static boolean start(RootDoc p_root) {
		boolean result = true;
		HtmlTestPlanDoclet doclet = null;

		try {
			Configuration.start(p_root);

			doclet = new HtmlTestPlanDoclet();
			doclet.processRootDoc(p_root);

			// No error processing done, simply return true.
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see HtmlTestPlanDoclet#languageVersion()
	 */
	public static LanguageVersion languageVersion() {
		return LanguageVersion.JAVA_1_5;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sun.javadoc.Doclet#optionLength(java.lang.String)
	 */
	public static int optionLength(String p_option) {
		return Configuration.optionLength(p_option);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.sun.javadoc.Doclet#validOptions(String[][], DocErrorReporter)
	 */
	public static boolean validOptions(String p_options[][],
			DocErrorReporter p_reporter) {
		return Configuration.validOptions(p_options, p_reporter);
	}

	// ------------------------- public methods -------------------------

	/**
	 * Process all classes to build the test plan.
	 * 
	 * @param root
	 *            The javadoc root.
	 */
	public void processRootDoc(RootDoc root) {
		ClassDoc[] classes = null;

		classes = root.classes();
		if (null != classes) {
			for (ClassDoc clazz : classes) {
				processClass(clazz);
			}

			TestPlanHtmlGenerator generator = new TestPlanHtmlGeneratorImpl();
			generator.generate("toto", m_testPlan);
		}
	}

	/**
	 * Process a class.
	 * <p>
	 * Its creates a new testcase if this class
	 * <ul>
	 * <li>has a name ending by "IT".</li>
	 * <li>has methods annotated with @Test.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param p_clazz
	 *            The class to process
	 */
	public void processClass(ClassDoc p_clazz) {
		MethodDoc[] methods = null;
		FieldDoc[] fields = null;

		String serviceTested = null;
		List<Requirement> requirements = null;
		TestCase testcase = null;
		boolean isTestClass = false;
		boolean isRequirementsClass = false;

		LOGGER.debug("Processing class {}", p_clazz);

		for (Tag tag : p_clazz.tags(TagName.TAG_SERVICE)) {
			LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());
			serviceTested = tag.text();
			isTestClass = true;
		}

		for (Tag tag : p_clazz.tags(TagName.TAG_REQUIREMENTS)) {
			LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());
			isRequirementsClass = true;
		}

		if (isRequirementsClass) {
			fields = p_clazz.fields();
			requirements = processFieldsOfClassDefiningRequirements(fields);

			m_testPlan.add(requirements);
		}

		if (isTestClass) {
			methods = p_clazz.methods();
			if (null != methods) {
				for (MethodDoc method : methods) {
					if (true == isTestMethod(method)) {
						testcase = processMethod(method);
						testcase.setTestedService(serviceTested);

						m_testPlan.add(testcase);
					}
				}
			}
		}
	}

	/**
	 * Process fields of a class defining requirements.
	 * 
	 * @param p_fields
	 *            The fields to process.
	 * @return The requirements list defined in the fields list.
	 */
	public List<Requirement> processFieldsOfClassDefiningRequirements(
			FieldDoc[] p_fields) {
		List<Requirement> requirements = new ArrayList<Requirement>();
		Requirement requirement = null;
		String service = null;
		String code = null;
		String description = null;

		if (null != p_fields) {
			for (FieldDoc field : p_fields) {
				LOGGER.debug("Processing field {}", field);

				for (Tag tag : field.tags(TagName.TAG_SERVICE)) {
					LOGGER.debug("Processing tag field {} : {}", tag.name(),
							tag.text());
					service = tag.text();
				}
				
				for (Tag tag : field.tags(TagName.TAG_REQUIREMENT)) {
					LOGGER.debug("Processing tag field {} : {}", tag.name(),
							tag.text());
					code = field.name();
					description = (String) field.constantValue();
				}
				
				requirement = new Requirement(service, code, description);
				requirements.add(requirement);
			}
		}

		return requirements;
	}

	/**
	 * Returns a {@link TestCase} corresponding to this test method.
	 * <p>
	 * Reads javadoc tags to complete the {@link TestCase}.
	 * </p>
	 * 
	 * @param p_method
	 *            The method to process
	 * @return The {@link TestCase}.
	 */
	public TestCase processMethod(MethodDoc p_method) {
		TestCase testcase = null;

		LOGGER.debug("Processing method {}", p_method);

		testcase = new TestCase(p_method.name());

		// @title
		for (Tag tag : p_method.tags(TagName.TAG_TITLE)) {
			LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());
			testcase.setTitle(tag.text());
		}

		// @requirement
		for (Tag tag : p_method.tags(TagName.TAG_REQUIREMENT)) {
			LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());

			for (Tag inlineTag : tag.inlineTags()) {
				LOGGER.debug("Processing inlinetag {} : {}", inlineTag.name(),
						inlineTag.text());
				if ("@link".equals(inlineTag.name())) {

					testcase.getRequirements().add(
							extractFieldNameFromLinkTag(inlineTag.text()));
				}
			}
		}

		// @prerequisite
		for (Tag tag : p_method.tags(TagName.TAG_PREREQUISITE)) {
			LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());
			testcase.setPrerequisite(tag.text());
		}

		// @input
		for (Tag tag : p_method.tags(TagName.TAG_INPUT)) {
			LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());
			testcase.setInput(tag.text());
		}

		// @result
		for (Tag tag : p_method.tags(TagName.TAG_RESULT)) {
			LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());
			testcase.setResult(tag.text());
		}

		return testcase;
	}

	// ------------------------- public methods -------------------------

	/**
	 * Returns true if the method is annotated with @Test
	 * 
	 * @param p_method
	 *            The current method
	 * @return true if the method is annotated with @Test
	 */
	private boolean isTestMethod(MethodDoc p_method) {
		boolean testMethod = false;

		if (null != p_method.annotations()) {
			for (AnnotationDesc annotation : p_method.annotations()) {
				LOGGER.debug("Processing annotation {} of {}", annotation,
						p_method.name());
				if ("Test".equals(annotation.annotationType().simpleTypeName())) {
					testMethod = true;
				}
			}
		}

		return testMethod;
	}

	/**
	 * Extract from the Link tag text the name of a field.
	 * 
	 * @param p_Text
	 *            The text of a link tag.
	 * @return The name of the field.
	 */
	private String extractFieldNameFromLinkTag(String p_Text) {
		String name = null;
		int index = 0;

		index = p_Text.indexOf('#');
		name = p_Text.substring(index + 1, p_Text.length());

		return name;
	}
}
