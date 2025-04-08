<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Depense</title>
</head>
<body>
    <h2>Ajouter Depense</h2>
    <form action="depense" method="post">

      
        <label for="somme">Somme:</label>
        <input type="number" id="somme" name="somme">
        
        <br>
        <br>


        
        
        <br>
        <h3>prevision:</h3>
        <select name="idPv" id="idPv">
            <option value="1" ${idPv == 1 ? 'selected' : ''}>Choisir Prevision</option>
            <c:forEach var="pv" items="${PrevisionList}">
                <option value="${pv.getId()}" ${idPv == pv.getId() ? 'selected' : ''}>
                    ${pv.libelle}
                </option>
            </c:forEach>
        </select>

  
        <input type="submit" value="Submit">
    </form>

   
  
    
</body>
</html>
