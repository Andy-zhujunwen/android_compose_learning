package com.ugc.andorid.mycompose.screen.RecyclerViewDemoScreen

import android.media.Image
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ugc.andorid.mycompose.R

@Composable
fun RecyclerViewDemoScreenView() {
    val colorList = mutableListOf<ColorItem>(
        ColorItem(Color.Blue),
        ColorItem(Color.Red),
        ColorItem(Color.Gray),
        ColorItem(Color.Blue),
        ColorItem(Color.Red),
        ColorItem(Color.Gray),
        ColorItem(Color.Blue),
        ColorItem(Color.Red),
        ColorItem(Color.Gray),
    )

    val imageList = mutableListOf<ImageItem>(
        ImageItem(R.drawable.image1),
        ImageItem(R.drawable.image2),
        ImageItem(R.drawable.image3),
        ImageItem(R.drawable.image1),
        ImageItem(R.drawable.image2),
        ImageItem(R.drawable.image3),
    )

    val navControlloer = rememberNavController()
    NavHost(navController = navControlloer,startDestination = "RecyclerViewDemoScreenView") {
        composable("RecyclerViewDemoScreenView") { Textt() }
    }
    val buttonList = mutableListOf<ButtonItem>(
        ButtonItem("Recycler Demo1"){
            navControlloer.navigate("Demo1")
        }
    )

    Row {
        BoxFeedView(colorList = colorList)
        ImageFeedView(imageList = imageList)
        ButtonFeedView(buttonList = buttonList)
    }

}

@Composable
fun BoxFeedView(colorList: List<ColorItem>) {
    LazyColumn {
        item {
            Box(modifier = Modifier
                .size(100.dp, 20.dp)
                .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Color",color = Color.White)
            }
        }

        items(colorList) {
            Box(modifier = Modifier
                .background(it.color)
                .size(100.dp)) {
                Text(text = it.color.toString())
            }
        }

        item {
            Box(modifier = Modifier
                .size(100.dp, 150.dp)
                .background(Color.Black))
        }

    }
}

@Composable
fun ImageFeedView(imageList: List<ImageItem>) {
    LazyColumn {
        item {
            Box(modifier = Modifier
                .size(100.dp, 20.dp)
                .background(Color.Black),
                contentAlignment = Alignment.Center) {
                Text(text = "Image",color = Color.White)
            }
        }
        items(imageList) {
            Image(painter = painterResource(id = it.imageRes),
                contentDescription = "this is a image",
                modifier = Modifier.size(100.dp,150.dp),
            contentScale = ContentScale.FillHeight,)
        }
    }
}

@Composable
fun ButtonFeedView(buttonList: List<ButtonItem>) {
    val mInteractionSource = remember { // 创建交互源
        MutableInteractionSource()
    }
    LazyColumn {
        item {
            Box(modifier = Modifier
                .size(100.dp, 20.dp)
                .background(Color.Black),
                contentAlignment = Alignment.Center) {
                Text(text = "Image",color = Color.White)
            }
        }

        items(buttonList) {
            Button(onClick = it.onClick,modifier = Modifier.size(width = 100.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray,
                    contentColor = Color.White
            )) {
                Text(text = it.buttonTitle,
                    fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun Textt(){
    androidx.compose.material.Text(text = "123")
}

data class ColorItem(val color: Color)
data class ImageItem(val imageRes: Int)
data class ButtonItem(val buttonTitle: String, val onClick: () -> Unit)