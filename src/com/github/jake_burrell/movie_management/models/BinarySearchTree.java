package com.github.jake_burrell.movie_management.models;

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

    public int getNumNodes() {
        return numNodes;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>(rootNode, numNodes);
    }

    /**
     * Binary Search Tree Node
     * @param <T>
     */
    protected static class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {

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


        protected void deleteChild(TreeNode<T> childNode) {
            if (this.rightNode == childNode) this.rightNode = null;
            else this.leftNode = null;
        }

        protected void replaceChild(TreeNode<T> oldChild, TreeNode<T> newChild) {
            if (this.rightNode == oldChild) this.rightNode = newChild;
            else this.leftNode = newChild;
        }


        @Override
        public int compareTo(TreeNode<T> node) {
            return this.nodeData.compareTo(node.nodeData);
        }
    }

    /**
     * Iterates over the binary search tree in order.
     * @param <F>
     */

    private static class TreeIterator<F extends Comparable<F>> implements Iterator<F> {

        private TreeNode<F> currentNode;
        private final TreeNode<F>[] nodesReturned;
        private final Stack<TreeNode<F>> previousNodes;
        private int numNodesReturned;
        private int numNodes;

        /**
         * Constructor for Binary Search Tree Iterator. It sets the currentNode to the leftmost TreeNode.
         * @param rootNode Binary Search Tree root node.
         * @param numNodes Number of nodes within Tree.
         */
        public TreeIterator(TreeNode<F> rootNode, int numNodes) {
            currentNode = rootNode;
            previousNodes = new Stack<>();
            numNodesReturned = 0;
            nodesReturned = new TreeNode[numNodes];
            this.numNodes = numNodes;
            while (currentNode != null && currentNode.leftNode != null) {
                previousNodes.push(currentNode);
                currentNode = currentNode.leftNode;
            }
        }

        /**
         * Checks if a next node exists
         * @return True when more nodes exist to be traversed
         */
        @Override
        public boolean hasNext() {
            return (numNodes > 0);
        }

        /**
         * BinarySearchTree Traversal
         * @implNote Implements in-order traversal non recursive
         */
        @Override
        public F next() {
            F node = currentNode.nodeData;
            numNodesReturned++;
            nodesReturned[numNodesReturned -1] = currentNode;
            numNodes--;
            if (currentNode.rightNode != null && notBeenReturned(currentNode.rightNode)) {
                currentNode = currentNode.rightNode;
                while (currentNode.leftNode != null) {
                    previousNodes.push(currentNode);
                    currentNode = currentNode.leftNode;
                }
            } else if (currentNode.leftNode != null && notBeenReturned(currentNode.leftNode)) {
                currentNode = currentNode.leftNode;
            } else if (!previousNodes.empty() ) {
                currentNode = previousNodes.pop();
            }
            return node;
        }

        /**
         * Returns true if nodes has already been returned
         * @param checkNode The node to be checked if it has been returned
         * @return True if and only if node has not previously been returned
         */
        private boolean notBeenReturned(TreeNode<F> checkNode) {
            for (TreeNode<F> node : nodesReturned) {
                if (node == checkNode) return false;
            }
            return true;
        }
    }

    /**
     * Binary Search Tree Constructor
     */
    public BinarySearchTree() {
        rootNode = new TreeNode<>(null);
        numNodes = 0;
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
     * Searches Binary Search Tree and then removes the given data from the Tree
     * @param nodeData An object of some type that is stored in the Binary Search Tree
     * @return Returns true if the node existed otherwise returns false
     */
    public boolean removeNode(E nodeData) {
        numNodes--;
        try {
            TreeNode<E> node = searchTreeNodes(nodeData);
            return removeNodeRecursive(node);
        } catch (NullPointerException E) {
            return false;
        }

    }

    /**
     * Removes a given tree node from the BinarySearchTree
     * @param node The node to be remove.
     * @return Returns true if node removed successful. Unsuccessful if given tree node is null
     */
    public boolean removeNodeRecursive(TreeNode<E> node) {
        boolean removed = true;
        try {
            TreeNode<E> parentNode = returnParent(node);
            // Check its not the root node
            if (parentNode != null) {
                if (node.rightNode == null &&  node.leftNode == null) {
                    parentNode.deleteChild(node);
                } else if (node.leftNode == null) {
                    parentNode.replaceChild(node, node.rightNode);
                } else if (node.rightNode == null){
                    parentNode.replaceChild(node,node.leftNode);
                } else {
                    TreeNode<E> largestNode = searchLargest(node);
                    parentNode.replaceChild(node,largestNode);
                    removeNodeRecursive(largestNode);
                }
                // If there was only a single node in tree
            } else if (rootNode.leftNode == null && rootNode.rightNode == null ) {
                rootNode = new TreeNode<>(null);
            }
            // If the root being deleted had two subtrees
            else if (rootNode.leftNode != null && rootNode.rightNode != null) {
                TreeNode<E> tmpNode = rootNode;
                rootNode = rootNode.leftNode;
                rootNode.rightNode = tmpNode.rightNode;
            }
            else if (rootNode.leftNode == null) rootNode = rootNode.rightNode;
            else rootNode = rootNode.leftNode;

        } catch (NullPointerException E) {
            removed = false;
        }
        return removed;
    }

    /**
     * Searches BinarySearchTree and returns a given tree nodes parent node.
     * @param childNode The child node whose parent is being searched for.
     * @return If parent node of the child being searched is found it returns the parent else null.
     */
    public TreeNode<E> returnParent(TreeNode<E> childNode) {
        TreeNode<E> currentNode = rootNode;

        while (currentNode.leftNode != childNode && currentNode.rightNode != childNode) {
            if (currentNode.compareTo(childNode) >= 0) {
                currentNode = currentNode.leftNode;
            } else currentNode = currentNode.rightNode;
            if (currentNode == null) break;
        }
        return currentNode;
    }


    /**
     * Searched for largest node in given sub tree static
     * @param rootNode The root node of a given subtree.
     * @return The largest node within given subtree.
     */
    public TreeNode<E> searchLargest(TreeNode<E> rootNode) {
        TreeNode<E> currentNode = rootNode;
        while (currentNode.rightNode != null) {
            currentNode = currentNode.rightNode;
        }
        return currentNode;
    }

    /**
     * Search for item within binary search tree returning the item if its found otherwise it returns null
     * @param searchItem Item to search for within the search tree
     * @return Returns the item when found otherwise it returns null
     */
    public E searchTree(E searchItem) {
        try {
            return searchTreeNodes(searchItem).nodeData;
        } catch (NullPointerException E) {
            return null;
        }
    }

    /**
     * @throws NullPointerException If provided with an invalid item
     */
    private TreeNode<E> searchTreeNodes(E searchItem) throws NullPointerException {
        TreeNode<E> searchNode = new TreeNode<>(searchItem);
        TreeNode<E> currentNode = rootNode;

        while (!searchNode.nodeData.equals(currentNode.nodeData)) {
            if (currentNode.compareTo(searchNode) >= 0) {
                currentNode = currentNode.leftNode;
            } else currentNode = currentNode.rightNode;
        }
        return currentNode;

    }

    /**
     * Checks if a given item exists within the BinarySearchTree.
     * @param checkItem The item to be search for.
     * @return Returns true if item exists within tree.
     */
    public boolean itemExists(E checkItem) {
        return (searchTree(checkItem) != null);
    }

}

