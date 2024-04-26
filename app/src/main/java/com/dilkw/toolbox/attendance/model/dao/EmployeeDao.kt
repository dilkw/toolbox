package com.dilkw.toolbox.attendance.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilkw.toolbox.attendance.model.entity.Employee
import kotlinx.coroutines.flow.Flow

/*
    员工DAO
 */
@Dao
interface EmployeeDao {

    // 查询所有员工
    @Query("SELECT * FROM employee")
    fun getAll(): Flow<List<Employee>>

    // 根据uid查询员工
    @Query("SELECT * FROM employee WHERE id IN (:employeeIds)")
    fun loadAllByIds(employeeIds: IntArray): Flow<List<Employee>>

    // 根据字符串模糊查询与名字相似的员工
    @Query("SELECT * FROM employee WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String): Flow<Employee>

    // 新增员工
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg employees: Employee)

    // 删除员工
    @Delete
    suspend fun delete(employee: Employee)

}