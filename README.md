

## Steps

### JBOSS EAP

> download the JBOSS Version 7.2 https://access.redhat.com/jbossnetwork/restricted/softwareDownload.html?softwareId=64311

```shell
# unzip the zip installation
unzip jboss-eap-7.2.0.zip

# add ejb aplication user
${JBOSS_HOME}/bin/add-user.sh -a -u ejb -p ejb

# start EAP standalone
${JBOSS_HOME}/bin/standalone.sh
```

### Build + Deploy

> in another terminal

```shell
# compile and install 
mvn clean install
```

```shell
# deploy
mvn wildfly:deploy
```

> output on server from deployed artifacts: **wejb.ear** and **web.war**

```shell
15:55:42,401 INFO  [org.jboss.as.repository] (management-handler-thread - 1) WFLYDR0001: Content added at location /Users/renato/redhat/products/EAP/jboss-eap-7.2/standalone/data/content/cc/cec715a4c497b82113ae394c2ebc2fa928d454/content
15:55:42,415 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-7) WFLYSRV0027: Starting deployment of "web.war" (runtime-name: "web.war")
15:55:43,560 WARN  [org.jboss.weld.deployer] (MSC service thread 1-8) WFLYWELD0013: Deployment web.war contains CDI annotations but no bean archive was found (no beans.xml or class with bean defining annotations was present).
15:55:43,715 INFO  [org.infinispan.factories.GlobalComponentRegistry] (MSC service thread 1-1) ISPN000128: Infinispan version: Infinispan 'Estrella Galicia' 9.3.3.Final-redhat-00001
15:55:43,850 INFO  [org.jboss.as.clustering.infinispan] (ServerService Thread Pool -- 72) WFLYCLINF0002: Started client-mappings cache from ejb container
15:55:43,940 INFO  [org.wildfly.extension.undertow] (ServerService Thread Pool -- 72) WFLYUT0021: Registered web context: '/web' for server 'default-server'
15:55:43,983 INFO  [org.jboss.as.server] (management-handler-thread - 1) WFLYSRV0010: Deployed "web.war" (runtime-name : "web.war")
15:55:44,293 INFO  [org.jboss.as.repository] (management-handler-thread - 1) WFLYDR0001: Content added at location /Users/renato/redhat/products/EAP/jboss-eap-7.2/standalone/data/content/03/482985c68e1e873f49feb31279d8635530f635/content
15:55:44,296 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-2) WFLYSRV0027: Starting deployment of "wejb.ear" (runtime-name: "wejb.ear")
15:55:44,307 WARN  [org.jboss.as.server.deployment] (MSC service thread 1-7) WFLYSRV0059: Class Path entry wejb.jar in /content/wejb.ear  does not point to a valid jar for a Class-Path reference.
15:55:44,307 INFO  [org.jboss.as.server.deployment] (MSC service thread 1-2) WFLYSRV0207: Starting subdeployment (runtime-name: "wejb.jar")
15:55:44,352 INFO  [org.jboss.weld.deployer] (MSC service thread 1-8) WFLYWELD0003: Processing weld deployment wejb.ear
15:55:44,432 INFO  [org.hibernate.validator.internal.util.Version] (MSC service thread 1-8) HV000001: Hibernate Validator 6.0.14.Final-redhat-00001
15:55:44,554 INFO  [org.jboss.weld.deployer] (MSC service thread 1-3) WFLYWELD0003: Processing weld deployment wejb.jar
15:55:44,560 INFO  [org.jboss.as.ejb3.deployment] (MSC service thread 1-3) WFLYEJB0473: JNDI bindings for session bean named 'CalculatorEJB' in deployment unit 'subdeployment "wejb.jar" of deployment "wejb.ear"' are as follows:

	java:global/wejb/wejb/CalculatorEJB!br.com.server.Calculator
	java:app/wejb/CalculatorEJB!br.com.server.Calculator
	java:module/CalculatorEJB!br.com.server.Calculator
	java:jboss/exported/wejb/wejb/CalculatorEJB!br.com.server.Calculator
	ejb:wejb/wejb/CalculatorEJB!br.com.server.Calculator
	java:global/wejb/wejb/CalculatorEJB
	java:app/wejb/CalculatorEJB
	java:module/CalculatorEJB

15:55:44,584 INFO  [org.jboss.weld.Version] (MSC service thread 1-7) WELD-000900: 3.0.5 (redhat)
15:55:45,032 INFO  [org.jboss.as.server] (management-handler-thread - 1) WFLYSRV0010: Deployed "wejb.ear" (runtime-name : "wejb.ear")
```

## Run on Browser

http://localhost:8080/web/



> expected output on <u>browser</u>:
>
> ## Hello World!

>  expected output on <u>server</u>:

```shell
15:57:22,640 INFO  [org.wildfly.naming] (default task-1) WildFly Naming version 1.0.9.Final-redhat-1
15:57:22,652 INFO  [stdout] (default task-1) jndiName: ejb:wejb/wejb/CalculatorEJB!br.com.server.Calculator
15:57:22,653 INFO  [stdout] (default task-1) providerUrl: http://localhost:8080/wildfly-services
15:57:22,665 INFO  [org.jboss.ejb.client] (default task-1) JBoss EJB Client version 4.0.12.Final-redhat-00001
15:57:22,672 INFO  [stdout] (default task-1) Proxy for remote EJB StatelessEJBLocator for "wejb/wejb/CalculatorEJB", view is interface br.com.server.Calculator, affinity is None
15:57:23,094 INFO  [stdout] (default task-1) 24.15
```



