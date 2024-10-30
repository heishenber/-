package com.software.t2;

import java.io.*;
import java.util.*;

public class KWICProcessor {
    private ArrayList<String> kwicList = new ArrayList<>();
    private ArrayList<String> lineTxt = new ArrayList<>();
    private BufferedReader inputFile;
    private BufferedWriter outputFile;

    public void input(String fileName) {
        try {
            inputFile = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = inputFile.readLine()) != null) {
                lineTxt.add(line);
            }
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shift() {
        for (String line : lineTxt) {
            StringTokenizer token = new StringTokenizer(line);
            ArrayList<String> tokens = new ArrayList<>();
            while (token.hasMoreTokens()) {
                tokens.add(token.nextToken());
            }
            for (int i = 0; i < tokens.size(); i++) {
                StringBuilder lineBuffer = new StringBuilder();
                for (int j = 0; j < tokens.size(); j++) {
                    lineBuffer.append(tokens.get((i + j) % tokens.size())).append(" ");
                }
                kwicList.add(lineBuffer.toString().trim());
            }
        }
    }

    public void alphabetizer() {
        Collections.sort(kwicList, String::compareToIgnoreCase);
    }

    public String getKWICList() {
        StringBuilder result = new StringBuilder();
        for (String line : kwicList) {
            result.append(line).append("\n");
        }
        return result.toString();
    }
}
