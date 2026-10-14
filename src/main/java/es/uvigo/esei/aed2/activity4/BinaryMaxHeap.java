package es.uvigo.esei.aed2.activity4;

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

import java.util.ArrayList;

public class BinaryMaxHeap<T extends Comparable<T>> implements MaxHeap<T> {

  // Exercise 1
  private final ArrayList<T> arrayHeap;

  public BinaryMaxHeap() {
    this.arrayHeap = new ArrayList<>();
    this.arrayHeap.add(0, null);
  }

  /**
   * Comprueba si el heap está vacío.
   *
   * @return <code>true</code> cuando el heap está vacío y <code>false</code> en
   *         caso contrario.
   */
  @Override
  public boolean isEmpty() {
    return arrayHeap.size() == 1;
  }

  /**
   * Recupera el mayor elemento del heap.
   *
   * @return el elemento mayor del heap.
   * @throws HeapEmptyException cuando el heap está vacío.
   */
  @Override
  public T getMaxValue() throws HeapEmptyException {
    if (isEmpty()) {
      throw new HeapEmptyException("Empty Heap");
    }

    return arrayHeap.get(1);
  }

  /**
   * Elimina y devuelve el elemento mayor del heap.
   *
   * @return el elemento mayor del heap.
   * @throws HeapEmptyException cuando el heap está vacío.
   */
  @Override

  public T removeMaxValue() throws HeapEmptyException {

    if (isEmpty()) {
      throw new HeapEmptyException("Empty Heap");
    }

    T max = getMaxValue();

    // solo hay un elemento real
    if (arrayHeap.size() == 2) {
      arrayHeap.remove(1);
      return max;
    }

    T last = arrayHeap.remove(arrayHeap.size() - 1); // último elemento

    arrayHeap.set(1, last);

    sink(1);

    return max;
  }

  /**
   * Mueve el elemento en la posición {@code hollow} hacia arriba en el heap
   * hasta que se cumpla la propiedad de ordenación.
   *
   * @param hollow la posición del elemento a mover.
   */
  private void sink(int hollow) {

    /*FLORO
     * 
     * int hijo = hollow * 2;
     * T value = arrayHeap.get(hollow);
     * 
     * boolean end = false;
     * 
     * while(hijo <= arrayHeap.size()-1 && !end){
      * if(hijo < arrayHeap.size()-1 &&
        * arrayHeap.get(hijo+1).compareTo(arrayHeap.get(hijo))>0){
        * hijo++;
      * }
      * 
      * if(arrayHeap.get(hijo).compareTo(value) > 0){
      * 
        * arrayHeap.set(hollow, arrayHeap.get(hijo));
        * arrayHeap.set(hijo, value);
        * hijo = hollow/2;
      * 
      * }else{
      *   end = true;
      * }
     * 
     * 
     * }
     * 
     */

    int currentPos = hollow;

    while (true) {
      int left = currentPos * 2;
      int right = currentPos * 2 + 1;

      if (left >= arrayHeap.size()) { // no tiene hijos que comprobar
        break;
      }

      int biggerChild = left;

      if (right < arrayHeap.size()) {
        if (arrayHeap.get(right).compareTo(arrayHeap.get(left)) > 0) {
          biggerChild = right;
        }
      }

      if (arrayHeap.get(currentPos).compareTo(arrayHeap.get(biggerChild)) >= 0) {

        break;
      }

      T temp = arrayHeap.get(currentPos);
      arrayHeap.set(currentPos, arrayHeap.get(biggerChild));
      arrayHeap.set(biggerChild, temp);

      // actualizamos currentPos
      currentPos = biggerChild;

    }
  }

  /**
   * Añade un valor al heap.
   *
   * @param value el elemento a añadir.
   * @throws NullPointerException si el valor es <code>null</code>.
   */
  @Override
  public void add(T value) throws NullPointerException {

    if (value == null) {
      throw new NullPointerException("Null Values are not allowed");
    }

    if (arrayHeap.isEmpty()) {
      arrayHeap.set(1, value);
    }

    arrayHeap.add(value);

    int currentPos = arrayHeap.size() - 1;

    int padre = currentPos / 2;

    while (currentPos > 1 && value.compareTo(arrayHeap.get(currentPos / 2)) > 0) {

      T temp = arrayHeap.get(padre);
      arrayHeap.set(padre, value);
      arrayHeap.set(currentPos, temp);
      currentPos = currentPos / 2;

      padre = currentPos / 2;

    }

  }

  /**
   * Elimina los valores del heap, convirtiéndolo en un heap vacío.
   */
  @Override
  public void clear() {

    arrayHeap.clear();
    arrayHeap.add(0, null);
  }

  /**
   * Añade un valor al heap sin mantener la propiedad de ordenacion
   *
   * @param value el elemento a añadir
   * @throws NullPointerException si el valor es <code>null</code>
   */
  @Override
  public void insert(T value) throws NullPointerException {

    if (value == null) {
      throw new NullPointerException("Null values are not allowed");
    }

    arrayHeap.add(value);

  }

  /**
   * Restablece el orden en el montículo binario
   */
  @Override
  public void orderHeap() {

    int lastFather = (arrayHeap.size() - 1) / 2;

    for (int i = lastFather; i > 0; i--) {
      sink(i);
    }

  }

}