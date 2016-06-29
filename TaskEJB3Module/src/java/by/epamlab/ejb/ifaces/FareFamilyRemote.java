package by.epamlab.ejb.ifaces;

import by.epamlab.exception.DAOException;
import by.epamlab.model.beans.FareFamily;
import java.rmi.RemoteException;
import javax.ejb.Remote;

@Remote
public interface FareFamilyRemote {
        public FareFamily getFareFamily() throws DAOException, RemoteException;  
}
