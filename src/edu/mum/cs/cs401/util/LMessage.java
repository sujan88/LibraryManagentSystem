package edu.mum.cs.cs401.util;

/**
 * Created by 985333 on 9/13/2016.
 */
public class LMessage {
    public static int SUCCESS= 1;
    public static int FAILED= 0;
    int states ;
    Object returnDate;

    public LMessage(int states, Object returnDate) {
        this.states = states;
        this.returnDate = returnDate;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }



    public Object getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Object returnDate) {
        this.returnDate = returnDate;
    }




}
