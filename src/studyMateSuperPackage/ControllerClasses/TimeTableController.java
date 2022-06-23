package studyMateSuperPackage.ControllerClasses;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeTableController implements Initializable {


    // DECLARING STATIC LABELS TO INITIALIZE THE FX ID FIELDS
    public static Label static_mondayDetail1a, static_mondayDetail1b, static_mondayDetail1c,static_mondayDetail1d;
    public static Label static_mondayDetail2a, static_mondayDetail2b, static_mondayDetail2c,static_mondayDetail2d;
    public static Label static_mondayDetail3a, static_mondayDetail3b, static_mondayDetail3c,static_mondayDetail3d;
    public static Label static_mondayDetail4a, static_mondayDetail4b, static_mondayDetail4c,static_mondayDetail4d;
    public static Label static_mondayDetail5a, static_mondayDetail5b, static_mondayDetail5c,static_mondayDetail5d;

    public static Label static_tuesdayDetail1a, static_tuesdayDetail1b, static_tuesdayDetail1c,static_tuesdayDetail1d;
    public static Label static_tuesdayDetail2a, static_tuesdayDetail2b, static_tuesdayDetail2c,static_tuesdayDetail2d;
    public static Label static_tuesdayDetail3a, static_tuesdayDetail3b, static_tuesdayDetail3c,static_tuesdayDetail3d;
    public static Label static_tuesdayDetail4a, static_tuesdayDetail4b, static_tuesdayDetail4c,static_tuesdayDetail4d;
    public static Label static_tuesdayDetail5a, static_tuesdayDetail5b, static_tuesdayDetail5c,static_tuesdayDetail5d;

    public static Label static_wednesdayDetail1a, static_wednesdayDetail1b, static_wednesdayDetail1c,static_wednesdayDetail1d;
    public static Label static_wednesdayDetail2a, static_wednesdayDetail2b, static_wednesdayDetail2c,static_wednesdayDetail2d;
    public static Label static_wednesdayDetail3a, static_wednesdayDetail3b, static_wednesdayDetail3c,static_wednesdayDetail3d;
    public static Label static_wednesdayDetail4a, static_wednesdayDetail4b, static_wednesdayDetail4c,static_wednesdayDetail4d;
    public static Label static_wednesdayDetail5a, static_wednesdayDetail5b, static_wednesdayDetail5c,static_wednesdayDetail5d;

    public static Label static_thursdayDetail1a, static_thursdayDetail1b, static_thursdayDetail1c,static_thursdayDetail1d;
    public static Label static_thursdayDetail2a, static_thursdayDetail2b, static_thursdayDetail2c,static_thursdayDetail2d;
    public static Label static_thursdayDetail3a, static_thursdayDetail3b, static_thursdayDetail3c,static_thursdayDetail3d;
    public static Label static_thursdayDetail4a, static_thursdayDetail4b, static_thursdayDetail4c,static_thursdayDetail4d;
    public static Label static_thursdayDetail5a, static_thursdayDetail5b, static_thursdayDetail5c,static_thursdayDetail5d;

    public static Label static_fridayDetail1a, static_fridayDetail1b, static_fridayDetail1c,static_fridayDetail1d;
    public static Label static_fridayDetail2a, static_fridayDetail2b, static_fridayDetail2c,static_fridayDetail2d;
    public static Label static_fridayDetail3a, static_fridayDetail3b, static_fridayDetail3c,static_fridayDetail3d;
    public static Label static_fridayDetail4a, static_fridayDetail4b, static_fridayDetail4c,static_fridayDetail4d;
    public static Label static_fridayDetail5a, static_fridayDetail5b, static_fridayDetail5c,static_fridayDetail5d;

    @FXML
    public Tooltip mytooltip;

    @FXML
    public Parent fxml;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    // FX ID NAMES. LABELS IN EACH ANCHOR PANE BOX THROUGH OUT PERIODS OF THE DAY
    @FXML
    private Label mondayDetail1a, mondayDetail1b, mondayDetail1c,mondayDetail1d;
    @FXML
    private Label mondayDetail2a, mondayDetail2b, mondayDetail2c,mondayDetail2d;
    @FXML
    private Label mondayDetail3a, mondayDetail3b, mondayDetail3c,mondayDetail3d;
    @FXML
    private Label mondayDetail4a, mondayDetail4b, mondayDetail4c,mondayDetail4d;
    @FXML
    private Label mondayDetail5a, mondayDetail5b, mondayDetail5c,mondayDetail5d;

    @FXML
    private Label tuesdayDetail1a, tuesdayDetail1b, tuesdayDetail1c,tuesdayDetail1d;
    @FXML
    private Label tuesdayDetail2a, tuesdayDetail2b, tuesdayDetail2c,tuesdayDetail2d;
    @FXML
    private Label tuesdayDetail3a, tuesdayDetail3b, tuesdayDetail3c,tuesdayDetail3d;
    @FXML
    private Label tuesdayDetail4a, tuesdayDetail4b, tuesdayDetail4c,tuesdayDetail4d;
    @FXML
    private Label tuesdayDetail5a, tuesdayDetail5b, tuesdayDetail5c,tuesdayDetail5d;


    @FXML
    private Label wednesdayDetail1a, wednesdayDetail1b, wednesdayDetail1c,wednesdayDetail1d;
    @FXML
    private Label wednesdayDetail2a, wednesdayDetail2b, wednesdayDetail2c,wednesdayDetail2d;
    @FXML
    private Label wednesdayDetail3a, wednesdayDetail3b, wednesdayDetail3c,wednesdayDetail3d;
    @FXML
    private Label wednesdayDetail4a, wednesdayDetail4b, wednesdayDetail4c,wednesdayDetail4d;
    @FXML
    private Label wednesdayDetail5a, wednesdayDetail5b, wednesdayDetail5c,wednesdayDetail5d;


    @FXML
    private Label thursdayDetail1a, thursdayDetail1b, thursdayDetail1c,thursdayDetail1d;
    @FXML
    private Label thursdayDetail2a, thursdayDetail2b, thursdayDetail2c,thursdayDetail2d;
    @FXML
    private Label thursdayDetail3a, thursdayDetail3b, thursdayDetail3c,thursdayDetail3d;
    @FXML
    private Label thursdayDetail4a, thursdayDetail4b, thursdayDetail4c,thursdayDetail4d;
    @FXML
    private Label thursdayDetail5a, thursdayDetail5b, thursdayDetail5c,thursdayDetail5d;


    @FXML
    private Label fridayDetail1a, fridayDetail1b, fridayDetail1c,fridayDetail1d;
    @FXML
    private Label fridayDetail2a, fridayDetail2b, fridayDetail2c,fridayDetail2d;
    @FXML
    private Label fridayDetail3a, fridayDetail3b, fridayDetail3c,fridayDetail3d;
    @FXML
    private Label fridayDetail4a, fridayDetail4b, fridayDetail4c,fridayDetail4d;
    @FXML
    private Label fridayDetail5a, fridayDetail5b, fridayDetail5c,fridayDetail5d;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        static_mondayDetail1a = mondayDetail1a;
        static_mondayDetail1b = mondayDetail1b;
        static_mondayDetail1c = mondayDetail1c;
        static_mondayDetail1d = mondayDetail1d;

        static_mondayDetail2a = mondayDetail2a;
        static_mondayDetail2b = mondayDetail2b;
        static_mondayDetail2c = mondayDetail2c;
        static_mondayDetail2d = mondayDetail2d;

        static_mondayDetail3a = mondayDetail3a;
        static_mondayDetail3b = mondayDetail3b;
        static_mondayDetail3c = mondayDetail3c;
        static_mondayDetail3d = mondayDetail3d;

        static_mondayDetail4a = mondayDetail4a;
        static_mondayDetail4b = mondayDetail4b;
        static_mondayDetail4c = mondayDetail4c;
        static_mondayDetail4d = mondayDetail4d;

        static_mondayDetail5a = mondayDetail5a;
        static_mondayDetail5b = mondayDetail5b;
        static_mondayDetail5d = mondayDetail5d;

        static_tuesdayDetail1a = tuesdayDetail1a;
        static_tuesdayDetail1b = tuesdayDetail1b;
        static_tuesdayDetail1c = tuesdayDetail1c;
        static_tuesdayDetail1d = tuesdayDetail1d;

        static_tuesdayDetail2a = tuesdayDetail2a;
        static_tuesdayDetail2b = tuesdayDetail2b;
        static_tuesdayDetail2c = tuesdayDetail2c;
        static_tuesdayDetail2d = tuesdayDetail2d;

        static_tuesdayDetail3a = tuesdayDetail3a;
        static_tuesdayDetail3b = tuesdayDetail3b;
        static_tuesdayDetail3c = tuesdayDetail3c;
        static_tuesdayDetail3d = tuesdayDetail3d;

        static_tuesdayDetail4a = tuesdayDetail4a;
        static_tuesdayDetail4b = tuesdayDetail4b;
        static_tuesdayDetail4c = tuesdayDetail4c;
        static_tuesdayDetail4d = tuesdayDetail4d;

        static_tuesdayDetail5a = tuesdayDetail5a;
        static_tuesdayDetail5b = tuesdayDetail5b;
        static_tuesdayDetail5c = tuesdayDetail5c;
        static_tuesdayDetail5d = tuesdayDetail5d;

        static_wednesdayDetail1a = wednesdayDetail1a;
        static_wednesdayDetail1b = wednesdayDetail1b;
        static_wednesdayDetail1c = wednesdayDetail1c;
        static_wednesdayDetail1d = wednesdayDetail1d;

        static_wednesdayDetail2a = wednesdayDetail2a;
        static_wednesdayDetail2b = wednesdayDetail2b;
        static_wednesdayDetail2c = wednesdayDetail2c;
        static_wednesdayDetail2d = wednesdayDetail2d;

        static_wednesdayDetail3a = wednesdayDetail3a;
        static_wednesdayDetail3b = wednesdayDetail3b;
        static_wednesdayDetail3c = wednesdayDetail3c;
        static_wednesdayDetail3d = wednesdayDetail3d;

        static_wednesdayDetail4a = wednesdayDetail4a;
        static_wednesdayDetail4b = wednesdayDetail4b;
        static_wednesdayDetail4c = wednesdayDetail4c;
        static_wednesdayDetail4d = wednesdayDetail4d;

        static_wednesdayDetail5a = wednesdayDetail5a;
        static_wednesdayDetail5b = wednesdayDetail5b;
        static_wednesdayDetail5c = wednesdayDetail5c;
        static_wednesdayDetail5d = wednesdayDetail5d;

        static_thursdayDetail1a = thursdayDetail1a;
        static_thursdayDetail1b = thursdayDetail1b;
        static_thursdayDetail1c = thursdayDetail1c;
        static_thursdayDetail1d = thursdayDetail1d;

        static_thursdayDetail2a = thursdayDetail2a;
        static_thursdayDetail2b = thursdayDetail2b;
        static_thursdayDetail2c = thursdayDetail2c;
        static_thursdayDetail2d = thursdayDetail2d;

        static_thursdayDetail3a = thursdayDetail3a;
        static_thursdayDetail3b = thursdayDetail3b;
        static_thursdayDetail3c = thursdayDetail3c;
        static_thursdayDetail3d = thursdayDetail3d;

        static_thursdayDetail4a = thursdayDetail4a;
        static_thursdayDetail4b = thursdayDetail4b;
        static_thursdayDetail4c = thursdayDetail4c;
        static_thursdayDetail4d = thursdayDetail4d;

        static_thursdayDetail5a = thursdayDetail5a;
        static_thursdayDetail5b = thursdayDetail5b;
        static_thursdayDetail5c = thursdayDetail5c;
        static_thursdayDetail5d = thursdayDetail5d;

        static_fridayDetail1a = fridayDetail1a;
        static_fridayDetail1b = fridayDetail1b;
        static_fridayDetail1c = fridayDetail1c;
        static_fridayDetail1d = fridayDetail1d;

        static_fridayDetail2a = fridayDetail2a;
        static_fridayDetail2b = fridayDetail2b;
        static_fridayDetail2c = fridayDetail2c;
        static_fridayDetail2d = fridayDetail2d;

        static_fridayDetail3a = fridayDetail3a;
        static_fridayDetail3b = fridayDetail3b;
        static_fridayDetail3c = fridayDetail3c;
        static_fridayDetail3d = fridayDetail3d;

        static_fridayDetail4a = fridayDetail4a;
        static_fridayDetail4b = fridayDetail4b;
        static_fridayDetail4c = fridayDetail4c;
        static_fridayDetail4d = fridayDetail4d;

        static_fridayDetail5a = fridayDetail5a;
        static_fridayDetail5b = fridayDetail5b;
        static_fridayDetail5c = fridayDetail5c;
        static_fridayDetail5d = fridayDetail5d;


        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM mondayperiod1table ");
            while (rs.next()) {
                static_mondayDetail1a.setText(rs.getString(2));
                static_mondayDetail1b.setText(rs.getString(3));
                static_mondayDetail1c.setText(rs.getString(4));
                static_mondayDetail1d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM mondayperiod2table ");
            while (rs.next()) {
                static_mondayDetail2a.setText(rs.getString(2));
                static_mondayDetail2b.setText(rs.getString(3));
                static_mondayDetail2c.setText(rs.getString(4));
                static_mondayDetail2d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM mondayperiod3table ");
            while (rs.next()) {
                static_mondayDetail3a.setText(rs.getString(2));
                static_mondayDetail3b.setText(rs.getString(3));
                static_mondayDetail3c.setText(rs.getString(4));
                static_mondayDetail3d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM mondayperiod4table ");
            while (rs.next()) {
                static_mondayDetail4a.setText(rs.getString(2));
                static_mondayDetail4b.setText(rs.getString(3));
                static_mondayDetail4c.setText(rs.getString(4));
                static_mondayDetail4d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM mondayperiod5table ");
            while (rs.next()) {
                static_mondayDetail5a.setText(rs.getString(2));
                static_mondayDetail5b.setText(rs.getString(3));
                static_mondayDetail5c.setText(rs.getString(4));
                static_mondayDetail5d.setText(rs.getString(5));


            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }



        // TUESDAY CONNECTION
        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tuesdayperiod1table ");
            while (rs.next()) {
                static_tuesdayDetail1a.setText(rs.getString(2));
                static_tuesdayDetail1b.setText(rs.getString(3));
                static_tuesdayDetail1c.setText(rs.getString(4));
                static_tuesdayDetail1d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tuesdayperiod2table ");
            while (rs.next()) {
                static_tuesdayDetail2a.setText(rs.getString(2));
                static_tuesdayDetail2b.setText(rs.getString(3));
                static_tuesdayDetail2c.setText(rs.getString(4));
                static_tuesdayDetail2d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tuesdayperiod3table ");
            while (rs.next()) {
                static_tuesdayDetail3a.setText(rs.getString(2));
                static_tuesdayDetail3b.setText(rs.getString(3));
                static_tuesdayDetail3c.setText(rs.getString(4));
                static_tuesdayDetail3d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tuesdayperiod4table ");
            while (rs.next()) {
                static_tuesdayDetail4a.setText(rs.getString(2));
                static_tuesdayDetail4b.setText(rs.getString(3));
                static_tuesdayDetail4c.setText(rs.getString(4));
                static_tuesdayDetail4d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tuesdayperiod5table ");
            while (rs.next()) {
                static_tuesdayDetail5a.setText(rs.getString(2));
                static_tuesdayDetail5b.setText(rs.getString(3));
                static_tuesdayDetail5c.setText(rs.getString(4));
                static_tuesdayDetail5c.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }



        // WEDNESDAY CONNECTION
        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM wednesdayperiod1table ");
            while (rs.next()) {
                static_wednesdayDetail1a.setText(rs.getString(2));
                static_wednesdayDetail1b.setText(rs.getString(3));
                static_wednesdayDetail1c.setText(rs.getString(4));
                static_wednesdayDetail1d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM wednesdayperiod2table ");
            while (rs.next()) {
                static_wednesdayDetail2a.setText(rs.getString(2));
                static_wednesdayDetail2b.setText(rs.getString(3));
                static_wednesdayDetail2c.setText(rs.getString(4));
                static_wednesdayDetail2d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM wednesdayperiod3table ");
            while (rs.next()) {
                static_wednesdayDetail3a.setText(rs.getString(2));
                static_wednesdayDetail3b.setText(rs.getString(3));
                static_wednesdayDetail3c.setText(rs.getString(4));
                static_wednesdayDetail3d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM wednesdayperiod4table ");
            while (rs.next()) {
                static_wednesdayDetail4a.setText(rs.getString(2));
                static_wednesdayDetail4b.setText(rs.getString(3));
                static_wednesdayDetail4c.setText(rs.getString(4));
                static_wednesdayDetail4d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM wednesdayperiod5table ");
            while (rs.next()) {
                static_wednesdayDetail5a.setText(rs.getString(2));
                static_wednesdayDetail5b.setText(rs.getString(3));
                static_wednesdayDetail5c.setText(rs.getString(4));
                static_wednesdayDetail5d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }


        // THURSDAY CONNECTION
        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM thursdayperiod1table ");
            while (rs.next()) {
                static_thursdayDetail1a.setText(rs.getString(2));
                static_thursdayDetail1b.setText(rs.getString(3));
                static_thursdayDetail1c.setText(rs.getString(4));
                static_thursdayDetail1d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM thursdayperiod2table ");
            while (rs.next()) {
                static_thursdayDetail2a.setText(rs.getString(2));
                static_thursdayDetail2b.setText(rs.getString(3));
                static_thursdayDetail2c.setText(rs.getString(4));
                static_thursdayDetail2d.setText(rs.getString(5));

            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }


        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM thursdayperiod3table ");
            while (rs.next()) {
                static_thursdayDetail3a.setText(rs.getString(2));
                static_thursdayDetail3b.setText(rs.getString(3));
                static_thursdayDetail3c.setText(rs.getString(4));
                static_thursdayDetail3d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM thursdayperiod4table ");
            while (rs.next()) {
                static_thursdayDetail4a.setText(rs.getString(2));
                static_thursdayDetail4b.setText(rs.getString(3));
                static_thursdayDetail4c.setText(rs.getString(4));
                static_thursdayDetail4d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM thursdayperiod5table ");
            while (rs.next()) {
                static_thursdayDetail5a.setText(rs.getString(2));
                static_thursdayDetail5b.setText(rs.getString(3));
                static_thursdayDetail5c.setText(rs.getString(4));
                static_thursdayDetail5d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }


        // FRIDAY CONNECTION
        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM fridayperiod1table ");
            while (rs.next()) {
                static_fridayDetail1a.setText(rs.getString(2));
                static_fridayDetail1b.setText(rs.getString(3));
                static_fridayDetail1c.setText(rs.getString(4));
                static_fridayDetail1d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM fridayperiod2table ");
            while (rs.next()) {
                static_fridayDetail2a.setText(rs.getString(2));
                static_fridayDetail2b.setText(rs.getString(3));
                static_fridayDetail2c.setText(rs.getString(4));
                static_fridayDetail2d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM fridayperiod3table ");
            while (rs.next()) {
                static_fridayDetail3a.setText(rs.getString(2));
                static_fridayDetail3b.setText(rs.getString(3));
                static_fridayDetail3c.setText(rs.getString(4));
                static_fridayDetail3d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM fridayperiod4table ");
            while (rs.next()) {
                static_fridayDetail4a.setText(rs.getString(2));
                static_fridayDetail4b.setText(rs.getString(3));
                static_fridayDetail4c.setText(rs.getString(4));
                static_fridayDetail4d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            ConnectionData();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM fridayperiod5table ");
            while (rs.next()) {
                static_fridayDetail5a.setText(rs.getString(2));
                static_fridayDetail5b.setText(rs.getString(3));
                static_fridayDetail5c.setText(rs.getString(4));
                static_fridayDetail5d.setText(rs.getString(5));
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, e);
        }

    }


    public void openMenu(ActionEvent event) {
        try {
            FXMLLoader timeTable = new FXMLLoader(getClass().getResource("../FxmlFiles/TimeTableManager.fxml"));
            fxml = timeTable.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(fxml));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

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


}

