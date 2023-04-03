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
* [Yael](https://github.com/YaelRmz)
* [Diego](https://github.com/n-DiegoTF)
* [Mario](https://github.com/marioquintalcob)
* [Erick](https://github.com/ErickDaniel04)

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
**Colección de datos**

Kotlin nos proporciona diferentes tipos de estructuras de datos para poder realizar dterminadas operaciones con los datos
que queramos almacenar. Algunas estructuras de datos que nos ofrece Kotlin son *List, Set, Map*.

Para nuestro proyecto requerimos el uso de datos persistentes para poder almacenar todas las operaciones realizadas durante
la ejecución del programa por lo que hicimos uso de un archivo externo para guardar los cambios realizados, así como tambien
se implementaron las estructuras de datos pertinentes para poder operar con los datos de nuestro archivo externo y de esta
manera no perder los cambios realizados.
```kotlin
class LoadData {
    companion object {
        fun loadUsers(): MutableMap<String, User> {
            return try {
                val assetsPath = System.getProperty("user.dir") + ASSETS_DIRECTORY
                val lines = File("$assetsPath/$USERS_DB").readText()

                val gson = Gson()
                val listPersonType = object : TypeToken<List<User>>() {}.type

                val usersList: List<User> = gson.fromJson(lines, listPersonType)
                usersList.associateBy({ it.user }, { it }).toMutableMap()
            } catch (e: Exception) {
                e.printStackTrace()
                mutableMapOf()
            }
        }
    }
}
```

### Sesión 3
***
**Programación Orientada a Objetos**

Para aplicar los conceptos de la POO definimos una clase para la creación de usuarios en donde a sus atributos se le
asignan modificadores privados para evitar que estos sean modificados desde otra clase.

```kotlin
class CreateUser(
    private var users: MutableMap<String, User>,
    private val name: String,
    private val userName: String,
    private val password: String
) {
    fun saveUser() {
        val id = generateId()
        val hashPassword = generateHashPassword(password)
        val user = User(id, name, userName, 0.0, hashPassword)
        users[userName] = user
    }
}
```
Estos parametros son modificados desde la clase home, pasamos los valores del nuevo usuario a traves del objeto newUser
a traves de la función *saveUser()*
```kotlin
class home{
    private fun createUser() {
        print("Nombre: ")
        var name = readlnOrNull()
        while (name == null) {
            print("Nombre: ")
            name = readlnOrNull()
        }
        print("Usuario: ")
        var userName = readlnOrNull()
        while (userName == null) {
            print("Nombre: ")
            userName = readlnOrNull()
        }
        print("Contraseña: ")
        var password = readlnOrNull()
        while (password == null) {
            print("Nombre: ")
            password = readlnOrNull()
        }

        val newUser = CreateUser(mUsers, name, userName, password)
        newUser.saveUser()
    }
}
```

### Sesión 4
***
**Clases Abstractas**

Las clases abstractas es una manera de generalizar nuestro código y poder reutilizarlo y sobreescribirlo segun sea necesario.
En nuestro proyecto creamos una clase abstracta para las transacciones, ya que se requiere para optimizar las clases para
recibir y enviar dinero.
```kotlin
abstract class Transaction {
    abstract val mMoneyToTransfer: Double
    abstract val mUser: User

    abstract fun doTransfer()

    fun saveTransfer(transference: MutableMap<Int, Transference>) {
        val id = Random.nextInt()
        transference[id] = Transference(id, mUser.user, mMoneyToTransfer, getTransactionType())
        println(transference[id])
    }

    private fun getTransactionType(): String {
        return this.javaClass.name
    }
}
```
En las siguientes clases podemos observar como cada clase implementa y sobreescribe las funciones de la clase abstracta
para realizar o modificar las operaciones de cada función.
```kotlin
class TransactionSent(
    override val mMoneyToTransfer: Double,
    override val mUser: User
) : Transaction() {
    override fun doTransfer() {
        println("Doing Transaction sent")
    }
}

class TransactionReceived(
    override val mMoneyToTransfer: Double,
    override val mUser: User
) : Transaction() {
    override fun doTransfer() {
        println("Doing Transaction received")
    }
}
```
**Data Class**

Los Data Class en Kotlin es una implementación más sencilla de las clases POJO en Java, ahorrandonos gran cantidad de código.

En nuestro proyecto decidimos implementar dos Data Class para agilizar la creación de usuarios y transferencias.
```kotlin
data class User(
    val id: Int,
    val name: String,
    val user: String,
    var balance: Double,
    val password: String
)

data class Transference(
    val id: Int,
    val userName: String,
    val amount: Double,
    val type: String,
)
```

### Sesión 5
**Programación Funcional**

La programación funcional nos permiten trabajar de forma más dinamica con las funciones dentro de nuestro código, por ejemplo,
las funciones lambda que nos permiten asignar funciones a variables o pasarlas como parametros de otra función.

Para nuestro proyecto se aplicaron funciones lambda para determinadas operaciones, sin embargo, estas funciones fueron
cambias o reemplazadas por funciones más eficientes o complejas.
```kotlin
//Función usada para calcular el saldo total después de un depósito
val saldoTotal = {deposito: Double -> saldoInicial + saldoDepositado}

//Función usada para calcular el saldo total después de un retiro
val saldoRestante: (Double) = {retiro: Double -> saldoInicial - saldoRetirado}

//Pasando funciones como parametro a funciones de orden superior
fun imprimirOperacion(saldoTotal: (Double, Double)-> saldoI, saldoD: Double){
    println("Saldo total actualizado: ${saldoTotal(saldoI, saldoD)}")
}

fun imprimirSaldoRestante(saldoRestante: (Double, Double)-> saldoI, saldoR: Double){
    println("Saldo total actualizado: ${saldoTotal(saldoI, saldoR)}")
}
```

### Sesión 7
***
**Manejo de Errores**

Para lograr que un programa sea fiable durante su ejecución existe la posibilidad de prevenirse de un cierre o
errores inesperados. Tal es el caso del manejo de Excepciones y Null Safety.

Para nuestro proyecto implementamos principalmente los operadores Null Safety, esto para evitar que el programa cierre
inesperadamente al no recibir un valor válido.

Por ejemplo en la función *doTransfer()* permitimos que el programa lea valores nulos para después canalizarlos con la condición
*while* en donde si el valor de la transferencia o el usuario ingresados no son compatibles se le notifica al usuario que ingrese
valores correctos
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

El manejo de excepciones decimos implementarlo en la carga de los datos de los usuarios, esto debido a que si no se logran
 cargar los datos no podremos ejecutar correctamente nuestro programa.
```kotlin
return try {
    val assetsPath = System.getProperty("user.dir") + ASSETS_DIRECTORY
    val lines = File("$assetsPath/$USERS_DB").readText()

    val gson = Gson()
    val listPersonType = object : TypeToken<List<User>>() {}.type

    val usersList: List<User> = gson.fromJson(lines, listPersonType)
    usersList.associateBy({ it.user }, { it }).toMutableMap()
} catch (e: Exception) {
    e.printStackTrace()
    mutableMapOf()
}
```

### Sesión 8
***
**Programación asíncrona**

La programación asíncrona tiene varios modelos para poder ejecutar varias funciones del programa según se requieren en determinado
tiempo. Para nuestro proyecto implementamos la función de Coroutines, ya que su principal caracteristica es la de suspender funciones
en cierto punto hasta que otro proceso termine y se reanude, así como también es el metodo más sencillo de implementar.

En nuestro proyecto implementamos una Coroutine en la función main, ya que antes de interactuar con el programa primero debemos
de cargar los datos de nuestros usuarios para poder realizar las operaciones del programa.
```kotlin
fun main(args: Array<String>) {
    val home = Home()
    CoroutineScope(Dispatchers.IO).launch {
        val users = LoadData.loadUsers()
        home.setUsers(users)
        home.setIsDBLoaded(users.isNotEmpty())
    }
    home.initHome()
}
```
