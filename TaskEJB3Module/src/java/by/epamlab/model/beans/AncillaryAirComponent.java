package by.epamlab.model.beans;

import java.io.Serializable;

public class AncillaryAirComponent implements Serializable {

    private String ancillaryAirComponentCode;

    public String getAncillaryAirComponentCode() {
        return ancillaryAirComponentCode;
    }

    public void setAncillaryAirComponentCode(String ancillaryAirComponentCode) {
        this.ancillaryAirComponentCode = ancillaryAirComponentCode;
    }

    @Override
    public String toString() {
        return "AncillaryAirComponent{" + "ancillaryAirComponentCode=" + ancillaryAirComponentCode + '}';
    }

}
