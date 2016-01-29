# Bitacora de pasos seguidos con el ejemplo hello-spring-mongodb #

  1. Instalo VMware Player y abro la VM de micro cloud.
  1. Habilito la VT en mi BIOS para permitir 64-bits
  1. Configuro el cloud con mi cuenta
  1. En Windows: me instalo ruby, vmc y git.
  1. C:\GWT\Workspace>git clone https://carlosyague@github.com/SpringSource/cloudfoundry-samples.git
  1. Establecemos como $JAVA\_HOME una JDK 1.6
  1. $hello-spring-mongodb>mvn package
  1. Despliego el WAR en un Apache Tomcat 6 configurado con una JDK 1.6
  1. Pruebo a acceder a: http://localhost:8080/hello-spring-mongodb/ y me da un fallo de conexión a la DB en: 127.0.0.1:27017

Me instalo la DB MongoDB
  1. Me descargo la versión para windows de la bbdd mongoDB: http://downloads.mongodb.org/win32/mongodb-win32-i386-1.8.3.zip
  1. Sigo la guía de instalación: http://www.mongodb.org/display/DOCS/Quickstart+Windows
  1. Me creo el directorio "C:\data\db"
  1. Levanto el servidor de db: $MONGO\_HOME\bin> mongod
  1. Pruebo a acceder a: http://localhost:8080/hello-spring-mongodb/ y me da un fallo de autenticación en la DB para:test\_user/efgh@pwdtest

Creo el usuario **test\_user** en la BD creada por defecto 'pwdtest':
  1. $MONGO\_HOME\bin> mongo
  1. mongo> show dbs;
  1. mongo> use pwdtest;
  1. mongo> db.addUser("test\_user", "efgh")

Vuelvo a acceder a: http://localhost:8080/hello-spring-mongodb/ y compruebo que ya funciona.

Despliego dicha aplicación en CloudFoundry.com:

  1. $hello-spring-mongodb\target> **vmc push --no-start** (primera vez)

  1. $hello-spring-mongodb\target> vmc update hello-spring-mongodb (sucesivas veces)

```
Would you like to deploy from the current directory? [Yn]: Y
Application Name: carlosyague-spring-mongodb
Application Deployed URL: 'carlosyague-spring-mongodb.cloudfoundry.com'?
Detected a Java SpringSource Spring Application, is this correct? [Yn]:Y
Memory Reservation [Default:512M] (64M, 128M, 256M, 512M or 1G)
Creating Application: OK
Would you like to bind any services to 'carlosyague-spring-mongodb'? [yN]: y
Would you like to use an existing provisioned service [yN]? y
The following provisioned services are available:
1. prueba
Please select one you wish to provision: prueba
Binding Service: OK
Uploading Application:
  Checking for available resources: OK
  Processing resources: OK
  Packing application: OK
  Uploading (2K): OK
Push Status: OK
```

  1. Verifico que la aplicación está creada:

$hello-spring-mongodb\target> **vmc apps**

```
+----------------------------+----+---------+---------------------------------------------+----------+
| Application                | #  | Health  | URLS                                        | Services |
+----------------------------+----+---------+---------------------------------------------+----------+
| carlosyague                | 1  | 0%      | carlosyague.cloudfoundry.com                | prueba   |
| carlosyague-spring-mongodb | 1  | STOPPED | carlosyague-spring-mongodb.cloudfoundry.com | prueba   |
+----------------------------+----+---------+---------------------------------------------+----------+
```

  1. Levanto la aplicación en el servidor:

$hello-spring-mongodb\target> **vmc start carlosyague-spring-mongodb**
Staging Application: OK
Starting Application: OK

  1. Verifico que está corriendo el servidor:

hello-spring-mongodb\target> **vmc apps**

```
+----------------------------+----+---------+---------------------------------------------+----------+
| Application                | #  | Health  | URLS                                        | Services |
+----------------------------+----+---------+---------------------------------------------+----------+
| carlosyague                | 1  | 0%      | carlosyague.cloudfoundry.com                | prueba   |
| carlosyague-spring-mongodb | 1  | RUNNING | carlosyague-spring-mongodb.cloudfoundry.com | prueba   |
+----------------------------+----+---------+---------------------------------------------+----------+
```

# Cliente REST Flickr #

java Driver.class be0292b9f8fb1621c685ec812f715c2c penguin


# Servidor REST restdemo #

http://localhost:8080/restdemo/services/customers

# Servidor REST url-shortener-ws #

GET
Content-Type=application/x-www-form-urlencoded
http://localhost:8080/url-shortener-ws/rest/expandURL/1212

# Creo colección #
> db.createCollection("urlshortable", {capped:true, size:100000});

# Borro la colección #
> db.urlshortable.drop();
> db.urlshortable.remove();