
package studyMateSuperPackage.main;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author Nikhil
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField emailid;
    @FXML
    private JFXTextField subject;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextArea message;
    @FXML
    private JFXTextField recieverid;
    static String newMail = "tinglingsguest.com";


    Connection con;
    PreparedStatement pst;
    ResultSet rs;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSendButton(ActionEvent event) {
        
        SendEmail(emailid.getText(), password.getText(), recieverid.getText(), subject.getText() ,message.getText());
    }

    
    public void   SendEmail(String user, String pass, String to, String sub, String msg)
    {
        Properties prop= new Properties();
        
         prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
         prop.put("mail.smtp.auth",true);
         prop.put("mail.smtp.starttls.enable",true);
         prop.put("mail.smtp.host","smtp.gmail.com");
         prop.put("mail.smtp.port","587");
         
         
         Session session= Session.getInstance(prop, new javax.mail.Authenticator()
         {
             @Override
             protected javax.mail.PasswordAuthentication getPasswordAuthentication()
             {
             return new javax.mail.PasswordAuthentication(user, pass);
       
             }
             
         });
         
         try
         {
             Message message1= new MimeMessage(session);
             
             message1.setFrom( new InternetAddress("no-reply@gmail.com"));
             message1.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
             message1.setSubject(sub);
             message1.setText(msg);
             
             Transport.send(message1);
             
              JOptionPane.showMessageDialog(null,"message sent");
         
         }   
         
         catch(Exception e)
         {
          JOptionPane.showMessageDialog(null,e);
         }
      
        
        
    }

    private void ConnectionData () {
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
