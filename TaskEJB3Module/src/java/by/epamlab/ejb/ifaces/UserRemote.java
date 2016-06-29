package by.epamlab.ejb.ifaces;

import by.epamlab.exception.DAOException;
import by.epamlab.model.beans.User;
import java.rmi.RemoteException;
import javax.ejb.Remote;

@Remote
public interface UserRemote {
        public User getUser(String login, String password) throws DAOException, RemoteException;  
}
