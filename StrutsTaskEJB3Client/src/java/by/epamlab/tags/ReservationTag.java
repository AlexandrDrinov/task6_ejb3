package by.epamlab.tags;

import by.epamlab.ejb.ifaces.ReservationRemote;
import by.epamlab.exception.DAOException;
import java.rmi.RemoteException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ReservationTag extends AbstractTag {

    @Override
    public void doTag() {

        try {
            InitialContext ctx = initContext();  
            ReservationRemote reservation = (ReservationRemote) ctx.lookup("ReservationSessionBean/remote");
            getJspContext().setAttribute("reservation", reservation.getReservation());
        } catch (NamingException | DAOException | RemoteException ex) {            
            getJspContext().setAttribute("reservation", null);
        }
    }
}
