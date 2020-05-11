package com.zakmicallef;

import java.io.*;

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

}
