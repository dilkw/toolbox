package com.dilkw.toolbox.attendance.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * @ClassName Employee
 * @property uid Int
 * @property name String?
 * @constructor
 * @author dilkw
 * @date 2024/02/29
 * @
 */
@Entity(tableName = "employee")
data class Employee(
    // 主键 id
    @PrimaryKey val id: Int,
    // 名称
    @ColumnInfo(name = "name") val name: String?,
    // 创建时间
    @ColumnInfo(name = "create_date") val createDate: Date,
    // 修改时间
    @ColumnInfo(name = "update_date") val updateDate: Date,
)
