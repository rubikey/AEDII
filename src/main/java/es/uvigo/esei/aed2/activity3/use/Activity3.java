package es.uvigo.esei.aed2.activity3.use;

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

import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import java.util.LinkedList;
import java.util.List;

public class Activity3 {
  // exercise a)
  public static <T> T getParent(BinaryTree<T> tree, T value) {

    if (tree.isEmpty()) {
      return null;
    }
    if (value.equals(tree.getRootValue())) {
      return null;
    }

    if (tree.hasLeftChild()) {
      if (tree.getLeftChild().getRootValue().equals(value)) {
        return tree.getRootValue();
      }
    }

    if (tree.hasRightChild()) {
      if (tree.getRightChild().getRootValue().equals(value)) {
        return tree.getRootValue();
      }
    }

    if (tree.hasLeftChild()) {
      T result = getParent(tree.getLeftChild(), value);

      if (result != null) {
        return result;
      }
    }

    if (tree.hasRightChild()) {
      T result = getParent(tree.getRightChild(), value);

      if (result != null) {
        return result;
      }

    }

    return null;
  }

  // exercise b)
  public static <T> void printAllValues(BinaryTree<T> tree) {
    if (tree.isEmpty()) {
      return;
    }

    if (tree.hasLeftChild()) {
      printAllValues(tree.getLeftChild());
    }

    System.out.println(tree.getRootValue());

    if (tree.hasRightChild()) {
      printAllValues(tree.getRightChild());
    }
  }

  // exercise c)
  public static List<Integer> listEvenValues(BinaryTree<Integer> tree) {

    if (tree.isEmpty()) {
      return new LinkedList<>();
    }

    List<Integer> list = new LinkedList<>();

    int value = tree.getRootValue();

    if (value % 2 == 0) {
      list.add(value);
    }

    if (tree.hasLeftChild()) {
      List<Integer> izq = listEvenValues(tree.getLeftChild());
      list.addAll(izq);
    }

    if (tree.hasRightChild()) {
      List<Integer> der = listEvenValues(tree.getRightChild());
      list.addAll(der);
    }
    return list;
  }

  // exercise d)
  public static void printValuesHigherThan30(BinaryTree<Integer> tree) {

    if (tree.isEmpty()) {
      return;
    }

    if (tree.hasLeftChild()) {
      printValuesHigherThan30(tree.getLeftChild());
    }

    if (tree.hasRightChild()) {
      printValuesHigherThan30(tree.getRightChild());
    }

    if (tree.getRootValue() > 30) {
      System.out.println(tree.getRootValue());
    }

  }
}