package com.ljmu.niallgallagher;

public class BookRunner {



    public BookRunner(int noOfCopies) {
            this.noOfCopies = noOfCopies;
    }

  private int noOfCopies;

  public int getCopies(){

      return this.noOfCopies;
  }
    public void copies(int noOfCopies){
            this.noOfCopies = noOfCopies;
       // System.out.println("pages turn");
    }
    public void increaseCopies(int howMuch) {
        this.noOfCopies = this.noOfCopies + howMuch;
    }

    public void decreaseCopies(int howMuch) {
        this.noOfCopies = this.noOfCopies - howMuch;
    }
}

