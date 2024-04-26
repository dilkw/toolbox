package com.dilkw.toolbox.attendance.ui.employee_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilkw.toolbox.R

/**
 * @ClassName WorkerListPage
 * @Author name
 * @Date 2024/4/6
 * @Description
 */

// 员工列表
@Composable
fun PageStaffList() {
    val dataList = arrayListOf<Int>() // 数据源
    for (index in 0 .. 30) {
        dataList.add(index)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(), // 设置列表填满父控件
        contentPadding = PaddingValues(8.dp), // 设置列表中内容与列表的间隔
        verticalArrangement = Arrangement.spacedBy(8.dp),   // 设置列表项间的垂直间隔
        horizontalArrangement = Arrangement.spacedBy(16.dp) //列表项水平居中
    ) {
        items(dataList.size) { data ->
            StaffItemView(data)
        }
    }
}

// 员工列表项UI
@Composable
private fun StaffItemView(data: Int) {
    Card(
        modifier = Modifier.height(80.dp),
        shape = RoundedCornerShape(10)
    ) {

        Row(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3F),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp, 32.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_worker),
                    contentDescription = stringResource(id = R.string.ic_worker_content_description)
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(0.7F),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "姓名：员工名字",
                    modifier = Modifier,
                    fontSize = 16.sp
                )
                Text(
                    text = "出勤天数:$data",
                    modifier = Modifier
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp
                )
            }
        }
    }
}