<html>
<head>

</head>
<body>
<h1>Racun</h1>
<p><b>Prodavnica:</b> ${prodavnica}</p>
<table>
    <tr>
        <td><b>Artikal</b></td>
        <td><b>Cena</b></td>
        <td><b>Cena sa PDV</b></td>
    </tr>
    <#list artikli as artikal>
    <tr>
        <td>${artikal.naziv}</td>
        <td>${artikal.cena}</td>
        <td>${artikal.cenaSaPorezom()}</td>
    </tr>
    </#list>
    <tr>
        <td colspan="2">Ukupno</td>
        <td>${ukupno}</td>
    </tr>
    <tr>
        <td colspan="2">Porez</td>
        <td>${porez}</td>
    </tr>
</table>
<p><b>Racun izdat:</b> ${izdato}</p>
</body>
</html>