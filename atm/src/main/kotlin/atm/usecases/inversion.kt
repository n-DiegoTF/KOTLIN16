
// FUNCIONES LITERALES //
 fun main(){
    // FUNCION INVERSION ES UNA FUNCION LAMBDA//
    val inversion: (Int,Int) -> String = { monto: Int, tasa: Int ->
        val rendimiento = (monto*tasa)/100


        when {
            (rendimiento*100)/monto == 8 -> "Tu rendimiento al final del plazo sera de 1 MES: $rendimiento"
            (rendimiento*100)/monto == 9 -> "Tu rendimiento al final del plazo sera de 3 MESES: $rendimiento"
            (rendimiento*100)/monto == 10 -> "Tu rendimiento al final del plazo sera de 6 MESES: $rendimiento"
            else -> "Gracias por invertir"
        }
    }
    println("Bienvenida al sistema de inversion de BANCO NACIONAL BEDU S.A. de C.V.\n" +
            "Elige el monto que deseas invertir")
    val monto = readlnOrNull()
    println("Elige la opción del plazo a meses a que deseas invertir\n" +
            "1) Un mes (tasa de 8%)\n" +
            "2) Tres meses (tasa de 9%)\n" +
            "3) Seis meses (tasa de 10%)\n")
    val option = readlnOrNull()
    var tasa=0
    when (option?.toInt()) {
        1 -> tasa = 8
        2 -> tasa = 9
        3 -> tasa = 9
    }
    /* AQUI ESTA EL ERROR NO SÉ COMO RESOLVER CONVERTIR MONTO DE STRING A INT PARA QUE SEA ACEPTADO
    EN EL PRINTLN DE ABAJO EN INVERSION */
    /*toInteger(monto)*/
    println(monto)
    println(tasa)
    println(inversion(1000,tasa))


}

// NULL EXCEPTION //
fun toInteger(monto: String) {
    try {
        val value = monto.toInt()
        println("El valor a invertir es: $value")
    } catch (ex: NumberFormatException) {
        println("El valor dado es no válido")
    }
}


