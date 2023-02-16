package com.basic.project.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class PageVO<T> {
    @Schema(description = "总数")
    private Integer total;
    @Schema(description = "列表")
    private List<T> list;
}
