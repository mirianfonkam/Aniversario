package com.g.aniversario

class User {
    var name = ""
    var daysUntilBday = 0
    var presente = ""
    //class variables
    var numberofusers = 0

    init {
        numberofusers++
    }

    constructor(nm: String, dub: Long, presente: String){
        this.name = nm
        this.daysUntilBday = dub.toInt()
        this.presente = presente
    }

    override fun toString(): String {
        return "Nome: ${this.name}\n" +
                "Dias para o aniversário: ${this.daysUntilBday}" +
                "\nPresente esperado: ${this.presente}"
    }
}