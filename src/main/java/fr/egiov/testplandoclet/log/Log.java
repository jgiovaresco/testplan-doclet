package fr.egiov.testplandoclet.log;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * Defines methods to handle log in the doclet.
 */
public class Log {
	// ------------------------- private constants -------------------------

	/** The name of the log file for the doclet. */
	private static final String DOCLET_LOG_FILENAME = "./testplan-doclet.log";
	
	// ------------------------- public methods-------------------------

	/**
	 * Initializes the log4j facility.
	 * 
	 * @param p_isDebug
	 *            The debug flag indicating if the log must be configured with
	 *            the DEBUG level.
	 */
	public static void initLog4j(boolean p_isDebug) {
		// clean away the old logfile
		File logFile = null;
		
		logFile = new File(DOCLET_LOG_FILENAME);
		logFile.delete();
		

		Properties props = new Properties();

		// appender for console
		props.setProperty("log4j.appender.stout",
				"org.apache.log4j.ConsoleAppender");
		props.setProperty("log4j.appender.stout.layout",
				"org.apache.log4j.PatternLayout");
		props.setProperty("log4j.appender.stout.layout.ConversionPattern",
				"%5p %C{1}:%M() - %m%n");

		// appender for logfile
		props.setProperty("log4j.appender.logfile",
				"org.apache.log4j.DailyRollingFileAppender");
		props.setProperty("log4j.appender.logfile.File", DOCLET_LOG_FILENAME);
		props.setProperty("log4j.appender.logfile.DatePattern", "yyyy-MM-dd");
		props.setProperty("log4j.appender.logfile.layout",
				"org.apache.log4j.PatternLayout");
		props.setProperty("log4j.appender.logfile.layout.ConversionPattern",
				"%d{yyyy-MM-dd HH:mm:ss.SSS} %5p %C{1}:%M() - %m%n");

		if (p_isDebug) {
			props.setProperty("log4j.rootLogger", "DEBUG, logfile, stout");
			props.setProperty("log4j.appender.stout.Threshold", "INFO");
		} else {
			props.setProperty("log4j.rootLogger", "FATAL, stout");
			props.setProperty("log4j.appender.stout.Threshold", "FATAL");
		}

		PropertyConfigurator.configure(props);
	}
}
