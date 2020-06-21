package br.com.bandtec.quatrominds.model

class Psicologo {

    var nome: String? = null
    var dataNasc: String? = null
    var cargo: String? = null
    var email: String? = null
    var senha: String? = null

    constructor() : super() {}

    constructor(nome: String?,dataNasc: String?,cargo: String?,email: String, senha: String) : super() {
        this.nome = nome
        this.dataNasc = dataNasc
        this.cargo = cargo
        this.email = email
        this.senha = senha
    }


}