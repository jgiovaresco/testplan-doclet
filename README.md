testplan-doclet
===============

A doclet to generate a test plan from javadoc.

Maintainer
----------

[Julien Giovaresco] (https://github.com/jgiovaresco)

Limitations
-----------

Currently, 
 * the test plan is in HTML only without any css style.
 * the velocity template is in french
  

Usage
-----

1. In Maven 

		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-javadoc-plugin</artifactId>
			<version>2.9</version>
			<executions>
				<execution>
					<id>install</id>
					<phase>install</phase>
					<goals>
						<goal>test-javadoc</goal>
					</goals>
				</execution>
			</executions>
			<configuration>
				<doclet>fr.egiov.testplandoclet.HtmlTestPlanDoclet</doclet>
				<docletArtifact>
					<groupId>fr.egiov</groupId>
					<artifactId>testplan-doclet</artifactId>
					<version>0.0.1-SNAPSHOT</version>
				</docletArtifact>
				<useStandardDocletOptions>false</useStandardDocletOptions>
				<additionalparam>-debug -file sample_testplan.html</additionalparam>
			</configuration>
		</plugin>

2. With commandline

		javadoc -doclet fr.egiov.testplandoclet.HtmlTestPlanDoclet -docletpath /path/to/doclet/testplan-doclet-0.0.1-SNAPSHOT-jar-with-dependencies.jar -classpath /path/to/junit/junit-4.10.jar -debug -file target/toto.html -sourcepath src/it/java/ -subpackages fr
	
Description of tags
-------------------

<table>
	<tr>
		<th>Tag</th>
		<th>Cardinality</th>
		<th>Location</th>
		<th>Description</th>
	</tr>
	<tr>
		<td>@service</td>
		<td>1</td>
		<td>in the javadoc of a test class</td>
		<td>used to define the service tested by this class.</td>
	</tr>
	<tr>
		<td>@service</td>
		<td>1</td>
		<td>in the javadoc of a constant in a class defining requirements </td>
		<td>used to define the service covered by the requirement.</td>
	</tr>
	<tr>
		<td>@title</td>
		<td>1</td>
		<td>in the javadoc of a test method (annoted with @Test)</td>
		<td>used to define the title of the test case.</td>
	</tr>
	<tr>
		<td>@requirement</td>
		<td>*</td>
		<td>in the javadoc of a test method (annoted with @Test)</td>
		<td>used to define the list of requirement covered by the test case.</td>
	</tr>
	<tr>
		<td>@requirement</td>
		<td>*</td>
		<td>in the javadoc of a constant in a class defining requirements</td>
		<td>used to define a requirement.</td>
	</tr>
	<tr>
		<td>@prerequisite</td>
		<td>1</td>
		<td>in the javadoc of a test method (annoted with @Test)</td>
		<td>used to define the prerequisite of the test case.</td>
	</tr>
	<tr>
		<td>@input</td>
		<td>1</td>
		<td>in the javadoc of a test method (annoted with @Test)</td>
		<td>used to define the input data of the test case.</td>
	</tr>
	<tr>
		<td>@result</td>
		<td>1</td>
		<td>in the javadoc of a test method (annoted with @Test)</td>
		<td>used to define the expected result of the test case.</td>
	</tr>
</table>
