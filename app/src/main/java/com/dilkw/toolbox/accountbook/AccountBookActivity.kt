package com.dilkw.toolbox.accountbook

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.dilkw.toolbox.R
import com.dilkw.toolbox.accountbook.dao.AccountBookScreen
import com.dilkw.toolbox.custom_views.DraggableFloatActionButton
import com.dilkw.toolbox.ui.theme.ToolboxTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class AccountBookActivity : ComponentActivity() {
    // 新增账单记录对话框是否显示的标记
    private var addRecodeDialog: MutableState<Boolean> = mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainView()
                    AddRecodeDialog(isVisibility = addRecodeDialog)
                }
            }
        }

        //dilkwFloatButton()
    }

    // 获取composeView，往其中添加浮动布局（如FloatActionButton）
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    @OptIn(ExperimentalFoundationApi::class)
    private fun dilkwFloatButton() {
        val customComposeView = ComposeView(this)

        customComposeView.setContent {
            var offsetX by remember { mutableFloatStateOf(customComposeView.x) }
            var offsetY by remember { mutableFloatStateOf(customComposeView.y) }
            customComposeView.x = offsetX
            customComposeView.y = offsetY
            val viewTreeObserver = customComposeView.viewTreeObserver
            viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    Log.d("TAG", "dilkwFloatButton: ${customComposeView.x}")
                    offsetX = customComposeView.x
                    offsetY = customComposeView.y
                    customComposeView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }

            })
            FloatingActionButton(onClick = { /*TODO*/ },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .draggable2D(
                        state = rememberDraggable2DState {
                            offsetX += it.x
                            offsetY += it.y
                        }
                    )
            ) {
                Text(
                    text = "添加",
                    fontSize = 15.sp,
                    modifier = Modifier
                )
            }
        }
        val viewGroup = window.decorView as ViewGroup
        val frameLayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        frameLayoutParams.gravity = Gravity.CENTER_VERTICAL
        frameLayoutParams.marginEnd = 8
        customComposeView.layoutParams = frameLayoutParams
        viewGroup.addView(customComposeView)
    }

    //
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun MainView() {

        // HorizontalPager的状态变量
        val pagerState = rememberPagerState { 2 }
        val scope = rememberCoroutineScope()

        var offsetX by remember { mutableFloatStateOf(0f) }
        var offsetY by remember { mutableFloatStateOf(0f) }

        Scaffold(
            // 悬浮按钮，作用于新增账单记录
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        addRecodeDialog.value = true
                    },
                    modifier = Modifier
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .size(60.dp, 60.dp)
                        .draggable2D(
                            state = rememberDraggable2DState {
                                offsetX += it.x
                                offsetY += it.y
                            }
                        )
                ){
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            // 替补导航栏
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier,
                    backgroundColor = Color.Transparent
                ) {
                    val items = listOf(AccountBookScreen.Statistics, AccountBookScreen.AccountList)
                    for (screenIndex in items.indices){
                        BottomNavigationItem(
                            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                            label = { Text(stringResource(items[screenIndex].resourceId)) },
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
        ) {
            // ViewPager 类似于view体系中的ViewPager2
            HorizontalPager(state = pagerState,
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) { page ->
                when (page) {
                    0 -> { PageAccountList() }
                    1 -> { PageStatistics() }
                }
            }
        }
    }


    // 新增账单对话框
    @Composable
    fun AddRecodeDialog(isVisibility: MutableState<Boolean>) {
        if (!isVisibility.value) return
        Dialog(onDismissRequest = { addRecodeDialog.value = false }) {
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                ,shape = RoundedCornerShape(8.dp)
                ,backgroundColor = Color.White
            ) {
                Text(modifier = Modifier
                    .wrapContentSize(Alignment.TopCenter),
                    text = "I'm a Dialog")
            }

        }
    }

    @Composable
    fun PageAccountList() {
        val dataList = arrayListOf<Int>() // 数据源
        for (index in 0 .. 30) {
            dataList.add(index)
        }
        LazyColumn(
            Modifier.fillMaxSize(), // 设置列表填满父控件
            contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 8.dp), // 设置列表中内容与列表的间隔
            verticalArrangement = Arrangement.spacedBy(8.dp),   // 设置列表项间的垂直间隔
            horizontalAlignment = Alignment.CenterHorizontally) //列表项水平居中
        {
            items(dataList) { data ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    //backgroundColor = Color.Black,
                    shape = RoundedCornerShape(4.dp)) {
                    Text("item:$data",
                        Modifier
                            .padding(8.dp)
                            .wrapContentSize(Alignment.Center)) // Item布局
                }

            }
        }
    }

    private val mClickTimes: MutableState<Int> =  mutableIntStateOf(0)
    @Composable
    fun PageStatistics() {
        Button(onClick = { mClickTimes.value++ }) {
            Text(text = "num ${mClickTimes.value}")
        }
    }
}