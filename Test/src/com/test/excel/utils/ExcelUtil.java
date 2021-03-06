package com.test.excel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.vo.FormBean;
import com.test.vo.TotalBillBean;



/**
 * @author Hongten
 * @created 2014-5-20
 */
public class ExcelUtil {
	private int nameIndex=0;
	private int moneyIndex=0;
	
	public ExcelUtil(int nameIndex,int moneyIndex){
		this.nameIndex=nameIndex;
		this.moneyIndex=moneyIndex;
	}
    public void writeExcel(List<TotalBillBean> list, String path) throws Exception {
        if (list == null) {
            return;
        } else if (path == null || Common.EMPTY.equals(path)) {
            return;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    writeXls(list, path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    writeXlsx(list, path);
                }
            }else{
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
    }
    
    

    
    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<FormBean> readExcel(String path) throws IOException {
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
    public List<FormBean> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<FormBean> list = new ArrayList<FormBean>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    XSSFCell money = xssfRow.getCell(moneyIndex);
                    XSSFCell name = xssfRow.getCell(nameIndex);
          
                    
                    FormBean formBean =generFormBean( getValue(money),getValue(name));
                    list.add(formBean);

                }
            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<FormBean> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<FormBean> list = new ArrayList<FormBean>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            } 
            
            // Read the Row
            for (int rowNum = 1; rowNum <hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
              
                    HSSFCell money = hssfRow.getCell(moneyIndex);
                    HSSFCell name = hssfRow.getCell(nameIndex);
               
                    FormBean formBean =generFormBean( getValue(money),getValue(name));
                    list.add(formBean);

                }
            }
        }
        return list;
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
    
    public void writeXls(List<TotalBillBean> list, String path) throws Exception {
        if (list == null) {
            return;
        }
        int countColumnNum = list.size();
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("studentSheet");
        // option at first row.
        HSSFRow firstRow = sheet.createRow(0);
        HSSFCell[] firstCells = new HSSFCell[countColumnNum];
        String[] options = { "name", "money"};
        for (int j = 0; j < options.length; j++) {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(new HSSFRichTextString(options[j]));
        }
        //
        for (int i = 0; i < countColumnNum; i++) {
            HSSFRow row = sheet.createRow(i + 1);
            TotalBillBean totalBillBean = list.get(i);
            for (int column = 0; column < options.length; column++) {
                HSSFCell name = row.createCell(0);
                HSSFCell money = row.createCell(1);

                name.setCellValue(totalBillBean.getName());
                money.setCellValue(totalBillBean.getMoney().toString());

            }
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(Common.WRITE_DATA + path);
        book.write(os);
        os.close();
    }
    
    public void writeXlsx(List<TotalBillBean> list, String path) throws Exception {
        if (list == null) {
            return;
        }
        //XSSFWorkbook
        int countColumnNum = list.size();
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("studentSheet");
        // option at first row.
        XSSFRow firstRow = sheet.createRow(0);
        XSSFCell[] firstCells = new XSSFCell[countColumnNum];
        String[] options = { "name", "money"};
        for (int j = 0; j < options.length; j++) {
            firstCells[j] = firstRow.createCell(j);
            firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
        }
        //
        for (int i = 0; i < countColumnNum; i++) {
            XSSFRow row = sheet.createRow(i + 1);
            TotalBillBean totalBillBean = list.get(i);
            for (int column = 0; column < options.length; column++) {
                XSSFCell name = row.createCell(0);
                XSSFCell money = row.createCell(1);
                name.setCellValue(totalBillBean.getName());
                money.setCellValue(totalBillBean.getMoney().toString());
            }
        }
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        System.out.println(Common.WRITE_DATA + path);
        book.write(os);
        os.close();
    }
   

    
    private  FormBean generFormBean(String totalmoney,String totalName){
    	
    	FormBean formBean=new FormBean();
        formBean.setMoney(totalmoney);
        formBean.setPeopleName(totalName);
        
        BigDecimal allMoney=new BigDecimal(totalmoney);
        BigDecimal divMoney=new BigDecimal(26);
        BigDecimal workinghoursBigDecimal=allMoney.divide(divMoney,1,BigDecimal.ROUND_HALF_UP);
       
        formBean.setWorkinghoursBigDecimal(workinghoursBigDecimal);
       
      return formBean;
        
    	
    }
    
    public interface OnReadExcelListener{
    	void onFail();
    	void onSuccess();
    }

 


}