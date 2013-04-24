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
package com.github.testplandoclet.log;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * Defines methods to handle log in the doclet.
 * 
 * @author Julien Giovaresco
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
