package com.JackeyZz.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class docIO {
	public static void main(String[] args) {
		docIO obj=new docIO();
		File file=new File("C:\\Users\\acer\\Desktop\\ml-latest-small\\movies.xls");
		obj.readExcel(file);
	}
	
	//读取excel
	public void readExcel(File file){
		try {
			//创建输入流
			InputStream is;
			is = new FileInputStream(file);
			//jxl.jar提供的workbook类
			Workbook wb;
			wb = Workbook.getWorkbook(is);
			//页签数量
			int sheet_size=wb.getNumberOfSheets();
			for(int index=0;index<sheet_size;index++){
				//每个页签创建一个sheet对象
				Sheet sheet=wb.getSheet(index);
				//逐个输出
				int x=sheet.getRows();
				int y=sheet.getColumns();
				for(int i=0;i<sheet.getRows();i++){
					for(int j=0;j<sheet.getColumns();j++){
						String cellinfo=sheet.getCell(j, i).getContents();
						System.out.print(cellinfo);
					}
					System.out.println();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}
}
