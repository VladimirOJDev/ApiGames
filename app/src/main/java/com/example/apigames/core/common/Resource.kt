package com.example.apigames.core.common


// representar el estado de una operación (carga, éxito, error).
// 'T' tipo de datos que la operación exitosa devolverá.

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    // estado de éxito.
    // Contiene los datos que se obtuvieron correctamente.
    class Success<T>(data: T) : Resource<T>(data)

    // estado de error.
    // Contiene un mensaje de error y opcionalmente datos si hay datos parciales.
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    // estado de carga.
    // Puede contener datos si se está actualizando contenido existente (ej. refrescar).
    class Loading<T>(data: T? = null) : Resource<T>(data)
}