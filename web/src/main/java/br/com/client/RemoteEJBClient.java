package br.com.client;

import br.com.server.Calculator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class RemoteEJBClient {
    private final static String providerUrl="http://localhost:8080/wildfly-services";

    public void func() throws Exception {
        RemoteEJBClient.main();
    }

    public static void main(String ... args) throws Exception {
        Calculator calc = (Calculator) lookup(Calculator.class, "wejb");
        System.out.println(calc);
        System.out.println(calc.calculateInterest(23L));
    }

    @SuppressWarnings("unchecked")
    public static <T> T lookup(Class<T> t, String packageName) throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        properties.put(Context.PROVIDER_URL, providerUrl);
        InitialContext context = new InitialContext(properties);

        StringBuilder jndiName = new StringBuilder();
        jndiName.append("ejb:");
        jndiName.append(packageName);
        jndiName.append("/");
        jndiName.append(packageName);
        jndiName.append("/");
        jndiName.append(t.getSimpleName());
        jndiName.append("EJB!");
        jndiName.append(t.getName());

        System.out.println("jndiName: " + jndiName);
        System.out.println("providerUrl: " + providerUrl);

        return (T) context.lookup(jndiName.toString());
    }

}
