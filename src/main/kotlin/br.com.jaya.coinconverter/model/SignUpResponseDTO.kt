package br.com.jaya.coinconverter.model

import br.com.jaya.coinconverter.repository.model.Users

data class SignUpResponseDTO(
    val user: Users,
    val token: String
) {}
