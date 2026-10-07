package es.uvigo.esei.aed2.activity3.implementation;

import static java.util.Objects.requireNonNull;
import java.util.function.Consumer;

import es.uvigo.esei.aed1.tads.common.EmptyException;
import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import es.uvigo.esei.aed2.tree.exceptions.EmptyTreeException;

public class LinkedBinaryTree<T> implements BinaryTree<T> {

    private LinkedBinaryTreeNode<T> rootNode;

    public LinkedBinaryTree() {
        rootNode = null;
    }

    // llama al constructor privado para crear un linkedBinaryTreeNode
    public LinkedBinaryTree(T value) throws NullPointerException {

        this(new LinkedBinaryTreeNode<>(requireNonNull(value, "Null values are not allowed")));
    }

    // constructor privado, recive un treeNode y devuelve un tree
    private LinkedBinaryTree(LinkedBinaryTreeNode<T> root) {

        rootNode = root;
    }

    public LinkedBinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) throws NullPointerException {

        if (value == null) {
            throw new NullPointerException("Null values are not allowed");
        }
        rootNode = new LinkedBinaryTreeNode<>(
                requireNonNull(value, "Null values are not allowed"),
                buildNodeFromTree(requireNonNull(leftChild)),
                buildNodeFromTree(requireNonNull(rightChild)));

    }

    private static <T> LinkedBinaryTreeNode<T> buildNodeFromTree(BinaryTree<T> tree) {

        if (tree.isEmpty()) {
            return null;
        } else {
            return new LinkedBinaryTreeNode<>(
                    tree.getRootValue(),
                    tree.hasLeftChild() ? buildNodeFromTree(tree.getLeftChild()) : null,
                    tree.hasRightChild() ? buildNodeFromTree(tree.getRightChild()) : null
            );

        }

    }

    // Métodos lanzan excepción
    @Override
    public T getRootValue() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyException("Empty Tree");
        }

        return rootNode.getValue();
    }

    @Override
    public void setRootValue(T value) throws EmptyTreeException, NullPointerException {
        if (isEmpty()) {
            throw new EmptyException("Empty tree");
        }

        requireNonNull(value, "Null values are not allowed");

        rootNode.setValue(value);
    }

    @Override
    public boolean contains(T value) {

        if (rootNode.equals(value)) {
            return true;
        }

        return value != null && contains(rootNode, value);
    }

    private boolean contains(LinkedBinaryTreeNode<T> node, T value) {

        if (node == null) {
            return false;
        } else {
            return node.getValue().equals(value) || contains(node.getLeftNode(), value) || contains(node.getRightNode(), value);
        }

    }

    @Override
    public boolean hasLeftChild() {
        return !isEmpty() && rootNode.hasLeftNode();
    }

    @Override
    public BinaryTree<T> getLeftChild() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Empty tree");
        }
        return new LinkedBinaryTree<>(rootNode.getLeftNode());
    }

    @Override
    public void setLeftChild(BinaryTree<T> leftChild) throws EmptyTreeException, NullPointerException {
        if (isEmpty()) {
            throw new EmptyTreeException("Empty tree");
        }
        rootNode.setLeftNode(buildNodeFromTree(requireNonNull(leftChild)));
    }

    @Override
    public void removeLeftChild() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Empty tree");
        }
        rootNode.setLeftNode(null);
    }

    @Override
    public boolean hasRightChild() {
        return !isEmpty() && rootNode.hasRightNode();
    }

    @Override
    public BinaryTree<T> getRightChild() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Empty tree");
        }
        return new LinkedBinaryTree<>(rootNode.getRightNode());
    }

    @Override
    public void setRightChild(BinaryTree<T> rightChild) throws EmptyTreeException, NullPointerException {
        if (isEmpty()) {
            throw new EmptyTreeException("Empty tree");
        }
        rootNode.setRightNode(buildNodeFromTree(requireNonNull(rightChild)));
    }

    @Override
    public void removeRightChild() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException("Empty tree");
        }
        rootNode.setRightNode(null);
    }

    @Override
    public void clear() {

        if (!isEmpty()) {
            rootNode = null;
        }

    }

    @Override
    public boolean isEmpty() {
        return rootNode == null;
    }

    @Override
    public void forEachInOrder(Consumer<T> action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void forEachPreOrder(Consumer<T> action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void forEachPostOrder(Consumer<T> action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void forEachLevelOrder(Consumer<T> action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
