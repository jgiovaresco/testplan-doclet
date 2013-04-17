package fr.egiov.testplandoclet.html;

import fr.egiov.testplandoclet.plan.TestPlan;

/**
 * Defines the interface for the generator of test plan in HTML.
 * 
 * @author Julien Giovaresco
 */
public interface TestPlanHtmlGenerator
{
   /**
    * Generates the test plan in a html file.
    * @param p_applicationName The name of the application.
    * @param p_testplan The test plan to generate.
    */
   void generate(String p_applicationName, TestPlan p_testplan);
}
