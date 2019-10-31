# MeliGalaxy

_Esta app intenta resolver el ejercicio propuesta por MercadoLibre._

## Construido con 🛠️

_Se utilizaron las siguientes tecnologias_

* [SpringBoot](http://www.dropwizard.io/1.0.2/docs/) - El framework web usado.
* [SpringData](https://spring.io/projects/spring-data#overview) - FrameWork para realizar persistencia y consulta a la db.
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [H2DataBaseEngine](https://www.h2database.com/html/main.html) - Base de datos SQL.
* [Junit](https://junit.org/junit5/) - Framework de testing.
* [Java8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Lenguaje de pogramacion.

## Endpoints que expone la API
_Esta API te permite predecir el clima en el sistema de los proximos 10 años, para ello se dispone de lo siguiente:_

	GET    /galaxy/clima?dia
	GET    /galaxy/clima/dias_sequia
  	GET    /galaxy/clima/dias_optimos
	GET    /galaxy/clima/dias_max_precipitacion


## Ejemplos

	# Obtiene la cantidad de dias de lluvia en los proximos 10 años y ademas el pico maximo de lluvia.
	GET http://localhost:8080/galaxy/clima/dias_max_precipitacion

	# Obtiene el clima del dia pasado como parametro.
	GET http://localhost:8080/galaxy/clima?dia=1

	# Devuelve la cantida de dias de sequia en los proximos 10 años.
	GET http://localhost:8080/galaxy/clima/dias_sequia

	# Devuelve la cantidad de dias optimo en los proximos 10 años.
	GET http://localhost:8080/galaxy/clima/dias_optimos
