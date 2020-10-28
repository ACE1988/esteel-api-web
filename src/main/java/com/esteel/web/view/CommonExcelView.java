package com.esteel.web.view;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esteel.web.utils.ClazzUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.esteel.common.spreadsheet.RowBuilder;
import com.esteel.common.spreadsheet.SheetBuilder;
import com.esteel.common.spreadsheet.Spreadsheet;
import com.esteel.common.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("common-view")
public class CommonExcelView extends AbstractXlsxView {

    /**@VIEW 注解 model强制配置成MODEL*/
    public static final String MODEL = "LIST_MODEL";

    @Override
    protected void buildExcelDocument(
            Map<String, Object> model, Workbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setCharacterEncoding("UTF-8");
        String fileName = String.format("%s-%s.xlsx", "no-content", DateUtils.getFormatDateTime(new Date(), DateUtils.DATE_TIMESTAMP_SHORT_FORMAT));
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(fileName, "UTF-8")));
        //noinspection unchecked
        @SuppressWarnings("unchecked")
		List<Field> list = List.class.cast(model.get(MODEL));
        if (CollectionUtils.isEmpty(list)) {
            new Spreadsheet(workbook).newSheet("No data.");
            return;
        }
        try {
            Spreadsheet spreadsheet = new Spreadsheet(workbook);
            SheetBuilder sheet = null;
            List<Field> headFieldList = new ArrayList<>();
            Map<Field, BigDecimal> countMap = new HashMap<>();
            //获取字体格式
            Font bodyFont = getFont(spreadsheet);
            for (int i = 0; i < list.size(); i++) {
                Object bean = list.get(i);
                //获取字段名，同时设置表头
                if (CollectionUtils.isEmpty(headFieldList)) {
                    Excel excel = bean.getClass().getAnnotation(Excel.class);
                    if (Objects.nonNull(excel)) {
                        fileName = String.format("%s-%s.xlsx", excel.fileName(), DateUtils.getFormatDateTime(new Date(), DateUtils.DATE_TIMESTAMP_SHORT_FORMAT));
                        response.setHeader("Content-Disposition", String.format("attachment; filename=%s", URLEncoder.encode(fileName, "UTF-8")));
                        sheet = spreadsheet.newSheet(excel.sheetName());
                    }
                    List<Field> fieldList = getClassFieldList(bean.getClass());
                    //设置Excel表头字段
                    RowBuilder headRowBuilder = sheet.newRow(getHeadStyle(spreadsheet, bodyFont));
                    headRowBuilder.cell("No.");
                    for (Field field : fieldList) {
                        ExcelHead excelHead = field.getAnnotation(ExcelHead.class);
                        if (Objects.nonNull(excelHead)) {
                            headRowBuilder.cell(excelHead.name());
                            headFieldList.add(field);
                            // 是否需要统计
                            if (excelHead.count()) {
                                countMap.put(field, new BigDecimal(0));
                            }
                        }
                    }
                }

                //新增内容行
                RowBuilder bodyRowBuilder = sheet.newRow(getBodyStyle(spreadsheet, bodyFont));
                bodyRowBuilder.cell(String.valueOf(i + 1));
                for (Field field : headFieldList) {
                    Object value = ClazzUtil.getField(bean, field);
                    if (Objects.isNull(value)) {
                        bodyRowBuilder.cell("");
                    } else {
                        if (value instanceof Date) {
                            ExcelHead excelHead = field.getAnnotation(ExcelHead.class);
                            bodyRowBuilder.cell(DateUtils.getFormatDateTime(value, excelHead.dateFormat()));
                        } else if (value instanceof Number) {
                            bodyRowBuilder.digit((Number) value);
                            ExcelHead excelHead = field.getAnnotation(ExcelHead.class);
                            // 是否需要统计
                            if (Objects.nonNull(excelHead) && excelHead.count()) {
                                countMap.put(field, countMap.get(field).add(new BigDecimal(value.toString())));
                            }
                        } else {
                            bodyRowBuilder.cell(value.toString());
                            ExcelHead excelHead = field.getAnnotation(ExcelHead.class);
                            // 是否需要统计
                            if (Objects.nonNull(excelHead) && excelHead.count()) {
                                countMap.put(field, countMap.get(field).add(new BigDecimal(value.toString())));
                            }
                        }
                    }
                }
            }

            // 添加统计行
            RowBuilder totalRowBuilder = sheet.newRow(getBodyStyle(spreadsheet, bodyFont));
            totalRowBuilder.cell("合计");
            for (Field field : headFieldList) {
                if (countMap.containsKey(field)) {
                    totalRowBuilder.decimal(countMap.get(field));
                } else {
                    totalRowBuilder.cell("-");
                }
            }
        } catch (Exception e) {
            log.error("export file fail!", e);
        }
    }

    private Font getFont(Spreadsheet spreadsheet) {
        return spreadsheet.newFont(font -> {
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);
        });
    }

    private CellStyle getBodyStyle(Spreadsheet spreadsheet, Font bodyFont) {
        return spreadsheet.newStyle(cellStyle -> {
            cellStyle.setFont(bodyFont);
        });
    }

    private CellStyle getHeadStyle(Spreadsheet spreadsheet, Font bodyFont) {
        return spreadsheet.newStyle(cellStyle -> {
            cellStyle.setFont(bodyFont);
        });
    }

    private List<Field> getClassFieldList(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        do {
            Collections.addAll(list, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);
        return list;
    }
}
