import atm.entities.User
import atm.usecases.CreateUser
import atm.usecases.services

class Home {
    private lateinit var mUsers: List<User>
    private var mIsDBLoaded = false

    fun initHome() {
        println(
            "BANCO NACIONAL BEDU S.A. de C.V." +
                    "Bienvenido a tu cajero. Selecciona una opción:\n" +
                    "1 - Crear usuario.\n" +
                    "2 - Iniciar sesión."
        )
        val optionSelected = readlnOrNull()
    }

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
        //        val newUser = CreateUser(name,userName, password)        newUser.saveUser()
    }

    fun doLogin() {
    services()
    }

    fun setUsers(users: List<User>) {
        mUsers = users
    }

    fun setIsDBLoaded(isDBLoaded: Boolean) {
        mIsDBLoaded = isDBLoaded
    }
}