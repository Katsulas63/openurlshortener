# ¿Qué es Open URL Shortener? #

Consiste en un servicio de url-shortening (del tipo de bit.ly o tinyURL) muy básico desplegado en Cloud Foundry (https://www.cloudfoundry.com/)

![http://2.bp.blogspot.com/-oUOEWlmPSGU/TbBlZOvTPjI/AAAAAAAABGE/687QgQrterk/s320/Cloud%2BFoundry.png](http://2.bp.blogspot.com/-oUOEWlmPSGU/TbBlZOvTPjI/AAAAAAAABGE/687QgQrterk/s320/Cloud%2BFoundry.png)

**Funcionalmente** es:

1) Servicio web con las operaciones:

  * shortenURL (longURL)
  * expandURL (shortURL)

La implementación de este servicio utiliza [MongoDB](http://www.mongodb.org/) para el almacenamiento.

2) Cliente web

  * Interfaz que internamente llama a shortenURL y que devuelva el resultado
  * Redirija a la URL larga cuando reciba una url corta

Se trata de una arquitectura distribuida en capas diferenciando Servicio, DAO, etc.

La **implementación** hace uso de las siguientes tecnologías:

  * Spring Framework
  * Maven
  * GWT
  * Webservices (REST)

![http://www.software-p2p.com/wp-content/uploads/2009/09/google-app-engine.jpg](http://www.software-p2p.com/wp-content/uploads/2009/09/google-app-engine.jpg)

# Demo online #

**URL-Shortener Server**: http://url-shortener-ws.cloudfoundry.com/

**URL-Shortener Client**: http://orgurlshortener.appspot.com/

**Sistema Integración Contínua (Hudson)**: http://url-shortener-hudson.cloudfoundry.com/