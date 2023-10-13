<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<html>
<body>
<H4>Welcome!</H4>
<p>If you wanna to check weather: - <a href="/weather">Check!</a></p>

<%!
    String getFormatted()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy hh:mm:ss");
        return sdf.format(new Date());
    }
%>
<i>Today <%= getFormatted() %></i>
</body>
</html>