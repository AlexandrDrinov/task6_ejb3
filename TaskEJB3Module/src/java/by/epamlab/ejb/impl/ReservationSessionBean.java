package by.epamlab.ejb.impl;


import by.epamlab.ejb.ifaces.ReservationRemote;
import by.epamlab.exception.DAOException;
import by.epamlab.ifaces.IDataDAO;
import by.epamlab.model.beans.Reservation;
import by.epamlab.model.factories.DataFactory;
import java.rmi.RemoteException;
import javax.ejb.Stateless;

@Stateless
public class ReservationSessionBean implements ReservationRemote {

    public Reservation getReservation() throws DAOException, RemoteException {

        IDataDAO data = DataFactory.getClassFromFactory();
        Reservation reservation = data.getReservation();
        return reservation;
    }

    

}
