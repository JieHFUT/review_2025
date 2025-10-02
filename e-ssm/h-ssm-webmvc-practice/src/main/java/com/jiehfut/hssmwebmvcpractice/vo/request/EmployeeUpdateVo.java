package com.jiehfut.hssmwebmvcpractice.vo.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Schema(description = "员工修改需要请求需要携带的数据")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateVo {


    @NotNull(message = "修改员工数据时，ID不能为空")
    @Schema(description = "员工ID")
    private Integer id;

    @Schema(description = "员工姓名")
    private String name;

    @Schema(description = "员工年龄")
    private Integer age;

    @Schema(description = "员工邮箱")
    private String email;

    @Schema(description = "员工性别")
    private String gender;

    @Schema(description = "员工地址")
    private String address;

    @Schema(description = "员工薪资")
    private BigDecimal salary;

}
