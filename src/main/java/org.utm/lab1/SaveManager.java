package org.utm.lab1;

import java.io.*;

public class SaveManager {
    private static final String SAVE_FILE = "university_data.ser";

    // Serialize and save the University object to a file
    public void saveUniversity(University university) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(university);
            System.out.println("Program state saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize and load the University object from a file (if available)
    public University loadUniversity() {
        University university = null;
        File file = new File(SAVE_FILE);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
                university = (University) ois.readObject();
                System.out.println("Program state loaded.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return university != null ? university : new University(); // Return a new University if no saved data found
    }
}
