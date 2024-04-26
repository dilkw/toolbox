package com.dilkw.toolbox.attendance.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * @ClassName EmployeeAttendance
 * @Author name
 * @Date 2024/4/5
 * @Description
 */
@Entity(tableName = "employee_attendance")
data class EmployeeAttendance(
    // 主键 id
    @PrimaryKey val id: Int,
    // 工作记录 id
    @ColumnInfo(name = "w_r_id") val workRecordId: Int,
    // 工作人员 id
    @ColumnInfo(name = "e_id") val employeeId: Int,
    // 创建时间
    @ColumnInfo(name = "create_date") val createDate: Date,
    // 修改时间
    @ColumnInfo(name = "update_date") val updateDate: Date,
)
