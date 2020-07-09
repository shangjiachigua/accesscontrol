package com.accesscontrol.manage.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    public static List<List<String>> readExcelFile(File file, String fileType, String rootPath) throws Exception {
        List<List<String>> excelDateList = new ArrayList<List<String>>();
        int rowsNum = 0;  //定义行数
        int columnNum = 0;  //定义列数
        Sheet rs;
        try {
            String tempFileName = Constance.orderFormNumber();
            InputStream is = new FileInputStream(file);
            String root = rootPath;
            File destFile = new File(root, tempFileName + "." + fileType);
            OutputStream os = new FileOutputStream(destFile);
            byte[] buffer = new byte[400];
            int length = 0;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
            /***************************************************************************/

            InputStream fis = new FileInputStream(root + tempFileName + "." + fileType);
            Workbook wb = null;
            //根据文件是2003或者是2007创建Workbook
            if ("xls".equals(fileType)) {
                wb = new HSSFWorkbook(fis);
            } else {
                wb = new XSSFWorkbook(fis);
            }
            Sheet sheet = wb.getSheetAt(0);  //获取sheet
            //获取excel的行数和列数
            rowsNum = sheet.getPhysicalNumberOfRows();
            columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 1; i < rowsNum; i++) {
                List<String> datalist = new ArrayList<String>();
                Row tempRow = sheet.getRow(i);
                if (null == tempRow) {
                    continue;
                }
                for (int j = 0; j < columnNum; j++) {
                    Cell tempCell = tempRow.getCell(j);
                    if (null == tempCell) {
                        datalist.add("");
                        continue;
                    }
                    if (tempCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        if (HSSFDateUtil.isCellDateFormatted(tempCell)) {
                            //用于转化为日期格式
                            Date d = tempCell.getDateCellValue();
                            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                            datalist.add(formater.format(d));
                        } else {
                            BigDecimal bigDecimal = new BigDecimal(tempCell.getNumericCellValue());
                            datalist.add(bigDecimal.toString());
                        }

                    } else if (tempCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        datalist.add(Constance.Null2EmptyTrim(tempCell.getStringCellValue()));
                    } else if (tempCell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        datalist.add("");
                        continue;
                    } else if (tempCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
                        //是否为公式计算值
                        datalist.add(String.valueOf((int) tempCell.getNumericCellValue()));
                    } else {
                        datalist.add("");
                    }


                  /*  else {
                        datalist.add("");
                    }*/
                }
                if (null != datalist && datalist.size() > 1 && !"".equals(datalist.get(0))) {
                    excelDateList.add(datalist);
                }
            }
            //删除生成的临时文件
            File tempFile = new File(root + tempFileName + "." + fileType);
            if (tempFile.exists() && tempFile.isFile()) {
                tempFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return excelDateList;
    }


}
