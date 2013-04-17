package fr.egiov.testplandoclet;

import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.RootDoc;

import fr.egiov.testplandoclet.log.Log;

/**
 * Handles the TestPlanDoclets configuration properties.
 * 
 * @author Julien Giovaresco
 */
public class Configuration {
	// ------------------------- private constants -------------------------

	/** The option name to activate debug logging. */
	private final static String OPTION_DEBUG = "-debug";

	/** The length of the option debug. */
	private final static int OPTION_LENGTH_DEBUG = 1;

	/** The option name to set the filename of the test plan. */
	private final static String OPTION_FILENAME = "-file";

	/** The length of the option debug. */
	private final static int OPTION_LENGTH_FILENAME = 2;

	// --- defaults values

	/** The default filename. */
	private final static String DEFAULT_FILE = "testplan.html";

	// ------------------------- private members-------------------------

	/** The flag to activate / deactivate debug output. */
	private static boolean s_debugEnabled = false;

	/** The filename of the test plan. */
	private static String s_fileName = DEFAULT_FILE;

	// ------------------------- public methods -------------------------

	/**
	 * Initializes the configuration by processing the input (commandline)
	 * options.
	 * 
	 * @param p_root
	 *            The javadoc root object.
	 */
	public static void start(RootDoc p_root) throws Exception {
		processOptions(p_root.options());

		Log.initLog4j(s_debugEnabled);
	}

	/**
	 * Returns the length of the option passed in parameter.
	 * 
	 * @param p_option
	 *            The option
	 * @return The length of the option passed in parameter.
	 * @see com.sun.javadoc.Doclet#optionLength(java.lang.String)
	 */
	public static int optionLength(String p_option) {
		int optionLength = 0;

		if (OPTION_DEBUG.equals(p_option)) {
			optionLength = OPTION_LENGTH_DEBUG;
		} else if (OPTION_FILENAME.equals(p_option)) {
			optionLength = OPTION_LENGTH_FILENAME;
		}
		return optionLength;
	}

	/**
	 * Validates the options.
	 * <p>
	 * <ul>
	 * <li>Option {@link Configuration#OPTION_FILENAME} : only one allowed.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param p_options
	 *            the commandline options.
	 * @param p_reporter
	 *            The {@link DocErrorReporter}.
	 * @return true if all options are valid.
	 * @see com.sun.javadoc.Doclet#validOptions(String[][], DocErrorReporter)
	 */
	public static boolean validOptions(String p_options[][],
			DocErrorReporter p_reporter) {
		boolean foundFileOption = false;

		for (int i = 0; i < p_options.length; i++) {
			String[] opt = p_options[i];
			if (opt[0].equals(OPTION_FILENAME)) {
				if (foundFileOption) {
					p_reporter.printError("Only one -file option allowed.");
					return false;
				} else {
					foundFileOption = true;
				}
			}
		}
		if (!foundFileOption) {
			p_reporter
					.printError("Usage: javadoc -file my_filename -doclet HtmlTestPlan ...");
		}
		return foundFileOption;
	}

	// ------------------------- private methods -------------------------

	/**
	 * Processes the input options passed to the doclet.
	 * 
	 * @param p_options
	 *            the input options to process.
	 */
	private static void processOptions(String[][] p_options) {
		for (int i = 0; i < p_options.length; i++) {
			if ((p_options[i][0].equals(OPTION_DEBUG))) {
				s_debugEnabled = true;
			} else if ((p_options[i][0].equals(OPTION_FILENAME))) {
				s_fileName = p_options[i][1];
			}
		}
	}

	// ------------------------- public accessors-------------------------

	/**
	 * Returns the filename.
	 * 
	 * @return String The filename.
	 */
	public static String getFileName() {
		return s_fileName;
	}
}
