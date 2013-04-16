package fr.egiov.simple;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.log.SimpleLog4JLogSystem;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

public class VelocityHelloWorldTest
{
   @Test
   public void test() throws Exception
   {
      /*  first, get and initialize an engine  */
      VelocityEngine ve = new VelocityEngine();
      ve.setProperty(Velocity.RESOURCE_LOADER, "classpath");
      ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//      ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "./templates/");
      ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, Log4JLogChute.class.getName());
      
      ve.init();
      /*  next, get the Template  */
//      Template t = ve.getTemplate("src/test/resources/helloworld.vm");
      Template t = ve.getTemplate("templates/helloworld.vm");
//      Template t = ve.getTemplate("helloworld.vm");
      /*  create a context and add data */
      VelocityContext context = new VelocityContext();
      context.put("name", "World");
      /* now render the template into a StringWriter */
      StringWriter writer = new StringWriter();
      t.merge( context, writer );
      /* show the World */
      System.out.println( writer.toString() );   
   }
}
