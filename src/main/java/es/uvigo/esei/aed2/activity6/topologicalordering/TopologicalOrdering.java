package es.uvigo.esei.aed2.activity6.topologicalordering;

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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;

public class TopologicalOrdering {
  /**
   * Calcula el orden topológico de un grafo dirigido acíclico (DAG).
   * 
   * @param graph grafo dirigido acíclico.
   * @return lista de vértices en orden topológico.
   * @throws IllegalArgumentException si el grafo contiene un ciclo.
   */
  public static <T, E> List<Vertex<T>> getTopologicalOrder(Graph<T, E> graph) {

    List<Vertex<T>> result = new ArrayList<>();

    Map<Vertex<T>, Integer> grados = new HashMap<>();

    for (Vertex<T> vertex : graph.getVertices()) {
      grados.add(vertex, 0);

    }

    for (Vertex<T> v : graph.getVertices()) {
      for (Vertex<T> adj : graph.getAdjacentsVertex(v)) {
        int grado = grados.get(adj);
        grados.add(adj, grado + 1);
      }
    }

    Queue<Vertex<T>> queue = new ArrayDeque<>();

    for (Vertex<T> vertex : grados.getKeys()) {
      if (grados.get(vertex) == 0) {
        queue.add(vertex);
      }
    }

    while (!queue.isEmpty()) {
      Vertex<T> del = queue.remove();
      result.add(del);

      for (Vertex<T> adj : graph.getAdjacentsVertex(del)) {
        int grade = grados.get(adj) - 1;

        grados.add(adj, grade);

        if (grade == 0) {
          queue.add(adj);
        }

      }

    }
    if (result.size() != graph.numberOfVertices()) {
      throw new IllegalArgumentException("El grafo contiene un ciclo");
    }

    return result;
  }

}
