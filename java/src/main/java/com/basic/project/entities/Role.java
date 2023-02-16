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
 * @TableName role
 */
@TableName(value ="role")
@Data
public class Role implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    @Schema(description = "角色编号")
    private String id;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色描述")
    private String roleDescription;
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Serial
    private static final long serialVersionUID = 1L;
}