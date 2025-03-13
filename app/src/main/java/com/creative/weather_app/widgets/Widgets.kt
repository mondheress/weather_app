package com.creative.weather_app.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.creative.weather_app.R
import com.creative.weather_app.model.weatherResponse
import com.creative.weather_app.utils.DateUtils.Companion.convertTimeStampToDate
import com.creative.weather_app.utils.DateUtils.Companion.formatDate

    @Composable
    fun WeatherDetailsItem(da: weatherResponse.Item0) {
        val imageUrl = "https://openweathermap.org/img/wn/${da.weather[0].icon}.png"
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            shape = CircleShape.copy(
                topEnd = CornerSize(15.dp),
                bottomEnd = CornerSize(15.dp),
                bottomStart = CornerSize(15.dp),
                topStart = CornerSize(15.dp)
            ),
            color = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = formatDate(da.dt).split(",")[0].substring(0, 3),
                    modifier = Modifier.padding(start = 4.dp)
                )
                WeatherStateImage(imageUrl)
                Surface(
                    modifier = Modifier.padding(3.dp),
                    shape = CircleShape,
                    color = Color(0xFFD2D7F3)
                ) {
                    Text(da.weather[0].description, style = MaterialTheme.typography.bodySmall)


                }
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold

                        )
                    )
                    {
                        append(da.temp.max.toString()+ "")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.LightGray
                        )
                    ){
                        append((da.temp.min.toString())+ "")

                    }


                })

            }

        }

    }


    @Composable
    fun WeatherSecondRow(list: List<weatherResponse.Item0>) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row(modifier = Modifier.padding(4.dp))
            {
                Icon(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = "humidity icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "${convertTimeStampToDate(list[0].sunrise.toLong())} psi",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(5.dp, 1.dp, 0.dp, 0.dp)
                )
            }

            Row(modifier = Modifier.padding(4.dp))
            {
                Text(
                    text = "${list[0].pressure} psi",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(0.dp, 1.dp, 5.dp, 0.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.pressure),
                    contentDescription = "pressure icon",
                    modifier = Modifier.size(20.dp)
                )

            }
        }
    }

    @Composable
    fun WeatherPressureRow(list: List<weatherResponse.Item0>) {

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Row(modifier = Modifier.padding(4.dp))
            {
                Icon(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = "humidity icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "${list[0].humidity} psi",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(5.dp, 1.dp, 0.dp, 0.dp)
                )
            }

            Row(modifier = Modifier.padding(4.dp))
            {
                Icon(
                    painter = painterResource(R.drawable.pressure),
                    contentDescription = "pressure icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "${list[0].pressure} psi",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(5.dp, 1.dp, 0.dp, 0.dp)
                )
            }

            Row(modifier = Modifier.padding(4.dp))
            {
                Icon(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = "rain icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "${list[0].humidity} psi",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(5.dp, 1.dp, 0.dp, 0.dp)
                )
            }
        }
    }

    @Composable
    fun WeatherStateImage(imageUrl: String) {


        AsyncImage(
            model = imageUrl,
            contentDescription = "Icone Image2",
            modifier = Modifier.size(50.dp)
        )
    }