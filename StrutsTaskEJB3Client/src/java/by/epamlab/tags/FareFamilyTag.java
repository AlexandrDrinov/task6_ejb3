package by.epamlab.tags;

import by.epamlab.ejb.ifaces.FareFamilyRemote;
import by.epamlab.exception.DAOException;
import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FareFamilyTag extends AbstractTag {

    @Override
    public void doTag() {

        try {
            InitialContext ctx = initContext();  
            FareFamilyRemote fareFamilyRemote = (FareFamilyRemote) ctx.lookup("FareFamilySessionBean/remote");
            getJspContext().setAttribute("fareFamily", fareFamilyRemote.getFareFamily());
        } catch (NamingException | DAOException | RemoteException ex) {
            getJspContext().setAttribute("fareFamily", null);
        }

    }
}
