package co.com.ceiba.mobile.pruebadeingreso.common.dialog

data class DataBuilderDialog (
        val title: String = "",
        val message: String = "",
        val namePositiveButton: String = "",
        val nameNegativeButton: String = "",
        val successAction: () -> Unit = {},
        val cancelAction: () -> Unit = {},
        val cancellable: Boolean = true
)


