package com.ljmu.niallgallagher;

import com.sun.jdi.Value;

import java.math.BigDecimal;

public class SimpleInterestCalculator {

    BigDecimal principal;
    BigDecimal interest;

    public SimpleInterestCalculator(String principal, String interest) {
        this.principal = new BigDecimal(principal);
        this.interest = new BigDecimal(interest).divide(new BigDecimal(100));
    }


    public BigDecimal calculateTotalValue(int noOfYears) {

        Integer num = 0;
        String s = "";
        boolean bo =  false;



        return  principal.add(principal).multiply(interest.multiply(new BigDecimal(noOfYears)));


    }
}


