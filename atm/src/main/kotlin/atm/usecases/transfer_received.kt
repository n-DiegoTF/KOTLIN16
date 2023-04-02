package atm.usecases

import atm.entities.User
import atm.usecases.abstracs.Transaction

class TransactionReceived(
    override val mMoneyToTransfer: Double,
    override val mUser: User
) : Transaction() {
    override fun doTransfer() {
        println("Doing Transaction received")
    }
}
