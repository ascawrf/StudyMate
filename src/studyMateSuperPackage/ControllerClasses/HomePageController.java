package studyMateSuperPackage.ControllerClasses;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePageController implements Initializable {
    final DirectoryChooser directoryChooser = new DirectoryChooser();
    private final Desktop desktop = Desktop.getDesktop();
    @FXML
    public Parent fxml;
    @FXML
    private Circle homeImage;
    @FXML
    public JFXButton getDashBoard;
    @FXML
    public BorderPane borderPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox drawerVBox;

    @FXML
    Circle min;
    @FXML
    Circle close;

    private File file;
    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DashBoard(null);
       Image image = new Image("studyMateSuperPackage/image/appLogo.png");

    }


    @FXML
    private void grabScene() {
        borderPane.setOnMousePressed((MouseEvent event2) -> {  // Grab your root here
            xOffset = event2.getSceneX();
            yOffset = event2.getSceneY();
        });
    }


    @FXML
    private void moveScene() {
        borderPane.setOnMouseDragged((MouseEvent event) -> {  // Grab your root here
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    public void minimizeClick(MouseEvent event) throws IOException {

        ((Stage) ((Circle) event.getSource()).getScene().getWindow()).setIconified(true);

    }

    /**** close screen ****/
    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);

    }

//    @FXML
//    private void exitButton() {
//        System.exit(0);
//    }
//
//    @FXML
//    private void minimizeButton() {
//        Stage stage = (Stage) borderPane.getScene().getWindow();
//        stage.setIconified(true);
//    }


    @FXML
    private void ToDoScene(ActionEvent event) {
        Parent loader = null;
        try {
          //  studyMateSuperPackage.loader = FXMLLoader.load(getClass().getResource("../fxmlFiles/Todo.fxml"));
            loader = FXMLLoader.load(getClass().getResource("/studyMateSuperPackage/ui/mainView/MainView.fxml"));
           // studyMateSuperPackage.loader = FXMLLoader.load(getClass().getResource("/studyMateSuperPackage/ui/mainView/MusicPlayer.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);

        }
        borderPane.setCenter(loader);
    }

    @FXML
    public void TimeTable(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../fxmlFiles/TimeTable.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);

        }
        borderPane.setCenter(loader);
    }

    @FXML
    private void DashBoard(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../FxmlFiles/Dashboard.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);

        }
        borderPane.setCenter(loader);
    }

    @FXML
    private void createFile() throws IOException {
        File file = new File("C:\\Study Mate");
        if (!file.exists()) {
          Path p1 = (Path) Paths.get("C:\\Images\\Background");
            Path p2 = (Path) Paths.get("C:\\Images\\Foreground\\Necklace");
            Path p3 = (Path) Paths.get("C:\\Images\\Foreground\\Earrings");
            Path p4 = (Path) Paths.get("C:\\Images\\Foreground\\Etc");
            if (file.mkdir()) {
                System.out.println("Directory Created");
            } else {
                System.out.println("Directory not Created");
            }
            Files.createDirectories((java.nio.file.Path) p1);
            Files.createDirectories((java.nio.file.Path) p2);
            Files.createDirectories((java.nio.file.Path) p3);
            Files.createDirectories((java.nio.file.Path) p4);
        }
        fileChooser();
    }

    @FXML
    private void creatMultipleFiles(){

    }

    @FXML
    private void fileChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*txt", "*pdf"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );


        fileChooser.setInitialDirectory(new File(("C:\\Study Mate")));
        file = fileChooser.showOpenDialog(null);


        if (file != null) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void textChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*txt", "*docx")
        );

        fileChooser.setInitialDirectory(new File(("C:\\Study Mate\\Texts")));
        file = fileChooser.showOpenDialog(null);


        if (file != null) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void pdfChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*txt", "*pdf")
        );

        fileChooser.setInitialDirectory(new File(("C:\\Study Mate\\PDF")));
        file = fileChooser.showOpenDialog(null);


        if (file != null) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void videoChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.wav", "*.mp4")
        );

        fileChooser.setInitialDirectory(new File(("C:\\Study Mate\\Videos")));
        file = fileChooser.showOpenDialog(null);


        if (file != null) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void PowerPointChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PowerPoint Files", "*.pptx")
        );

        fileChooser.setInitialDirectory(new File(("C:\\Study Mate\\Slides")));
        file = fileChooser.showOpenDialog(null);


        if (file != null) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void gpaTable(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../FxmlFiles/view.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);

        }
        borderPane.setCenter(loader);
    }


    @FXML
    private void imageSelector(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(new File(("C:\\Study Mate")));
        file = fileChooser.showOpenDialog(null);

//        if (file != null) {
//            try {
//                Image image = new Image();
//                homeImage.setFill(new ImagePattern(file));//desktop.open(file);
//            } catch (IOException ex) {
//                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

//        Image image = new Image("studyMateSuperPackage/images/IMG_4257.png");
//        homeImage.setStroke(Color.LIGHTBLUE);
//        homeImage.setFill(new ImagePattern(image));
//        homeImage.setEffect(new DropShadow(+25d, 0d, +2d, Color.LIGHTBLUE));

    }
}


