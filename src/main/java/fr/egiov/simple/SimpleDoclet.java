package fr.egiov.simple;

import java.text.MessageFormat;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;

/**
 */
public class SimpleDoclet extends Doclet {

	private static MessageFormat METHODINFO = new MessageFormat(
			"Method: return type {0}, name = {1};");
	private static MessageFormat FIELDINFO = new MessageFormat(
			"Field: name = {0}, comment = {1}, type = {2};");

	public static boolean start(RootDoc root) {
		// iterate over all classes.
		ClassDoc[] classes = root.classes();
		if (null != classes) {

			for (ClassDoc classe : classes) {
				out("-------");
				out("Classes : " + classe.name());
				out("comments : " + classe.commentText());
				for (Tag tag : classe.tags("author")) {
					out("author : " + tag.text());
				}
				for (Tag tag : classe.tags("test")) {
					out("test : " + tag.text());
				}

				out("-------");

				// iterate over all annotations and print their names.
				if (null != classe.annotations()) {
					out("Annotation class");
					out("-------");
					for (AnnotationDesc annotation : classe.annotations()) {
						out("Annotation class: name = " + annotation.toString());
					}
				}

				// iterate over all methods and print their names.
				MethodDoc[] methods = classe.methods();
				out("Methods");
				out("-------");
				for (int j = 0; j < methods.length; j++) {
					out("Method: name = " + methods[j].name());
					
		          // iterate over all annotations and print their names.
	            if (null != methods[j].annotations()) {
	               for (AnnotationDesc annotation : methods[j].annotations()) {
	                  out("Annotation method: name = " + annotation.toString());
	               }
	               out("---");
	            }
				}
				out("-------");
				out("Fields");
				out("------");
				// iterate over all fields, printing name, comment text, and
				// type.
				FieldDoc[] fields = classe.fields();
				for (int j = 0; j < fields.length; j++) {
					Object[] field_info = { fields[j].name(),
							fields[j].commentText(), fields[j].type() };
					out(FIELDINFO.format(field_info));
					// iterate over all field tags and print their values.
					Tag[] tags = fields[j].tags();
					for (int k = 0; k < tags.length; k++) {
						out("\tField Tag Name= " + tags[k].name());
						out("\tField Tag Value = " + tags[k].text());
					}
				}
			}
		}
		// No error processing done, simply return true.
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see com.sun.javadoc.Doclet#optionLength(java.lang.String)
	 */
	public static LanguageVersion languageVersion() {
		return LanguageVersion.JAVA_1_5;
	}

	private static void out(String msg) {
		System.out.println(msg);
	}
}
