<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : departamentosList.xsl
    Created on : 12 de noviembre de 2018, 8:57
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
                <title>departamentosList.xsl</title>
            </head>
            <body>
                <table>
                    <tr>
                        <th>id</th>
                        <th>tipo</th>
                        <th>nombre</th>
                        <th>domicilio</th>
                        <th>ciudad</th>
                        <th>codigoPostal</th>
                        <th>provincia</th>
                        <th>pais</th>
                    </tr>
                    <xsl:for-each select="departamento">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="tipo"/></td>
                            <td><xsl:value-of select="nombre"/></td>
                            <td><xsl:value-of select="domicilio"/></td>
                            <td><xsl:value-of select="ciudad"/></td>
                            <td><xsl:value-of select="codigoPostal"/></td>
                            <td><xsl:value-of select="provincia"/></td>
                            <td><xsl:value-of select="pais"/></td>
                            
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
