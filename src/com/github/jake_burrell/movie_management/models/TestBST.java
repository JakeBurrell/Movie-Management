package com.github.jake_burrell.movie_management.models;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class TestBST {
    BinarySearchTree<Integer> numbers;
    final int INPUT_SIZE = 100_000_000;
    int[] numbersArray;

    @Test
    public void testIterator() {
        Random r = new Random();
        numbers = new BinarySearchTree<>();
        numbersArray = new int[INPUT_SIZE];
        for (int i = 0; i < INPUT_SIZE; i++) {
            int randomInt = r.nextInt(10000);
            numbersArray[i] = randomInt;
            numbers.addNode(randomInt);
        }
        Arrays.sort(numbersArray);
        int index = 0;
        for (Integer number : numbers) {
            assertEquals(number, numbersArray[index]);
            index++;
        }
    }

    @Test
    public void testAddSpeed() {
        Random r = new Random();
        numbers = new BinarySearchTree<>();
        for (int i = 0; i < INPUT_SIZE; i++) {
            int randomInt = r.nextInt(10000);
            numbers.addNode(randomInt);
        }
    }

}
