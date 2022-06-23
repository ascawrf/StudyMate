package studyMateSuperPackage.ControllerClasses;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeTableManagerController<cmbcourse, static_cmbcourse> extends TimeTableController implements Initializable {

    @FXML
    public Parent fxml;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;




    @FXML
    private TextField txttime,  txtcourse, txtvenue, txtlecturer, getCourseText;
    @FXML
    private TabPane mainTabPane;


    @FXML
    private Button btnSubmitDetails;

    @FXML
    private ComboBox<String> cmbtime, cmbcourse, cmbvenue, cmblecturer;

    public static ComboBox<String> static_cmbcourse;
    public static ComboBox<String> static_cmbvenue;
    public static ComboBox<String> static_cmblecturer;
    public static ComboBox<String> static_cmbtime;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        static_cmbcourse = cmbcourse;
        static_cmblecturer = cmblecturer;
        static_cmbvenue = cmbvenue;
        static_cmbtime = cmbtime;

    }



    private void notSelected(){

    }

    @FXML
    private void courseLoader() {
        cmbcourse.setItems(FXCollections.observableArrayList(getCourse()));
    }

    @FXML
    private void venueLoader() {
        cmbvenue.setItems(FXCollections.observableArrayList(getVenue()));
    }

    @FXML
    private void lecturerLoader() {
        cmblecturer.setItems(FXCollections.observableArrayList(getLecturer()));
    }

    @FXML
    private void timeLoader() {
        cmbtime.setItems(FXCollections.observableArrayList(getTime()));
    }


    private List<String> getTime() {
        List<String> timeOptions = new ArrayList<>();
        try {
            ConnectionData();

            String query = "select time from tabledetails";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                timeOptions.add(set.getString("time"));
            }
            statement.close();
            set.close();

            return timeOptions;

        } catch (SQLException ex) {
            Logger.getLogger(TimeTableManagerController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<String> getCourse() {
        List<String> courseOptions = new ArrayList<>();
        try {
            ConnectionData();

            String query = "select course from tabledetails";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                courseOptions.add(set.getString("course"));
            }
            statement.close();
            set.close();

            return courseOptions;

        } catch (SQLException ex) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<String> getVenue() {
        List<String> venueOptions = new ArrayList<>();
        try {
            ConnectionData();

            String query = "select venue from tabledetails";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                venueOptions.add(set.getString("venue"));
            }
            statement.close();
            set.close();

            return venueOptions;

        } catch (SQLException ex) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    private List<String> getLecturer() {
        List<String> lecturerOptions = new ArrayList<>();
        try {
            ConnectionData();

            String query = "select lecturer from tabledetails";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                lecturerOptions.add(set.getString("lecturer"));
            }
            statement.close();
            set.close();

            return lecturerOptions;

        } catch (SQLException ex) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    @FXML
    private void addDetails(ActionEvent event) throws IOException {
        try {
            ConnectionData();
            pst = con.prepareStatement("INSERT INTO tabledetails ( course, venue, lecturer,time) VALUES (?,?,?,?)");
            pst.setString(1, txtcourse.getText());
            pst.setString(2, txtvenue.getText());
            pst.setString(3, txtlecturer.getText());
            pst.setString(4, txttime.getText());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("A row is updated");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Information has been stored successfully");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeTableManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ConnectionData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studymateproject",
                    "root", "19THwarlock*@1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void initializeCourse(ActionEvent event) {
        if(cmbcourse.getSelectionModel().getSelectedItem().equals("")) return;
        if(cmbvenue.getSelectionModel().getSelectedItem().equals("")) return;
        if(cmblecturer.getSelectionModel().getSelectedItem().equals("")) return;
        if(cmbtime.getSelectionModel().getSelectedItem().equals("")) return;

        try {
            FXMLLoader periodSelector = new FXMLLoader(getClass().getResource("../FxmlFiles/PeriodSelector.fxml"));
            fxml = periodSelector.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxml));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }

