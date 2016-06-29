package by.epamlab.ejb.impl;

import by.epamlab.ejb.ifaces.UserRemote;
import by.epamlab.exception.DAOException;
import by.epamlab.ifaces.IUserDAO;
import by.epamlab.model.beans.User;
import by.epamlab.model.factories.UserFactory;
import java.rmi.RemoteException;
import javax.ejb.Stateless;

@Stateless
public class UserSessionBean implements UserRemote {

    public User getUser(String login, String password) throws DAOException, RemoteException {

        IUserDAO userDAO = UserFactory.getClassFromFactory();
        User user = userDAO.getUser(login, password);
        return user;
    }

}
