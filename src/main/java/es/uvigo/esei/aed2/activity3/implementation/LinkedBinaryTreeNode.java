package es.uvigo.esei.aed2.activity3.implementation;

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
public class LinkedBinaryTreeNode<T> {

    private T value;
    private LinkedBinaryTreeNode<T> leftChildNode;
    private LinkedBinaryTreeNode<T> rightChildNode;

    // crea árbol vacío, invocamos al segundo constructor
    public LinkedBinaryTreeNode() {
        this(null);
    }

    // constructor para crear árbol, con raiz pero hijos null
    // invocamos al constructor de abajo
    public LinkedBinaryTreeNode(T value) {
        this(value, null, null);
    }

    // constructor para crear arbol, con raiz, e hijos
    public LinkedBinaryTreeNode(
            T value,
            LinkedBinaryTreeNode<T> leftChild,
            LinkedBinaryTreeNode<T> rightChild
    ) {

        this.value = value;
        this.leftChildNode = leftChild;
        this.rightChildNode = rightChild;

    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean hasLeftNode() {
        return leftChildNode != null;
    }

    public LinkedBinaryTreeNode<T> getLeftNode() {
        return leftChildNode;
    }

    public void setLeftNode(LinkedBinaryTreeNode<T> leftChildNode) {
        this.leftChildNode = leftChildNode;
    }

    public boolean hasRightNode() {
        return rightChildNode != null;
    }

    public LinkedBinaryTreeNode<T> getRightNode() {
        return rightChildNode;
    }

    public void setRightNode(LinkedBinaryTreeNode<T> rightChildNode) {
        this.rightChildNode = rightChildNode;
    }
}
