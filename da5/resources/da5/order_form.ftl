<html>
<head>

</head>
<body>
    <h1>Order</h1>

    <p><b>Size:</b> ${size}</p>

    <p><b>Meat</b></p>
    <ul>
    <#list meat as m>
        <li>${m}</li>
    </#list>
    </ul>


    <p><b>Toppings</b></p>
    <ul>
    <#list toppings as t>
        <li>${t}</li>
    </#list>
    </ul>

    <p><b>Contact info</b></p>
    <p>${contact_info}</p>
</body>
</html>