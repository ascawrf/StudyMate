package studyMateSuperPackage.ControllerClasses;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends DeleteDatabase implements Initializable {


    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    ImageView ic;
    @FXML
    Circle pic;
    @FXML
    Circle min;
    @FXML
    Circle close;

    @FXML
    private JFXButton loginButton;
    @FXML
    public TextField loginUsername;
    @FXML
    private JFXButton createNewProfile;
    @FXML
    public PasswordField loginPassword;
    @FXML
    private JFXButton resetPassword;
    @FXML
    private AnchorPane parent;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField newProfileEmailAdd;
    @FXML
    private TextField newProfileLastName;
    @FXML
    private TextField newProfileUsername;
    @FXML
    private PasswordField newProfileDefaultPassword;
    @FXML
    private PasswordField newProfileNewPassword;
    @FXML
    private TextField newProfileFirstName;
    @FXML
    private JFXButton newProfileSaveButton;
    @FXML
    private Label newprofileError;


    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // new Bounce(loginButton).setCycleCount(2).setDelay(Duration.valueOf("600ms")).play();
    }

    @FXML
    public void minimizeClick(MouseEvent event) throws IOException {

        ((Stage) ((Circle) event.getSource()).getScene().getWindow()).setIconified(true);

    }

    @FXML
    public void closeclick(MouseEvent event) throws IOException {
        System.exit(0);

    }

    @FXML
    private void disabledLoginButton() { // disabled button for signUpSignUp button
        String username = loginUsername.getText();
        String passField = loginPassword.getText();
        boolean disableButton = (username.isEmpty() || username.trim().isEmpty()
                || passField.isEmpty() || passField.trim().isEmpty());
        loginButton.setDisable(disableButton);
    }

    @FXML
    private void LoginButtonAction(ActionEvent event) throws IOException {
        try {
            if (loginUsername.getText().equals("")) return;
            if (loginPassword.getText().equals("")) return;
            disabledLoginButton();
            ConnectionData();
            pst = con.prepareStatement("SELECT * FROM newprofile Where username = ? and  password = ? ");
            pst.setString(1, loginUsername.getText());
            pst.setString(2, loginPassword.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../fxmlFiles/HomePage.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                newprofileError.setText("Either username or password is incorrect");
                createNewProfile.setVisible(false);
                loginUsername.setText("");
                loginPassword.setText("");
                loginUsername.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void disableNewProfileButton() { // disabled button for signUpSignUp button
        String FirstName = newProfileFirstName.getText();
        String LastName = newProfileLastName.getText();
        String username = newProfileUsername.getText();
        String Password = newProfileNewPassword.getText();
        String EmailAdd = newProfileEmailAdd.getText();


        boolean disableButton = (FirstName.isEmpty() || FirstName.trim().isEmpty() ||
                LastName.isEmpty() || LastName.trim().isEmpty() ||
                EmailAdd.isEmpty() || EmailAdd.trim().isEmpty() ||
                Password.isEmpty() || Password.trim().isEmpty() ||
                username.isEmpty() || username.trim().isEmpty());

        newProfileSaveButton.setDisable(disableButton);
    }

    @FXML
    private void saveNewProfile(ActionEvent event) throws SQLException {
        if (newProfileUsername.getText().equals("")) return;
        if (newProfileFirstName.getText().equals("")) return;
        if (newProfileLastName.getText().equals("")) return;
        if (newProfileNewPassword.getText().equals("")) return;
        if (newProfileEmailAdd.getText().equals("")) return;
        disableNewProfileButton();
        ConnectionData();

        String passconstaints = newProfileNewPassword.getText();
        if ((passconstaints.length() < 8)) {
            JOptionPane.showMessageDialog(null, "New Password should be more than 8 characters");
        } else {

            DeletedRecordsData deletedRecordsData = new DeletedRecordsData();
            deletedRecordsData.deletedData();


            try {
                pst = con.prepareStatement("INSERT INTO newprofile (firstName,lastName,username,password,email) VALUES (?,?,?,?,?)");
                pst.setString(1, newProfileFirstName.getText());
                pst.setString(2, newProfileLastName.getText());
                pst.setString(3, newProfileUsername.getText());
                pst.setString(4, newProfileNewPassword.getText());
                pst.setString(5, newProfileEmailAdd.getText());
                int rows = pst.executeUpdate();
                if (rows > 0) {
//
                    JOptionPane.showMessageDialog(null, "Account created successfully. You can now login");
                    try {
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../fxmlFiles/profile.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {


                        pst = con.prepareStatement("SELECT  * FROM newprofile ");

                        rs = pst.executeQuery();
                        if (rs.next()) {
                            SendEmail("tinglinsguest@gmail.com", "Weatherbirds*1", rs.getString("email"),
                                    "Saved Password", rs.getString("password"));
                        } else {
                            System.out.println("mail not reached");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("error password");
                }
            } catch (SQLException e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }


    @FXML
    private void openNewProfile(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/NewProfile.fxml "));
        parent.getChildren().add(root);
    }


    @FXML
    private void firstNametextField() {
        newProfileFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                newProfileFirstName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    @FXML
    private void lastNametextField() {
        newProfileLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                newProfileLastName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    @FXML
    private void userNametextField() {
        String userCheck = newProfileFirstName.getText();
        newProfileUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                newProfileUsername.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            } else if (userCheck.length() < 8) {
                System.out.println("Username lenght is too short");
            }
        });


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


    protected void SendEmail(String user, String pass, String to, String sub, String msg) {
        Properties prop = new Properties();

        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");


        //Session session = Session.getInstance(prop, new javax.mail.Authenticator()
        Session session = Session.getInstance(prop, new javax.mail.Authenticator(){
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(user, pass);

            }

        });

        try {
            Message message1 = new MimeMessage(session);

            message1.setFrom(new InternetAddress("no-reply@gmail.com"));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message1.setSubject(sub);
            message1.setText(msg);

            Transport.send(message1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Internet Connection not available." +
                    "Password was not sent to email");
        }


    }

    protected void SendAgainEmail(String user, String pass, String to, String sub, String msg) {
        Properties prop = new Properties();

        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");


        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(user, pass);

            }

        });

        try {
            Message message1 = new MimeMessage(session);

            message1.setFrom(new InternetAddress("no-reply@gmail.com"));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message1.setSubject(sub);
            message1.setText(msg);

            Transport.send(message1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }


}
