package atm.usecases

fun services(){
    println("¿Qué operación deseas realizar:\n" +
            "1. Retirar efectivo\n" +
            "2. Transferir\n" +
            "3. Pagar tus tarjetas\n" +
            "4. Pagar servicios\n")
    var option = option()
    when (option?.toInt()) {
        1 -> Transfer()
    }
}
fun Transfer(){
    var amount1: Int =0
    var balance:Int
    println(amount1)
    println("How much money you would like to transfer?")
    var amount = option()
    println("Elige tu cuenta de efectivo:\n"+
            "1. Nómina\n"+
            "2. Ahorros\n")
    var option1 = option()
    when(option1?.toInt()){
        1 -> println("Tu saldo ese insuficiente")
        2 -> "Tu transferencia fue completada"
    }
    println(amount)

}
private fun option(): String? {
    return readlnOrNull()
}