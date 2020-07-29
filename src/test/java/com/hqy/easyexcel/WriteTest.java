package com.hqy.easyexcel;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class WriteTest {

    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = "E:\\EasyExcelTest.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data(1000000));

        //写法2
        fileName = "E:\\EasyExcelTest02.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();

        excelWriter.write(data(100), writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }


    @Data
    @AllArgsConstructor
    class DemoData {
        String name;
        String age;
        String address;
    }

    List<DemoData> data(int rows) {
        List<DemoData> datas = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            datas.add(new DemoData("姓名" + i, "年龄" + i, "地址" + i));
        }
        return datas;
    }
}
