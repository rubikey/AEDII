package es.uvigo.esei.aed2.activity6.anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import es.uvigo.esei.aed2.map.Map;
import es.uvigo.esei.aed2.map.HashMap;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

public class Anagrams {

  public static Set<Set<String>> getAnagrams(List<String> words) {
    // TODO: Implementa la obtención de los conjuntos de anagramas a partir de la
    // agrupación

    Map<String, List<String>> anagrams = new HashMap<>();

    for (String word : words) {
      // transformar cada palabra en array de chars
      char[] chars = word.toCharArray();

      // ordenar alfabéticamente las palabras
      Arrays.sort(chars);

      String sortedKey = new String(chars); // sacar anagramas

      List<String> currentList = anagrams.get(sortedKey);

      // si la lista es null, no existe el anagrama en el mapa
      if (currentList == null) {

        // crear una nueva lista
        List<String> newList = new ArrayList<>();

        // añadir la palabra a la lista
        newList.add(word);

        // añadir el par clave valor
        anagrams.add(sortedKey, newList);

      }

      else {
        // si la clave ya existe, cogemos la lista y añadimos la palabra

        currentList.add(word);

      }
    }

    // conjunto a devolver
    Set<Set<String>> result = new HashSet<>();

    // iterador sobre los valores de cada lista
    Iterator<List<String>> iterator = anagrams.getValues();


    while (iterator.hasNext()) {
      List<String> anagramsList = iterator.next();

      // añadir al conjunto, un conjunto de listas String
      result.add(new HashSet<>(anagramsList));
    }

    return result;

  }

}
