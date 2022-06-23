package studyMateSuperPackage.ControllerClasses;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import studyMateSuperPackage.database.PostTable;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteDatabase {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

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

    public void deleteUserDatabase() {
            try {
                ConnectionData();
                pst = con.prepareStatement("TRUNCATE newprofile ");
                pst.executeUpdate();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Could not truncate table");
                Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    public void deleteUserGpaRecords() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE gparecords ");
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteGpaTable() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE gpatable ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mondayPeriod1(){
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE mondayperiod1table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mondayPeriod2(){
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE mondayperiod2table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mondayPeriod3() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE mondayperiod3table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mondayPeriod4() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE mondayperiod4table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mondayPeriod5() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE mondayperiod5table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tuesdayPeriod1() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE tuesdayperiod1table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tuesdayPeriod2() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE tuesdayperiod2table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tuesdayPeriod3() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE tuesdayperiod3table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tuesdayPeriod4() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE tuesdayperiod4table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tuesdayPeriod5() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE tuesdayperiod5table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void  wednesdayPeriod1() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  wednesdayperiod1table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void  wednesdayPeriod2() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  wednesdayperiod2table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void  wednesdayPeriod3() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  wednesdayperiod3table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void  wednesdayPeriod4() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  wednesdayperiod4table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void wednesdayPeriod5() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  wednesdayperiod5table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void thursdayPeriod1() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  thursdayperiod1table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void thursdayPeriod2() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  thursdayperiod2table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void thursdayPeriod3() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  thursdayperiod3table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void thursdayPeriod4() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  thursdayperiod4table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void thursdayPeriod5() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  thursdayperiod5table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fridayPeriod1() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  fridayperiod1table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fridayPeriod2() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  fridayperiod2table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fridayPeriod3() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  fridayperiod3table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fridayPeriod4() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  fridayperiod4table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fridayPeriod5() {
        try {
            ConnectionData();
            pst = con.prepareStatement("TRUNCATE  fridayperiod5table ");
            pst.executeUpdate();
            pst.close();
            // refreshList();
        } catch (SQLException ex) {
            System.out.println("Could not truncate table");
            Logger.getLogger(DeleteDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void h2Database() {
        PostTable postTable = new PostTable();
        postTable.myTruncate();
    }

}


