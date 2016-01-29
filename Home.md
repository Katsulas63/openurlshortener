# Introducción #

Consiste en un servicio de url-shortener (del tipo de bit.ly o tinyURL) muy básico desplegable en Cloud Foundry (https://www.cloudfoundry.com/ ), para la prueba se puede utilizar https://www.cloudfoundry.com/micro

**Funcionalmente** el proyecto debe cumplir:

1) Servicio web con las operaciones:

  * shortenURL (longURL)
  * expandURL (shortURL)

La implementación del servicio debe utilizar mongodb para el almacenamiento (ver ejemplo en https://github.com/SpringSource/cloudfoundry-samples/wiki/Spring-Hello-MongoDB-Sample-Application )

2) Cliente web

  * Interfaz que internamente llame a shortenURL y que devuelva el resultado
  * Redirija a la URL larga cuando reciba una url corta

El **objetivo** del ejercicio es mostrar la capacidad para desarrollar un proyecto básico usando una arquitectura en capas diferenciando Servicio, DAO, etc, así como los conocimientos técnicos de las herramientas a emplear en el proyecto.

La **implementación** deberá hacer uso de todas o algunas de las siguientes tecnologías:

  * Spring Framework
  * Hibernate
  * Maven
  * GWT
  * Webservices (SOAP y/o REST)