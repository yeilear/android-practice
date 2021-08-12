package co.com.ceiba.mobile.pruebadeingreso.users.domain.entity

enum class UserMessageResponse(val message: String){
    SYSTEM_ERROR("Ocurrió un error en el sistema, reintente"),
    NO_RESULTS("No se ha encontrado resultados")
}