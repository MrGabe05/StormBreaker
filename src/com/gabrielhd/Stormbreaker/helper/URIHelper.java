package com.gabrielhd.Stormbreaker.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class URIHelper
{
    public static String readContent(URI uri) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(uri.toURL().openConnection().getInputStream()));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            content.append(currentLine);
        }
        reader.close();
        return content.toString();
    }
    
    private URIHelper() {
    }
}
