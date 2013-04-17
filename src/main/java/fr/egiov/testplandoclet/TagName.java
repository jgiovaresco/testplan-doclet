package fr.egiov.testplandoclet;

/**
 * Defines the specifics tags name of the doclet.
 * 
 * @author Julien Giovaresco
 */
public final class TagName {
	// ------------------------- public constants -------------------------

	/** Tag defining the service tested by the test case. */
	public static final String TAG_SERVICE = "service";

	/** Tag defining the title of the test case. */
	public static final String TAG_TITLE = "title";

	/** Tag defining the requirement covered by the test case. */
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
	private TagName() {
		// NTD
	}

}
