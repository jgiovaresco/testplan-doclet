package fr.egiov.testplandoclet;

import java.io.File;

import com.sun.javadoc.RootDoc;

/**
 * Handles the PDFDoclet configuration properties.
 * @author Julien Giovaresco
 */
public class Configuration
{
   // ------------------------- private constants -------------------------
   
   private final static String ARG_DEBUG = "debug";
   
   // ------------------------- private members-------------------------

   /** The flag to activate / deactivate debug output. */
   private static boolean s_debugEnabled = false;
   
   /** The working directory */
   private static String workDir = null;

   // ------------------------- public methods -------------------------

   /**
    * Initializes the configuration by processing the input (commandline) options.
    * @param p_root The javadoc root object.
    */
   public static void start(RootDoc p_root) throws Exception
   {

      processOptions(p_root.options());

      boolean log4jInitialized = false;
      if(true == s_debugEnabled) {
          Util.initLog4j(true);
          log4jInitialized = true;
      }
      
      if(!log4jInitialized) {
          // by default, log on info level in console (file is always debug)
          Util.initLog4j(false);
      }

      workDir = "/tmp/testplan-doclet";

   }

   // ------------------------- private methods -------------------------

   /**
    * Processes the configuration Properties and sets internal values accordingly.
    */
   private static void processOptions(String[][] p_options)
   {
      for (int i = 0; i < p_options.length; i++)
      {
         if ((p_options[i][0].equals("-" + ARG_DEBUG)))
         {
            s_debugEnabled = true;
         }
      }
   }

   // ------------------------- public accessors-------------------------

   /**
    * Returns the current working directory used to resolve relative paths while looking for files,
    * like target document filename, config file, title file name.
    * @return String The working directory, WITHOUT a directory separating character at the end
    */
   public static String getWorkDir()
   {
      if (null == workDir)
      {
         workDir = ".";
      }
      if (workDir.endsWith(File.separator))
      {
         workDir = workDir.substring(0, workDir.length() - 1);
      }
      return workDir;
   }
}
