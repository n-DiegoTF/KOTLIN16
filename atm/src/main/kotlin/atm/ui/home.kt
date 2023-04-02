import atm.entities.Transference
import atm.entities.User
import atm.usecases.CreateUser
import atm.usecases.TransactionReceived
import atm.usecases.TransactionSent
import atm.usecases.abstracs.Transaction
import atm.usecases.cash_dispensar

class Home {
    private lateinit var mUsers: MutableMap<String, User>
    private var mTransfers = mutableMapOf<Int, Transference>()
    private var mIsDBLoaded = false
    private lateinit var mCurrentUser: User

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

    private fun doLogin() {
        // TODO: Use correct user
        mCurrentUser = mUsers.get("jmcpheat0")!!
        showUserOperations()
    }

    private fun doLogout() {

    }

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

    private fun processTransfer(userToTransfer: String, userSent: String, moneyToTransfer: Double) {
        mUsers[userSent]?.balance = mUsers[userSent]?.balance?.minus(moneyToTransfer)!!
        mUsers[userToTransfer]?.balance = mUsers[userToTransfer]?.balance?.plus(moneyToTransfer)!!

        val transactionReceived: Transaction = TransactionReceived(moneyToTransfer, mUsers[userToTransfer]!!)
        val transactionSent: Transaction = TransactionSent(moneyToTransfer, mUsers[userSent]!!)
        transactionReceived.doTransfer()
        transactionSent.doTransfer()
        mUsers[userToTransfer]?.let { transactionReceived.saveTransfer(mTransfers) }
        mUsers[userSent]?.let { transactionSent.saveTransfer(mTransfers) }
    }

    fun setUsers(users: MutableMap<String, User>) {
        mUsers = users
    }

    fun setIsDBLoaded(isDBLoaded: Boolean) {
        mIsDBLoaded = isDBLoaded
    }

    private fun showHeader() {
        println("BANCO NACIONAL BEDU S.A. de C.V.")
    }
}
