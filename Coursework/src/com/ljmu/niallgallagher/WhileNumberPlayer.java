package com.ljmu.niallgallagher;

public class WhileNumberPlayer {
    private final int limit;

    public WhileNumberPlayer(int limit) {
        this.limit = limit;
    }

    public void printSquaresUpToLimit() {
        int i = 1;
        while (i*i < limit) {

            System.out.print(i*i + " ");
            i++;

        }
        System.out.println("");
    }

    public void printCubedUpToLimit() {
        int i = 1;
        while(i*i*i < limit) {
            System.out.print(i*i*i + " ");
        i++;
        }
    }
}
