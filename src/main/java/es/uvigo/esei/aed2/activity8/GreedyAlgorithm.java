package es.uvigo.esei.aed2.activity8;
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
import java.util.List;
import java.util.Set;
import java.util.LinkedList;

import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.AdjacencyList;

import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;

public class GreedyAlgorithm {

  // Exercise 1
  public static <T> Graph<T, Integer> traveller(Graph<T, Integer> graph, Vertex<T> vertex) {

    return null;
  }

  // Exercise 2
  public static <T> Graph<T, Integer> prim(Graph<T, Integer> graph, Vertex<T> vertex) {

    return null;
  }

  // Exercise 3
  public static <T> Map<Vertex<T>, Integer> dijkstra(Graph<T, Integer> graph, Vertex<T> vertex) {

    final Integer INFINITO = Integer.MAX_VALUE;

    Set<Vertex<T>> porVisitar = new HashSet<>();
    Map<Vertex<T>, Integer> distancias = new HashMap<>();

    for (Vertex<T> v : graph.getVertices()) {
      porVisitar.add(v);
      distancias.add(v, INFINITO);

    }

    // el vértice por el que empezamos debe tener distancia 0
    distancias.add(vertex, 0);

    while (!porVisitar.isEmpty()) {
      Vertex<T> vMinimo = minimo(porVisitar, distancias);
      porVisitar.remove(vMinimo);
      Integer dist_vMinimo = distancias.get(vMinimo);

      for (Edge<T, Integer> arco : graph.getEdges()) {
        if (arco.getSource().equals(vMinimo) && porVisitar.contains(arco.getTarget())) {

          Integer dist_arco = arco.getLabel();

          Integer distTotal = dist_arco + dist_vMinimo;
          if (distTotal < distancias.get(arco.getTarget())) {
            distancias.add(arco.getTarget(), distTotal);
          }

        }
      }

    }

    return distancias;

  }

  // método privado para buscar el vértice con distancia mínima

  private static <T> Vertex<T> minimo(Set<Vertex<T>> porVisitar, Map<Vertex<T>, Integer> distancias) {

    Integer minD = Integer.MAX_VALUE;

    Vertex<T> minv = null;

    for (Vertex<T> vertex : porVisitar) {
      Integer distancia_v = distancias.get(vertex);

      if (distancia_v < minD) {
        minD = distancia_v;
        minv = vertex;
      }

    }

    return minv;

  }

  // Exercise 4
  public static <T> Map<Vertex<T>, String> colourMap(Graph<T, Integer> graph, String[] colours) {

    return null;
  }

  // Exercise 5
  public static Map<Integer, Integer> giveChange(int amountReturned, Map<Integer, Integer> changeAvailable) {

    return null;
  }

  // Exercise 6
  public static Set<String> burnCD(int maximumCapacity, Map<String, Integer> espacePrograms) {

    return null;
  }

  // Exercise 7
  public static Map<String, Integer> fillRucksack(
      int maxVolume, Map<String, Integer> amounts, Map<String, Integer> volumes) {

    return null;
  }

  // Exercise 8
  public static Map<Vertex<String>, String> examSchedule(Graph<String, Integer> graph, String[] daysWeek) {

    return null;
  }

  // Exercise 9
  public static Set<String> plannerActivities(List<Activity> listActivities) {

    return null;
  }

}
