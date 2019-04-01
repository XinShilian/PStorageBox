package org.pstoragebox.tools;

import javafx.application.Application;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {

    public static Logger getMyLogger(){
        Logger myLogger = Logger.getLogger(Application.class.getName());
        myLogger.setLevel(Level.ALL);
        return myLogger;
    }
}
