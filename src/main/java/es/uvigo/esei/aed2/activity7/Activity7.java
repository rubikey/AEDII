package es.uvigo.esei.aed2.activity7;

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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed1.tads.queue.LinkedQueue;
import es.uvigo.esei.aed1.tads.queue.Queue;

public class Activity7 {

  // exercise 1
  public static <T, E> Set<Vertex<T>> getPredecessors(Graph<T, E> graph, Vertex<T> vertex) {
    // TODO: Implementa la obtención de predecesores de un vértice en el grafo

    Set<Vertex<T>> predecessors = new HashSet<>();

    for (Vertex<T> v : graph.getVertices()) {
      for (Vertex<T> adj : graph.getAdjacentsVertex(v)) {
        if (adj.equals(vertex)) {
          predecessors.add(v);
        }
      }
    }

    return predecessors;
  }

  // exercise 2
  public static <T, E> boolean isRegular(Graph<T, E> graph) {
    // TODO: Implementa la comprobación de si el grafo es regular

    if (graph.isEmpty()) {
      return true;
    }

    Set<Vertex<T>> allVertex = graph.getVertices();

    Iterator<Vertex<T>> itr = allVertex.iterator();

    Vertex<T> first = itr.next();

    int ref = graph.getAdjacentsVertex(first).size();

    for (Vertex<T> vertex : allVertex) {
      if (graph.getAdjacentsVertex(vertex).size() != ref) {
        return false;
      }

    }

    return true;
  }

  // exercise 3
  public static <T, E> boolean isConnectedFromVertex(Graph<T, E> graph, Vertex<T> vertex) {
    // TODO: Implementa la comprobación de si el grafo es conexo desde un vértice

    if (graph.isEmpty()) {
      return false;
    }

    Set<Vertex<T>> porVisitar = graph.getVertices();

    Queue<Vertex<T>> toExplore = new LinkedQueue<>();
    toExplore.add(vertex);
    porVisitar.remove(vertex);

    while (!toExplore.isEmpty()) {
      Vertex<T> current = toExplore.remove();
      for (Vertex<T> adj : graph.getAdjacentsVertex(current)) {
        if (porVisitar.contains(adj)) {
          porVisitar.remove(adj);
          toExplore.add(adj);
        }

      }

    }

    if (porVisitar.isEmpty()) {
      return true;
    }

    return false;
  }

  // exercise 4
  public static <T, E> boolean thereIsPathBetweenVertices(Graph<T, E> graph, Vertex<T> source, Vertex<T> target) {
    // TODO: Implementa la comprobación de si existe un camino entre dos vértices

    if (source.equals(target)) {
      return true;
    }

    Set<Vertex<T>> visited = new HashSet<>();

    Queue<Vertex<T>> toExplore = new LinkedQueue<>();

    toExplore.add(source);
    visited.add(source);

    while (!toExplore.isEmpty()) {

      Vertex<T> current = toExplore.remove();

      for (Vertex<T> vertex : graph.getAdjacentsVertex(current)) {
        if (vertex.equals(target)) {
          return true;
        }

        if (!visited.contains(vertex)) {
          visited.add(vertex);
          toExplore.add(vertex);
        }
      }

    }

    return false;
  }

  // exercise 5
  public static <T, E> boolean isACycle(Graph<T, E> graph, List<Vertex<T>> path) {
    // TODO: Implementa la comprobación de si una lista de vértices forma un ciclo
    // en el grafo

    if (path.size() < 2) {
      return false;
    }

    Set<Vertex<T>> visited = new HashSet<>();

    if (!path.get(0).equals(path.get(path.size() - 1))) {
      return false; // no es ciclo si el primero no es igual al último
    }

    for (int i = 0; i < path.size() - 1; i++) {
      Set<Vertex<T>> adj = graph.getAdjacentsVertex(path.get(i));

      if (visited.contains(path.get(i))) {
        return false;
      }
      visited.add(path.get(i));

      if (!adj.contains(path.get(i + 1))) {
        return false;

      }

    }

    return true;
  }

  // exercise 6
  public static <T, E> int numberOfConnectedComponents(Graph<T, E> graph) {
    // TODO: Implementa el cálculo del número de componentes conexas en el grafo

    Queue<Vertex<T>> toExplore = new LinkedQueue<>();

    Set<Vertex<T>> porVisitar = new HashSet<>(graph.getVertices());

    int counter = 0;

    while (!porVisitar.isEmpty()) {
      Vertex<T> inicio = porVisitar.iterator().next();

      porVisitar.remove(inicio);
      toExplore.add(inicio);

      while (!toExplore.isEmpty()) {
        Vertex<T> current = toExplore.remove();

        for (Vertex<T> adj : graph.getAdjacentsVertex(current)) {
          if (porVisitar.contains(adj)) {
            toExplore.add(adj);
            porVisitar.remove(adj);

          }

        }

      }

      counter++;

    }

    return counter;
  }

  // exercise 7
  public static <T, E> Map<Vertex<T>, Integer> BFSShortestPath(Graph<T, E> graph, Vertex<T> start) {
    // TODO: Implementa el cálculo del número de componentes conexas en el grafo

    Map<Vertex<T>, Integer> components = new HashMap<>();

    Set<Vertex<T>> visited = new HashSet<>();

    Queue<Vertex<T>> toExplore = new LinkedQueue<>();

    toExplore.add(start);
    visited.add(start);
    components.add(start, 0);

    while (!toExplore.isEmpty()) {
      Vertex<T> current = toExplore.remove();
      int dist = components.get(current);

      for (Vertex<T> adj : graph.getAdjacentsVertex(current)) {
        if (!visited.contains(adj)) {
          toExplore.add(adj);
          visited.add(adj);
          components.add(adj, dist + 1);

        }
      }

    }

    return components;
  }
}
