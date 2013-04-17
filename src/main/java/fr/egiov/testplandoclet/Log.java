package fr.egiov.testplandoclet;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 */
public class Log
{

   /**
    * Initializes the log4j facility.
    */
   public static void initLog4j(boolean debug)
   {
      // clean away the old logfile
      File logFile = new File("./testplan-doclet.log");
      logFile.delete();

      Properties props = new Properties();

      // appender for console
      props.setProperty("log4j.appender.stout", "org.apache.log4j.ConsoleAppender");
      props.setProperty("log4j.appender.stout.layout", "org.apache.log4j.PatternLayout");
      props.setProperty("log4j.appender.stout.layout.ConversionPattern", "%5p %C{1}:%M() - %m%n");

      // appender for logfile
      props.setProperty("log4j.appender.logfile", "org.apache.log4j.DailyRollingFileAppender");
      props.setProperty("log4j.appender.logfile.File", "./doclet.log");
      props.setProperty("log4j.appender.logfile.DatePattern", "yyyy-MM-dd");
      props.setProperty("log4j.appender.logfile.layout", "org.apache.log4j.PatternLayout");
      props.setProperty("log4j.appender.logfile.layout.ConversionPattern",
         "%d{yyyy-MM-dd HH:mm:ss.SSS} %5p %C{1}:%M() - %m%n");

      if (debug)
      {
         props.setProperty("log4j.rootLogger", "DEBUG, logfile, stout");
         // props.setProperty("log4j.appender.stout.Threshold", "INFO");
      }
      else
      {
         props.setProperty("log4j.rootLogger", "FATAL, stout");
         props.setProperty("log4j.appender.stout.Threshold", "FATAL");
      }

      PropertyConfigurator.configure(props);
   }

}
