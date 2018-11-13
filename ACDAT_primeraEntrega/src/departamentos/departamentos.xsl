<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : departamentos.xsl
    Created on : 8 de noviembre de 2018, 17:28
    Author     : likendero
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="departamentos">
        <html>
            <head>
                <title>departamentos</title>
            </head>
            <body>
               
                <table>
                <tr>
                    <th> atributo </th>
                    <th> valor </th>
                </tr>
                <tr>
                    <td>id</td>
                    <td><xsl:value-of select="id"/></td>
                </tr>
                <tr>
                    <td>tipo</td>
                    <td><xsl:value-of select="tipo"/></td>
                </tr>
                <tr>
                    <td>nombre</td>
                    <td><xsl:value-of select="nombre"/></td>
                </tr>
                <tr>
                    <td>domicilio</td>
                    <td><xsl:value-of select="domicilio"/></td>
                </tr>
                <tr>
                    <td>ciudad</td>
                    <td><xsl:value-of select="ciudad"/></td>
                </tr>
                <tr>
                    <td>codigo postal</td>
                    <td><xsl:value-of select="cp"/></td>
                </tr>
                <tr>
                    <td>provincia</td>
                    <td><xsl:value-of select="provincia"/></td>
                </tr>
                <tr>
                    <td>pais</td>
                    <td><xsl:value-of select="pais"/></td>
                </tr>
                </table>
                
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
