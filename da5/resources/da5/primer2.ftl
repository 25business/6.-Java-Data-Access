<html>
<head>
    <style>
        h1 {
            font-family: sans-serif;
        }
    </style>
</head>
<body>
    <h1>TODO lista generisana ${datum}</h1>
    <ul>
        <#list stavke as stavka>
        <li>${stavka}</li>
        </#list>
    </ul>
</body>
</html>