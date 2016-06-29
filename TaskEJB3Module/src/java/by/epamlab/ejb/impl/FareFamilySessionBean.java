package by.epamlab.ejb.impl;

import by.epamlab.ejb.ifaces.FareFamilyRemote;
import by.epamlab.exception.DAOException;
import by.epamlab.ifaces.IDataDAO;
import by.epamlab.model.beans.FareFamily;
import by.epamlab.model.factories.DataFactory;
import java.rmi.RemoteException;
import javax.ejb.Stateless;

@Stateless
public class FareFamilySessionBean implements FareFamilyRemote {

    public FareFamily getFareFamily() throws DAOException, RemoteException {

        IDataDAO data = DataFactory.getClassFromFactory();
        FareFamily fareFamily = data.getFareFamily();
        return fareFamily;
    }

}
