package studyMateSuperPackage.ControllerClasses;

import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeriodSelectorController extends TimeTableController implements Initializable {


    @FXML
    public Parent fxml;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private ComboBox<String> daySelector;
    @FXML
    private ComboBox<String> periodSelector;
    @FXML
    private Button saveSelector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    private void dayLoader() {
        daySelector.setItems(FXCollections.observableArrayList(getday()));
    }

    @FXML
    private void periodLoader() {
        periodSelector.setItems(FXCollections.observableArrayList(getperiod()));
    }
private void successMessage(){
        JOptionPane.showMessageDialog(null, "Table Updated Successfully");
}
    private List<String> getday() { // get days into combobox
        List<String> dayOptions = new ArrayList<>();
        try {
            ConnectionData();

            String query = "select days from periodposition";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                dayOptions.add(set.getString("days"));
            }
            statement.close();
            set.close();

            return dayOptions;

        } catch (SQLException ex) {
            Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<String> getperiod() {
        List<String> periodOptions = new ArrayList<>();
        try {
            ConnectionData();

            String query = "select periods from periodposition";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                periodOptions.add(set.getString("periods"));
            }
            statement.close();
            set.close();

            return periodOptions;

        } catch (SQLException ex) {
            Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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

    @FXML
    private void selectedPosition(ActionEvent event) {
        if (daySelector.getSelectionModel().getSelectedItem().equals("")) return;
        if (periodSelector.getSelectionModel().getSelectedItem().equals("")) return;

        if ((daySelector.getSelectionModel().getSelectedItem().equals("Monday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("1")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO mondayperiod1table (mondayperiod1course, mondayperiod1lecturer, mondayperiod1venue,mondayperiod1time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_mondayDetail1a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_mondayDetail1b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_mondayDetail1c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_mondayDetail1d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
              successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Monday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("2")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO mondayperiod2table (mondayperiod2course, mondayperiod2lecturer, mondayperiod2venue,mondayperiod2time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_mondayDetail2a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_mondayDetail2b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_mondayDetail2c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_mondayDetail2d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Monday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("3")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO mondayperiod3table (mondayperiod3course, mondayperiod3lecturer, mondayperiod3venue, mondayperiod3time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_mondayDetail3a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_mondayDetail3b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_mondayDetail3c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_mondayDetail3d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Monday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("4")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO mondayperiod4table (mondayperiod4course, mondayperiod4lecturer, mondayperiod4venue, mondayperiod4time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_mondayDetail4a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_mondayDetail4b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_mondayDetail4c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_mondayDetail4d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Monday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("5")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO mondayperiod5table (mondayperiod5course, mondayperiod5lecturer, mondayperiod5venue, mondayperiod5time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_mondayDetail5a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_mondayDetail5b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_mondayDetail5c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_mondayDetail5d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }


            //TUESDAY POSITIONING
        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Tuesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("1")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO tuesdayperiod1table (tuesdayperiod1course, tuesdayperiod1lecturer, tuesdayperiod1venue,tuesdayperiod1time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_tuesdayDetail1a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail1b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail1c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail1d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Tuesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("2")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO tuesdayperiod2table (tuesdayperiod2course, tuesdayperiod2lecturer, tuesdayperiod2venue, tuesdayperiod2time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_tuesdayDetail2a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail2b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail2c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail2d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Tuesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("3")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO tuesdayperiod3table (tuesdayperiodc3ourse, tuesdayperiod3lecturer, tuesdayperiod3venue,tuesdayperiod3time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_tuesdayDetail3a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail3b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail3c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail3d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Tuesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("4")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO tuesdayperiod4table (tuesdayperiod4course, tuesdayperiod4lecturer, tuesdayperiod4venue,tuesdayperiod4 ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_tuesdayDetail4a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail4b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail4c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail4d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
                successMessage();

            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Tuesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("5")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO tuesdayperiod5table (tuesdayperiod2course, tuesdayperiod5lecturer, tuesdayperiod5venue ,tuesdayperiod5time) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_tuesdayDetail5a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail5b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail5c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_tuesdayDetail5d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
              successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }


            //WEDNESDAY POSITIONING

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Wednesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("1")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO wednesdayperiod1table (wednesdayperiod1course, wednessdayperiod1lecturer, wednesdayperiod1venue,wednesdayperiod1time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_wednesdayDetail1a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail1b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail1c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail1d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Wednesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("2")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO wednesdayperiod2table (wednesdayperiod2course, wednesdayperiod2lecturer, wednesdayperiod2venue,wednesdayperiod2time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_wednesdayDetail2a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail2b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail2c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail2d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Wednesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("3")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO wednesdayperiod3table (wednesdayperiod3course, wednesdayperiod3lecturer, wednesdayperiod3venue,wednesdayperiod3time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_wednesdayDetail3a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail3b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail3c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail3d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Wednesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("4")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO wednesdayperiod4table (wednesdayperiod4course, wednesdayperiod4lecturer, wednesdayperiod4venue,wednesdayperiod4time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_wednesdayDetail4a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail4b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail4c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail4d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
               successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Wednesday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("5")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO wednesdayperiod5table (wednesdayperiod5course, wednesdayperiod5lecturer, wednesdayperiod5venue,wednesdayperiod5time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_wednesdayDetail5a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail5b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail5c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_wednesdayDetail5d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }


            //THURSDAY POSITIONING

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Thursday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("1")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO thursdayperiod1table (thursdayperiod1course, thursdayperiod1lecturer, thursdayperiod1venue, thursdayperiod1time) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_thursdayDetail1a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_thursdayDetail1b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_thursdayDetail1c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_thursdayDetail1d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Thursday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("2")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO thursdayperiod2table (thursdayperiod2course, thursdayperiod2lecturer, thursdayperiod2venue ,thursdayperiod2) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_thursdayDetail2a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_thursdayDetail2b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_thursdayDetail2c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_thursdayDetail2d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Thursday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("3")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO thursdayperiod3table (thursdayperiod3course, thursdayperiod3lecturer, thursdayperiod3venue,thursdayperiod3time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_thursdayDetail3a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_thursdayDetail3b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_thursdayDetail3c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_thursdayDetail3d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Thursday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("4")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO thursdayperiod4table (thursdayperiod4course, thursdayperiod4lecturer, thursdayperiod4venue,  thursdayperiod4time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_thursdayDetail4a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_thursdayDetail4b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_thursdayDetail4c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_thursdayDetail4d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
               successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Thursday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("5")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO thursdayperiod5table (thursdayperiod5course, thursdayperiod5lecturer, thursdayperiod5venue, tthursdayperiod5time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_thursdayDetail5a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_thursdayDetail5b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_thursdayDetail5c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_thursdayDetail5d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
               successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }





            //FRIDAY POSITIONING

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Friday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("1")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO fridayperiod1table (fridayperiod1course, fridayperiod1lecturer, fridayperiod1venue,fridayperiod1time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_fridayDetail1a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_fridayDetail1b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_fridayDetail1c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_fridayDetail1d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
               successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Friday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("2")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO fridayperiod2table (fridayperiod2course, fridayperiod2lecturer, fridayperiod2venue,fridayperiod2time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_fridayDetail2a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_fridayDetail2b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_fridayDetail2c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_fridayDetail2d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Friday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("3")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO fridayperiod3table (fridayperiod3course, fridayperiod3lecturer, fridayperiod3venue, fridayperiod3time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_fridayDetail3a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_fridayDetail3b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_fridayDetail3c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_fridayDetail3d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
               successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Friday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("4")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO fridayperiod4table (fridayperiod4course, fridayperiod4lecturer, fridayperiod4venue,fridayperiod4time ) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_fridayDetail4a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_fridayDetail4b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_fridayDetail4c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_fridayDetail4d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                }
                successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ((daySelector.getSelectionModel().getSelectedItem().equals("Friday")) && (periodSelector.getSelectionModel().getSelectedItem().equals(("5")))) {
            try {
                ConnectionData();
                pst = con.prepareStatement("INSERT INTO fridayperiod5table (fridayperiod5course, fridayperiod5lecturer, fridayperiod5venue , fridayperiod5time) VALUES (?,?,?,?)");
                pst.setString(1, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                pst.setString(2, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                pst.setString(3, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                pst.setString(4, studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    static_fridayDetail5a.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbcourse.getSelectionModel().getSelectedItem());
                    static_fridayDetail5b.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmblecturer.getSelectionModel().getSelectedItem());
                    static_fridayDetail5c.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbvenue.getSelectionModel().getSelectedItem());
                    static_fridayDetail5d.setText(studyMateSuperPackage.ControllerClasses.TimeTableManagerController.static_cmbtime.getSelectionModel().getSelectedItem());
                }
              successMessage();
            } catch (SQLException ex) {
                Logger.getLogger(PeriodSelectorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Error in updating the table. Please try again!");
        }

    }

}
