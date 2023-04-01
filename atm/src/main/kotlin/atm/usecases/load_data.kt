package atm.usecases

import atm.entities.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import core.ASSETS_DIRECTORY
import core.USERS_DB
import java.io.File

class LoadData {
    companion object {
        fun loadUsers(): List<User> {
            return try {
                val assetsPath = System.getProperty("user.dir") + ASSETS_DIRECTORY
                val lines = File("$assetsPath/$USERS_DB").readText()

                val gson = Gson()
                val listPersonType = object : TypeToken<List<User>>() {}.type

                gson.fromJson(lines, listPersonType)
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}
