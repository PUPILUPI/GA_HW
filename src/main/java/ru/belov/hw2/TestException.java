package ru.belov.hw2;

import java.util.LinkedList;
import java.util.List;

public class TestException {
    //    public static void main(String[] args) {
////        List<String> list = new MyArrayList<>();
////        list.add("1");
////        list.add("2");
////        list.add("3");
////        list.add("4");
////        list.add("5");
////        list.add("6");
////        list.add("7");
////        list.add("8");
////        list.add("9");
////        list.add("10");
////        list.add("11");
//    }
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            try {
                new MyException();
            } catch (Throwable e) {
                System.out.println("e = " + e);
                if (e.getCause() instanceof MyException) {
                    MyException ex = (MyException) e.getCause();
                    System.out.println("второй вывод");
                    System.out.println("e instanceof " + MyException.class.getName() + ", s = " + ex.get());
                }
            }
        }
    }
}

class MyException extends RuntimeException {
    private String s;

    static {
        init();
    }

    public MyException() {
        s = "Hello";
    }

    private static void init() {
        throw new MyException();
    }

    public String get() {
        return s;
    }
}


