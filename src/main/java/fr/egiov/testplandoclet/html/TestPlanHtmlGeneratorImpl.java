package fr.egiov.testplandoclet.html;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import fr.egiov.testplandoclet.Configuration;
import fr.egiov.testplandoclet.plan.TestPlan;

public class TestPlanHtmlGeneratorImpl implements TestPlanHtmlGenerator
{
   // ------------------------- private constants -------------------------

   /** The directory containing the templates. */
   private static final String TEMPLATES_DIR = "templates/";

   // ------------------------- private members -------------------------

   // ------------------------- constructors -------------------------

   /**
    * Constructor for {@link TestPlanHtmlGeneratorImpl}.
    * <p>
    * Initialize the {@link VelocityEngine}. </>
    */
   public TestPlanHtmlGeneratorImpl()
   {
      Velocity.setProperty(Velocity.RESOURCE_LOADER, "classpath");
      Velocity
         .setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
      // ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "./templates/");
      Velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
         Log4JLogChute.class.getName());
      Velocity.setProperty(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER, "velocity");

      Velocity.init();
   }

   // ------------------------- public methods-------------------------

   /**
    * {@inheritDoc}
    * @see fr.egiov.testplandoclet.html.TestPlanHtmlGenerator#generate(java.lang.String,
    *      fr.egiov.testplandoclet.plan.TestPlan)
    */
   @Override
   public void generate(String p_applicationName, TestPlan p_testplan)
   {
      Template t = null;
      VelocityContext context = null;
      FileWriter writer = null;
      
      t = Velocity.getTemplate(TEMPLATES_DIR + "html_testplan.vm", "UTF-8");
      
      context = new VelocityContext();
      context.put("applicatioName", p_applicationName);
      context.put("testplan", p_testplan);

      try
      {
         writer = new FileWriter(Configuration.getFileName());
         t.merge( context, writer );
         writer.flush();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

}
