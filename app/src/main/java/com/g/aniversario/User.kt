package com.g.aniversario

class User {
    var name = ""
    var daysUntilBirthday = 0
    var presente = ""
    //class variables
    var numberofusers = 0

    init {
        numberofusers++
    }

    constructor(nm: String, dub: Long, presente: String){
        this.name = nm
        this.daysUntilBirthday = dub.toInt()
        this.presente = presente
    }

    override fun toString(): String {
        return "Nome: ${this.name}\n" +
                "Dias para o aniversário: ${this.daysUntilBirthday}" +
                "\nPresente esperado: ${this.presente}"
    }
}