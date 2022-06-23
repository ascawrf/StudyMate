package studyMateSuperPackage.shortNotes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

class Note extends Stage implements Externalizable {

    private static final long serialVersionUID = 6L;

    private final static double X_OFFSET = 20;
    private final static double Y_OFFSET = 20;

    private double mDragXPos = 0;
    private double mDragYPos = 0;

    private double xPos;
    private double yPos;
    private String contents;

    public Note(Note parentNote) {
        super(StageStyle.TRANSPARENT);

        xPos = 0;
        yPos = 0;
        contents = "";

        try {
            boolean isFirst = parentNote == null;
            Parent root = FXMLLoader.load(getClass().getResource("note.fxml"));

            root.setOnMousePressed(event -> {
                mDragXPos = event.getSceneX();
                mDragYPos = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                xPos = event.getScreenX() - mDragXPos;
                yPos = event.getScreenY() - mDragYPos;
                setWindowX();
                setWindowY();
            });

            setScene(new Scene(root, 270, 240));
            xPos = X_OFFSET + (isFirst ? 0 : parentNote.getX());
            yPos = Y_OFFSET + (isFirst ? 0 : parentNote.getY());
            constructNote();

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public Note() {
        this(null);
    }

    boolean isEmpty() {
        return contents.isEmpty();
    }

    void setContents() {
        TextArea textArea = (TextArea) getScene().lookup("#noteContents");
        contents = textArea.getText();
    }

    private void constructNote() {
        setWindowX();
        setWindowY();
        restoreContents();
    }

    private void setWindowX() {
        setX(xPos);
    }

    private void setWindowY() {
        setY(yPos);
    }

    private void restoreContents() {
        TextArea textArea = (TextArea) getScene().lookup("#noteContents");
        textArea.setText(contents);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(xPos);
        out.writeDouble(yPos);
        out.writeUTF(contents);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        xPos = in.readDouble();
        yPos = in.readDouble();
        contents = in.readUTF();
        constructNote();
    }
}
