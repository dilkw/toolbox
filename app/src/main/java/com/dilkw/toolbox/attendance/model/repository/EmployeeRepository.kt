package com.dilkw.toolbox.attendance.model.repository;

import android.app.Application
import androidx.room.Room
import com.dilkw.toolbox.attendance.model.AttendanceDatabase
import com.dilkw.toolbox.attendance.model.dao.EmployeeDao
import com.dilkw.toolbox.attendance.model.entity.Employee
import kotlinx.coroutines.flow.Flow

/**
 * @ClassName WorkerRepository
 * @Author name
 * @Date 2024/4/6
 * @Description
 */
class EmployeeRepository (application: Application) {

    private lateinit var employeeDao: EmployeeDao

    init {
        employeeDao = Room
            .databaseBuilder(application.applicationContext, AttendanceDatabase::class.java, "employee")
            .build()
            .employeeDao()
    }

    // 获取所有员工
    fun getAllEmployees(): Flow<List<Employee>> {
        return employeeDao.getAll()
    }

    suspend fun addEmployees(vararg employees: Employee) {
        employeeDao.insertAll(*employees)
    }

}
