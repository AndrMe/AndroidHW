package com.example.dz


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme



class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme()) {
                    darkColorScheme()
                } else {
                    lightColorScheme(
                        background = colorResource( R.color.backGround),
                        )
                }
            ) {
                AppScreen()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreen()
{
    val list = rememberSaveable { mutableStateListOf<Int>() }
    val cellCount: Int = getCellCount()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
    ){ padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Bottom
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(cellCount),
                modifier = Modifier
                    .weight(1f)
            ) {
                items(list, key = {it}) { it ->
                    MyCell(it.toString(), colorResource(if (it % 2 ==0) R.color.redSquare else R.color.blueSquare) )
                }
            }
            FloatingActionButton(
                onClick = {
                    list.add(element = list.size)
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 32.dp, end = 32.dp, top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_add_24),
                    contentDescription = stringResource(R.string.add_button_content_description),
                )
            }
        }
    }
}


@Composable
fun getCellCount():Int
{
    val portraitC = integerResource(R.integer.portrait_columns)
    val landscapeC = integerResource(R.integer.landscape_columns)
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) portraitC else landscapeC
}

@Composable
fun MyCell(it: String,color: Color)
{
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = it,
            textAlign = TextAlign.Center,
        )
    }

}
