package midexam.useinterface;

import edu.mum.cs.cs401.model.entity.Address;
import edu.mum.cs.cs401.model.entity.CheckOutRecord;
import edu.mum.cs.cs401.model.entity.Person;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

/**
 * Created by 985333 on 9/13/2016.
 */
public class Member  extends PersonRole {

    public SimpleObjectProperty<CheckOutRecord> checkOutRecord= new SimpleObjectProperty<>();

    public Member(String role) {
        super(role);
    }

    public CheckOutRecord getCheckOutRecord() {
        return checkOutRecord.get();
    }

    public SimpleObjectProperty<CheckOutRecord> checkOutRecordProperty() {
        return checkOutRecord;
    }

    public void setCheckOutRecord(CheckOutRecord checkOutRecord) {
        this.checkOutRecord.set(checkOutRecord);
    }


}
