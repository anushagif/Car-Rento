package com.anusha.carrento.screens.carrentalscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anusha.carrento.commoncomponents.DatePickerField

@Composable
fun CarRentalScreen(modifier: Modifier = Modifier) {
        val context = LocalContext.current

        var pickupLocation by remember { mutableStateOf(TextFieldValue("")) }
        var dropOffLocation by remember { mutableStateOf(TextFieldValue("")) }
        var pickupDate by remember { mutableStateOf("") }
        var dropoffDate by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(24.dp),
                text="Rent a car",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = pickupLocation,
                onValueChange = { pickupLocation = it },
                label = { Text("Pickup Location *") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dropOffLocation,
                onValueChange = { dropOffLocation = it },
                label = { Text("Drop-off Location (Optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            DatePickerField(context, "Pickup Date *", pickupDate) { selectedDate ->
                pickupDate = selectedDate
            }

            Spacer(modifier = Modifier.height(8.dp))

            DatePickerField(context, "Drop-off Date *", dropoffDate) { selectedDate ->
                dropoffDate = selectedDate
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (pickupLocation.text.isBlank() || pickupDate.isBlank() || dropoffDate.isBlank()) {
                        Toast.makeText(context, "Please fill required fields", Toast.LENGTH_SHORT).show()
                    } else {
                        val url = generateKayakDeepLink(pickupLocation.text, dropOffLocation.text, pickupDate, dropoffDate)
                        openKayakSearch(context, url)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search on Kayak")
            }
        }

}

fun generateKayakDeepLink(pickup: String, dropoff: String, pickupDate: String, dropoffDate: String): String {
    val baseUrl = "https://www.kayak.com/in?a=awesomecars&url=/cars"
    val dropoffPart = if (dropoff.isNotBlank()) "/$dropoff" else ""
    return "$baseUrl/$pickup$dropoffPart/$pickupDate/$dropoffDate"
}

fun openKayakSearch(context: Context, url: String) {
    val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
    context.startActivity(intent)
}