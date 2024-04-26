package com.dilkw.toolbox.attendance.ui.work_record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilkw.toolbox.R

/**
 * @ClassName WorkRecordPage
 * @Author name
 * @Date 2024/4/6
 * @Description
 */

// 出勤信息统计页面
private val mClickTimes: MutableState<Int> =  mutableIntStateOf(0)
@Composable
fun PageAttendanceStatistics() {
    val dataList = arrayListOf<Int>() // 数据源
    for (index in 0 .. 30) {
        dataList.add(index)
    }
    LazyColumn(
        Modifier.fillMaxSize(), // 设置列表填满父控件
        contentPadding = PaddingValues(6.dp), // 设置列表中内容与列表的间隔
        verticalArrangement = Arrangement.spacedBy(8.dp),   // 设置列表项间的垂直间隔
        horizontalAlignment = Alignment.CenterHorizontally) //列表项水平居中
    {
        items(dataList) { data ->
            WorkDayView(data)

        }
    }
}

@Composable
private fun WorkDayView(data: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(10)
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2F),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp, 32.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_workday),
                    contentDescription = stringResource(id = R.string.ic_work_day_content_description)
                )
                Text(
                    text = "碾米$data",
                    modifier = Modifier,
                    fontSize = 12.sp
                )
                Text(
                    text = "2024年4月10日",
                    modifier = Modifier.padding(top = 4.dp),
                    fontSize = 8.sp
                )
            }

            Divider(
                modifier = Modifier
                    .width(0.5.dp)
                    .height(60.dp)
                    .weight(0.001F),
                color = Color.Black
            )
            // modifier = Modifier.border(BorderStroke(1.dp, Color.Black)),
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
                    .weight(0.8F),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val array = arrayOf("张三七", "李四", "王五", "赵六", "李四", "王五", "赵六", "李四", "王五", "赵六")
                items(array.size) {index ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "员工$index",
                            modifier = Modifier,
                            fontSize = 16.sp
                        )
                        Divider(
                            modifier = Modifier
                                .width(35.dp)
                                .padding(top = 8.dp),
                            thickness = 0.2.dp,
                            color = Color.Black
                        )
                        Text(
                            text = array[index],
                            modifier = Modifier.padding(top = 4.dp),
                            //fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}