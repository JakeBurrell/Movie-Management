package com.github.jake_burrell.movie_management.models;

/**
 * Binary Search Tree
 * @param <E>
 * @author Jake Burrell
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private TreeNode<E> rootNode;

    /**
     * Binary Search Tree Node
      * @param <T>
     */
    private class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {

        protected T nodeData;
        protected TreeNode<T> leftNode;
        protected TreeNode<T> rightNode;

        public TreeNode(T nodeData) {
            this.nodeData = nodeData;
            this.leftNode = null;
            this.rightNode = null;
        }

        /**
         * Swaps node with its left child node
         **/
        public void leftNodeSwap() {
            if (this.leftNode != null) {
                T tmpData = this.nodeData;
                this.nodeData = this.leftNode.nodeData;
                this.leftNode.nodeData = tmpData;
            }
        }


        @Override
        public int compareTo(TreeNode<T> node) {
            return this.nodeData.compareTo(node.nodeData);
        }
    }

    /**
     * Binary Search Tree Constructor
     */
    public BinarySearchTree() {
        rootNode = new TreeNode(null);
    }

    /**
     * Adds a node to the Binary Search tree
      * @param nodeData Node to be added to Binary search tree
     */
    public void addNode(E nodeData) {
        TreeNode<E> dataNode = new TreeNode<>(nodeData);
        boolean nodeAdded = false;
        TreeNode<E> currentNode = rootNode;
        while (!nodeAdded) {
            if (currentNode == null) {
                currentNode.nodeData = nodeData;
                nodeAdded = true;
            } else {
                if (currentNode.compareTo(dataNode) <= 0) {
                    if (currentNode.leftNode == null) {
                        currentNode.leftNode = dataNode;
                        nodeAdded = true;
                    } else currentNode = currentNode.leftNode;
                } else {
                    // Checks if swap is required
                    if (currentNode.leftNode == null) {
                        currentNode.leftNode = dataNode;
                        currentNode.leftNodeSwap();
                    } else if (currentNode.rightNode == null) {
                        currentNode.rightNode = dataNode;
                        nodeAdded = true;
                    } else currentNode = currentNode.rightNode;
                }
            }
        }
    }




}
