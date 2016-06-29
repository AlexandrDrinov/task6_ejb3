package by.epamlab.tags;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class AbstractTag extends SimpleTagSupport {

    private String var;

    public void setVar(String var) {
        this.var = var;
    }

    public InitialContext initContext() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        return new InitialContext(props);
    }

    public String getFullPath() {
        PageContext context = (PageContext) getJspContext();
        return context.getServletContext().getRealPath("/WEB-INF/reservation.xml");
    }
}
