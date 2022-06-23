package studyMateSuperPackage.shortNotes;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class NoteController {

    @FXML
    public void close(MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).getScene().getWindow().hide();
    }

    public void newNote(MouseEvent mouseEvent) {
        Note srcNote = (Note) ((Node) mouseEvent.getSource()).getScene().getWindow();
        MainNotes.newNote(srcNote);
    }

    public void updateText(KeyEvent listener) {
        TextArea textArea = (TextArea) listener.getSource();
        textArea.getScene().getWindow();

    }
}
