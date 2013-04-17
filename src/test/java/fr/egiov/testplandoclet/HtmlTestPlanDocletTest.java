package fr.egiov.testplandoclet;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import com.sun.tools.javadoc.Main;

/**
 * Tests for {@link HtmlTestPlanDoclet}.
 */
public class HtmlTestPlanDocletTest {

	// ------------------------- rules -------------------------

	// ------------------------- test methods-------------------------

	@Test
	public void testKO() {
		StringWriter err = new StringWriter();
		StringWriter warn = new StringWriter();
		StringWriter notice = new StringWriter();

		String[] args = new String[] { "-sourcepath", "./sample/src/it/java",
				"-subpackages", "fr", "fr" };

		Main.execute("HtmlTestPlanDoclet", new PrintWriter(err),
				new PrintWriter(warn), new PrintWriter(notice),
				HtmlTestPlanDoclet.class.getName(), args);

		Assert.assertEquals(
				"HtmlTestPlanDoclet: error - Usage: javadoc -file my_filename -doclet HtmlTestPlan ...\n",
				err.toString());
	}

	@Test
	public void test() {
		final String fileName = "target/html-doc.html";

		// String[] args = new String[] { "-sourcepath", "./sample/src/it/java",
		// "-subpackages", "fr",
		// "-private" };
		String[] args = new String[] { "-debug", "-file", fileName,
				"-sourcepath", "./sample/src/it/java", "-subpackages", "fr",
				"fr" };

		Main.execute("HtmlTestPlanDoclet", HtmlTestPlanDoclet.class.getName(),
				args);
	}
}
