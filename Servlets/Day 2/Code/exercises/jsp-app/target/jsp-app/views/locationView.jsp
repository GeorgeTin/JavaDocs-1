<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ro.teamnet.zth.appl.domain.Location" language="java" %>
<%@ page import="ro.teamnet.zth.appl.dao.LocationDao" language="java" %>
<html>
<head>
    <title>Locations List</title>
</head>
<body>

<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Street address</td>
        <td>Postal code</td>
        <td>City</td>
        <td>State province</td>
    </tr>
    </thead>
    <tbody>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    %>
    <tr>
        <!--TODO de completat cu cod pentru a afisa detaliile locatiei cu id-ul trimis din locationlist.jsp in momentul in care se acceseaza link-ul 'View'-->
        <%
            Integer id = Integer.parseInt(request.getParameter("id"));
            LocationDao locDao = new LocationDao();
            Location loc = locDao.getLocationById(id);
        %>
        <td>
            <%=loc.getId()%>
        </td>
        <td>
            <%=loc.getStreetAddress()%>
        </td>
        <td>
            <%=loc.getPostalCode()%>
        </td>
        <td>
            <%=loc.getCity()%>
        </td>
        <td>
            <%=loc.getStateProvince()%>
        </td>
    </tr>

    </tbody>
</table>
<a href="locationList.jsp">Locations List</a>
</body>
</html>
