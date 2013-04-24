package fr.egiov.testreport.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests AutreServiceIT
 * @domain DTS
 * @service AutreService
 */
public class AutreService1IT {
	// ------------------------- Constantes private -------------------------

	// ------------------------- Membres private -------------------------

	/**
	 * Initialise l'environnement
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Nettoie l'environnement apres chaque test.
	 */
	@After
	public void tearDown() {
	}

	// ------------------------- Methodes de tests -------------------------

	/**
	 * @title cas 1 pour autre service.
	 * 
	 * @requirement {@link ExigencesFonctionnelles#RQ_00800} 
	 * 
	 * @prerequisite Le service est déployé
	 * 
	 * @input La requête est {@code 
	 * <REQUETE>
	 * 	<ID>monId</ID>
	 * 	<TYPE>monType</TYPE>
	 * 	<VALUE>value</VALUE>
	 *</REQUETE>}
	 * 
	 * @result La réponse est OK.
	 */
	@Test
	public void TA_DTS_15050() throws Exception {
	}

	/**
	 * @title Acquisition des associations code/libellé cas 2
	 * 
	 * @requirement {@link ExigencesFonctionnelles#RQ_00801} 
	 * 
	 * @prerequisite Le service est déployé.
	 * 
	 * @input <ul>
	 *        <li>att : val</li>
	 *        <li>att1 : val1</li>
	 *        </ul>
	 * 
	 * @result <ul>
	 *         <li>Pas d'erreur</li>
	 *         <li>le résultat attendu.</li>
	 *         </ul>
	 */
	@Test
	public void TA_DTS_15051() throws Exception {
	}
}
