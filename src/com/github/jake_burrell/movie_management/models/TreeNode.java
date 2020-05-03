package com.github.jake_burrell.movie_management.models;

/**
 * Binary Search Tree Node
 * @param <E>
 * @author Jake Burrell
 */

public class TreeNode<E> {

    private E nodeData;
    private TreeNode<E> leftNode;
    private TreeNode<E> rightNode;

    public TreeNode(E nodeData) {
        this.nodeData = nodeData;
        this.leftNode = null;
        this.rightNode = null;
    }

}
