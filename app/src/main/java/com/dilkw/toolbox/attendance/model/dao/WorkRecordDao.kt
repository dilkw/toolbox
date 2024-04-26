package com.dilkw.toolbox.attendance.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dilkw.toolbox.attendance.model.entity.Employee
import com.dilkw.toolbox.attendance.model.entity.WorkRecord

/*
    WorkRecord DAO
 */
@Dao
interface WorkRecordDao {

    // 查询所有工作记录
    @Query("SELECT * FROM work_record")
    fun getAll(): List<WorkRecord>

    // 根据uid查询工作记录
    @Query("SELECT * FROM work_record WHERE id IN (:workRecordIds)")
    fun loadAllByIds(workRecordIds: IntArray): List<WorkRecord>

    // 新增工作记录
    @Insert
    fun insertAll(vararg workRecords: WorkRecord)

    // 删除工作记录
    @Delete
    fun delete(workRecord: WorkRecord)

}