<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Etat</title>
</head>
<body>
    <h2>Etat</h2>

    <table border="1">
        <thead>
            <tr>
                <th>idprevision</th>
                <th>libelle</th>
                <th>depense</th>
                <th>reste</th>
             
            </tr>
        </thead>
        <tbody>
            <c:forEach var="etat" items="${EtatList}">
                <tr>
                    <td>${etat.getprevision()}</td>
                    <td>${etat.libelle}</td>
                    <td>${etat.depense}</td>
                    <td>${etat.reste}</td>
              
                </tr>

            </c:forEach>
        </tbody>
    </table>

    <br>
    
</body>
</html>
