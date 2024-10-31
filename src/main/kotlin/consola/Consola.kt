package org.example.consola

class Consola() {
    fun imprimir(mensaje:String, salto:Boolean= true){
        if (salto){
            println(mensaje)
        }else{
            print(mensaje)
        }
    }

    fun input():String{
        return readln()
    }
}