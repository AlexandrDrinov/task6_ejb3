package by.epamlab.tags;

import by.epamlab.ejb.ifaces.ResComponentRemote;
import by.epamlab.exception.DAOException;
import by.epamlab.model.beans.ResComponent;
import java.rmi.RemoteException;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ResComponentTag extends AbstractTag {

    private String resCode;
    private String resComponentTypeCode;

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setResComponentTypeCode(String resComponentTypeCode) {
        this.resComponentTypeCode = resComponentTypeCode;
    }

    @Override
    public void doTag() {

        try {
            InitialContext ctx = initContext();
            ResComponentRemote resComponentRemote = (ResComponentRemote) ctx.lookup("ResComponentSessionBean/remote");
            List<ResComponent> resList;
            if (resCode == null) {
                resList = resComponentRemote.getResComponents();
            } else if (resComponentTypeCode == null) {
                resList = resComponentRemote.getResComponents(resCode);
            } else {
                resList = resComponentRemote.getResComponents(resCode, resComponentTypeCode);
            }
            getJspContext().setAttribute("resComponents", resList);

        } catch (NamingException | DAOException | RemoteException ex) {
            getJspContext().setAttribute("resComponents", null);
        }
    }
}
