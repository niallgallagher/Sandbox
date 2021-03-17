package com.ljmu.niallgallagher.security;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String nyNum = "5593338291";

        int securityNumber = GenerateSecurityNumber.getSecurityNumber(nyNum);
    }
}
