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

import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.RootDoc;

import com.github.testplandoclet.log.Log;

/**
 * Handles the TestPlanDoclets configuration properties.
 * @author Julien Giovaresco
 */
public class Configuration
{
   // ------------------------- private constants -------------------------

   // -debug option

   /** The option name to activate debug logging. */
   private final static String OPTION_DEBUG = "-debug";

   /** The length of the option debug. */
   private final static int OPTION_LENGTH_DEBUG = 1;

   // -filename option

   /** The option name to set the filename of the test plan. */
   private final static String OPTION_FILENAME = "-file";

   /** The length of the filename option. */
   private final static int OPTION_LENGTH_FILENAME = 2;

   // -template option

   /** The option name to set the Velocity template to use for the test plan. */
   private final static String OPTION_TEMPLATE = "-template";

   /** The length of the template option. */
   private final static int OPTION_LENGTH_TEMPLATE = 2;

   // -application option

   /** The option name to set the application name. */
   private final static String OPTION_APPLICATION = "-application";

   /** The length of the application option. */
   private final static int OPTION_LENGTH_APPLICATION = 2;

   // --- defaults values

   /** The default filename. */
   private final static String DEFAULT_FILE = "testplan.html";

   // ------------------------- private members-------------------------

   /** The flag to activate / deactivate debug output. */
   private static boolean s_debugEnabled = false;

   /** The filename of the test plan. */
   private static String s_fileName = DEFAULT_FILE;

   /** The Velocity's template filename. */
   private static String s_templateFileName = null;

   /** The application's name. */
   private static String s_applicationName = null;

   // ------------------------- public methods -------------------------

   /**
    * Initializes the configuration by processing the input (commandline) options.
    * @param p_root The javadoc root object.
    */
   public static void start(RootDoc p_root) throws Exception
   {
      processOptions(p_root.options());

      Log.initLog4j(s_debugEnabled);
   }

   /**
    * Returns the length of the option passed in parameter.
    * @param p_option The option
    * @return The length of the option passed in parameter.
    * @see com.sun.javadoc.Doclet#optionLength(java.lang.String)
    */
   public static int optionLength(String p_option)
   {
      int optionLength = 0;

      if (OPTION_DEBUG.equals(p_option))
      {
         optionLength = OPTION_LENGTH_DEBUG;
      }
      else if (OPTION_FILENAME.equals(p_option))
      {
         optionLength = OPTION_LENGTH_FILENAME;
      }
      else if (OPTION_TEMPLATE.equals(p_option))
      {
         optionLength = OPTION_LENGTH_TEMPLATE;
      }
      else if (OPTION_APPLICATION.equals(p_option))
      {
         optionLength = OPTION_LENGTH_APPLICATION;
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
    * @param p_options the commandline options.
    * @param p_reporter The {@link DocErrorReporter}.
    * @return true if all options are valid.
    * @see com.sun.javadoc.Doclet#validOptions(String[][], DocErrorReporter)
    */
   public static boolean validOptions(String p_options[][], DocErrorReporter p_reporter)
   {
      boolean errorFound = false;
      boolean foundFileOption = false;
      boolean foundTemplateOption = false;
      boolean foundApplicationOption = false;
      int i = 0;

      while (!errorFound && i < p_options.length)
      {
         String[] opt = p_options[i];
         if (opt[0].equals(OPTION_FILENAME))
         {
            if (foundFileOption)
            {
               errorFound = true;
               p_reporter.printError("Only one -file option allowed.");
            }
            else
            {
               foundFileOption = true;
            }
         }
         else if (opt[0].equals(OPTION_TEMPLATE))
         {
            if (foundTemplateOption)
            {
               errorFound = true;
               p_reporter.printError("Only one -template option allowed.");
            }
            else
            {
               foundTemplateOption = true;
            }
         }
         else if (opt[0].equals(OPTION_APPLICATION))
         {
            foundApplicationOption = true;
         }
         i++;
      }

      if (!errorFound && !foundApplicationOption)
      {
         errorFound = true;
         p_reporter.printError("-application option is mandatory.");
      }

      if (errorFound)
      {
         p_reporter.printError("Usage: javadoc -application name [-template my_template.vm] [-file my_filename] -doclet HtmlTestPlan ...");
      }
      return !errorFound;
   }

   /**
    * Returns the String containing the options of the doclet.
    * @return The String containing the options of the doclet.
    */
   public static String getOptionsString()
   {
      StringBuilder sb = new StringBuilder();

      sb.append("Options : ");
      if (true == s_debugEnabled)
      {
         sb.append("-debug ");
      }
      if (null != s_fileName)
      {
         sb.append("-file ").append(s_fileName).append(' ');
      }
      if (null != s_templateFileName)
      {
         sb.append("-template ").append(s_templateFileName).append(' ');
      }
      if (null != s_applicationName)
      {
         sb.append("-application ").append(s_applicationName).append(' ');
      }

      return sb.toString();
   }

   // ------------------------- private methods -------------------------

   /**
    * Processes the input options passed to the doclet.
    * @param p_options the input options to process.
    */
   private static void processOptions(String[][] p_options)
   {
      for (int i = 0; i < p_options.length; i++)
      {
         if ((p_options[i][0].equals(OPTION_DEBUG)))
         {
            s_debugEnabled = true;
         }
         else if ((p_options[i][0].equals(OPTION_FILENAME)))
         {
            s_fileName = p_options[i][1];
         }
         else if ((p_options[i][0].equals(OPTION_TEMPLATE)))
         {
            s_templateFileName = p_options[i][1];
         }
         else if ((p_options[i][0].equals(OPTION_APPLICATION)))
         {
            s_applicationName = p_options[i][1];
         }
      }
   }

   // ------------------------- public accessors-------------------------

   /**
    * Returns the filename.
    * @return String The filename.
    */
   public static String getFileName()
   {
      return s_fileName;
   }

   /**
    * Returns the template's filename.
    * @return The template's filename.
    * @see Configuration#s_templateFileName
    */
   public static String getTemplateFileName()
   {
      return s_templateFileName;
   }

   /**
    * Returns the application's name.
    * @return The application's name.
    * @see Configuration#s_applicationName
    */
   public static String getApplicationName()
   {
      return s_applicationName;
   }
}
