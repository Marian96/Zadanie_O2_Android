package marian.puskas.o2zadanie.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import marian.puskas.o2zadanie.R

@Composable
fun InputView(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    onValidationError: (TextValidationError?) -> Unit = {},
    optionalLabel: String? = null,
    placeholder: String = "",
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    validations: List<TextValidation> = emptyList()
) {
    var validationError by remember { mutableStateOf<TextValidationError?>(null) }

    LaunchedEffect(value, validations) {
        validationError = validations.firstOrNull { !it(value) }?.validationError
        onValidationError(if (value.isNotEmpty()) validationError else null)
    }

    val hasError = validationError != null && value.isNotEmpty()

    Column(verticalArrangement = Arrangement.spacedBy(Dimensions.xs)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimensions.xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = Typography.labelMedium, color = contentHigh)
            optionalLabel?.let { Text(text = it, style = Typography.labelMedium, color = if (hasError) contentDanger else contentLow) }
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, style = MaterialTheme.typography.bodyMedium) },
            isError = hasError,
            enabled = enabled,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            shape = RoundedCornerShape(Dimensions.inputRadius),
            modifier = modifier
        )
    }
}

@Composable
fun PasswordInput(
    password: String,
    onPasswordChange: (String) -> Unit,
    onValidationError: (TextValidationError?) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    optionalLabel: String? = null,
    ) {
    InputView(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChange,
        onValidationError = onValidationError,
        label = label,
        optionalLabel = optionalLabel,
        placeholder = stringResource(R.string.password_input_placeholder),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        validations = listOf(
            TextValidation.HasUpperCase,
            TextValidation.HasDigit,
            TextValidation.HasSpecialCharacter,
            TextValidation.MinEightCharacters
        )
    )
}






