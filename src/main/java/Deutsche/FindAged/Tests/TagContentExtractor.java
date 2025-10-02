package Deutsche.FindAged.Tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sarav on 02 Oct 2025
 * @project govtech
 * @package Deutsche.FindAged.Tests
 * @class TagContentExtractor
 */

public class TagContentExtractor {
    public static void main(String[] args) {
        String[] inputs = {
                "<h1><a>contents</a>invalid</h1>",
                "<h1>Nayeem loves counseling</h1>",
                "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
                "<Amee>safat codes like a ninja</amee>",
                "<SA premium>Imtiaz has a secret crush</SA premium>"
        };

        for (String input : inputs) {
            extractContent(input);
        }
    }

    public static void extractContent(String input) {
        // Pattern to match valid tags and their content
        Pattern pattern = Pattern.compile("<(.+?)>([^<]+)</\\1>");
        Matcher matcher = pattern.matcher(input);

        boolean found = false;

        while (matcher.find()) {
            String content = matcher.group(2).trim();
            // Check if content is not empty and doesn't contain '<'
            if (!content.isEmpty() && !content.contains("<")) {
                System.out.println(content);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }
}

/*
Output :

contents
Nayeem loves counseling
Sanjay has no watch
So wait for a while
None
Imtiaz has a secret crush

 */