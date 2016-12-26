package com.test.wiget;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

public class FileDialogHelper {
	
	public static final int type_no_choose=0;
	public static final int type_Directory=1;
	public static final int type_File=2;
	public OnFileChooseListener onFileChooseListener;
	
	public void showFileDialog(OnFileChooseListener onFileChooseListener){
		this.onFileChooseListener=onFileChooseListener;
        JFileChooser jfc=new JFileChooser();  
        ExcelFileFilter excelFilter = new ExcelFileFilter(); //excel过滤器    
        jfc.addChoosableFileFilter(excelFilter);  
        jfc.setFileFilter(excelFilter);
       // jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();  
        if(null==file){
        	 System.out.println("取消");
        	 if(onFileChooseListener!=null){
        	 	 onFileChooseListener.onFail(type_no_choose,"取消");
        	 }
        	return;
        }
        if(file.isDirectory()){  
            System.out.println("文件夹:"+file.getAbsolutePath()); 
       	 if(onFileChooseListener!=null){
    	 	 onFileChooseListener.onFail(type_Directory,"不是正确的文件");
    	 }
        }else if(file.isFile()){  
            System.out.println("文件:"+file.getAbsolutePath());  
          	 if(onFileChooseListener!=null){
        	 	 onFileChooseListener.onSucess(type_File, file, jfc);
        	 }
        }  
        System.out.println(jfc.getSelectedFile().getName());  

//        filename=jfc.getSelectedFile().getName();//获取选择的文件名
//        parentPath= file.getParentFile().getAbsolutePath();
//			System.out.println("parentPath="+parentPath);
//        readExcel(file);
        
       

	}
	
	public interface OnFileChooseListener{
		void onSucess(int type,File file,JFileChooser jfc);
		void onFail(int type,String errorInfo);
	}
	
	
	class ExcelFileFilter extends FileFilter {    
	    public String getDescription() {    
	        return "*.xls;*.xlsx";    
	    }    
	    
	    public boolean accept(File file) {    
	        String name = file.getName();    
	        return file.isDirectory() || name.toLowerCase().endsWith(".xls") || name.toLowerCase().endsWith(".xlsx");  // 仅显示目录和xls、xlsx文件  
	    }    
	}  
}
