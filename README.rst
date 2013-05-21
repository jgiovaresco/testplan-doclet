.. -*- coding: utf-8 -*-

.. contents::
.. sectnum::

===============
README
===============

Description
-----------

**testplan-doclet** is a Javadoc doclet which allows you to generate a test plan from the Javadoc of your JUnit test classes.

The doclet contains a generic template for your test plan. However you still have the possibility to create your own template.

Maintainer
----------

[Julien Giovaresco] (https://github.com/jgiovaresco)

Limitations
-----------

Currently, 

* the test plan is in HTML only with limited css styles.
* the default velocity template is in french
  
===============
Documentation
===============

Usage
-----

1. In Maven

::

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
      <doclet>com.github.testplandoclet.HtmlTestPlanDoclet</doclet>
      <docletArtifact>
        <groupId>com.github</groupId>
        <artifactId>testplan-doclet</artifactId>
        <version>0.0.1-SNAPSHOT</version>
      </docletArtifact>
      <useStandardDocletOptions>false</useStandardDocletOptions>
      <additionalparam>-debug -file sample_testplan.html</additionalparam>
    </configuration>
  </plugin>

2. With commandline

::

  javadoc -doclet org.github.testplandoclet.HtmlTestPlanDoclet -docletpath /path/to/doclet/testplan-doclet-0.0.1-SNAPSHOT-jar-with-dependencies.jar -classpath /path/to/junit/junit-4.10.jar -debug -file target/toto.html -sourcepath src/it/java/ -subpackages fr

Description of the specific options
-----------------------------------

The options availables for the doclet are 

* \-application : used to the the application name in the test plan.
* \-debug       : used to activate the debug logging (logs are present in the file *testplan-doclet.log*)
* \-file        : used to set the file name of the test plan. This parameter is optional, if it isn't there the doclet generate a file *testplan.html*.
* \-template    : used to set the Velocity's template to use.

Description of tags
-------------------

+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
|      Tag      | Cardinality |                            Location                           |            Description                                          |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @service      | 1           | in the javadoc of a test class                                | used to define the service tested by this class                 |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @service      | 1           | in the javadoc of a constant in a class defining requirements | used to define the service covered by the requirement           |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @title        | 1           | in the javadoc of a test method (annoted with @Test)          | used to define the title of the test case                       |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @requirement  | \*          | in the javadoc of a test method (annoted with @Test)          | used to define the list of requirement covered by the test case |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @requirement  | 1           | in the javadoc of a constant in a class defining requirements | used to define a requirement                                    |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @prerequisite | 1           | in the javadoc of a test method (annoted with @Test)          | used to define the prerequisite of the test case                |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @input        | 1           | in the javadoc of a test method (annoted with @Test)          | used to define the input data of the test case                  |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+
| @result       | 1           | in the javadoc of a test method (annoted with @Test)          | used to define the expected result of the test case             |
+---------------+-------------+---------------------------------------------------------------+-----------------------------------------------------------------+

