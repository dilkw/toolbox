package com.dilkw.toolbox.attendance.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilkw.toolbox.R
import com.dilkw.toolbox.accountbook.dao.AccountBookScreen
import kotlinx.coroutines.launch

@Preview
@Composable
fun DilkwSearchViewCompose(startIco: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_search), @SuppressLint(
    "ModifierParameter"
) modifier: Modifier = Modifier) {
        var text by remember { mutableStateOf("") }
        BasicTextField(
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Start),
            onValueChange = { text = it},
            value = text,
            singleLine = true,
            modifier = Modifier
                .then(modifier)
                .size(200.dp, 40.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(50))
                .background(Color.White, RoundedCornerShape(50))
                .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp),
            decorationBox = { innerTextField ->
                Surface(shape = RoundedCornerShape(50)) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp)
                            .fillMaxSize()
                    ) {
                        // 搜索框图标
                        Icon(
                            imageVector = startIco,
                            contentDescription = stringResource(R.string.ic_search_content_description),
                            modifier = Modifier.size(24.dp)
                        )
                        Box(
                            modifier = Modifier
                                .weight(2F)
                                .fillMaxSize()
                                .padding(start = 4.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (text.isEmpty()) {
                                // 搜索框内容输入提示文本
                                Text(
                                    modifier = Modifier
                                        .wrapContentSize(),
                                    text = stringResource(R.string.search_content_label),
                                    color = Color(0x88000000),
                                )
                            }
                            // 内容部分UI
                            innerTextField()
                        }
                        if (text.isNotEmpty()) {
                            // 当搜索框内容不为空时，显示文本清空按钮
                            Icon(
                                modifier = Modifier
                                    .weight(0.1F)
                                    .clickable {
                                        text = ""
                                    }
                                    .size(18.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                                contentDescription = stringResource(id = R.string.ic_search_clear_content),
                                tint = Color(0x88000000))
                        }
                    }
                }
            }
        )

}


// 顶部导航栏TopBar
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White)
            .padding(start = 8.dp, 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_top_bar_menu),
            contentDescription = stringResource(id = R.string.ic_description_top_bar_menu)
        )
        DilkwSearchViewCompose(
            modifier = Modifier
                .size(200.dp, 30.dp)
                .padding(start = 16.dp)
        )
    }
}

// 底部导航栏
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavigationView(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    BottomNavigation(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 6.dp),
        backgroundColor = colorResource(id = R.color.attendance_bottom_navigation_view_background)
    ) {
        val items = listOf(AccountBookScreen.Statistics, AccountBookScreen.AccountList)
        for (screenIndex in items.indices){
            BottomNavigationItem(
                icon = { androidx.compose.material.Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { androidx.compose.material.Text(stringResource(items[screenIndex].resourceId)) },
                selected = pagerState.currentPage == screenIndex,
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Black,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(page = screenIndex, animationSpec = spring(stiffness = 100f))
                    }
                })
        }
    }
}