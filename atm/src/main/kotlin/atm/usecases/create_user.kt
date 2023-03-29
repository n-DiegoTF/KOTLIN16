package atm.usecases

import atm.entities.User

class CreateUser(
    private val name: String,
    private val userName: String,
    private val password: String
) {
    fun saveUser() {
        val id = generateId()
        val hashPassword = generateHashPassword(password)
        val user = User(id, name, userName, 0.0, hashPassword)
        // TODO: Save persistent info
    }

    private fun generateId(): Int{
        // TODO: Generate an unique ID
        return -1
    }

    private fun generateHashPassword(password: String): String {
        return password.hashCode().toString()
    }

}