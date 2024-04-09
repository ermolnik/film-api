package com.ermolnik.api.users.auth

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object Auth {
    private const val ALGORITHM  = "HmacSHA1"
    private lateinit var secretKey: String
    private lateinit var hashKey: ByteArray
    private lateinit var hmacKey: SecretKeySpec
    fun init(secretKey: String){
        Auth.secretKey = secretKey
        hashKey = hex(Auth.secretKey)
        hmacKey = SecretKeySpec(hashKey, ALGORITHM)
    }

    fun convertToHash(password: String): String {
        val hmac = Mac.getInstance(ALGORITHM)
        hmac.init(hmacKey)
        return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
    }
}