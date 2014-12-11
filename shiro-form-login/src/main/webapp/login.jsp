<%@ include file="include.jsp"%>

<html>
    <head>
        <link type="text/css" rel="stylesheet"
              href="<c:url value="/style.css"/>" />
    </head>
    <body>

        <h2>Please Log in</h2>

    <shiro:guest>
        <p>Here are a few sample accounts to play with in the default
            text-based Realm (used for this demo and test installs only). Do you
            remember the movie these names came from? ;)</p>


        <style type="text/css">
            table.sample {
                border-width: 1px;
                border-style: outset;
                border-color: blue;
                border-collapse: separate;
                background-color: rgb(255, 255, 240);
            }
            table.sample th {
                border-width: 1px;
                padding: 1px;
                border-style: none;
                border-color: blue;
                background-color: rgb(255, 255, 240);
            }
            table.sample td {
                border-width: 1px;
                padding: 1px;
                border-style: none;
                border-color: blue;
                background-color: rgb(255, 255, 240);
            }
        </style>


        <table class="sample">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>admin</td>
                    <td>pass123</td>
                </tr>
            </tbody>
        </table>
        <br />
        <br />
    </shiro:guest>

    <h2>Login</h2>
    <form method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" />
        <br/>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" />
        <br/>
        <br/>
        <input type="submit" value="Login" />
    </form>

</body>
</html>