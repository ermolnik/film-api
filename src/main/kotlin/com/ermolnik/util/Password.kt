package com.ermolnik.util

import at.favre.lib.crypto.bcrypt.BCrypt

// hashPassword returns the bcrypt hash of the password
fun hashPassword(password: String): String {
    return BCrypt.withDefaults().hashToString(12, password.toCharArray())
}

// checkPassword checks if the provided password is correct or not
fun checkPassword(password: String, hashedPassword: String): Boolean {
    val verification = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword.toCharArray())
    return verification.verified
}