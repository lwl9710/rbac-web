package com.basic.project.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @TableName permission
 */
@TableName(value ="permission")
@Data
public class Permission implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    @Schema(description = "权限编号")
    private String id;
    @Schema(description = "父级编号")
    private String parentId;
    @Schema(description = "权限类型 1.普通权限")
    private Integer type;
    @Schema(description = "权限url")
    private String url;
    @Schema(description = "权限描述")
    private String description;
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Serial
    private static final long serialVersionUID = 1L;
}