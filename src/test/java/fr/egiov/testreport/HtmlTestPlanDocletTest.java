package fr.egiov.testreport;

import org.junit.Test;

import com.sun.tools.javadoc.Main;

import fr.egiov.testplandoclet.HtmlTestPlanDoclet;

/**
 * Unit test for simple App.
 */
public class HtmlTestPlanDocletTest
{
   @Test
   public void test()
   {
      final String fileName = "html-doc.html";

      // String[] args = new String[] { "-sourcepath", "./sample/src/it/java", "-subpackages", "fr",
      // "-private" };
      String[] args = new String[] { "-sourcepath", "./sample/src/it/java", "-subpackages", "fr", "fr", "-debug"};

      Main.execute("HtmlTestPlanDoclet", HtmlTestPlanDoclet.class.getName(), args);
   }
}
