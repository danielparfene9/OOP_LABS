package org.utm.lab2;

import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class File implements MyFile {
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

    @Override
    public String getFileName() {
        return fileName;
    }

    private String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}

class TextFile extends File {
    public TextFile(String fileName) {
        super(fileName);
    }

    @Override
    public String getInfo() {
        return "Text File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate;
    }

    @Override
    public boolean hasChanged(Date snapshotTime) {
        return updatedDate.after(snapshotTime);
    }
}

class ImageFile extends File {
    public ImageFile(String fileName) {
        super(fileName);
    }

    @Override
    public String getInfo() {
        return "Image File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate;
    }

    @Override
    public boolean hasChanged(Date snapshotTime) {
        return updatedDate.after(snapshotTime);
    }
}

class ProgramFile extends File {
    public ProgramFile(String fileName) {
        super(fileName);
    }

    @Override
    public String getInfo() {
        return "Program File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate;
    }

    @Override
    public boolean hasChanged(Date snapshotTime) {
        return updatedDate.after(snapshotTime);
    }
}