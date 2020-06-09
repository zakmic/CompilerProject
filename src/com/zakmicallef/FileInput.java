package com.zakmicallef;

import java.io.*;
import java.util.ArrayList;

public class FileInput {
    public static String readFile(String path) {
        File file = new File(path);
        return parseFile(file);
    }

    private static String parseFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder allCode = new StringBuilder();
            String code = null;
            while ((code = br.readLine()) != null) {
                allCode.append(code);
            }

            return allCode.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void writeToFile(ArrayList<String> allWords, String path) {
        try {
            File OutFile = new File(path);

            if (!OutFile.exists()) {
                OutFile.createNewFile();
            }

            FileWriter fw = new FileWriter(OutFile);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String word : allWords) {
                bw.write(word);
                bw.write("\n");
            }


            try {
                bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        } catch (IOException io) {
            io.getCause();
            io.getMessage();
        }

    }


}
