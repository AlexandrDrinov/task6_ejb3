package by.epamlab.ejb.ifaces;

import by.epamlab.exception.DAOException;
import by.epamlab.model.beans.Customer;
import java.rmi.RemoteException;
import javax.ejb.Remote;

@Remote
public interface CustomerRemote {
        public Customer getCustomer() throws DAOException, RemoteException;  
}
