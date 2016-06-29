package by.epamlab.model.beans;

import java.io.Serializable;

public class Reservation implements Serializable{

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reservation{" + "code=" + code + ", description=" + description + '}';
    }

}
