import atm.usecases.CreateUser
import atm.usecases.Transfer
import atm.usecases.services

class Home {
    fun initHome() {
        println("BANCO NACIONAL BEDU S.A. de C.V." +
                "Bienvenido a tu cajero. Selecciona una opción:\n" +
                "1 - Crear usuario.\n" +
                "2 - Iniciar sesión.")
        val optionSelected = readlnOrNull()
        when(optionSelected?.toInt()) {
            1 -> createUser()
            2 -> doLogin()
        }
    }

    fun createUser() {
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

        val newUser = CreateUser(name, userName, password)
        newUser.saveUser()
    }

    fun doLogin() {
    services()
        var option = readlnOrNull()
        when(option?.toInt()){
            1 -> println("Withdrawal()")
            2 -> {Transfer()}
            3-> println("PayMyCards)")
            4 -> println("PayBills()")
        }

    }

}

