package org.utm.lab2;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class FileMonitor {
    private static Date snapshotTime;
    private static List<MyFile> files;
    private static List<MyFile> previousFiles;
    private static Timer timer;

    public static void main(String[] args) {
        snapshotTime = new Date();
        files = new ArrayList<>();
        previousFiles = new ArrayList<>();

        String folderLocation = "C:\\Users\\danie\\Documents\\GitHub\\OOP_LABS\\";

        populateFileList(folderLocation);
        previousFiles.addAll(files);

        timer = new Timer();
        timer.schedule(new FileUpdateTask(), 0, 15 * 1000);

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
                timer.cancel();
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
            files.clear();
            for (File file : filesInFolder) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    MyFile fileObject = createFileObject(fileName);
                    if (fileObject != null) {
                        files.add(fileObject);
                    }
                }
            }
        }
    }

    private static MyFile createFileObject(String fileName) {
        String extension = getExtension(fileName);
        if (extension.equals("txt")) {
            return new TextFile(fileName);
        } else if (extension.equals("png") || extension.equals("jpg")) {
            return new ImageFile(fileName);
        } else if (extension.equals("py") || extension.equals("java")) {
            return new ProgramFile(fileName);
        }
        return null;
    }

    private static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

//    private static void updateFilesData() {
//        for (MyFile file : files) {
//            file.update();
//        }
//    }

    private static void displayFileInfo(String filename) {
        for (MyFile file : files) {
            if (file.getFileName().equals(filename)) {
                System.out.println(file.getInfo());
                return;
            }
        }
        System.out.println("File not found: " + filename);
    }

    private static void displayFileStatus() {
        System.out.println("File status since snapshot time: " + snapshotTime);

        for (MyFile file : files) {
            String status;

            if (file.hasChanged(snapshotTime)) {

                status = "Changed";
            } else status = "No Changes";

            System.out.println(file.getFileName() + " - " + status);

        }

        List<String> addedFiles = files.stream()
                .map(MyFile::getFileName)
                .filter(fileName -> previousFiles.stream().noneMatch(prevFile -> prevFile.getFileName().equals(fileName)))
                .collect(Collectors.toList());


        List<String> deletedFiles = previousFiles.stream()
                .map(MyFile::getFileName)
                .filter(fileName -> files.stream().noneMatch(currentFile -> currentFile.getFileName().equals(fileName)))
                .collect(Collectors.toList());

        if (!addedFiles.isEmpty()) {
            System.out.println("Added Files:\n");
            addedFiles.forEach(System.out::println);
        }

        if (!deletedFiles.isEmpty()) {
            System.out.println("Deleted Files:\n");
            deletedFiles.forEach(System.out::println);
        }

    }

    static class FileUpdateTask extends TimerTask {


        @Override
        public void run() {

            System.out.println("Updating file timestamps...\n");

            previousFiles.clear();
            previousFiles.addAll(files);

            String folderLocation = "C:\\Users\\danie\\Documents\\GitHub\\OOP_LABS\\";
            populateFileList(folderLocation);
            displayFileStatus();
            System.out.println("You can still enter a valid command: \n" +
                    "Commit\n" +
                    "info <file>\n" +
                    "status\n" +
                    "or exit:");

        }
    }

}