# Proyecto-final Shamir secret sharing

Proyecto final de modelado y programación.
Dania Paula Góngora Ramírez 318128274 y Daniel

Nota:Mi compañero dejo de contestar mis mensajes para
saber su nombre completo pero si participo en el proyecto

### Requisitos

Se necesita instalar java 13 y se necesita instalar ant, para
instalar ant en ubuntu es necesario abrir la terminal y poner los siguientes comandos:

```
sudo apt update
```
y después
```
sudo apt install ant
```

### Ejecución del proyecto

Para correr el proyecto en necesario poner:

```
ant jar
```
y depués
```
java -jar build/jar/proyecto.jar (el argumento que se necesiten 'c' o 'd') 
```
Si se quiere cifrar(argumento 'c')
```
java -jar build/jar/proyecto.jar c nombreDelArchivoAGuardar numeroDeEvaluaciones numeroMinimodeEv NombreDelArchivoOriginal 
```
Si se quiere descifrar(argumento 'd')
```
java -jar build/jar/proyecto.jar d nombreDelDocConLasEvaluaciones  nombreDElDocCifrado 
```
Para correr la pruebas es necesario poner:

```
ant tst
```
y después
```
ant test
```
Para generar la documentación es con:

```
ant doc
```