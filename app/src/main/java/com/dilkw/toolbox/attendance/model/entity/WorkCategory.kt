package com.dilkw.toolbox.attendance.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * @ClassName WorkCategory
 * @Author name
 * @Date 2024/4/5
 * @Description
 */
@Entity(tableName = "work_category")
data class WorkCategory(
    // 主键 id
    @PrimaryKey val id: Int,
    // 类别名称
    @ColumnInfo(name = "name") val name: String?,
    // 创建时间
    @ColumnInfo(name = "create_date") val createDate: Date,
    // 修改时间
    @ColumnInfo(name = "update_date") val updateDate: Date,
)
