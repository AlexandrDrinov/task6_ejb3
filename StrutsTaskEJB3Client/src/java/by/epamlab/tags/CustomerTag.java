package by.epamlab.tags;

import by.epamlab.ejb.ifaces.CustomerRemote;
import by.epamlab.exception.DAOException;
import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CustomerTag extends AbstractTag {

    @Override
    public void doTag() {
        
        try {  
            InitialContext ctx = initContext();            
            CustomerRemote customerRemote = (CustomerRemote) ctx.lookup("CustomerSessionBean/remote");
            getJspContext().setAttribute("customer", customerRemote.getCustomer());
        } catch (NamingException | DAOException | RemoteException ex) {            
            getJspContext().setAttribute("customer", null);
        }
        
    }
}
