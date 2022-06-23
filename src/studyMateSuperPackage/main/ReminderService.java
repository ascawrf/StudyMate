package studyMateSuperPackage.main;

import eu.hansolo.enzo.notification.Notification;
import eu.hansolo.enzo.notification.NotifierBuilder;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import studyMateSuperPackage.ControllerClasses.LoginController;
import studyMateSuperPackage.ControllerClasses.MusicFile;
import studyMateSuperPackage.database.DatabaseHelper;
import studyMateSuperPackage.resources.Post;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReminderService extends LoginController {
    private static ReminderService service = null;
    private final DatabaseHelper helper;
    private ScheduledExecutorService ses;
    private boolean running;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    private ReminderService() {
        ses = Executors.newScheduledThreadPool(1, (runnable) -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        helper = DatabaseHelper.getInstance();
        running = false;

    }

    public static ReminderService getInstance() {
        if (service == null) {
            service = new ReminderService();
        }
        return service;
    }

    public void startService() {
        if (!running) {
            InitiateSevice();
        }

    }

    public void stopService() {
        if (running) {
            ses.shutdown();
            running = false;
        }
    }


    public void addTask(Post mPost) {
        if (running) {
            long nowTime = (new Date()).getTime();
            ses.schedule(() -> {
                System.out.println(mPost.getTitle() + " added");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        showNotification(mPost.getTitle(), mPost.getTitle() + " reminder has arrived");

                    }
                });

            }, mPost.getPostReminder() - nowTime, TimeUnit.MILLISECONDS);
        }


        try {


            ConnectionData();
            pst = con.prepareStatement("SELECT  email FROM newprofile ");

            rs = pst.executeQuery();
            if (rs.next()) {
                SendEmail("tinglinsguest@gmail.com", "Weatherbirds*1", rs.getString("email"),
                        mPost.getTitle(), mPost.getDescription());
            } else {
                System.out.println("mail not reached");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }





    public void InitiateSevice() {
        if (running) {
            stopService();
            ses.shutdownNow();
            ses = null;
            ses = Executors.newScheduledThreadPool(1, (runnable) -> {
                Thread t = new Thread(runnable);
                t.setDaemon(true);

                return t;
            });
        }
        running = true;
        long nowTime = (new Date()).getTime();
        List<Post> postsList = helper.getPostList(nowTime);
        for (Post mPost : postsList) {
            addTask(mPost);
        }

    }

    private void showNotification(String title, String content) {
        Notification notification = new Notification(title, content, Notification.INFO_ICON);
        Notification.Notifier notifire = NotifierBuilder.create()
                .popupLifeTime(Duration.seconds(10))
                .popupLocation(Pos.TOP_RIGHT)
                .build();

        MusicFile musicFile = new MusicFile();

        notifire.notify(notification);
        musicFile.itWorked();

        notifire.setOnHideNotification((ev) -> notifire.stop());
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
