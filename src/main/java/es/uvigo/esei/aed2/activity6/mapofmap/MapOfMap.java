package es.uvigo.esei.aed2.activity6.mapofmap;

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

import es.uvigo.esei.aed2.activity6.HashMap.HashMap;
import es.uvigo.esei.aed2.graph.Edge;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.map.Map;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class MapOfMap<T, E> implements Graph<T, E> {

  private final Map<Vertex<T>, Map<Vertex<T>, E>> mapOfVertices;

  public MapOfMap() {
    mapOfVertices = new HashMap<>();
  }

  @Override
  public boolean isEmpty() {
    return mapOfVertices.size() == 0;
  }

  @Override
  public int numberOfVertices() {
    return this.mapOfVertices.size();

  }

  @Override
  public boolean containsVertex(Vertex<T> vertex) {
    if (vertex == null) {
      return false;
    }

    for (Vertex<T> v : mapOfVertices.getKeys()) {
      if (v.equals(vertex)) {
        return true;
      }

    }

    return false;

  }

  @Override
  public boolean containsEdge(Vertex<T> source, Vertex<T> target, E label) {

    if (source == null || target == null) {
      return false;

    }

    // mapa de adyacentes de Source
    Map<Vertex<T>, E> adjacentsMap = mapOfVertices.get(source);

    if (adjacentsMap == null) {
      return false;
    }

    if (adjacentsMap == null) {
      return false;
    }
    // recorremos los vértices del mapa, (las claves), hasta encontrar el vértice
    // target
    for (Vertex<T> v : adjacentsMap.getKeys()) {
      if (v.equals(target)) {

        // si encontramos target, sacamos su label
        E lab = adjacentsMap.get(target);

        // si es igual a la label que buscamos, return true
        if (Objects.equals(lab, label)) {
          return true;
        }

      }
    }

    return false;

  }

  @Override
  public Set<Vertex<T>> getVertices() {

    Set<Vertex<T>> vertices = new HashSet<>();

    for (Vertex<T> vertex : mapOfVertices.getKeys()) {
      vertices.add(vertex);
    }

    return vertices;

  }

  @Override
  public Set<Edge<T, E>> getEdges() {

    Set<Edge<T, E>> edgeSet = new HashSet<>();

    for (Vertex<T> source : mapOfVertices.getKeys()) {
      Map<Vertex<T>, E> adjacencyMap = mapOfVertices.get(source);

      for (Vertex<T> target : adjacencyMap.getKeys()) {
        E lab = adjacencyMap.get(target);
        Edge<T, E> edge = new Edge<>(source, target, lab);

        edgeSet.add(edge);
      }

    }

    return edgeSet;

  }

  @Override
  public Set<Vertex<T>> getAdjacentsVertex(Vertex<T> vertex) throws NullPointerException {

    if (vertex == null)
      throw new NullPointerException("Null Vertex");

    Set<Vertex<T>> adjacentsSet = new HashSet<>();

    for (Vertex<T> source : mapOfVertices.getKeys()) {
      if (source.equals(vertex)) {
        Map<Vertex<T>, E> adjacencyMap = mapOfVertices.get(source);

        for (Vertex<T> adjacent : adjacencyMap.getKeys()) {
          adjacentsSet.add(adjacent);
        }

      }

    }

    return adjacentsSet;

  }

  @Override
  public boolean addVertex(Vertex<T> vertex) throws NullPointerException {

    if (vertex == null)
      throw new NullPointerException("Null Vertex");

    Set<Vertex<T>> vertices = mapOfVertices.getKeys();

    for (Vertex<T> v : vertices) {
      if (v.equals(vertex)) {
        return false;
      }

    }

    mapOfVertices.add(vertex, new HashMap<>());
    return true;
  }

  @Override
  public boolean addEdge(Vertex<T> source, Vertex<T> target, E label)
      throws NullPointerException, IllegalArgumentException {

    if (source == null || target == null) {
      throw new NullPointerException("Null values are not allowed");
    }

    if (!mapOfVertices.getKeys().contains(source) || !mapOfVertices.getKeys().contains(target))
      throw new IllegalArgumentException();

    Map<Vertex<T>, E> adjMap = mapOfVertices.get(source);

    for (Vertex<T> v : adjMap.getKeys()) {
      if (v.equals(target)) {
        return false;
      }
    }

    adjMap.add(target, label);

    return true;

  }

  @Override
  public boolean removeVertex(Vertex<T> vertex) throws NullPointerException {
    if (vertex == null)
      throw new NullPointerException();

    if (!mapOfVertices.getKeys().contains(vertex)) {
      return false;
    }

    for (Vertex<T> v : mapOfVertices.getKeys()) {
      Map<Vertex<T>, E> adjMap = mapOfVertices.get(v);

      adjMap.remove(vertex);
    }

    mapOfVertices.remove(vertex);

    return true;

  }

  @Override
  public boolean removeEdge(Vertex<T> source, Vertex<T> target, E label) throws NullPointerException {
    if (source == null || target == null) {
      throw new NullPointerException("Null values are not allowed");
    }

    Map<Vertex<T>, E> adjMap = mapOfVertices.get(source);

    if (adjMap == null) {
      return false;
    }
    
    if (adjMap != null && adjMap.getKeys().contains(target)) {

      if (Objects.equals(adjMap.get(target), label)) {
        adjMap.remove(target);
        return true;
      }

    }

    return false;
  }

  @Override
  public void clear() {
    mapOfVertices.clear();
  }

  @Override
  public void forEachVertex(Consumer<Vertex<T>> action) {

    for (Vertex<T> v : mapOfVertices.getKeys()) {
      action.accept(v);

    }

  }

  @Override
  public void forEachEdge(Consumer<Edge<T, E>> action) {

    for (Vertex<T> v : mapOfVertices.getKeys()) {
      Map<Vertex<T>, E> adjMap = mapOfVertices.get(v);

      for (Vertex<T> target : adjMap.getKeys()) {

        E label = adjMap.get(target);

        Edge<T, E> edge = new Edge<>(v, target, label);
        action.accept(edge);

      }
    }
  }

}