# Know Error

> if you forget to create ejb Application user

```shell
16:17:44,690 ERROR [io.undertow.request] (default task-1) UT005023: Exception handling request to /web/: org.apache.jasper.JasperException: javax.ejb.EJBException: javax.naming.AuthenticationException: WFHTTP000013: Authentication failed (full response ClientResponse{responseHeaders={www-authenticate=[Digest realm="ApplicationRealm", nonce="AAAAAgAAE/hOV/klE7C/DgUjCoothcGzPCT3Gth0107GtPfNrI2mGJENkJs=", opaque="00000000000000000000000000000000", algorithm=MD5, qop=auth], content-length=[77], content-type=[text/html], date=[Tue, 17 Aug 2021 19:17:44 GMT]}, responseCode=401, status='', protocol=HTTP/2.0})
	at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:473)
	at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:403)
	at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:347)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:791)
	at io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:74)
	at io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)
	at io.undertow.jsp.JspFileHandler.handleRequest(JspFileHandler.java:32)
	at io.undertow.servlet.handlers.ServletChain$1.handleRequest(ServletChain.java:68)
	at io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)
	at org.wildfly.extension.undertow.security.SecurityContextAssociationHandler.handleRequest(SecurityContextAssociationHandler.java:78)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.servlet.handlers.RedirectDirHandler.handleRequest(RedirectDirHandler.java:68)
	at io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:132)
	at io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)
	at io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)
	at io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)
	at io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)
	at io.undertow.security.handlers.NotificationReceiverHandler.handleRequest(NotificationReceiverHandler.java:50)
	at io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at org.wildfly.extension.undertow.security.jacc.JACCContextIdHandler.handleRequest(JACCContextIdHandler.java:61)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at org.wildfly.extension.undertow.deployment.GlobalRequestControllerHandler.handleRequest(GlobalRequestControllerHandler.java:68)
	at io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)
	at io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:269)
	at io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:78)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:133)
	at io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:130)
	at io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)
	at io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)
	at org.wildfly.extension.undertow.security.SecurityContextThreadSetupAction.lambda$create$0(SecurityContextThreadSetupAction.java:105)
	at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
	at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
	at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
	at org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService$UndertowThreadSetupAction.lambda$create$0(UndertowDeploymentInfoService.java:1504)
	at io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:249)
	at io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:78)
	at io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:99)
	at io.undertow.server.Connectors.executeRootHandler(Connectors.java:376)
	at io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:830)
	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1982)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1486)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1348)
	at java.lang.Thread.run(Thread.java:748)
Caused by: javax.ejb.EJBException: javax.naming.AuthenticationException: WFHTTP000013: Authentication failed (full response ClientResponse{responseHeaders={www-authenticate=[Digest realm="ApplicationRealm", nonce="AAAAAgAAE/hOV/klE7C/DgUjCoothcGzPCT3Gth0107GtPfNrI2mGJENkJs=", opaque="00000000000000000000000000000000", algorithm=MD5, qop=auth], content-length=[77], content-type=[text/html], date=[Tue, 17 Aug 2021 19:17:44 GMT]}, responseCode=401, status='', protocol=HTTP/2.0})
	at org.jboss.ejb.client.EJBInvocationHandler.invoke(EJBInvocationHandler.java:221)
	at org.jboss.ejb.client.EJBInvocationHandler.invoke(EJBInvocationHandler.java:125)
	at com.sun.proxy.$Proxy61.calculateInterest(Unknown Source)
	at br.com.client.RemoteEJBClient.main(RemoteEJBClient.java:21)
	at br.com.client.RemoteEJBClient.func(RemoteEJBClient.java:15)
	at org.apache.jsp.index_jsp._jspService(index_jsp.java:99)
	at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:791)
	at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:433)
	... 46 more
Caused by: javax.naming.AuthenticationException: WFHTTP000013: Authentication failed (full response ClientResponse{responseHeaders={www-authenticate=[Digest realm="ApplicationRealm", nonce="AAAAAgAAE/hOV/klE7C/DgUjCoothcGzPCT3Gth0107GtPfNrI2mGJENkJs=", opaque="00000000000000000000000000000000", algorithm=MD5, qop=auth], content-length=[77], content-type=[text/html], date=[Tue, 17 Aug 2021 19:17:44 GMT]}, responseCode=401, status='', protocol=HTTP/2.0})
	at org.wildfly.httpclient.common.HttpTargetContext$1$1.lambda$completed$4(HttpTargetContext.java:232)
	at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
	at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:1982)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1486)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1377)
	... 1 more
```



# Reference

* Pure HTTP EJB invocation not working - https://issues.redhat.com/browse/WFLY-9869?attachmentOrder=asc
* Remote EJB Client Example - https://github.com/jboss-developer/jboss-eap-quickstarts/tree/7.2.0.GA/ejb-remote

