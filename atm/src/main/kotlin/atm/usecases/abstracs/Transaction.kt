package atm.usecases.abstracs

import atm.entities.Transference
import atm.entities.User
import kotlin.random.Random

abstract class Transaction {
    abstract val mMoneyToTransfer: Double
    abstract val mUser: User

    abstract fun doTransfer()

    fun saveTransfer(transference: MutableMap<Int, Transference>) {
        val id = Random.nextInt()
        transference[id] = Transference(id, mUser.user, mMoneyToTransfer, getTransactionType())
        println(transference[id])
    }

    private fun getTransactionType(): String {
        return this.javaClass.name
    }
}
