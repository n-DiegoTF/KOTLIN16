package atm.usecases

public fun paybills() {
    println("¿Que servicio deseas pagar?\n" +
            "1. Luz\n" +
            "2. Agua\n" +
            "3. Gas\n" +
            "4. Recarga\n")
        var billtopay = readlnOrNull()?.toDouble()
        while (billtopay == null) {
            println("Ingrese un cantidad valida: ")
            billtopay = readlnOrNull()?.toDouble()
        }
        for(i in 1..30){

            print("|")
        }
    print("100%")
        println("\n Su pago fué realizadao con exito.")
    }
