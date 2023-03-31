package atm.usecases

import atm.entities.User

class LoginUser(
    private val userName: String,
    private val password: String
) {
    fun execute(users: MutableList<User>): User? {
        val hashPassword = generateHashPassword(password)
        return users.find { it.user == userName && it.password == hashPassword }
    }

    private fun generateHashPassword(password: String): String {
        return password.hashCode().toString()
    }
}