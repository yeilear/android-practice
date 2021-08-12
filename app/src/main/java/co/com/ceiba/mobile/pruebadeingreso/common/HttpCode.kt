package co.com.ceiba.mobile.pruebadeingreso.common

enum class HttpCode (val code: Int) {
    TIME_OUT(0),
    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401)
}