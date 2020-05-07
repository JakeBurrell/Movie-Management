package com.github.jake_burrell.movie_management.models;

import com.github.jake_burrell.movie_management.Movie;
import com.github.jake_burrell.movie_management.MovieCollection;

import java.util.Iterator;
import java.util.Stack;

/**
 * Binary Search Tree
 * @param <E>
 * @author Jake Burrell
 */
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

    private TreeNode<E> rootNode;
    private int numNodes;

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>(rootNode, numNodes);
    }

    /**
     * Binary Search Tree Node
     * @param <T>
     */
    private class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {

        private T nodeData;
        private TreeNode<T> leftNode;
        private TreeNode<T> rightNode;

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
     * Iterates over the binary search tree in order
     * @param <E>
     */

    private class TreeIterator<E extends Comparable<E>> implements Iterator<E> {

        private TreeNode<E> currentNode;
        private TreeNode<E>[] nodesReturned;
        private Stack<TreeNode<E>> previousNodes;
        private int numNodesReturned;
        private int numNodes;

        public TreeIterator(TreeNode<E> rootNode, int numNodes) {
            currentNode = rootNode;
            previousNodes = new Stack<>();
            numNodesReturned = 0;
            nodesReturned = new TreeNode[numNodes];
            this.numNodes = numNodes;
            while (currentNode.leftNode != null) {
                previousNodes.push(currentNode);
                currentNode = currentNode.leftNode;
            }
        }

        @Override
        public boolean hasNext() {
            return (numNodes > 0);
        }

        @Override
        public E next() {
            E node = currentNode.nodeData;
            numNodesReturned++;
            nodesReturned[numNodesReturned -1] = currentNode;
            numNodes--;
            if (currentNode.rightNode != null && !beenReturned(currentNode.rightNode)) {
                currentNode = currentNode.rightNode;
                while (currentNode.leftNode != null) {
                    previousNodes.push(currentNode);
                    currentNode = currentNode.leftNode;
                }
            } else if (currentNode.leftNode != null && !beenReturned(currentNode.leftNode)) {
                currentNode = currentNode.leftNode;
            } else if (!previousNodes.empty() ) {
                currentNode = previousNodes.pop();
            }
            return node;
        }

        private boolean beenReturned(TreeNode<E> checkNode) {
            for (TreeNode<E> node : nodesReturned) {
                if (node == checkNode) return true;
            }
            return false;
        }


    }

    /**
     * Binary Search Tree Constructor
     */
    public BinarySearchTree() {
        rootNode = new TreeNode<>(null);
    }

    /**
     * Adds a node to the Binary Search tree
     *
     * @param nodeData Node to be added to Binary search tree
     */
    public void addNode(E nodeData) {
        TreeNode<E> dataNode = new TreeNode<>(nodeData);
        boolean nodeAdded = false;
        TreeNode<E> currentNode = rootNode;
        while (!nodeAdded) {
            if (numNodes == 0) {
                currentNode.nodeData = nodeData;
                nodeAdded = true;
            } else {
                if (currentNode.compareTo(dataNode) >= 0) {
                    if (currentNode.leftNode == null) {
                        currentNode.leftNode = dataNode;
                        nodeAdded = true;
                    } else currentNode = currentNode.leftNode;
                } else {
                    if (currentNode.rightNode == null) {
                        currentNode.rightNode = dataNode;
                        nodeAdded = true;
                    }
                    else currentNode = currentNode.rightNode;
                }
            }
        }
        numNodes++;
    }

    /**
     * Search for item within binary search tree returning the item if its found otherwise it returns null
     * @param searchItem Item to search for within the search tree
     * @return Returns the item when found otherwise it returns null
     */
    public E searchTree(E searchItem) {
        TreeNode<E> searchNode = new TreeNode<>(searchItem);
        TreeNode<E> currentNode = rootNode;
        try {
            while (!searchNode.nodeData.equals(currentNode.nodeData)) {
                if (currentNode.compareTo(searchNode) >= 0) {
                    currentNode = currentNode.leftNode;
                } else currentNode = currentNode.rightNode;
            }
            return currentNode.nodeData;
        } catch (NullPointerException E) {
            return null;
        }

    }

    public boolean itemExists(E checkItem) {
        return (searchTree(checkItem) != null);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.addNode(100);
        tree.addNode(50);
        tree.addNode(12);
        tree.addNode(1);
        tree.addNode(10);
        tree.addNode(400);
        tree.addNode(320);
        tree.addNode(500);
        tree.addNode(450);
        tree.addNode(430);
        tree.addNode(20);
        tree.addNode(12);
        tree.addNode(150);
        System.out.println(tree.searchTree(12));

        //System.out.println();

        for (Integer number: tree) {
            System.out.println(number);
        }

    }
}

