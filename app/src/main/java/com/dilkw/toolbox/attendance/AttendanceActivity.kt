package com.dilkw.toolbox.attendance

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.dilkw.toolbox.R
import com.dilkw.toolbox.attendance.ui.BottomNavigationView
import com.dilkw.toolbox.attendance.ui.TopBar
import com.dilkw.toolbox.attendance.ui.work_record.PageAttendanceStatistics
import com.dilkw.toolbox.attendance.ui.employee_list.PageStaffList
import com.dilkw.toolbox.ui.theme.ToolboxTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * 员工出勤簿
 */

@AndroidEntryPoint
class AttendanceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToolboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Preview
    @Composable
    fun MainView() {

        val pagerState = rememberPagerState(2) { 2 }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = { BottomNavigationView(pagerState = pagerState) }
        ){
            // ViewPager 类似于view体系中的ViewPager2
            HorizontalPager(state = pagerState,
                Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.attendance_page_list_background))
                    .padding(it)
            ) { page ->
                when (page) {
                    0 -> { PageStaffList() }
                    1 -> { PageAttendanceStatistics() }
                }
            }
        }
    }

}