package studyMateSuperPackage.shortNotes;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class NoteList implements Externalizable {

    private static final long serialVersionUID = 6L;

    private List<Note> notes = new ArrayList<>();

    public void newNote(Note sourceNote) {
        Note note = new Note(sourceNote);
        notes.add(note);
        note.show();
    }

    public void newNote() {
        newNote(null);
    }

    boolean showAll() {
        for (Note note : notes) {
            note.show();
        }
        return notes.size() > 0;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).isEmpty()) {
                System.out.println("Is empty: " + notes.get(i));
                notes.remove(i);
                i--;
            }
        }
        out.writeObject(notes);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ArrayList list = (ArrayList) in.readObject();
        // checks if every object in list is of Note type
        for (Object obj : list) {
            if (!(obj instanceof Note)) {
                System.out.println("not note type");
                list.remove(obj);
            }
        }

        notes = (ArrayList<Note>) list;
    }
}
