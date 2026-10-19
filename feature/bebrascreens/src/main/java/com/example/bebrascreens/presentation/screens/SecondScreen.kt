package com.example.bebrascreens.presentation.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.bebrascreens.R
import com.example.bebrascreens.presentation.albums.AlbumsIntent
import com.example.bebrascreens.presentation.viewmodels.SecondViewModel

@Composable
fun SecondScreen(viewModel: SecondViewModel = hiltViewModel()){
    LaunchedEffect(Unit) {
        viewModel.onIntent(AlbumsIntent.LoadAlbums)
    }
    val myFontFamily = FontFamily(Font(R.font.osw))
    val uistate by viewModel.state.collectAsState()

    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(Color(0xFFFDFBF7))
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(Color(0xFFFDFBF7))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFFFDFBF7))
        )
        {
            Text(
                text = "BebraWidget",
                fontSize = 28.sp,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = myFontFamily
                ),
                modifier = Modifier.
                offset(5.dp, 0.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .background(Color(0xFFFDFBF7)),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFFFDFBF7)),
            contentAlignment = Alignment.CenterStart
        )
        {
            Text(
                text = "Selected Album: ${uistate.albumSelected?.name ?: "выберите альбом"}",
                fontSize = 20.sp,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = myFontFamily
                ),
                modifier = Modifier
                    .offset(5.dp, 0.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color(0xFFFDFBF7))
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFDFBF7)),
            contentPadding = PaddingValues(5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        )
        {
            items(uistate.albumUiModel)
            {albumUiModel ->
                Box(
                    modifier = Modifier.background(color = albumUiModel.backgroundColor)
                        .width(50.dp)
                        .border(1.dp, Color.Black)
                        .padding(5.dp)
                        .clickable(onClick = {
                            viewModel.onIntent(AlbumsIntent.SelectedAlbum(albumUiModel.album.id))
                        })
                )
                {
                    Column {
                        Text(
                            "Album name: ${albumUiModel.album.name} \nPhoto count: ${albumUiModel.album.photoCount}",
                            fontSize = 10.sp,
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = myFontFamily
                            )
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )
                        AsyncImage(
                            model = Uri.parse(albumUiModel.album.secondUri),
                            contentDescription = "альбом",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .offset(4.dp, 4.dp)
                                .border(1.dp, Color.Black),
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )
                    }
                }
            }
        }
    }
}