package ru.belov.hw2;

import java.util.*;

public class TestSet {
    private final int[] indexes = {10000, 20000, 30000, 40000, 50000, 60000, 8888};
    private final int findElement = 99999999;
    private final int N = 80000;
    private double addElementsDuration;
    private double findElementDuration;
    private double removeElementsDuration;
    private Random rand = new Random();
    private Set<Integer> set;

    TestSet(Set<Integer> set) {
        this.set = set;
        learnOperations();
        LinkedList<String> list = new LinkedList<>();
    }

    public static void main(String[] args) {
        System.out.println("Test working of HashSet...");
        TestSet testHashSet = new TestSet(new HashSet<>());
        testHashSet.timeWorkCollection();
        System.out.println("Test working of TreeSet...");
        TestSet testTreeSet = new TestSet(new TreeSet<>());
        testTreeSet.timeWorkCollection();
    }

    protected void learnOperations() {
        addElements();
        findElement();
        removeElements();
    }

    private void addElements() {
        double startTime = System.nanoTime();
        int specialInsert = 15000;
        for (int i = 0; i < N; i++) {
            if (i == specialInsert)
                set.add(findElement);
            else
//                set.add(rand.nextInt(findElement-1));
                set.add(rand.nextInt(findElement-1));
        }
        this.addElementsDuration = calculateDuration(startTime);
    }

    private void findElement() {
        double startTime = System.nanoTime();
            if (this.set.contains(findElement))
        this.findElementDuration = calculateDuration(startTime);
    }

    private void removeElements() {
        double startTime = System.nanoTime();
        for (int i : indexes)
            this.set.remove(i);
        this.removeElementsDuration = calculateDuration(startTime);
    }

    private double calculateDuration(double startTime) {
        return (System.nanoTime() - startTime) / 1000000.0;
    }

    private String format(double value) {
        return String.format("%.2fms", value);
    }

    public void timeWorkCollection() {
        System.out.println("Operations of insert: " + format(addElementsDuration));
        System.out.println("Operations of finding: " + format(findElementDuration));
        System.out.println("Operations of remove: " + format(removeElementsDuration));
    }
}
