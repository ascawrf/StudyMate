package studyMateSuperPackage.ControllerClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import studyMateSuperPackage.ui.mainView.MainViewController;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GPAController implements Initializable {
    private static final GPAController instance = new GPAController();
    private static final String gpafilename = "GPADatasheet";
    public final GPAModel model = new GPAModel();
    private final ArrayList<TextField> listOfCredits = new ArrayList<TextField>();
    private final ArrayList<TextField> listOfCourses = new ArrayList<TextField>();
    private final ArrayList<ComboBox<String>> listOfGrades = new ArrayList<ComboBox<String>>();
    public ObservableList<GPAItem> GPAlist = FXCollections.observableArrayList();
    public ObservableList<ExamDetailLocalEvent> examDetails__ExamDetailsLocalEventItem = FXCollections.observableArrayList();


    Connection con;
    PreparedStatement pst,pst1,pst2;
    ResultSet rs;
    @FXML
    private GridPane inputGrid;
    @FXML
    private Text text0;
    @FXML
    private TextField creditsInput0;
    @FXML
    private Text text00;
    @FXML
    private ComboBox<String> gradeInput0;

    @FXML
    private TableView<ExamDetailLocalEvent> examTableViewExamDetailsLocalEventItem;
    @FXML
    private TableColumn<ExamDetailLocalEvent, String> courseIdColumn;
    @FXML
    private TableColumn<ExamDetailLocalEvent, String> courseTitleColumn;

    @FXML
    private TableColumn<ExamDetailLocalEvent, String> creditHoursColumn;

    @FXML
    private TableColumn<ExamDetailLocalEvent, String> gradeColumn;
    @FXML
    private Button removeButton0;
    @FXML
    private Button deleteCourses;
    @FXML
    private Text text1;
    @FXML
    private TextField creditsInput1;
    @FXML
    private Text text11;
    @FXML
    private ComboBox<String> gradeInput1;
    @FXML
    private Button removeButton1;
    @FXML
    private Text text2;
    @FXML
    private TextField creditsInput2;
    @FXML
    private Text text22;
    @FXML
    private ComboBox<String> gradeInput2;
    @FXML
    private TextField courseInput0;
    @FXML
    private TextField courseInput1;
    @FXML
    private TextField courseInput2;
    @FXML
    private Button removeButton2;
    @FXML
    private Text gpaOutput;
    @FXML
    private Text cumGPAOutput;
    @FXML
    private Button calcButton;
    @FXML
    private Button addButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newButton;
    @FXML
    public AreaChart<String, Double> graph;
    @FXML
    private PieChart pieChart;
    private Text courseName;
    private TextField courseInput;
    private Text creditsName;
    private TextField creditsInput;
    private Text gradeName;
    private ComboBox<String> gradeInput;
    private Button removeButton;
    private ObservableList<GPAItem> gpaItems;

    public static GPAController getInstance() {
        return instance;
    }


    @FXML
    private void calculateGPA(ActionEvent e) {

        //If we have no fields
        if (model.getNumOfRows() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR: NO FIELD(S) FOUND.");
            alert.setContentText("Please make sure you have at least one row filled out before calculating your GPA.");
            alert.showAndWait();
            return;
        }

        //If any input fields are empty
        for (int i = 0; i < model.getNumOfRows(); i++) {
            if (listOfCredits.get(i).getText().equals("") || listOfGrades.get(i).getValue() == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ERROR: EMPTY FIELD(S).");
                alert.setContentText("Please make sure you have completely filled out the form before calculating your GPA.");
                alert.showAndWait();
                return;
            }
        }

        saveButton.setDisable(false);
        newButton.setDisable(false);
        /*Gives the calculator all of the credits*/
        model.setCredits(listOfCredits);
        /*Gives the calculator all of the values from the grades*/
        model.setGradeList(listOfGrades);

        /*Calculate the gpa after error checking*/
        model.calculateGPA();

        /*Makes the data visible*/
        gpaOutput.setText(model.getCurrGpa() + "");
        cumGPAOutput.setText(model.getCumGpa() + "");
    }

    //Written by: Emily Black
    @SuppressWarnings({"unchecked"})
    @FXML
    private void addRow(ActionEvent e) {
        saveButton.setDisable(true);
        newButton.setDisable(true);

        courseName = new Text("Course:");
        courseName.fontProperty().setValue(new Font(15));
        courseName.setId("courseText" + model.getNumOfRows());

        courseInput = new TextField();
        listOfCourses.add(courseInput);
        courseInput.setId("courseInput" + model.getNumOfRows());

        creditsName = new Text("Credits:");
        creditsName.fontProperty().setValue(new Font(15));
        creditsName.setId("text" + model.getNumOfRows());

        creditsInput = new TextField();
        creditsInput.addEventFilter(KeyEvent.KEY_TYPED, numericalValue(2));
        creditsInput.setPromptText("Ex: 3");
        listOfCredits.add(creditsInput);
        creditsInput.setId("creditsInput" + model.getNumOfRows());

        gradeName = new Text("Grade:");
        gradeName.fontProperty().setValue(new Font(15));
        gradeName.setId("text" + model.getNumOfRows() + "" + model.getNumOfRows());

        gradeInput = new ComboBox<String>();
        gradeInput.getItems().addAll("A", "B", "C", "D", "F");
        listOfGrades.add(gradeInput);
        gradeInput.setId("gradeInput" + model.getNumOfRows());

        removeButton = new Button("-");
        removeButton.fontProperty().setValue(new Font(15));
        removeButton.setId("removeButton" + model.getNumOfRows());

        removeButton.setOnAction(event1 -> {
            removeRow(event1);
        });

        inputGrid.add(courseName, 0, model.getNumOfRows());
        inputGrid.add(courseInput, 1, model.getNumOfRows());
        inputGrid.add(creditsName, 2, model.getNumOfRows());
        inputGrid.add(creditsInput, 3, model.getNumOfRows());
        inputGrid.add(gradeName, 4, model.getNumOfRows());
        inputGrid.add(gradeInput, 5, model.getNumOfRows());
        inputGrid.add(removeButton, 6, model.getNumOfRows());

        model.setNumOfRows(model.getNumOfRows() + 1);
    }


    @FXML
    public void saveSemester(ActionEvent e) throws IOException {
        //Check for input
        if (gpaOutput.getText() == null) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR: EMPTY GPA");
            alert.setContentText("Please make sure you calculate your GPA before saving your semester.");
            alert.showAndWait();
            return;

        } else {
            //create the series data
            model.setSeries(Double.parseDouble(gpaOutput.getText()));

            //increment the number of semesters
            model.incrementSemesters();

            addEvent(null);
            saveGPARecords(null);
            refreshList();

            graph.getData().clear(); //clears data so we dont add duplicates
            graph.getData().addAll(model.getSeries());
            //graph.setLegendVisible(false);
            //we let model know that it is saved
            model.setSaved(true);
//            DashboardController dashboardController = new DashboardController();
//            dashboardController.displayDashboardGraph.setData(model.);
            pieChart.setData(model.createPieData());
            pieChart.setLegendVisible(false);

        }
    }


    @FXML
    public void newSemester(ActionEvent e) throws IOException {
        // save semester & clear elements if we have gpa to save
        if (gpaOutput.getText() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR: EMPTY GPA");
            alert.setContentText("Please make sure you calculate your GPA before saving your semester.");
            alert.showAndWait();
            return;
        } else {
            //If we havent saved
            if (model.isSaved() == false) {
                //create the series data
                model.setSeries(Double.parseDouble(gpaOutput.getText()));

                //increment the number of semesters
                model.incrementSemesters();

                graph.getData().clear(); //clears data so we dont add duplicates
                graph.getData().addAll(model.getSeries());
                graph.setLegendVisible(false);
                //we let model know that it is saved
                model.setSaved(true);

                pieChart.setData(model.createPieData());
                pieChart.setLegendVisible(false);

            }
            //If we have saved
            if (model.isSaved() == true) {
                // clear grid
                for (int i = model.getNumOfRows(); i > 2; i--) {
                    int row = i;
                    Set<Node> deleteNodes = new HashSet<>(5);

                    for (Node child : inputGrid.getChildren()) {
                        // get index from child
                        Integer rowIndex = GridPane.getRowIndex(child);

                        // handle null values for index=0
                        int r = rowIndex == null ? 0 : rowIndex;

                        if (r > row) {
                            // decrement rows for rows after the deleted row
                            GridPane.setRowIndex(child, r - 1);
                        } else if (r == row) {
                            // collect matching rows for deletion
                            deleteNodes.add(child);
                            child.setManaged(false);
                        }
                    }

                    // remove nodes from row
                    inputGrid.getChildren().removeAll(deleteNodes);
                }

                //Remove extra rows from list
                for (int j = model.getNumOfRows() - 1; j > 3; j--) {
                    listOfCredits.remove(listOfCredits.get(j));
                    listOfGrades.remove(listOfGrades.get(j));
                }

                model.setNumOfRows(3);

                //Clear first 3 rows
                for (int k = 0; k < model.getNumOfRows(); k++) {
                    listOfCredits.get(k).clear();
                    listOfGrades.get(k).valueProperty().set(null);
                    courseInput0.clear();
                    courseInput1.clear();
                    courseInput2.clear();
                }

                //Clear the data in the model
                model.clearData();

            }

            gpaOutput.setText("");
            model.setSaved(false);

            saveButton.setDisable(true);
            newButton.setDisable(true);

        }
    }

    @FXML
    private void removeRow(ActionEvent event) {
        @SuppressWarnings("static-access")
        int row = inputGrid.getRowIndex((Node) event.getSource());
        ArrayList<Node> deleteNodes = new ArrayList<Node>(5);

        for (Node child : inputGrid.getChildren()) {
            // get index from child
            Integer rowIndex = GridPane.getRowIndex(child);

            // handle null values for index=0
            int r = rowIndex == null ? 0 : rowIndex;

            if (r > row) {
                // decrement rows for rows after the deleted row
                GridPane.setRowIndex(child, r - 1);
            } else if (r == row) {
                // collect matching rows for deletion
                deleteNodes.add(child);
                child.setManaged(false);
            }
        }

        inputGrid.getChildren().removeAll(deleteNodes);
        model.setNumOfRows(model.getNumOfRows() - 1);

        listOfGrades.remove(row);
        model.setGradeList(listOfGrades);
        listOfCredits.remove(row);
        for (int i = 0; i < listOfCredits.size(); i++) {
            System.out.println(listOfCredits.get(i).getText());
        }
        model.setCredits(listOfCredits);
    }


    @Override
    public void initialize(URL arg, ResourceBundle arg1) {


        loadExamRecords();

        saveButton.setDisable(true);
        newButton.setDisable(true);

        listOfCredits.add(creditsInput0);
        gradeInput0.getItems().addAll("A", "B", "C", "D", "F");
        listOfGrades.add(gradeInput0);

        listOfCredits.add(creditsInput1);
        gradeInput1.getItems().addAll("A", "B", "C", "D", "F");
        listOfGrades.add(gradeInput1);

        listOfCredits.add(creditsInput2);
        gradeInput2.getItems().addAll("A", "B", "C", "D", "F");
        listOfGrades.add(gradeInput2);

        creditsInput0.addEventFilter(KeyEvent.KEY_TYPED, numericalValue(2));
        creditsInput1.addEventFilter(KeyEvent.KEY_TYPED, numericalValue(2));
        creditsInput2.addEventFilter(KeyEvent.KEY_TYPED, numericalValue(2));


    }


    private EventHandler numericalValue(int maxLength) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= maxLength) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-9]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }


    @FXML
    private void addEvent(ActionEvent event) throws IOException {

        try {
           ConnectionData();
            pst = con.prepareStatement("INSERT INTO gpatable (coursetitle, credithours, grade) VALUES (?,?,?)");
            pst.setString(1, courseInput0.getText());
            pst.setString(2, creditsInput0.getText());
            pst.setString(3, (gradeInput0.getSelectionModel().getSelectedItem()));

            pst1 = con.prepareStatement("INSERT INTO gpatable (coursetitle, credithours, grade) VALUES (?,?,?)");
            pst1.setString(1, courseInput1.getText());
            pst1.setString(2, creditsInput1.getText());
            pst1.setString(3, (gradeInput1.getSelectionModel().getSelectedItem()));

            pst2 = con.prepareStatement("INSERT INTO gpatable (coursetitle, credithours, grade) VALUES (?,?,?)");
            pst2.setString(1, courseInput2.getText());
            pst2.setString(2, creditsInput2.getText());
            pst2.setString(3, (gradeInput2.getSelectionModel().getSelectedItem()));
//
//
//            pst.setString(1, courseInput2.getText());
//            pst.setString(2, creditsInput2.getText());
//            pst.setString(3, (gradeInput2.getSelectionModel().getSelectedItem()));


            int rows = pst.executeUpdate();
            int rows1 = pst1.executeUpdate();
            int rows2 = pst2.executeUpdate();

            if ((rows > 0) && (rows1 > 0) && (rows2 > 0)) {
                System.out.println("A row 1 is updated");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Row events created");
                alert.showAndWait();
            }

//            if (rows1 > 0) {
//                System.out.println("A row is updated");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Information Dialog");
//                alert.setHeaderText(null);
//                alert.setContentText("Row 2 event created");
//                alert.showAndWait();
//            }
//
//            if (rows2 > 0) {
//                System.out.println("A row is updated");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Information Dialog");
//                alert.setHeaderText(null);
//                alert.setContentText("Row 3 event created");
//                alert.showAndWait();
//            }
//
//

        } catch (SQLException ex) {
            Logger.getLogger(GPAController.class.getName()).log(Level.SEVERE, null, ex);
        }




    }


    @FXML
    private void saveGPARecords(ActionEvent event) throws IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studymateproject",
                    "root", "19THwarlock*@1");
            pst = con.prepareStatement("INSERT INTO gparecords (gpascore, cumulativegpa) VALUES (?,?)");
            pst.setString(1, gpaOutput.getText());
            pst.setString(2, cumGPAOutput.getText());
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("GPA SCORES HAS BEEN SAVED");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("GPA SCORES HAS BEEN SAVED");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GPAController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @FXML
    private void loadExamRecords() {

        courseTitleColumn.setCellValueFactory(new PropertyValueFactory<>("coursetitle"));
        creditHoursColumn.setCellValueFactory(new PropertyValueFactory<>("credithours"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        try {
            examTableViewExamDetailsLocalEventItem.setItems(examDetails__ExamDetailsLocalEventItem);
            ConnectionData();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM gpatable "); // LIMIT 1
            while (rs.next()) {
                examDetails__ExamDetailsLocalEventItem.add(new ExamDetailLocalEvent(rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {
            Logger.getLogger(GPAController.class.getName()).log(Level.SEVERE, null, e);
        }
    }



@FXML
    private void deleteCourseRow(ActionEvent event){
        ConnectionData();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete a course");
    //alert.setHeaderText("Delete item: " + elist);
    alert.setContentText("Are you sure?  Press OK to confirm, or cancel to Back out.");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && (result.get() == ButtonType.OK)) {

        try {
            ConnectionData();
            String student = examTableViewExamDetailsLocalEventItem.getSelectionModel().getSelectedItem().toString();
            pst = con.prepareStatement("DELETE FROM gpatable WHERE coursetitle = ?");
            pst.setString(1,student);
            pst.executeUpdate();
            System.out.println("Event Deleted");
            pst.close();
            examTableViewExamDetailsLocalEventItem.getItems().clear();
            refreshList();
        } catch (SQLException ex) {
            Logger.getLogger(GPAController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    public void refreshList() {
        examTableViewExamDetailsLocalEventItem.setItems(examDetails__ExamDetailsLocalEventItem);
        try {
            ConnectionData();

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM gpatable ");
            while (rs.next()) {
                examDetails__ExamDetailsLocalEventItem.add(new ExamDetailLocalEvent(rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException e) {
            Logger.getLogger(GPAController.class.getName()).log(Level.SEVERE, null, e);
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
