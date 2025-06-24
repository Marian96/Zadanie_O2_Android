package marian.puskas.o2zadanie.ui.theme

import marian.puskas.o2zadanie.R

enum class TextValidationError {
    NO_UPPER_CASE,
    NO_DIGIT,
    NO_SPECIAL_CHARACTER,
    MIN_8_CHARACTERS
}

sealed class TextValidation(val validationError: TextValidationError, private val isValid: (String) -> Boolean) {
    data object HasUpperCase: TextValidation(TextValidationError.NO_UPPER_CASE, { text: String -> text.any { it.isUpperCase() }})
    data object HasDigit: TextValidation(TextValidationError.NO_DIGIT, { text: String -> text.any { it.isDigit() }})
    data object HasSpecialCharacter: TextValidation(TextValidationError.NO_SPECIAL_CHARACTER, { text: String -> text.any { "!@#\$%^&*()-_=+[]{}|;:'\",.<>?/\\`~".contains(it) }})
    data object MinEightCharacters: TextValidation(TextValidationError.MIN_8_CHARACTERS, { text: String -> text.length >= 8})
    operator fun invoke(text: String): Boolean = isValid(text)
}

fun TextValidationError.toTextRes(): Int {
    return when (this) {
        TextValidationError.NO_UPPER_CASE -> R.string.validation_error_no_upper_case_letter
        TextValidationError.NO_DIGIT -> R.string.validation_error_no_digit
        TextValidationError.NO_SPECIAL_CHARACTER -> R.string.validation_error_no_special_character
        TextValidationError.MIN_8_CHARACTERS -> R.string.validation_error_min_8_chars
    }
}