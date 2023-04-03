import atm.usecases.LoadData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    val square: (Int) -> Int = { it * it }
    val home = Home()
    CoroutineScope(Dispatchers.IO).launch {
        val users = LoadData.loadUsers()
        home.setUsers(users)
        home.setIsDBLoaded(users.isNotEmpty())
    }
    home.initHome()
}
fun inversion(){

}
