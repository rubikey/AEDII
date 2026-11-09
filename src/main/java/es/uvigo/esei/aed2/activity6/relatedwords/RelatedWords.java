package es.uvigo.esei.aed2.activity6.relatedwords;

import es.uvigo.esei.aed2.map.Map;
import es.uvigo.esei.aed2.map.HashMap;

import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.graph.AdjacencyList;

import java.util.ArrayList;
import java.util.Iterator;

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

import java.util.List;

import es.uvigo.esei.aed2.graph.Graph;

public class RelatedWords {
  /**
   * Construye un grafo que representa las relaciones entre las palabras.
   *
   * @param words palabras a relacionar.
   * @return grafo de palabras relacionadas.
   */
  public static Graph<String, Integer> buildGraph(List<String> words) {
    return mapToGraph(buildMap(words), words);
  }

  private static Map<String, List<String>> buildMap(List<String> palabras) {
    Map<String, List<String>> mapa = new HashMap<>();

    for (String palabra : palabras) {
      // cada clave sustituye una letra de la palabra con un guion
      for (int j = 0; j < palabra.length(); j++) {
        String clave = palabra.substring(0, j) + "_" + palabra.substring(j + 1);
        List<String> lista = mapa.get(clave);

        if (lista == null) {
          lista = new ArrayList<>();
          mapa.add(clave, lista);
        }
        lista.add(palabra);
      }
    }

    return mapa;
  }

  private static Graph<String, Integer> mapToGraph(Map<String, List<String>> mapa, List<String> palabras) {
    Graph<String, Integer> grafo = new AdjacencyList<>();

    // se incluyen todas las palabras, aunque no tengan ninguna relacionada
    for (String palabra : palabras) {
      grafo.addVertex(new Vertex<>(palabra));
    }

    Iterator<List<String>> listas = mapa.getValues();
    while (listas.hasNext()) {
      List<String> lista = listas.next();

      // conectar las palabras que comparten clave en ambos sentidoas
      for (int i = 0; i < lista.size(); i++) {
        for (int j = i + 1; j < lista.size(); j++) {
          Vertex<String> origen = new Vertex<>(lista.get(i));
          Vertex<String> destino = new Vertex<>(lista.get(j));

          if (!origen.equals(destino)) {
            grafo.addEdge(origen, destino, 0);
            grafo.addEdge(destino, origen, 0);
          }
        }
      }
    }

    return grafo;
  }
}
