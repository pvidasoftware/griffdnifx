Basic Griffon Swing/Groovy project
----------------------------------

GriffDnie es una aplicacion basada en Griffon (Swing UI toolkit) y
Groovy como lenguaje principal. Simplemente es un ejemplo de cómo utilizar este framework en el desarrollo
de aplicaciones Desktop demostrando cómo utilizar componentes hardware imposibles de usar en una aplicación
típica web.

La estructura de la aplicación es la típica de una aplicación Griffon:

    .
    ├── build.gradle
    ├── griffon-app
    │   ├── conf
    │   ├── controllers
    │   ├── i18n
    │   ├── lifecycle
    │   ├── models
    │   ├── resources
    │   ├── services
    │   └── views
    ├── pom.xml
    └── src
        ├── integration-test
        │   └── groovy
        ├── main
        │   ├── groovy
        │   └── resources
        └── test
            ├── groovy
            └── resources

Una vez descargada, simplemente ejecuta los siguientes comandos para ejecutarla.

    gradle build
    gradle test
    gradle run

Para una funcionalidad completa deberás tener un lector de DNIe insertado y configurado para la lectura
del DNIe. 

Para ejecutar la última versión accede a https://pvidasoftware.github.io/griffdni/
