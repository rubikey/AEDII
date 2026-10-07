package es.uvigo.esei.aed2.activity2;

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
import es.uvigo.esei.aed2.tree.binary.LinkedBinaryTree;

public class Activity2 {

    // exercise 1
    public static <T> boolean isComplete(BinaryTree<T> tree) {
        // TODO: Implementa la comprobación recursiva de si el árbol es completo

        if (tree.isEmpty()) { // árbol vacío
            return true;
        }

        if (!tree.hasRightChild() && !tree.hasLeftChild()) { // árbol raiz, no tiene hijos
            return true;
        }

        if (tree.hasLeftChild() && tree.hasRightChild()) { // búsqueda recursiva para comprobar que todos los nodos
                                                           // tienen hijos, para en las hojas
            return isComplete(tree.getLeftChild()) && isComplete(tree.getRightChild());
        }

        return false;
    }

    // exercise 2
    public static <T> boolean areIdentical(BinaryTree<T> treeA, BinaryTree<T> treeB) {
        // TODO: Implementa la comprobación recursiva de si dos árboles son idénticos

        if (treeA.isEmpty() && treeB.isEmpty()) {
            return true;
        }

        if (!treeA.hasLeftChild() && !treeA.hasRightChild() && !treeB.hasLeftChild() && !treeB.hasRightChild()) {
            return true;
        }

        if (treeA.hasLeftChild() && treeA.hasRightChild() && treeB.hasLeftChild() && treeB.hasRightChild()) {
            return areIdentical(treeA.getLeftChild(), treeB.getLeftChild())
                    && areIdentical(treeA.getRightChild(), treeB.getRightChild());

        }

        return false;
    }

    public static <T> boolean areIdentical2(BinaryTree<T> treeA, BinaryTree<T> treeB) {
        // TODO: Implementa la comprobación recursiva de si dos árboles son idénticos

        if (treeA.isEmpty() && treeB.isEmpty()) {
            return true;
        }

        if (treeA.isEmpty() || treeB.isEmpty()) {
            return false;
        }

        return treeA.getRootValue().equals(treeB.getRootValue())
                && areIdentical(treeA.getLeftChild(), treeB.getLeftChild())
                && areIdentical(treeA.getRightChild(), treeB.getRightChild());

    }

    // exercise 3
    public static <T> int countNodesInLevel(BinaryTree<T> tree, int level) {
        // TODO: Implementa el conteo recursivo de nodos en un nivel dado

        if (tree.isEmpty()) {
            return 0;
        }

        if (level == 0) {
            return 1;
        } else {

            return countNodesInLevel(tree.getLeftChild(), level - 1)
                    + countNodesInLevel(tree.getRightChild(), level - 1);
        }
    }

    // exercise 4
    public static <T> BinaryTree<T> removeLeaves(BinaryTree<T> tree) {
        // TODO: Implementa la eliminación recursiva de las hojas de un árbol

        // si árbol está vacío devuelve otro árbol vacio
        if (tree.isEmpty() || !tree.hasLeftChild() && !tree.hasRightChild()) {
            return new LinkedBinaryTree<>();
        }

        // copia el árbol (la raiz), y elimina las hojas
        return new LinkedBinaryTree<T>(tree.getRootValue(), removeLeaves(tree.getLeftChild()),
                removeLeaves(tree.getRightChild()));

    }

    // exercise 5
    public static <T> int calculateHeight(BinaryTree<T> tree) {
        // TODO: Implementa el cálculo recursivo de la altura de un árbol

        if (tree.isEmpty()) {
            return -1;
        } else if (!tree.hasLeftChild() && !tree.hasRightChild()) {
            return 0;
        }

        return 1 + Math.max(calculateHeight(tree.getLeftChild()), calculateHeight(tree.getRightChild()));
    }

    // exercise 6
    public static BinaryTree<String> rebuild(String preorder, String inorder) {
        // TODO: Implementa la reconstrucción recursiva de un árbol binario a partir de
        // los recorridos preorder e inorder

        // cadenas vacías
        if (preorder.isEmpty() || inorder.isEmpty()) {
            return new LinkedBinaryTree<>();
        }
        
        // valor de la raíz, en preorder es el primero
        String rootValue = preorder.substring(0,1);

        // en que posición está la raíz en inorder
        int rootPosInorder = inorder.indexOf(rootValue);


        String leftInorder = inorder.substring(0, rootPosInorder); // cadena left en inorder
        String rightInorder = inorder.substring(1 + rootPosInorder); // cadena right en inorder


        
        String leftPreorder = preorder.substring(1, 1 + rootPosInorder); // cadena left en preorder
        String rightPreorder = preorder.substring(1 + rootPosInorder);// cadena right en preorder



        return new LinkedBinaryTree<>(rootValue, rebuild(leftPreorder, leftInorder), rebuild(rightPreorder, rightInorder));



    }

    // exercise 7
    public static <T extends Comparable<T>> boolean isSelectionTree(BinaryTree<T> tree) {
        // TODO: Implementa la comprobación recursiva de si el árbol es un árbol de
        // selección
        return false;
    }

    // exercise 8
    public static <T> boolean isPath(BinaryTree<T> tree, String path) {
        // TODO: Implementa la comprobación recursiva de si existe un camino en el árbol
        // que siga la secuencia dada
        return false;
    }

    // exercise 9
    public static <T> BinaryTree<T> copyingToLevel(BinaryTree<T> tree, int level) {
        // TODO: Implementa la copia recursiva del árbol hasta un nivel dado
        return new LinkedBinaryTree<>();
    }

    // exercise 10
    public static <T> boolean isLevelK(BinaryTree<T> tree, T elem, int k) {
        // TODO: Implementa la comprobación recursiva de si un elemento está en el nivel
        // k del árbol
        return false;
    }
}
