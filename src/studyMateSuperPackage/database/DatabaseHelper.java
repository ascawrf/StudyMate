
package studyMateSuperPackage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import studyMateSuperPackage.ControllerClasses.DashboardController;
import studyMateSuperPackage.resources.Post;

/**
 *
 * @author redayoub
 */
public class DatabaseHelper {
    private static Connection conn=null;
    private static Statement stat=null;
    private static final String DB_URL="jdbc:h2:./database/my_db;create=true";
    private static DatabaseHelper helper;
    
    public DatabaseHelper() {
        try {
            Class.forName("org.h2.Driver");
            initDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DatabaseHelper getInstance(){
        if(helper==null){
            helper=new DatabaseHelper();
        }
        return helper;
    }
    
    public void createConnection(){
        try {
            conn=DriverManager.getConnection(DB_URL);
            stat=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initDatabase(){
        try {
            createConnection();
            stat.execute(studyMateSuperPackage.database.PostTable.CREATE_TABLE);
            //System.out.println("Table Created");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int addPost(Post post){
        try {
        final String ADD_POST="INSERT INTO "+ studyMateSuperPackage.database.PostTable.TABLE_NAME+"("+
                studyMateSuperPackage.database.PostTable.POST_TITLE+","+
                studyMateSuperPackage.database.PostTable.POST_DESCR+" , "+
                studyMateSuperPackage.database.PostTable.POST_DATE+" , "+
                studyMateSuperPackage.database.PostTable.POST_REMINDER+") VALUES( '"+
                post.getTitle()+"' ,'"+
                post.getDescription()+"' ,'"+
                post.getPostDate()+"' ,'"+
                post.getPostReminder()+"' );";
        createConnection();
       
        int res=stat.executeUpdate(ADD_POST);
        
        return res;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ResultSet getPostSet(){
        try {
            final String GET_POST="SELECT * FROM "+ studyMateSuperPackage.database.PostTable.TABLE_NAME+
                    " ORDER BY "+ studyMateSuperPackage.database.PostTable.POST_DATE+" DESC ;";
            createConnection();
            ResultSet res=stat.executeQuery(GET_POST);
            
            return res;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public ResultSet getPostSet(long reminderMin) {
        try {
            final String GET_POST="SELECT * FROM "+ studyMateSuperPackage.database.PostTable.TABLE_NAME+
                    " WHERE "+ studyMateSuperPackage.database.PostTable.POST_REMINDER+">"+reminderMin+
                    " ORDER BY "+ studyMateSuperPackage.database.PostTable.POST_DATE+" DESC ;";
            createConnection();
            ResultSet res=stat.executeQuery(GET_POST);
            
            return res;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Post getPostById(int id){
        try {
            final String GET_POST="SELECT * FROM "+ studyMateSuperPackage.database.PostTable.TABLE_NAME+
                    " WHERE "+ studyMateSuperPackage.database.PostTable.ID+"="+id+" LIMIT 1 ;";
            createConnection();
            ResultSet res=stat.executeQuery(GET_POST);
            res.next();
            DashboardController dashboardController = new DashboardController();


            Post mPost=new Post(

                    res.getString(studyMateSuperPackage.database.PostTable.POST_TITLE),
                    res.getString(studyMateSuperPackage.database.PostTable.POST_DESCR),
                    Long.parseLong(res.getString(studyMateSuperPackage.database.PostTable.POST_DATE)),
                    Long.parseLong(res.getString(studyMateSuperPackage.database.PostTable.POST_REMINDER))
//
                );
            mPost.setId(res.getInt(studyMateSuperPackage.database.PostTable.ID));

            res.close();
            return mPost;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    public boolean updatePost(int postID, Post mPost) {
        int aff_rows=0;
        try {
            
            final String UPT_POST="UPDATE "+ studyMateSuperPackage.database.PostTable.TABLE_NAME+" SET "+
                    studyMateSuperPackage.database.PostTable.POST_TITLE+" = '"+mPost.getTitle()+"' ,"+
                    studyMateSuperPackage.database.PostTable.POST_DESCR+" = '"+mPost.getDescription()+"' ,"+
                    studyMateSuperPackage.database.PostTable.POST_DATE+" = '"+Long.toString(mPost.getPostDate())+"' ,"+
                    studyMateSuperPackage.database.PostTable.POST_REMINDER+" = '"+Long.toString(mPost.getPostReminder())+"' "+
                    "WHERE "+ studyMateSuperPackage.database.PostTable.ID+"="+postID+" ;";
            createConnection();
            aff_rows=stat.executeUpdate(UPT_POST);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(conn!=null)conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (aff_rows==1);
    }

    public boolean delPost(int postID) {
        int aff_rows=0;
        try {
            final String DEL_POST="DELETE FROM "+ studyMateSuperPackage.database.PostTable.TABLE_NAME+
                    " WHERE "+ studyMateSuperPackage.database.PostTable.ID+" = "+postID+" ;";
            createConnection();
            aff_rows=stat.executeUpdate(DEL_POST);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(conn!=null)conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (aff_rows==1);  
    }
    
    public List<Post> getPostList(long reminderMin){
        List<Post> postsList=new ArrayList<>();
        try {
            ResultSet res;
            if(reminderMin==-1){
                res=helper.getPostSet();
            }else{
                if(reminderMin>0){
                    res=helper.getPostSet(reminderMin);
                }else{
                    return null;
                }
            }
            
            if(res==null)return null;
            while(res.next()){
                Post mPost=new Post(
                    res.getString(studyMateSuperPackage.database.PostTable.POST_TITLE),
                    res.getString(studyMateSuperPackage.database.PostTable.POST_DESCR),
                    Long.parseLong(res.getString(studyMateSuperPackage.database.PostTable.POST_DATE)),
                    Long.parseLong(res.getString(studyMateSuperPackage.database.PostTable.POST_REMINDER))
                );
                mPost.setId(res.getInt(studyMateSuperPackage.database.PostTable.ID));
                postsList.add(mPost);
            }
     
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return postsList;
    }

    
}
