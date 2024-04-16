package com.example.diceinput

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceinput.ui.theme.DiceInputTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceInputTheme{
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    Dice()
                }
            }
        }
    }
}

@Composable
fun DiceRoller(modifier: Modifier = Modifier) {
    var userInput by remember { mutableStateOf("") }
    var result by remember { mutableIntStateOf(1) }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current

    val imageResource = when(result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(16.dp))

        Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Dice Image"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
                onClick = {
                    val userNumber = userInput.toIntOrNull() ?: 0.0
                    result = (1..6).random()
                    message = if (userNumber == result) {
                        "Você venceu o jogo, o dado é $result."
                    } else {
                        "Você perdeu o jogo, o dado é $result."
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
        )
        {
            Text(stringResource(id = R.string.roll))
        }
        TextField(
                value = userInput,
                onValueChange = { userInput = it },
                label = { Text(stringResource(R.string.aposta)) },
                modifier = Modifier.padding(top = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun Dice() {
    DiceRoller()
}