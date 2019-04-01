package org.pstoragebox.filesystem;

import org.pstoragebox.tools.FileStream;

import java.util.HashMap;
import java.util.Map;

public class LogicalFileBlock {

    public LogicalFileBlock(String myId,String filePath,byte[] data) {
        blockLocations = new HashMap<>();
        FileStream.writeFileBlockToRealSystem(filePath,data);
        blockLocations.put(myId,filePath);
    }

    public void addABackup(){

    }

    public void deleteBackUpbyId(String id){

    }

    public byte[] downloadBlock(){
        byte[] data = null;
        //
        return data;
    }


    private Map<String,String> blockLocations;
}
