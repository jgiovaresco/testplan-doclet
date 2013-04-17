package fr.egiov.testplandoclet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;

import fr.egiov.testplandoclet.html.TestPlanHtmlGenerator;
import fr.egiov.testplandoclet.html.TestPlanHtmlGeneratorImpl;
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
	 * Main doclet method.
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

		String serviceTested = null;
		TestCase testcase = null;

		LOGGER.debug("Processing class {}", p_clazz);

		if (p_clazz.name().endsWith("IT")) {
			for (Tag tag : p_clazz.tags(TagName.TAG_SERVICE)) {
				LOGGER.debug("Processing tag {} : {}", tag.name(), tag.text());
				serviceTested = tag.text();
			}
		}

		// iterate over all methods and print their names.
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
			testcase.getRequirements().add(tag.text());
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
}
