package com.test.excel.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExelTitleUtil {
    public Map< Integer,String> readTitle(String path) throws IOException {
   
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    	

    }
    
    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public Map< Integer,String> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        Map< Integer,String> map=new HashMap<>();
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        TableItemVo tableItemVo = null;
        List<TableItemVo> list = new ArrayList<TableItemVo>();
        // Read the Sheet
        System.out.println("getNumberOfSheets="+xssfWorkbook.getNumberOfSheets());
        
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            System.out.println("xssfSheet.getLastRowNum()="+xssfSheet.getLastRowNum());
          
            if(xssfSheet.getLastRowNum()>0){
            	 XSSFRow xssfRow  = xssfSheet.getRow(0);
           	 for(int cellNum=0;cellNum< xssfRow.getLastCellNum();cellNum++){
           		XSSFCell xssfCell = xssfRow.getCell(cellNum);
           		   map.put(cellNum, getValue(xssfCell));
           	 }
           }

        }
        return map;
    }
    
    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public Map< Integer,String> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        Map< Integer,String> map=new HashMap<>();
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        TableItemVo tableItemVo = null;
        List<TableItemVo> list = new ArrayList<TableItemVo>();
        // Read the Sheet
        System.out.println("getNumberOfSheets="+hssfWorkbook.getNumberOfSheets());
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            if(hssfSheet.getLastRowNum()>0){
            	 HSSFRow hssfRow = hssfSheet.getRow(0);
            	 for(int cellNum=0;cellNum< hssfRow.getLastCellNum();cellNum++){
            		   HSSFCell hssfCell = hssfRow.getCell(cellNum);
            		   map.put(cellNum, getValue(hssfCell));
            	 }
            }

        }
        return map;
    }
    
    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
