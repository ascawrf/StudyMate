
package studyMateSuperPackage.ui.mainView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import studyMateSuperPackage.database.DatabaseHelper;

/**
 *
 * @author redayoub
 */
public class ReminderThread extends Thread{
    private final int MIN_IN_MS=60000;        // 60000
    private final double SLEEP_TIMEOUT_IN_MIN=  0.1; //1
    private DatabaseHelper helper;
    private volatile boolean running;
    public ReminderThread() {
        super();
        helper=DatabaseHelper.getInstance();
        this.setDaemon(true);
    }

    
    
    @Override
    public void run() {
        running=true;
        ScheduledExecutorService ses=Executors.newSingleThreadScheduledExecutor();
        
        while (running) {
//
            try {
                Thread.sleep ((long) (SLEEP_TIMEOUT_IN_MIN*MIN_IN_MS));
            } catch (InterruptedException ex) {
                Logger.getLogger(ReminderThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    
    
    public void stopTh(){
        running=false;
    }
    
}
