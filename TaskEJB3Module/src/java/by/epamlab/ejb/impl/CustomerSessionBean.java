package by.epamlab.ejb.impl;

import by.epamlab.ejb.ifaces.CustomerRemote;
import by.epamlab.exception.DAOException;
import by.epamlab.ifaces.IDataDAO;
import by.epamlab.model.beans.Customer;
import by.epamlab.model.factories.DataFactory;
import java.rmi.RemoteException;
import javax.ejb.Stateless;

@Stateless
public class CustomerSessionBean implements CustomerRemote {

    public Customer getCustomer() throws DAOException, RemoteException {

        IDataDAO data = DataFactory.getClassFromFactory();
        Customer customer = data.getCustomer();
        return customer;
    }

}
