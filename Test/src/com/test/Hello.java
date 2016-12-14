package com.test;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.test.excel.utils.ExcelUtil;
import com.test.excel.utils.ExelTitleUtil;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.awt.GridLayout;

public class Hello {

	private JFrame frame;
	
	private JButton button;
	
	private JLabel lblNewLabel;

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
        readExcel(file);
	}
	
	
	private void readExcel(File file){
		ExelTitleUtil  exelTitleUtil =new ExelTitleUtil();
		try {
			Map< Integer,String> map=	exelTitleUtil.readTitle(file.getAbsolutePath());
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

}
