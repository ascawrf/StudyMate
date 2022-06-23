package studyMateSuperPackage.ControllerClasses;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicFile {
    private MediaPlayer musicPlayer;

    public void itWorked() {
        String uriString = new File("C:\\Users\\ASCAWRF\\Desktop\\MYMUSIC\\src\\sample\\mp3Files\\wavFile.wav").toURI().toString();
        musicPlayer = new MediaPlayer(new Media(uriString));
        musicPlayer.setVolume(0.4);
        musicPlayer.play();
    }
}
