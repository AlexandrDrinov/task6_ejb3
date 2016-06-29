package by.epamlab.model.beans;

import java.io.Serializable;

public class Phone implements Serializable {

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone{" + "phoneNumber=" + phoneNumber + '}';
    }

}
