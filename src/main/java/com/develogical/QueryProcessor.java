package com.develogical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class QueryProcessor {

    private Pattern plus = Pattern.compile("(.*): what is ([0-9]*) plus ([0-9]*)");
    private Pattern multiply = Pattern.compile("(.*): what is ([0-9]*) multiplied by ([0-9]*)");
    private Pattern largest = Pattern.compile("(.*): which of the following numbers is the largest: (.*)");
    private Pattern prime = Pattern.compile("(.*): which of the following numbers are primes: (.*)");
    private Pattern cubeAndSquare = Pattern.compile("(.*): which of the following numbers is both a square and a cube: (.*)");

    public String process(String query) {
        Matcher plusMatcher;
        Matcher largerMatcher;
        Matcher cubeSquareMatcher;
        Matcher primeMatcher;
        Matcher multiplyMatcher;
        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.toLowerCase().contains("gunko")) {
            return "Really afraid of mentioning him in stories";
        } else if (query.toLowerCase().contains("what is your name")) {
            return "TwoBakers";
        } else if ((plusMatcher = plus.matcher(query.toLowerCase())).matches()) {
            int a1 = Integer.parseInt(plusMatcher.group(2));
            int a2 = Integer.parseInt(plusMatcher.group(3));
            return Integer.toString(a1 + a2);
        }  else if ((plusMatcher = plus.matcher(query.toLowerCase())).matches()) {
            int a1 = Integer.parseInt(plusMatcher.group(2));
            int a2 = Integer.parseInt(plusMatcher.group(3));
            return Integer.toString(a1 + a2);
        } else if ((largerMatcher = largest.matcher(query.toLowerCase())).matches()) {
            String values = largerMatcher.group(2).replace(" ","");
            String[] valArr = values.split(",");
            return Stream.of(valArr).map(Integer::parseInt).max(Integer::compareTo).get() +"";
        }  else if ((cubeSquareMatcher = cubeAndSquare.matcher(query.toLowerCase())).matches()) {
            String values = cubeSquareMatcher.group(2).replace(" ","");
            String[] valArr = values.split(",");
            for (String s : valArr) {
                Integer i = Integer.parseInt(s);
                final double sqrt = Math.round(Math.sqrt(i)*100.0)/100.0;
                final double cbrt = Math.round(Math.pow(i, 1.0/3)*100.0)/100.0;
                if (sqrt == Math.ceil(sqrt) && cbrt == Math.ceil(cbrt)) {
                    return Integer.toString(i);
                }
            }
        }  else if ((multiplyMatcher = multiply.matcher(query.toLowerCase())).matches()) {
            int a1 = Integer.parseInt(multiplyMatcher.group(2));
            int a2 = Integer.parseInt(multiplyMatcher.group(3));
            return Integer.toString(a1 * a2);
        } else if ((primeMatcher = prime.matcher(query.toLowerCase())).matches()) {
            String values = primeMatcher.group(2).replace(" ","");
            String[] valArr = values.split(",");
            return Stream.of(valArr).map(Integer::parseInt).filter(i -> isPrime(i)).findFirst().get() +"";
        }
        return "";
    }

    private boolean isPrime(Integer i) {
        for (int g=2; g<i; g++) {
            if (i%g==0) return false;
        }
        return true;
    }
}
