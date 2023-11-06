package org.utm.lab2;

import java.util.Date;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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

    private int lineCount;
    private int wordCount;
    private int charCount;

    public TextFile(String fileName) {
        super(fileName);
        String content = getTextFileContent(fileName);
        countTextFileMetrics(content);
    }

    @Override
    public String getInfo() {
        return "Text File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate + "\n"
                + "Line Count: " + lineCount + " Word Count: " + wordCount + " Character Count: " + charCount;
    }

    @Override
    public boolean hasChanged(Date snapshotTime) {
        return updatedDate.after(snapshotTime);
    }

    private String getTextFileContent(String fileName) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(fileName));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void countTextFileMetrics(String content) {
        String[] lines = content.split("\r\n|\r|\n");
        lineCount = lines.length;
        wordCount = content.split("\\s+").length;
        charCount = content.length();
    }

}

class ImageFile extends File {

    private int width;
    private int height;

    public ImageFile(String fileName) throws IOException {
        super(fileName);
        BufferedImage bimg = ImageIO.read(new java.io.File(fileName));
        this.width = bimg.getWidth();
        this.height = bimg.getHeight();
    }

    @Override
    public String getInfo() {
        return "Image File - " + fileName + " Extension: " + extension + "\n"
                + "Created: " + createdDate + " Updated: " + updatedDate + "\n"
                + "Image Size: " + width + "x" + height;
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
        String content = getProgramFileContent(fileName);
        countProgramFileMetrics(content);
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

    private String getProgramFileContent(String fileName) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(fileName));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void countProgramFileMetrics(String content) {
        String[] lines = content.split("\r\n|\r|\n");
        lineCount = lines.length;

        classCount = content.split("class\\s+").length - 1;
        methodCount = content.split("void\\s+").length - 1;
    }

}