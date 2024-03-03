/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travel.expense.calculator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private static final String FILENAME = "notes.txt";

    public static List<String> loadNotes() {
        List<String> notes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public static void saveNotes(List<String> notes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
