/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;

/**
 *
 * @author lelguea
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String LDAP="ldap://valida_mexico.academic.mixcoac.upmx.mx";
        String usuario="c_usuarios";
        String password="5a&udRadus!cEste";
        usuario="0160670";
        password="583661gB";
        Properties env = new Properties();
        
       env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, LDAP);
        env.put(Context.SECURITY_PRINCIPAL, "academic\\"+usuario);
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.REFERRAL, "follow");
        //env.put(Context.REFERRAL, "ignore");

        try {
        // Create initial context
        DirContext ctx = new InitialDirContext(env);

        String[] attrIDs = {"name","ou"};
        SearchControls ctls = new SearchControls();
        ctls.setReturningAttributes(attrIDs);
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //ctls.setSearchScope(SearchControls.);
        //ctls.s

        String id="(&ObjectCategory=computer)";
        String consulta="(operatingSystem=Windows 10*)";
        consulta="(&(objectClass=organizationalUnit)(ou=*))";
        consulta="(ou=*)";
        NamingEnumeration answer = ctx.search("OU=Mexico,DC=academic,DC=mixcoac,DC=upmx,DC=mx",consulta,ctls);

        while (answer.hasMore()) {
            SearchResult sr = (SearchResult)answer.next();
            Attributes result = sr.getAttributes();

            Attribute attr = result.get("name");
            Attribute attr2 = result.get("description");
            if (attr != null) {
                //System.out.println("Se valido "+ar+usuario);
                System.out.println(attr2+".- "+attr);
            }
        }
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        // TODO code application logic here
    }
    
}
