package com.sphincs.view.utils;

import static com.sphincs.view.utils.Constants.FILE_PATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileHandler {

    public static FileHandler aFileHandler() {
        return new FileHandler();
    }

    public Document getDocument(String filePath) throws IOException {
        File fileTemplate = new File(filePath);
        return Jsoup.parse(fileTemplate, "utf-8");
    }

    public File createOutputFile(String filename) {
        File file = new File(FILE_PATH + filename + ".html");
        updateFile(file, "", false);
        return file;
    }

    public void updateFile(File file, String body, boolean append) {
        try {
            FileWriter writer = new FileWriter(file, append);
            writer.write(body);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
