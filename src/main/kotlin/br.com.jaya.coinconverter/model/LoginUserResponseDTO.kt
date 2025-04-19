package br.com.jaya.coinconverter.model

data class LoginUserResponseDTO(
    val token: String,
    val expiresIn: Long
) {}
