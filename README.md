RIA_2018_Spring_Autorización
====================================

## Requisitos
Tener Maven instalado y agregado al path del correspondiente sistema operativo

## Compilación y empaquetado
```
mvn install
mvn compile
mvn package
```
Esto debería generar un archivo de nombre `RIA_spring-1.0.jar`

## Ejecución
Una vez dentro de la carpeta `target` ejecutamos el siguiente comando: 

``` 
java -jar RIA_spring-1.0.jar
```

## Uso
Para poder probar el programa debemos realizar las siguientes consultas:
>Los ejemplos de consultas correspondes a comandos pensados para ser ejecutados en un entorno Linux.

>Para poder realizar los pedidos desde un entorno Windows recomiendo la instalación de **Postman** o un programa de similares características.

 ### 1. JWT
#### Ovtener token
```
curl -X POST http://localhost:8080/token -H 'Content-Type: application/json' -d '{"email":"fulano@ria.com","password":"qwerty"}'
```
Esto retornara un token que utilizaremos en la siguiente consulta para poder acceder al contenido del endpoint `/index`.

Un ejemplo del token retornado es: `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmdWxhbm9AcmlhLmNvbSIsImV4cCI6MTUyODEzOTU5OCwiaWF0IjoxNTI4MTM5NTY4LCJ1c2VybmFtZSI6IkZ1bGFubyJ9.tjQYLTGKUl5IyStdJmSHUsuxzAUO9J-6e45McGxiVN4`


>Tener en cuenta que el token expira el poco tiempo de ser emitido (30 sec), si la próxima consulta retorna `'token invalido'`, esto puede deberse a que paso mucho tiempo entre una consulta y la siguiente.
#### Realizar consulta
```
curl -X GET http://localhost:8080/index -H 'Authorization: RIA {token}'
```
Un ejemplo de esta consulta seria: 
```
curl -X GET http://localhost:8080/index -H 'Authorization: RIA eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmdWxhbm9AcmlhLmNvbSIsImV4cCI6MTUyODEzOTU5OCwiaWF0IjoxNTI4MTM5NTY4LCJ1c2VybmFtZSI6IkZ1bGFubyJ9.tjQYLTGKUl5IyStdJmSHUsuxzAUO9J-6e45McGxiVN4'
```
>Es importante respetar el espacio entre el prefijo del Header `RIA` y el token.

En caso de que la consulta sea exitosa el resultado deberia ser: `Bienvendio Fulano`

 ### 2. Autenticación básica
#### Realizar consulta con autenticación básica
```
curl -X GET http://ria:qwerty@localhost:8080/service
```
Esta consulta debería retornar: `Usuario y contraseña correctos :D`
