package com.develogical;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    private Pattern plus = Pattern.compile("(.*): what is (.[0-9]) plus (.[0-9])");
    private Pattern largest = Pattern.compile("(.*): which of the following numbers is the largest: (.[0-9,])");

    public String process(String query) {
        Matcher plusMatcher;
        Matcher largerMatcher;
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
        }
        return "";
    }
}
