package fr.egiov.testplandoclet.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.egiov.testplandoclet.HtmlTestPlanDoclet;

/**
 * Defines the test plan.
 * 
 * @author Julien Giovaresco
 */
public class TestPlan
{
   // ------------------------- private contantes -------------------------

   /** The logger. */
   private final static Logger LOGGER = LoggerFactory.getLogger(HtmlTestPlanDoclet.class);

   // ------------------------- private members -------------------------

   /** The test cases of the test plan. */
   private Map<String, Map<String, TestCase>> m_plan;

   // ------------------------- constructors -------------------------

   /**
    * Empty constructor.
    */
   public TestPlan()
   {
      m_plan = new TreeMap<String, Map<String, TestCase>>();
   }

   // ------------------------- public methods -------------------------

   /**
    * Adds a testcase to the testplan.
    * @param p_testcase The testcase to add.
    */
   public void add(TestCase p_testcase)
   {
      String testedService = null;
      String testcaseName = null;

      Map<String, TestCase> testcases = null;

      testedService = p_testcase.getTestedService();
      testcases = m_plan.get(testedService);

      if (null == testcases)
      {
         LOGGER.debug("No testcase for the service {}", testedService);
         testcases = new TreeMap<String, TestCase>();
         m_plan.put(testedService, testcases);
      }

      testcaseName = p_testcase.getName();

      LOGGER.debug("Adding testcase {} for the service {} to the test plan", testcaseName, testedService);
      testcases.put(testcaseName, p_testcase);
   }

   /**
    * Returns the list of tested services.
    * @return The list of tested services.
    */
   public Collection<String> getTestedServices()
   {
      return Collections.unmodifiableSet(m_plan.keySet());
   }

   /**
    * Return testcases of a service.
    * @param p_service The service
    * @return The testcases of a service.
    */
   public List<TestCase> getTestCases(String p_service)
   {
      List<TestCase> testcases = null;
      Map<String, TestCase> serviceTestCases = null;

      testcases = new ArrayList<TestCase>();
      serviceTestCases = m_plan.get(p_service);

      LOGGER.debug("Gets testcases for the service {}", p_service);
      for (Map.Entry<String, TestCase> testcase : serviceTestCases.entrySet())
      {
         testcases.add(testcase.getValue());
      }

      return testcases;
   }
}
