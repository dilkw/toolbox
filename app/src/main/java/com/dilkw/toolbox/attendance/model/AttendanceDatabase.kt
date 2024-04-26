package com.dilkw.toolbox.attendance.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilkw.toolbox.attendance.model.dao.EmployeeDao
import com.dilkw.toolbox.attendance.model.entity.Employee
import com.dilkw.toolbox.attendance.model.entity.EmployeeAttendance
import com.dilkw.toolbox.attendance.model.entity.WorkCategory
import com.dilkw.toolbox.attendance.model.entity.WorkRecord

@Database(
    entities = [
        Employee::class,
        WorkCategory::class,
        WorkRecord::class,
        EmployeeAttendance::class],
    version = 1)
abstract class AttendanceDatabase: RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}