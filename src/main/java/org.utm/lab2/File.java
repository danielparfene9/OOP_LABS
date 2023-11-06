package org.utm.lab2;

import java.util.Date;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

abstract class File implements MyFile {

    protected String fileName;
    protected String extension;
    protected Date createdDate;
    protected Date updatedDate;

    public File(String fileName) {

        this.fileName = fileName;
        this.extension = getExtension(fileName);
        this.createdDate = getCreationDate(fileName);
        this.updatedDate = getModificationDate(fileName);
    }

//    public void update() {
//        this.updatedDate = new Date();
//    }

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

    private Date getCreationDate(String fileName) {

        Path path = FileSystems.getDefault().getPath(fileName);

        try {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime creationTime = attributes.creationTime();
            return new Date(creationTime.toMillis());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Date getModificationDate(String fileName) {

        Path path = FileSystems.getDefault().getPath(fileName);

        try {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime modificationTime = attributes.lastModifiedTime();
            return new Date(modificationTime.toMillis());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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