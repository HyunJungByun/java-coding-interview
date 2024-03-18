package com.coding.study.recursiveanddynamic.ex6;

public class Question {
    public static void main(String[] args) {
        Tower source = new Tower();
        Tower destination = new Tower();
        Tower buffer = new Tower();

        source.name = "s";
        destination.name = "d";
        buffer.name = "b";

        /* Load up tower */
        int numberOfDisks = 5;
        for (int disk = numberOfDisks - 1; disk >= 0; disk--) {
            source.add(disk);
        }

        source.print();
        source.moveDisks(numberOfDisks, destination, buffer);
        destination.print();
    }
}
