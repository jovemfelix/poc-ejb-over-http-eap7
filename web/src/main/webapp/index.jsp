<%@ page import="br.com.client.RemoteEJBClient"%>
<html>
<body>
<h2>Hello World!</h2>

<%
  RemoteEJBClient tc = new RemoteEJBClient();
  tc.func();
%>

</body>
</html>
