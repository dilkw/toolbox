package com.dilkw.toolbox.attendance.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dilkw.toolbox.attendance.model.entity.Employee
import com.dilkw.toolbox.attendance.model.entity.WorkCategory

/*
    WorkCategory DAO
 */
@Dao
interface WorkCategoryDao {

    // 查询所有工作类别
    @Query("SELECT * FROM work_category")
    fun getAll(): List<WorkCategory>

    // 根据id查询类别
    @Query("SELECT * FROM work_category WHERE id IN (:workCategoryIds)")
    fun loadAllByIds(workCategoryIds: IntArray): List<WorkCategory>

    // 根据字符串模糊查询与名字相似的类别
    @Query("SELECT * FROM work_category WHERE name LIKE :first LIMIT 1")
    fun findByName(first: String): WorkCategory

    // 新增类别
    @Insert
    fun insertAll(vararg categorys: WorkCategory)

    // 删除类别
    @Delete
    fun delete(employee: WorkCategory)

}