import atm.usecases.CreateUser

class Home {
    fun initHome() {
        println("Bienvenido a tu cajero. Selecciona una opción:\n" +
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

    }
}