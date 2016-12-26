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
import com.test.wiget.FileDialogHelper;
import com.test.wiget.FileDialogHelper.OnFileChooseListener;

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

	private JFrame frame;//显示的界面效果
	
	private JButton button;//显示Button
	
	private JLabel lblNewLabel;//显示标签
	
	
	int screenWidth ;//屏幕的宽

	int screenHeight ;//屏幕的高
	
	
	int windowWidth;// 窗口宽

	int windowHeight; // 窗口高

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
	 * 初始化控件
	 */
	public Hello() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		initFrame();
        initButton();
		initLabel();

	}
	
	
	/**
	 * 初始化Frame
	 */
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
	
	/**
	 * 初始化Button
	 */
	private void initButton(){
		button = new JButton("打开excle文件");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showFileDialog();
			}
		});
		button.setBounds(0,0, frame.getWidth()/10, frame.getWidth()/20);
		frame.getContentPane().add(button);
	}

	
	/**
	 * 初始化Label
	 */
	private void initLabel(){
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(frame.getWidth()/10, frame.getWidth()/20, 500, 500);
		frame.getContentPane().add(lblNewLabel);
	}
	
	private String parentPath;
	
	private String filename;
	
	private StringBuilder opercationInfo;
	
	private void showFileDialog(){
		lblNewLabel.setText("");
		addText("\n打开文件对话框");
		FileDialogHelper fileDialogHelper=new FileDialogHelper();
		fileDialogHelper.showFileDialog(new OnFileChooseListener() {
			
			@Override
			public void onSucess(int type, File file, JFileChooser jfc) {
				// TODO Auto-generated method stub
		        filename=jfc.getSelectedFile().getName();//获取选择的文件名
		        parentPath= file.getParentFile().getAbsolutePath();
					System.out.println("parentPath="+parentPath);
					 addText("选择文件完成："+parentPath);
					 readTitleExcel(file);
					 readExcel(file);
		       
			}
			
			@Override
			public void onFail(int type, String errorInfo) {
				// TODO Auto-generated method stub
				addText("选择文件完成：errorInfo");
			}
		});

	}
	
	
	
	private int nameIndex=0;
	private int moneyIndex=0;

	/**
	 * 
	 * @param file
	 */
	private void readTitleExcel(File file){
		ExelTitleUtil  exelTitleUtil =new ExelTitleUtil();
		try {
			Map< Integer,String> map=	exelTitleUtil.readTitle(file.getAbsolutePath());
			//reshGridAdater(1, map);
			for (Map.Entry entry : map.entrySet()) {       
			    
			    Integer key = (Integer) entry.getKey( );    
			    String value = (String) entry.getValue(); 
			    if(value.equals("工时费")){
			    	moneyIndex=key;
			    	System.out.println("工时费----key="+key+"---value="+value);
			    }
			    
			    if(value.equals("维修人员姓名")){
			    	nameIndex=key;
			    	 System.out.println("维修人员姓名----key="+key+"---value="+value);
			    }
			    
			    
			    System.out.println("key="+key+"---value="+value);
			    //lblNewLabel.setText(value);
			    
			}    
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void readExcel(File file){
		ExcelUtil  excelUtil =new ExcelUtil(nameIndex,moneyIndex);
		addText("准备读取execl文件的信息");
		try {
			List<FormBean> formBeans=	excelUtil.readExcel(file.getAbsolutePath());
           //System.out.println(formBeans.toString());
           generWorkerBeanList(formBeans);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addText("读取execl文件的出错"+e.toString());
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
		
		generTotalBillMoney(workbeans);
		
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
    	   addText("准备读取execl文件的成功");
    	   writeExcel(totalBillBeans);

    	
    }
    
    private void  writeExcel(List<TotalBillBean> totalBillBeans){
    	 addText("准备生成统计工时的excel文件");
 	   ExcelUtil  excelUtil =new ExcelUtil(nameIndex,moneyIndex);
 	 
 	   String time= DateUtils.getNow(DateUtils.FORMAT_LONG_CN);
 	   String path=parentPath+File.separator+"统计工时总工资"+time+filename;
 	   try {
		excelUtil.writeExcel(totalBillBeans, path);
		File file=new File(path);
		if(file.exists()){
			 addText("生成统计工时的excel文件成功");

			System.out.println("文件存在"+path);
			Runtime.getRuntime().exec(
	                "rundll32 SHELL32.DLL,ShellExec_RunDLL " +
	                "Explorer.exe /select," + path);
		}else{
			System.out.println("文件不存在"+path);
		}
	

	} catch (Exception e) {
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
   
    
    private void addText(String text){
	
		String nowText=lblNewLabel.getText();
		StringBuilder stringBuilder=new StringBuilder(nowText);
		stringBuilder.append("---》"+text);
		lblNewLabel.setText(stringBuilder.toString());
    }

}
