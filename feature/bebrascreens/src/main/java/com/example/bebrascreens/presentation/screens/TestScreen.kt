package com.example.bebrascreens.presentation.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.bebrascreens.presentation.albums.AlbumsIntent
import com.example.bebrascreens.presentation.viewmodels.SecondViewModel
import org.w3c.dom.Text

@Composable
fun Test() {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(Color(0x52C5CDB1))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0x52616C47))
        )
        {
            Text(
                text = "BebraWidget",
                fontSize = 30.sp,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Cursive
                ),
                modifier = Modifier.
                offset(5.dp, 0.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0x52D5E1B8)),
            contentAlignment = Alignment.CenterStart
        )
        {
            Text(
                text = "Selected Album: ...",
                fontSize = 20.sp,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Monospace
                ),
                modifier = Modifier
                    .offset(5.dp, 0.dp)
            )
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x52D5E1B8)),
            contentPadding = PaddingValues(5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        )
        {
            items(6){_ ->
                Box(
                    modifier = Modifier.background(Color.Gray)
                        .size(50.dp, 180.dp)
                        .padding(5.dp),
                ){
                    Text(
                        "",
                        fontSize = 30.sp
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                    )
                    /*AsyncImage(
                        model = Uri.parse("123"),
                        contentDescription = "альбом",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(100.dp)
                    )*/
                }
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun Show(){
    Test()
}