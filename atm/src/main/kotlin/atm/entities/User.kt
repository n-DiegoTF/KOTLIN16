package atm.entities

data class User(
    val id: Int,
    val name: String,
    val user: String,
    val balance: Double,
    val password: String
)
