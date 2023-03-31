package atm.usecases

import atm.entities.User

class CreateUser(
    private val name: String,
    private val userName: String,
    private val password: String
)