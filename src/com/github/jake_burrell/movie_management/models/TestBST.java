package com.github.jake_burrell.movie_management.models;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TestBST {
    BinarySearchTree<Integer> numbers;
    final int INPUT_SIZE = 1_000_000;
    final int RANGE = 1_000;
    int numbersRemoved = 1_000_000;
    ArrayList<Integer> removedNumbers;
    int[] numbersArray;
    int[] numbersLeftArray;


    @BeforeEach
    public void init() {
        Random r = new Random();
        numbers = new BinarySearchTree<>();
        removedNumbers = new ArrayList<>();
        ArrayList<Integer> numbersLeft = new ArrayList<>();

        numbersArray = new int[INPUT_SIZE];
        for (int i = 0; i < INPUT_SIZE; i++) {
            int randomInt = r.nextInt(RANGE);
            numbersArray[i] = randomInt;
            numbers.addNode(randomInt);
            numbersLeft.add(randomInt);
        }

        while (numbersRemoved != 0) {
            int random = r.nextInt(INPUT_SIZE);
            removedNumbers.add(numbersArray[random]);
            numbersLeft.remove((Integer) numbersArray[random]);
            numbersRemoved--;

        }
        numbersLeftArray =  numbersLeft.stream().mapToInt(i -> i).toArray();
        Arrays.sort(numbersLeftArray);

        Arrays.sort(numbersArray);
    }


    @Test
    public void testIterator() {
        testIteratorAgainst(numbersArray);
    }

    @Test
    public void removeFromEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        assertFalse(tree.removeNode(2));
        tree.addNode(10);
        assertTrue(tree.removeNode(10));
        assertFalse(tree.itemExists(10));
        tree.removeNode(10);
        assertFalse(tree.removeNode(10));
        tree.addNode(10);
        assertFalse(tree.removeNode(11));
    }

    @Test
    public void testRemoveNode() throws Exception {

        for(int number : removedNumbers) {
            numbers.removeNode(number);
        }
        testIteratorAgainst(numbersLeftArray);
    }

    @Test
    public void testAddNode() {
        Random r = new Random();
        numbers = new BinarySearchTree<>();
        for (int i = 0; i < INPUT_SIZE; i++) {
            int randomInt = r.nextInt(10000);
            numbers.addNode(randomInt);
        }
    }

    public void testIteratorAgainst(int[] checkArray) {
        int i = 0;
        for (Integer number : numbers) {
            assertEquals(number, checkArray[i]);
            i++;
        }
    }

}
