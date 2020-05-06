package com.github.jake_burrell.movie_management.models;

/**
 * Binary Search Tree
 * @param <E>
 * @author Jake Burrell
 */
public class BinarySearchTree<E extends Comparable<E>> {

    public TreeNode<E> rootNode;
    public int numNodes;

    /**
     * Binary Search Tree Node
      * @param <T>
     */
    public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {

        public T nodeData;
        public TreeNode<T> leftNode;
        public TreeNode<T> rightNode;

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
//                    // Checks if swap is required
//                    if (currentNode.leftNode == null) {
//                        currentNode.leftNode = dataNode;
//                        currentNode.leftNodeSwap();
                    else currentNode = currentNode.rightNode;
                }
            }
        }
        numNodes++;

    }


//    /**
//     * Returns an unordered array of the Binary Search Tree
//     * @return Returns an array of E
//     * @implNote Uses Preorder traversal
//     */
//    public E[] toArray() {
//        E[] treeArray = (E[])new Object[this.numNodes];
//        int addedNodes = 0;
//        TreeNode<E> currentNode = this.rootNode;
//        TreeNode<E> previousNode = this.rootNode;
//        while (addedNodes < this.numNodes) {
//            ifUniqueAdd(treeArray, currentNode.nodeData, addedNodes);
//            addedNodes++;
//            if (currentNode.leftNode != null) {
//                previousNode = currentNode;
//                currentNode = currentNode.leftNode;
//                ifUniqueAdd(treeArray, currentNode.nodeData, addedNodes);
//                addedNodes++;
//            } else if (currentNode.rightNode != null) {
//                previousNode = currentNode;
//                currentNode = currentNode.rightNode;
//                ifUniqueAdd(treeArray, currentNode.rightNode.nodeData, addedNodes);
//                addedNodes++;
//            }
//            currentNode = previousNode;
//        }
//        return treeArray;
//    }

    public void ifUniqueAdd(E[] nodeDataArray, E nodeData, int indexAdd) {
        if (!nodeDataExists(nodeDataArray, nodeData)) nodeDataArray[indexAdd] = nodeData;
    }

    public boolean nodeDataExists(E[] nodeDataArray, E nodeData) {
        boolean exists = false;
        for (E nodesData : nodeDataArray) {
            if (nodesData == nodeData) exists = true;
        }
        return exists;
    }

    public static void main (String[] args) {
        BinarySearchTree<String> testTree = new BinarySearchTree<>();
        testTree.addNode("b");
        testTree.addNode("a");
        testTree.addNode("c");
//        testTree.addNode("4");
//        testTree.addNode("5");
        //System.out.println(testTree.numNodes);
        System.out.println(testTree.rootNode.nodeData);
        System.out.println(testTree.rootNode.leftNode.nodeData);
        //System.out.println(testTree.rootNode.leftNode.nodeData);
        //System.out.println(testTree.rootNode.leftNode.leftNode.nodeData);
        System.out.println(testTree.rootNode.rightNode.nodeData);
//
//        Integer[] treeArray = testTree.toArray();
//        for (Integer i: treeArray){
//            i.toString();
//        }
    }
}
