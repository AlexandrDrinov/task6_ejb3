package by.epamlab.struts.actions;

import by.epamlab.ejb.ifaces.UserRemote;
import by.epamlab.exception.DAOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import by.epamlab.ifaces.IUserDAO;
import by.epamlab.model.beans.ResComponent;
import by.epamlab.model.beans.User;
import by.epamlab.model.factories.UserFactory;
import by.epamlab.struts.forms.LoginForm;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginAction extends Action {

    public InitialContext initContext() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        return new InitialContext(props);
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages errors = new ActionMessages();
        LoginForm loginForm = (LoginForm) form;
        String result = null;
        try {
            InitialContext ctx = initContext();
            UserRemote userRemote = (UserRemote) ctx.lookup("UserSessionBean/remote");
            User user = userRemote.getUser(loginForm.getUserLogin(), loginForm.getUserPassword());

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            result = "success";
        } catch (NamingException | DAOException | RemoteException ex) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("err.user.username.incorrect"));
            saveErrors(request, errors);
            result = "failure";
        } 
        return mapping.findForward(result);
    }
}
