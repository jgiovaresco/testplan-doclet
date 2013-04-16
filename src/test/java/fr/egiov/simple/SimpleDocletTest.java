package fr.egiov.simple;

import org.junit.Test;

import com.sun.tools.javadoc.Main;

import fr.egiov.simple.SimpleDoclet;

/**
 * Unit test for simple App.
 */
public class SimpleDocletTest {
	@Test
	public void test() {
		final String fileName = "simple-doc.html";

		String[] args = new String[] { "-sourcepath", "./sample/src/it/java", "-subpackages", "fr" };

		Main.execute("SimpleDoclet", SimpleDoclet.class.getName(), args);
	}
}
