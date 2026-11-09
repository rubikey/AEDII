package es.uvigo.esei.aed2.activity6.HashMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

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

import es.uvigo.esei.aed2.map.Map;

public class HashMap<K, V> implements Map<K, V> {
  private static final int CAPACITY = 50;

  private int size;
  private List<Pair<K, V>>[] map;

  public HashMap() {
    this(CAPACITY);
  }

  @SuppressWarnings("unchecked")
  private HashMap(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    this.map = new List[capacity];
    for (int i = 0; i < capacity; i++) {
      this.map[i] = new LinkedList<>();
    }

    this.size = 0;
  }

  private int calculateIndex(K key) {
    return Math.floorMod(key.hashCode(), map.length);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public V get(K key) {
    if (key == null) {
      return null;
    }

    int index = calculateIndex(key);

    for (Pair<K, V> par : map[index]) {
      if (par.getKey().equals(key)) {
        V value = par.getValue();

        return value;
      }

    }

    return null;

  }

  @Override
  public void add(K key, V value) throws NullPointerException {
    if (key == null) {
      throw new NullPointerException("Null Key");
    }

    // calcular índice
    int index = calculateIndex(key);

    // iterar sobre cada par de la lista
    for (Pair<K, V> par : map[index]) {
      if (par.getKey().equals(key)) {
        par.setValue(value);
        return;
      }

    }

    this.map[index].add(new Pair<K, V>(key, value));
    size++;

  }

  @Override
  public V remove(K key) {
    if (key == null) {
      return null;
    }

    int index = calculateIndex(key);

    for (Pair<K, V> par : map[index]) {
      if (par.getKey().equals(key)) {
        V value = par.getValue();
        this.map[index].remove(par);
        size--;
        return value;
      }

    }

    return null;
  }

  @Override
  public Set<K> getKeys() {

    Set<K> claves = new HashSet<>();

    for (List<Pair<K, V>> list : map) {
      for (Pair<K, V> pair : list) {
        claves.add(pair.getKey());
      }
    }

    return claves;
  }

  @Override
  public Iterator<V> getValues() {
    List<V> valores = new ArrayList<>();

    for (List<Pair<K, V>> lista : map) {
      if (lista != null) {
        for (Pair<K, V> par : lista) {
          valores.add(par.getValue());
        }
      }
    }

    return valores.iterator();
    
    // return new Iterator<V>();
  }

  @Override
  public void clear() {
    for (List<Pair<K, V>> lista : map) {
      if (lista != null) {
        lista.clear();
      }
    }
    size = 0;
  }

  @Override
  public void forEach(BiConsumer<K, V> action) {
    for (List<Pair<K, V>> lista : this.map) {
      for (Pair<K, V> par : lista) {
        action.accept(par.getKey(), par.getValue());
      }
    }
  }

  private static class Pair<K, V> {

    private final K k;
    private V v;

    public Pair(K k, V v) {
      this.k = k;
      this.v = v;
    }

    public K getKey() {
      return k;
    }

    public V getValue() {
      return v;
    }

    public void setValue(V v) {
      this.v = v;
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof Pair) {
        Pair<?, ?> hp = (Pair<?, ?>) other;
        return this.k.equals(hp.k) && this.v.equals(hp.v);
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(this.k) ^ Objects.hashCode(this.v);
    }
  }

}