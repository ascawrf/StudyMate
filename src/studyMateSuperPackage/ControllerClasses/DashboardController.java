package studyMateSuperPackage.ControllerClasses;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import studyMateSuperPackage.database.DatabaseHelper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardController extends HomePageController implements Initializable {
    public ObservableList<GPAGradeItem> gpaDashboardDisplayGPAGradeItem = FXCollections.observableArrayList();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Date currentDate = new Date();

    @FXML
    private Label timeLabel;
    @FXML
    private JFXButton timetableNotification;
    @FXML
    private AnchorPane parent;
    @FXML
    private BorderPane dashBoardBorderPane;
    @FXML
    private BorderPane stickyBorderpane;
    @FXML
    private LineChart<Number, Number> linechart1;

    @FXML
    private BorderPane calendarBorderPane;
    @FXML
    private BorderPane parentBorder;
    @FXML
    private Label pictureName;
    @FXML
    private Label upcomingCourseTime;
    @FXML
    public FlowPane listflowpane;
    @FXML
    public ListView bookslistview;
    @FXML
    public Label remindertimelabel;
    @FXML
    public Label remindertitlelabel;
    @FXML
    private JFXButton viewAllButton;
    @FXML
    private JFXButton gotoGpa;
    @FXML
    private Circle imageCircle;
    @FXML
    private JFXButton addDashbordImage;
    @FXML
    private Label courseNameLabel1;
    @FXML
    private Label courseLecturerLabel1;
    @FXML
    private Label courseVenueLabel1;
    @FXML
    private Label courseNameLabel2;
    @FXML
    private Label courseLecturerLabel2;
    @FXML
    private Label courseVenueLabel2;


    Date date = new Date();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadNewNote(null);
        dashboardPeriodDisplay();

        SimpleDateFormat upcomingCourseTimeClock = new SimpleDateFormat(" EEEE dd MMM yyyy ");
        upcomingCourseTimeClock.format(date);
        upcomingCourseTime.setText(upcomingCourseTimeClock.format(date));

        initClock();
//        imageCircle.setStroke(Color.SEAGREEN);
//        Image image = new Image("studyMateSuperPackage/image/IMG_3196.JPG");
//        imageCircle.setFill(new ImagePattern(image));
//        imageCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.TRANSPARENT));

    }


    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
            timeLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
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
    private void imageChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(null);

    }


    @FXML
    private void loadNewNote(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("/studyMateSuperPackage/shortNotes/note.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);

        }

        stickyBorderpane.setCenter(loader);
        loader.getChildrenUnmodifiable();
    }

    @FXML
    private void openNotificationfromDashboardButton(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../ui/mainView/MainView.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);

        }
        dashBoardBorderPane.setCenter(loader);
    }

    @FXML
    private void openTimetablefromDashboardButton(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../FxmlFiles/TimeTable.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);

        }
        dashBoardBorderPane.setCenter(loader);
    }


    @FXML
    private void openExamsfromDashboardButton(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../FxmlFiles/View.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);

        }
        dashBoardBorderPane.setCenter(loader);
    }

    @FXML
    private void openDocumentsfromDashboardButton(ActionEvent event) {
        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("../FxmlFiles/TimeTable.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);

        }
        dashBoardBorderPane.setCenter(loader);
    }

    @FXML
    private void loadpictureName() throws IOException {

        try {
            ConnectionData();
            pst = con.prepareStatement("SELECT * FROM newprofile ");

            rs = pst.executeQuery();
            if (rs.next()) {
                pictureName.setText(rs.getString("username"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void dashboardPeriodDisplay() {
        LocalDate localDate = LocalDate.now();//LocalDate.of(2016, 01, 01);

        //Getting the day of week for a given date
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println(localDate + " was a " + dayOfWeek);
        LocalDate firstWorkingDay;

        //Using DayOfWeek to execute dependent business logic
        switch (dayOfWeek) {
            case MONDAY:
                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM mondayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("momdayperiod1course"));
                        courseLecturerLabel1.setText(rs.getString("mondayperiod1lecturer"));
                        courseVenueLabel1.setText(rs.getString("mondayperiod1venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM mondayperiod2table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("mondayperiod2course"));
                        courseLecturerLabel1.setText(rs.getString("mondayperiod2lecturer"));
                        courseVenueLabel1.setText(rs.getString("mondayperiod2venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM mondayperiod3table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("mondayperiod3course"));
                        courseLecturerLabel2.setText(rs.getString("mondayperiod3lecturer"));
                        courseVenueLabel2.setText(rs.getString("mondayperiod3venue"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM mondayperiod4table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("mondayperiod4course"));
                        courseLecturerLabel2.setText(rs.getString("mondayperiod4lecturer"));
                        courseVenueLabel2.setText(rs.getString("mondayperiod4venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM mondayperiod5table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("mondayperiod5course"));
                        courseLecturerLabel2.setText(rs.getString("mondayperiod5lecturer"));
                        courseVenueLabel2.setText(rs.getString("mondayperiod5venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case TUESDAY:
                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM tuesdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("tuesdayperiod1course"));
                        courseLecturerLabel1.setText(rs.getString("tuesdayperiod1lecturer"));
                        courseVenueLabel1.setText(rs.getString("tuesdayperiod1venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM tuesdayperiod2table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("tuesdayperiod2course"));
                        courseLecturerLabel1.setText(rs.getString("tuesdayperiod2lecturer"));
                        courseVenueLabel1.setText(rs.getString("tuesdayperiod2venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM tuesdayperiod3table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("tuesdayperiod3course"));
                        courseLecturerLabel2.setText(rs.getString("tuesdayperiod3lecturer"));
                        courseVenueLabel2.setText(rs.getString("tuesdayperiod3venue"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM tuesdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("tuesdayperiod4course"));
                        courseLecturerLabel2.setText(rs.getString("tuesdayperiod4lecturer"));
                        courseVenueLabel2.setText(rs.getString("tuesdayperiod4venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM tuesdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("tuesdayperiod5course"));
                        courseLecturerLabel2.setText(rs.getString("tuesdayperiod5lecturer"));
                        courseVenueLabel2.setText(rs.getString("tuesdayperiod5venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case WEDNESDAY:
                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM wednesdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString(" wednesdayperiod1course"));
                        courseLecturerLabel1.setText(rs.getString(" wednesdayperiod1lecturer"));
                        courseVenueLabel1.setText(rs.getString(" wednesdayperiod1venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM  wednesdayperiod2table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString(" wednesdayperiod2course"));
                        courseLecturerLabel1.setText(rs.getString(" wednesdayperiod2lecturer"));
                        courseVenueLabel1.setText(rs.getString(" wednesdayperiod2venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM  wednesdayperiod3table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString(" wednesdayperiod3course"));
                        courseLecturerLabel2.setText(rs.getString(" wednesdayperiod3lecturer"));
                        courseVenueLabel2.setText(rs.getString(" wednesdayperiod3venue"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM  wednesdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString(" wednesdayperiod4course"));
                        courseLecturerLabel2.setText(rs.getString(" wednesdayperiod4lecturer"));
                        courseVenueLabel2.setText(rs.getString(" wednesdayperiod4venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM  wednesdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString(" wednesdayperiod5course"));
                        courseLecturerLabel2.setText(rs.getString(" wednesdayperiod5lecturer"));
                        courseVenueLabel2.setText(rs.getString(" wednesdayperiod5venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case THURSDAY:
                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM thursdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("thursdayperiod1course"));
                        courseLecturerLabel1.setText(rs.getString("thursdayperiod1lecturer"));
                        courseVenueLabel1.setText(rs.getString("thursdayperiod1venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM thursdayperiod2table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("thursdayperiod2course"));
                        courseLecturerLabel1.setText(rs.getString("thursdayperiod2lecturer"));
                        courseVenueLabel1.setText(rs.getString("thursdayperiod2venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM thursdayperiod3table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("thursdayperiod3course"));
                        courseLecturerLabel2.setText(rs.getString("thursdayperiod3lecturer"));
                        courseVenueLabel2.setText(rs.getString("thursdayperiod3venue"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM thursdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("thursdayperiod4course"));
                        courseLecturerLabel2.setText(rs.getString("thursdayperiod4lecturer"));
                        courseVenueLabel2.setText(rs.getString("thursdayperiod4venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM thursdayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("thursdayperiod5course"));
                        courseLecturerLabel2.setText(rs.getString("thursdayperiod5lecturer"));
                        courseVenueLabel2.setText(rs.getString("thursdayperiod5venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case FRIDAY:
                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM fridayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("fridayperiod1course"));
                        courseLecturerLabel1.setText(rs.getString("fridayperiod1lecturer"));
                        courseVenueLabel1.setText(rs.getString("fridayperiod1venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM fridayperiod2table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel1.setText(rs.getString("fridayperiod2course"));
                        courseLecturerLabel1.setText(rs.getString("fridayperiod2lecturer"));
                        courseVenueLabel1.setText(rs.getString("fridayperiod2venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM fridayperiod3table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("fridayperiod3course"));
                        courseLecturerLabel2.setText(rs.getString("fridayperiod3lecturer"));
                        courseVenueLabel2.setText(rs.getString("fridayperiod3venue"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM fridayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("fridayperiod4course"));
                        courseLecturerLabel2.setText(rs.getString("fridayperiod4lecturer"));
                        courseVenueLabel2.setText(rs.getString("fridayperiod4venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ConnectionData();
                    pst = con.prepareStatement("SELECT * FROM fridayperiod1table ");

                    rs = pst.executeQuery();
                    if (rs.next()) {
                        courseNameLabel2.setText(rs.getString("fridayperiod5course"));
                        courseLecturerLabel2.setText(rs.getString("fridayperiod5lecturer"));
                        courseVenueLabel2.setText(rs.getString("fridayperiod5venue"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case SATURDAY:

            case SUNDAY:
                courseLecturerLabel1.setText("No Courses Today");
                courseLecturerLabel2.setText("No Courses Today");
                break;

            default:
                firstWorkingDay = localDate.plusDays(1);
                break;
        }

    }

    private void getH2Connection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:./database/my_db;create=true");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE id=?");
      //  statement.setString(1, "D58BE");
        statement.executeQuery();
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
           // String id = rs.getString("id");
            //String name = rs.getNamex("NAME"); // Assuming there is a column called name.
            remindertitlelabel.setText("hello");
            System.out.println("helo");
        }


    }

}


