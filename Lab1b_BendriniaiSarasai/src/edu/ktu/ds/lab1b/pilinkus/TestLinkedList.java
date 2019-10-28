package edu.ktu.ds.lab1b.pilinkus;

import edu.ktu.ds.lab1b.util.LinkedList;

import java.util.Arrays;
import java.util.Iterator;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("String1");
        linkedList.add("String2");
        linkedList.add("String3");
        linkedList.add("String4");
        linkedList.add("String2");
        linkedList.add("String5");

        System.out.println(linkedList.lastIndexOf("String0"));
        System.out.println(linkedList.lastIndexOf("String2"));
        System.out.println(linkedList.lastIndexOf("String4"));
        System.out.println(linkedList.lastIndexOf(null));
    }
}
