<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login in system</title>

</head>
<body>
<h1>Вход в систему</h1>
<form method="post" action="${pageContext.request.contextPath}/login">
    <hr/>
    <tr>
        <td>Login: </td>
        <td><input type="text" name="login"></td>
    </tr><br/><br/>

    <tr>
        <td>Password: </td>
        <td><input type="password" name="password"></td>
    </tr><br/><br/>

    <input class="button" type="submit" value="Войти">
    </table>
</form>
<hr/>
<a href="${pageContext.request.contextPath}/">На головну</a>

</body>
</html>