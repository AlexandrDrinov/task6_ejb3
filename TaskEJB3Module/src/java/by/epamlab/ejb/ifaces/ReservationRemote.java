package by.epamlab.ejb.ifaces;

import by.epamlab.exception.DAOException;
import by.epamlab.model.beans.Reservation;
import java.rmi.RemoteException;
import javax.ejb.Remote;

@Remote
public interface ReservationRemote {
        public Reservation getReservation() throws DAOException, RemoteException;  
}
