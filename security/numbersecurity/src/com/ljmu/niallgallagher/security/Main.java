package com.ljmu.niallgallagher.security;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String nyNum = "4624652837";

        int securityNumber = GenerateSecurityNumber.getSecurityNumber(nyNum);
    }
}
