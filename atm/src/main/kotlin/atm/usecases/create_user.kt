package atm.usecases

import atm.entities.User
import kotlin.random.Random

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

    private fun generateId(): Int {
        return Random.nextInt()
    }

    private fun generateHashPassword(password: String): String {
        return password.hashCode().toString()
    }

    private fun updateLocalDB() {

    }
}
