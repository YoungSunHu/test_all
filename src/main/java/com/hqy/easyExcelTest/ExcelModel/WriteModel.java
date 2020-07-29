package com.hqy.easyExcelTest.ExcelModel;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteModel extends BaseRowModel {

    @ExcelProperty(value = "姓名" ,index = 0)
    String name;

    @ExcelProperty(value = "密码" ,index = 1)
    String password;

    @ExcelProperty(value = "年龄" ,index = 2)
    Integer age;
}
