package studyMateSuperPackage.shortNotes;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;

public class MainNotes extends Application {

    private static final String dirName = "notes";
    private static final String saveFile = "notes.dat";
    private static final String filePath = Paths.get(dirName, saveFile).toString();

    private static NoteList notes;

    public static void main(String[] args) {
        launch(args);
    }

    static void newNote(Note srcNote) {
        notes.newNote(srcNote);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            System.out.println("Saving NoteList to memory.");
            locFile.writeObject(notes);
        }
    }

    private static void createNoteSaveFile() throws IOException {
        // create directory
        if (new File(dirName).mkdirs()) System.out.println("The notes directory has been create.");
        else System.out.println("No directory created because already exists");
        // create file
        if (new File(filePath).createNewFile()) System.out.println("Created the notes file");
        else System.out.println("No file create because already exists.");
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            createNoteSaveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInput locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            System.out.println("Successfully restored former NoteList.");
            notes = (NoteList) locFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Created a new NoteList (failed to restore former NoteList).");
            notes = new NoteList();
        }

        if (!notes.showAll()) notes.newNote();
    }
}
