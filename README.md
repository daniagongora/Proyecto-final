# Proyecto-final Shamir secret sharing

Proyecto final de modelado y programación.

### Requisitos

Se necesita instalar java(yo tengo hasta java 13 asi que supongo que ese) y se necesita instalar ant, para
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