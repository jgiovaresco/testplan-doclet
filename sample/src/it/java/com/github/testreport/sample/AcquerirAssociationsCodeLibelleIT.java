package com.github.testreport.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests AcquerirAssociationsCodeLibelleIT
 * @domain DTS
 * @service AcquerirAssociationsCodeLibelle
 */
public class AcquerirAssociationsCodeLibelleIT
{
   // ------------------------- Constantes private -------------------------


   // ------------------------- Membres private -------------------------


   /**
    * Initialise l'environnement
    */
   @Before
   public void setUp() throws Exception
   {
   }

   /**
    * Nettoie l'environnement apres chaque test.
    */
   @After
   public void tearDown()
   {
   }

   // ------------------------- Methodes de tests -------------------------

   /**
    * @title Acquisition des associations code/libellé cas 1
    * 
    * @requirement {@link ExigencesFonctionnelles#RQ_00700} 
    * @requirement {@link ExigencesFonctionnelles#RQ_00701} 
    * 
    * @prerequisite Le service est déployé.
    *   
    * @input Pas de paramètre en entrée
    * 
    * @result Toutes les associations code/libellé sont présentes dans la Vue Service out
    */
   @Test
   public void TA_DTS_14050() throws Exception
   {
   }
   
   /**
    * @title Acquisition des associations code/libellé cas 2
    * 
    * @prerequisite Le service est déployé.
    *   
    * @input <ul>
    * <li>att : val</li>
    * <li>att1 : val1</li>
    * </ul>
    * 
    * @result <ul>
    * <li>Pas d'erreur</li>
    * <li>le résultat attendu.</li>
    * </ul>
    */
   @Test
   public void TA_DTS_14051() throws Exception
   {
   }
}
