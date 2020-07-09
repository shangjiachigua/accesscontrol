package com.accesscontrol.manage.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通用的导出excel服务
 */
@Service
public class PoiService {

    private static final Logger log= LoggerFactory.getLogger(PoiService.class);

    private static final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private Environment env;



    //TODO：dataList.subList(0,100)
    //TODO：截取 0-100 总数100 调用一次fillExcelSheetData
    //TODO：截取 100-200 总数 100  调用一次fillExcelSheetData

    /**
     * 分sheet导出实战
     * @param dataList
     * @param headers
     * @param sheetName
     * @return
     */
    public static Workbook manageSheet(List<Map<Integer, Object>> dataList, String[] headers, String sheetName){
        final Integer sheetSize=1000;
        //final Integer sheetSize=env.getProperty("poi.product.excel.sheet.size",Integer.class);
        int dataTotal=dataList.size();

        int sheetTotal = (dataTotal%sheetSize==0)? dataTotal/sheetSize : (dataTotal/sheetSize + 1);
        int start=0;
        int end=sheetSize;

        List<Map<Integer, Object>> subList;
        Workbook wb=new HSSFWorkbook();

        for (int i=0;i<sheetTotal;i++){
            subList=dataList.subList(start,end);
            wb=fillExcelSheetDataV2(subList,headers,sheetName+"_"+(i+1),wb);

            start += sheetSize;
            end += sheetSize;
            if (end>=dataTotal){
                end=dataTotal;
            }
        }
        return wb;
    }


    /**
     * 填充数据到excel的sheet中 - 分sheet实战
     * @param dataList
     * @param headers
     * @param sheetName
     */
    public static Workbook fillExcelSheetDataV2(List<Map<Integer, Object>> dataList, String[] headers, String sheetName,Workbook wb){
        Sheet sheet=wb.createSheet(sheetName);

        //TODO：创建sheet的第一行数据-即excel的表头
        Row headerRow=sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        //TODO：从第二行开始塞入真正的数据列表
        int rowIndex=1;
        Row row;
        Object obj;

        if (dataList!=null && dataList.size()>0){
            for(Map<Integer, Object> rowMap:dataList){
                try {
                    row=sheet.createRow(rowIndex++);

                    //TODO：遍历表头行-每个key -> 取到实际的value
                    for(int i=0;i<headers.length;i++){
                        obj=rowMap.get(i);

                        if (obj==null) {
                            log.debug("--");

                            row.createCell(i).setCellValue("");
                        }else if (obj instanceof Date) {
                            String tempDate=simpleDateFormat.format((Date)obj);
                            row.createCell(i).setCellValue((tempDate==null)?"":tempDate);
                        }else {
                            row.createCell(i).setCellValue(String.valueOf(obj));
                        }
                    }
                } catch (Exception e) {
                    log.debug("充数据到excel的sheet中 - 分sheet实战 发生异常： ",e.fillInStackTrace());
                }
            }
        }


        return wb;
    }



    /**
     * 填充数据到excel的sheet中 - v1
     * @param dataList
     * @param headers
     * @param sheetName
     */
    public static Workbook fillExcelSheetData(List<Map<Integer, Object>> dataList, String[] headers, String sheetName){
        Workbook wb=new HSSFWorkbook();
        Sheet sheet=wb.createSheet(sheetName);

        //TODO：创建sheet的第一行数据-即excel的表头
        Row headerRow=sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        //TODO：从第二行开始塞入真正的数据列表
        int rowIndex=1;
        Row row;
        Object obj;

        if (dataList!=null && dataList.size()>0){
            for(Map<Integer, Object> rowMap:dataList){
                try {
                    row=sheet.createRow(rowIndex++);

                    //TODO：遍历表头行-每个key -> 取到实际的value
                    for(int i=0;i<headers.length;i++){
                        obj=rowMap.get(i);

                        if (obj==null) {
                            row.createCell(i).setCellValue("");
                        }else if (obj instanceof Date) {
                            String tempDate=simpleDateFormat.format((Date)obj);
                            row.createCell(i).setCellValue((tempDate==null)?"":tempDate);
                        }else {
                            row.createCell(i).setCellValue(String.valueOf(obj));
                        }
                    }
                } catch (Exception e) {
                    log.debug("excel sheet填充数据 发生异常： ",e.fillInStackTrace());
                }
            }
        }

        return wb;
    }

    //读取excel 文件数据
    public static List<String[]> readExcel(File file, String fileName,boolean deleteFlag) throws IOException {
        //检查文件
        checkFile(file,fileName);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file,fileName);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum<lastCellNum+firstCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum-firstCellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
        if(deleteFlag){
            MultipartFileToFile.delteTempFile(file);
        }
        return list;
    }

    public static String checkFile(File file,String fileName) throws IOException{
        String flag = "1";
        //判断文件是否存在
        if(null == file||!file.exists()){
            flag = "2";
        }
        //判断文件是否是excel文件
        if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
            flag = "3";
        }
        return flag;
    }

    public static Workbook getWorkBook(File file,String fileName) {
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith("xls")){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith("xlsx")){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return workbook;
    }

    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把所以类型当成String来读
        cell.setCellType(Cell.CELL_TYPE_STRING);
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                Object obj = cell.getStringCellValue();
                if(obj != null) {
                    cellValue = String.valueOf(obj);
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "";
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue.trim();
    }
}



















































