package by.epamlab.ifaces;

import by.epamlab.exception.DAOException;
import java.util.List;

import by.epamlab.model.beans.Customer;
import by.epamlab.model.beans.FareFamily;
import by.epamlab.model.beans.ResComponent;
import by.epamlab.model.beans.Reservation;

public interface IDataDAO {

    Reservation getReservation() throws DAOException;

    List<ResComponent> getResComponents() throws DAOException;
    
    List<ResComponent> getResComponents(String resCode) throws DAOException;
    
    List<ResComponent> getResComponents(String resCode, String resComponentTypeCode) throws DAOException;

    FareFamily getFareFamily() throws DAOException;

    Customer getCustomer() throws DAOException;
}
