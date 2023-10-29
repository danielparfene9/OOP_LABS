package org.utm.lab2;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileMonitor {
    private static Date snapshotTime;
    private static List<File> files;

    public static void main(String[] args) {
        snapshotTime = new Date();
        files = new ArrayList<>();

        String folderLocation = "C:\\Users\\danie\\Documents\\GitHub\\OOP_LABS";

        populateFileList(folderLocation);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. commit - Update snapshot time");
            System.out.println("2. info <filename> - Get file information");
            System.out.println("3. status - Show file status");
            System.out.println("4. exit - Exit the program");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if (choice.startsWith("commit")) {
                snapshotTime = new Date();
                System.out.println("Snapshot time updated to: " + snapshotTime);
                updateFilesData();
            } else if (choice.startsWith("info")) {
                String[] parts = choice.split(" ");
                if (parts.length == 2) {
                    String filename = parts[1];
                    displayFileInfo(filename);
                } else {
                    System.out.println("Invalid command. Usage: info <filename>");
                }
            } else if (choice.equals("status")) {
                displayFileStatus();
            } else if (choice.equals("exit")) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid command. Please enter a valid option.");
            }
        }

        scanner.close();
    }

    private static void populateFileList(String folderLocation) {
        File folder = new File(folderLocation);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder location: " + folderLocation);
            return;
        }

        File[] filesInFolder = folder.listFiles();
        if (filesInFolder != null) {
            for (File file : filesInFolder) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    File fileObject = createFileObject(fileName);
                    if (fileObject != null) {
                        files.add(fileObject);
                    }
                }
            }
        }
    }

    private static File createFileObject(String fileName) {
        String extension = getExtension(fileName);
        if (extension.equals("txt")) {
            return new TextFile(fileName);
        } else if (extension.equals("png") || extension.equals("jpg")) {
            return new ImageFile(fileName);
        } else if (extension.equals("py") || extension.equals("java")) {
            return new ProgramFile(fileName);
        }
        return null; // Unsupported file type
    }

    private static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    private static void updateFilesData() {
        for (File file : files) {
            if (file instanceof TextFile) {
                ((TextFile) file).updateTextFileData();
            } else if (file instanceof ImageFile) {
                ((ImageFile) file).updateImageFileData();
            } else if (file instanceof ProgramFile) {
                ((ProgramFile) file).updateProgramFileData();
            }
        }
    }

    private static void displayFileInfo(String filename) {
        for (File file : files) {
            if (file.fileName.equals(filename)) {
                System.out.println(file.getInfo());
                return;
            }
        }
        System.out.println("File not found: " + filename);
    }

    private static void displayFileStatus() {
        System.out.println("File status since snapshot time: " + snapshotTime);
        for (File file : files) {
            String status = file.hasChanged(snapshotTime) ? "Changed" : "No changes";
            System.out.println(file.fileName + " - " + status);
        }
    }


}
