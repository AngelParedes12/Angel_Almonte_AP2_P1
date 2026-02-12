package edu.ucne.angel_almonte_ap2_p1.domain.validation

data class ValidationResult(
    val isValid: Boolean,
    val error: String? = null
)

fun validateTexto(texto: String): ValidationResult {
    return when {
        texto.isBlank() -> ValidationResult(false, "Este campo no puede estar vacío")
        texto.length < 3 -> ValidationResult(false, "Debe tener al menos 3 caracteres")
        else -> ValidationResult(true)
    }
}

fun validatePuntuacion(puntuacion: String): ValidationResult {
    val num = puntuacion.toIntOrNull()
    return when {
        puntuacion.isBlank() -> ValidationResult(false, "La puntuación no puede estar vacía")
        num == null -> ValidationResult(false, "Debe ser un número entero")
        num !in 1..5 -> ValidationResult(false, "La puntuación debe ser entre 1 y 5")
        else -> ValidationResult(true)
    }
}