import atm.entities.User
import atm.usecases.CreateUser
import atm.usecases.LoginUser

class Home {
    private val users = mutableListOf<User>()
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
        val id = generateId()
        val hashPassword = generateHashPassword(password)
        users.add(User(id, name, userName, 0.0, hashPassword))
        println("Registro correcto, ya puedes iniciar sesión.\n" +
                "Por favor ingresa tus datos:\n")
        doLogin()
    }

    private fun generateHashPassword(password: String): String {
        return password.hashCode().toString()

    }

    private fun generateId(): Int {
        return -1
    }

    fun doLogin() {
        print("Usuario: ")
        var userName = readlnOrNull()
        while (userName == null) {
            print("Usuario: ")
            userName = readlnOrNull()
        }
        print("Contraseña: ")
        var password = readlnOrNull()
        while (password == null) {
            print("Contraseña: ")
            password = readlnOrNull()
        }

        val loginUser = LoginUser(userName, password)
        val user = loginUser.execute(users)
        if (user != null) {
            println("Bienvenido, ${user.name}!")
            // TODO: Implement banking options for logged in user
        } else {
            println("Usuario o contraseña incorrectos.")
        }
    }
}