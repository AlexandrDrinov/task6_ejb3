package by.epamlab.ejb.impl;

import by.epamlab.ejb.ifaces.ResComponentRemote;
import by.epamlab.exception.DAOException;
import by.epamlab.ifaces.IDataDAO;
import by.epamlab.model.beans.ResComponent;
import by.epamlab.model.factories.DataFactory;
import java.rmi.RemoteException;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ResComponentSessionBean implements ResComponentRemote {

    public List<ResComponent> getResComponents() throws DAOException, RemoteException {

        IDataDAO data = DataFactory.getClassFromFactory();
        List<ResComponent> resComponents = data.getResComponents();
        return resComponents;
    }

    public List<ResComponent> getResComponents(String resCode) throws DAOException, RemoteException {
        return getDataOfComponents(resCode, null);
    }

    public List<ResComponent> getResComponents(String resCode, String resComponentTypeCode) throws DAOException, RemoteException {
        return getDataOfComponents(resCode, resComponentTypeCode);
    }

    private List<ResComponent> getDataOfComponents(String resCode, String resComponentTypeCode) throws DAOException, RemoteException {

        IDataDAO data = DataFactory.getClassFromFactory();
        List<ResComponent> resComponents = data.getResComponents(resCode, resComponentTypeCode);
        return resComponents;

    }  

}
