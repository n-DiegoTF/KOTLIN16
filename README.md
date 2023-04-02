# Fase II Desarrollo Movil - Kotlin Fundamentals
## Proyecto: ATM

### Definición del Proyecto
***
Este proyecto está hecho para simular las operaciones que realizaría el software de un cajero automatico, por ejemplo,
consultar saldo de usuarios, realizar transacciones, simular retiros y depositos, etc.

### Alcance del Proyecto
***
La finalidad de este proyecto es demostrar los conocimientos adquiridos durante el módulo II de Desarrollo Movil,
por lo que tomamos una idea ya existente y la desarrollamos basándonos en el lenguaje kotlin y a los conceptos aprendidos
durante cada sesión de la fase 2.

***Nota importante:*** durante el desarrollo del proyecto algunas partes del código tuvieron cambios significativos para poder
implementar temas que se vieron a lo largo del módulo.

### Integrantes:
* [Usuario1](https://github.com/YaelRmz)
* [Usuario2](https://github.com/n-DiegoTF)
* [Usuario3](https://github.com/marioquintalcob)
* [Usuario4](https://github.com/ErickDaniel04)
* [Usuario5](https://github.com/YaelRmz)

### Sesión 1
***
**Almacenando datos en las diferentes tipos de variables** <br>
Para nuestro proyecto haremos uso de los distintos tipos de variables con las que cuenta Kotlin, por ejemplo,
para los valores númericos usaremos variables que soporten dichos valores, por ejemplo, variables de tipo Int, Double, Float, etc.
Y para cadenas de texto y caracteres utilizaremos variables de tipo String o Char.

Ejemplos de variables que usaremos para nuestro proyecto

```kotlin
val user: String = jmcpheat0
val IdCliente: Int = 1
val name: String = "Jeannette McPheat"
val balance: Int = 348655
val password: String = "acUMQeLfEGek"
```

Ejemplos de operaciones que realizaremos con nuestras variables
```kotlin
// Incrementar saldo
saldoTotal += deposito
println("El saldo actualizado es: $ $saldoTotal")
// Decrementar saldo
saldoTotal -= deposito
println("El saldo actualizado es: $ $saldoTotal")
```

### Sesión 2
***
**Funciones, Condicionales y Colecciones**

**Funciones**

De las funciones y condicionales aprendidas en la sesion 2, aplicamos a nuestro proyecto aquellas que creemos se acoplan
mejor a las problematicas de nuestro proyecto.

Por ejemplo, en la siguiente funcion desplegamos el menú principal y se solicita al usuario ingresar la opción que desea
realizar, así como también agregamos una condición when para validar la petición del usuario y se realicen las funciones
de la opción que eligió.
```kotlin
fun initHome() {
        showHeader()
        println(
            "Bienvenido a tu cajero. Selecciona una opción:\n" +
                    "1 - Crear usuario.\n" +
                    "2 - Iniciar sesión."
        )
        val optionSelected = readlnOrNull()
        when (optionSelected?.toInt()) {
            1 -> createUser()
            2 -> doLogin()
        }
    }
```

Una vez que el usuario ingresa a su cuenta, hacemos uso de la función showUserOperations para mostrar un menú con las 
diferentes operaciones que puede realizar.
```kotlin
private fun showUserOperations() {
    println(
        "BANCO NACIONAL BEDU S.A. de C.V." +
                "Operaciones disponibles:\n" +
                "1 - Deposito.\n" +
                "2 - Retiro.\n" +
                "3 - Transferencia.\n" +
                "4 - Cerrar sesión."
    )
    val optionSelected = readlnOrNull()
    when (optionSelected?.toInt()) {
        1 -> doDeposit()
        2 -> cash_dispensar()
        3 -> doTransfer()
        4 -> doLogout()
    }
}
```
**Condicionales**

Para las funcion de depósito hacemos uso de la condicional *if-else* para validar que el usuario y la cantidad a ingresar
no sean nulos y poder realizar la operación de depósito al usuario y cantidad ingresados.
```kotlin
private fun doDeposit() {
        showHeader()
        println("Ingrese la cantidad a depositar: ")
        val money = readlnOrNull()
        if (mUsers[mCurrentUser.user] != null && money != null) {
            mUsers[mCurrentUser.user]?.balance = mUsers[mCurrentUser.user]?.balance?.plus(money.toInt())!!
            println("Operacion realizada. Saldo actual: ${mCurrentUser.balance}")
        } else {

            println("Hubo un problema al realizar la operacion")
        }
        showUserOperations()
    }
```

Para la función de transferencia usamos la condicional while para validar que la cantidad ingresada no sea nula, lo mismo
para validar el usuario que va a recibir la transferencia
```kotlin
private fun doTransfer() {
        println("Ingrese la cantidad a transferir: ")
        var moneyToTransfer = readlnOrNull()?.toDouble()
        while (moneyToTransfer == null) {
            println("Ingrese un cantidad valida: ")
            moneyToTransfer = readlnOrNull()?.toDouble()
        }
        println("Ingrese el usuario a hacer la transferencia: ")
        var userToTransfer = readlnOrNull()
        while (userToTransfer == null || !mUsers.containsKey(userToTransfer)) {
            println("Usuario invalido o no existe. Por favor ingrese un usuario valido")
            println("Ingrese el usuario a hacer la transferencia: ")
            userToTransfer = readlnOrNull()
        }
        processTransfer(userToTransfer, mCurrentUser.user, moneyToTransfer!!)
        println("Transferencia realizada con exito.")
        showUserOperations()
    }
```



```kotlin

```
