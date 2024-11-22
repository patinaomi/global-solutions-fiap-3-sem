package br.com.fiap.challenge.models

data class UpdatePasswordRequest(
    val id: String,
    val novaSenha: String
)
