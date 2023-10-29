package org.utm.lab2;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

abstract class File {
    protected String fileName;
    protected String extension;
    protected Date createdDate;
    protected Date updatedDate;

    public File(String fileName) {
        this.fileName = fileName;
        this.extension = getExtension(fileName);
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public void update() {
        this.updatedDate = new Date();
    }

    public abstract String getInfo();
    public abstract boolean hasChanged(Date snapshotTime);

    private String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}

class TextFile extends File {
    private int lineCount;
    private int wordCount;
    private int charCount;

    public TextFile(String fileName) {
        super(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTextFileData() {

    }

    @Override
    public String getInfo() {
        return "Text File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate + "\n"
                + "Line Count: " + lineCount + " Word Count: " + wordCount + " Char Count: " + charCount;
    }

    @Override
    public boolean hasChanged(Date snapshotTime) {
        return updatedDate.after(snapshotTime);
    }
}

class ImageFile extends File {
    private int width;
    private int height;

    public ImageFile(String fileName) {
        super(fileName);
    }

    public void updateImageFileData() {
    }

    @Override
    public String getInfo() {
        return "Image File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate + "\n"
                + "Width: " + width + " Height: " + height;
    }

    @Override
    public boolean hasChanged(Date snapshotTime) {
        return updatedDate.after(snapshotTime);
    }
}

class ProgramFile extends File {
    private int lineCount;
    private int classCount;
    private int methodCount;

    public ProgramFile(String fileName) {
        super(fileName);
    }

    public void updateProgramFileData() {
    }

    @Override
    public String getInfo() {
        return "Program File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate + "\n"
                + "Line Count: " + lineCount + " Class Count: " + classCount + " Method Count: " + methodCount;
    }

    @Override
    public boolean hasChanged(Date snapshotTime) {
        return updatedDate.after(snapshotTime);
    }
}