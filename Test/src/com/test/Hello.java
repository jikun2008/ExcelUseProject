package com.test;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;

import com.test.excel.utils.DateUtils;
import com.test.excel.utils.ExcelUtil;
import com.test.excel.utils.ExelTitleUtil;
import com.test.vo.FormBean;
import com.test.vo.TotalBillBean;
import com.test.vo.WorkerBean;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.GridLayout;

public class Hello {

	private JFrame frame;
	
	private JButton button;
	
	private JLabel lblNewLabel;
	
	
	int screenWidth ;//获取屏幕的宽

	int screenHeight ;//获取屏幕的高
	
	
	int windowWidth;// 获得窗口宽

	int windowHeight; // 获得窗口高

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hello window = new Hello();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Hello() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		initFrame();
		button = new JButton("打开excle文件");
		//button.setPreferredSize( frame.getWidth()/2, frame.getWidth()/2);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showFileDialog();
			}
		});
		button.setBounds(0,0, frame.getWidth()/10, frame.getWidth()/20);
		frame.getContentPane().add(button);
		 

		
		 lblNewLabel = new JLabel("New label");
		 lblNewLabel.setBounds(frame.getWidth()/10, frame.getWidth()/20, 500, 500);
		frame.getContentPane().add(lblNewLabel);
	
	
		
	}
	
	private void initFrame(){

		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		



		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包

		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸

		int screenWidth = screenSize.width; // 获取屏幕的宽

		int screenHeight = screenSize.height; // 获取屏幕的高
		frame.setSize(screenWidth*3/4, screenHeight*3/4);
		
		int windowWidth = frame.getWidth(); // 获得窗口宽

		int windowHeight = frame.getHeight(); // 获得窗口高

	
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设
	}
	
	private String parentPath;
	private String filename;
	private void showFileDialog(){
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();  
        if(null==file){
        	 System.out.println("取消");  
        	return;
        }
        if(file.isDirectory()){  
            System.out.println("文件夹:"+file.getAbsolutePath());  
        }else if(file.isFile()){  
            System.out.println("文件:"+file.getAbsolutePath());  
        }  
        System.out.println(jfc.getSelectedFile().getName());  

        filename=jfc.getSelectedFile().getName();
        parentPath= file.getParentFile().getAbsolutePath();
			System.out.println("parentPath="+parentPath);
        readExcel(file);
        
       
   

			
//			//当前用户桌面
//			File desktopDir = FileSystemView.getFileSystemView()
//			.getHomeDirectory();
//			String desktopPath = desktopDir.getAbsolutePath();
//			canonicalPath=desktopPath;

	}
	
	
	
	private void readExcel(File file){
		ExcelUtil  excelUtil =new ExcelUtil();
		try {
			List<FormBean> formBeans=	excelUtil.readExcel(file.getAbsolutePath());
           //System.out.println(formBeans.toString());
           generWorkerBeanList(formBeans);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void generWorkerBeanList(List<FormBean> formBeans){

	    List<WorkerBean> workbeans=new ArrayList<>();
		for(FormBean formBean: formBeans){
			
			String peopleNmae=formBean.getPeopleName();
			String[] nameList=	peopleNmae.split( "[、，]");
			
			
			for(String name:nameList){
				WorkerBean workerBean=new WorkerBean();
				workerBean.setName(name);
				
				BigDecimal  divideMoney=new BigDecimal(nameList.length);
			    BigDecimal money=formBean.getWorkinghoursBigDecimal().divide(divideMoney,1,BigDecimal.ROUND_HALF_UP);
				workerBean.setMoney(money);
				workbeans.add(workerBean);
			
	
			
			}
		
		}
		
		System.out.println(workbeans.toString());
		
		for(WorkerBean workerBean:workbeans){
			if(workerBean.getName().equals("袁江峰")){
				System.out.println(workerBean.toString());
			
			
			}
		}
		
		
		generTotalBillMoney(workbeans);
		//System.out.println(workbeans.toString());
		
	}
	
    private void generTotalBillMoney( List<WorkerBean> workbeanList){
    	
    	List<TotalBillBean> totalBillBeans=new ArrayList<>();
    	
    	Map<String, BigDecimal> map=new HashMap<>();
    	for(WorkerBean workerBean:workbeanList){
    		if(map.get(workerBean.getName())==null){
    			map.put(workerBean.getName(), workerBean.getMoney());	
    		}else{
    			BigDecimal nowMoney=map.get(workerBean.getName());
    			BigDecimal totalMoney=nowMoney.add(workerBean.getMoney());
    			map.put(workerBean.getName(), totalMoney);
    		}
    	
    	}
    	   for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
    		   TotalBillBean totalBillBean=new TotalBillBean();
    		   totalBillBean.setName( entry.getKey());
    		   totalBillBean.setMoney(entry.getValue());
    		   totalBillBeans.add(totalBillBean);
    	   }
    	//System.out.println("Map统计"+map.get("袁江峰"));
    	   writeExcel(totalBillBeans);

    	
    }
    
    private void  writeExcel(List<TotalBillBean> totalBillBeans){
 	   ExcelUtil  excelUtil =new ExcelUtil();
 	 
 	   String time= DateUtils.getNow(DateUtils.FORMAT_LONG_CN);
 	   String path=parentPath+File.separator+"统计工时总工资"+time+filename;
 	   try {
		excelUtil.writeExcel(totalBillBeans, path);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	/**
	 * key=15---value=工时费
key=17---value=维修人员姓名
	 * @param file
	 */
	
	private void readTitleExcel(File file){
		ExelTitleUtil  exelTitleUtil =new ExelTitleUtil();
		try {
			Map< Integer,String> map=	exelTitleUtil.readTitle(file.getAbsolutePath());
			reshGridAdater(1, map);
			for (Map.Entry entry : map.entrySet()) {       
			    
			    Integer key = (Integer) entry.getKey( );    
			    String value = (String) entry.getValue(); 
			    
			    System.out.println("key="+key+"---value="+value);
			    lblNewLabel.setText(value);
			    
			}    
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param row 行
	 * @param column 列
	 */
    private void reshGridAdater(int row,Map< Integer,String> map){
    	System.out.println("map.size()="+map.size());
        JPanel panel = new JPanel(new GridLayout(row,(map.size()+1)));
        
        //加一些标签  就能显示了
        
		for (Map.Entry entry : map.entrySet()) {    
			
		    Integer key = (Integer) entry.getKey( );    
		    String value = (String) entry.getValue(); 
			JLabel jLabel=new JLabel();
			jLabel.setText(value);
		    
			panel.add(jLabel);
	
		    
		} 

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 10, windowWidth, windowHeight);
        frame.getContentPane().add(scrollPane);
    }
}
