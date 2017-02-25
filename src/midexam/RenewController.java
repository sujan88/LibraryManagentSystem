package midexam;

import edu.mum.cs.cs401.dataaccess.DataBase;
import edu.mum.cs.cs401.model.entity.*;

import java.util.List;

/**
 * Created by hards on 9/16/2016.
 */
public class RenewController{


    public List<CheckOutEntry> loadCheckOuts(String memeberId){

        Member m=DataBase.loadMember(memeberId);
       return m.getCheckOutRecord().getCheckOutEntryList();
    }

    public boolean reNewEntry(CheckOutEntry selectedEntry){

       if(selectedEntry.isRenewble()){
           selectedEntry.setRenewed(true);
           saveMemebrChecoutEntry(selectedEntry);
           return  true;
       }
       return  false;
    }
    void saveMemebrChecoutEntry(CheckOutEntry checkOutEntry){
        DataBase.checkOutEntryTable.update(checkOutEntry.getCeID(),checkOutEntry);
    }


}
