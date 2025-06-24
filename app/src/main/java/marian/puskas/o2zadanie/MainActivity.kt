package marian.puskas.o2zadanie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import marian.puskas.o2zadanie.ui.theme.InputView
import marian.puskas.o2zadanie.ui.theme.O2ZadanieTheme
import marian.puskas.o2zadanie.ui.theme.PasswordInput
import marian.puskas.o2zadanie.ui.theme.toTextRes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            O2ZadanieTheme {
                val context = LocalContext.current

                Column(Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    val inputText = remember { mutableStateOf("") }
                    val password  = remember { mutableStateOf("") }
                    val passwordErrorLabel: MutableState<String?> = remember { mutableStateOf("") }

                    InputView(
                        modifier = Modifier.fillMaxWidth(),
                        value = inputText.value,
                        label = stringResource(R.string.text_input_label),
                        onValueChange = { inputText.value = it }
                    )

                    PasswordInput(
                        modifier = Modifier.fillMaxWidth(),
                        label    = stringResource(R.string.password_label),
                        optionalLabel = passwordErrorLabel.value,
                        password = password.value,
                        onPasswordChange = { password.value = it },
                        onValidationError = {
                            passwordErrorLabel.value = it?.let { context.getString(it.toTextRes()) }
                        }
                    )
                }
            }
        }
    }
}