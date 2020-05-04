package com.github.jake_burrell.movie_management.models;

/**
 * Binary Search Tree Node
 * @param <E>
 * @author Jake Burrell
 */

public class BinarySearchTree<E extends Comparable<E>> {

    private TreeNode<E> rootNode;

    private class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {

        protected T nodeData;
        protected TreeNode<T> leftNode;
        protected TreeNode<T> rightNode;

        public TreeNode(T nodeData) {
            this.nodeData = nodeData;
            this.leftNode = null;
            this.rightNode = null;
        }

        @Override
        public int compareTo(TreeNode<T> node) {
            return this.nodeData.compareTo(node.nodeData);
        }
    }


    public BinarySearchTree() {
        rootNode = new TreeNode(null);
    }

    public void addNode(E nodeData) {
        TreeNode<E> dataNode = new TreeNode<>(nodeData);
        boolean nodeAdded = false;
        TreeNode<E> node = rootNode;
        while (!nodeAdded) {
            if (node == null) {
                node.nodeData = nodeData;
            } else {
                if (node.compareTo(dataNode) < 0) {
                    if (node.leftNode == null) {
                    }
                }
            }
        }
    }


}
