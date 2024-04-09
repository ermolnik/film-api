package com.ermolnik.api.users

object UserApiConst {
    private const val USERS_API = "/users"
    const val USER_SIGNIN_API = "$USERS_API/signin"
    const val USER_SIGNUP_API = "$USERS_API/signup"

    const val USER_NAME_MISSING_ERROR = "Missing username field"
    const val PASSWORD_MISSING_ERROR = "Missing password field"
    const val INCORRECT_INPUT_ERROR = "Username or Password is incorrect"
    const val USER_REGISTERED_ERROR = "This user has already been registered"
    const val FAIL_REGISTERED_ERROR = "Failed to register user"
}