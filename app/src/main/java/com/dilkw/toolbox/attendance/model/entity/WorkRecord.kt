package com.dilkw.toolbox.attendance.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * @ClassName WorkRecord
 * @Author name
 * @Date 2024/4/5
 * @Description
 */
@Entity(tableName = "work_record")
data class WorkRecord(
    // 主键 id
    @PrimaryKey val id: Int,
    // 类别 id
    @ColumnInfo(name = "cid") val cid: Int,
    // 时间
    @ColumnInfo(name = "date") val date: Date,
    // 创建时间
    @ColumnInfo(name = "create_date") val createDate: Date,
    // 修改时间
    @ColumnInfo(name = "update_date") val updateDate: Date,
)
