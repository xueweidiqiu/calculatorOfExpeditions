package readExcelData;

/**
 * 参考资料：https://www.jianshu.com/p/1da98d77880e
 */

import java.io.File;

import calculatorOfTimeAvailability.TimeAvailability;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import writeExcelData.WriteExcel;

public class TestOfExcel {
	public static void main(String args[]) {
		int Task[] =new int[4];
		Sheet sheet;
		Workbook book;
		Cell cell;
		int[] resultData;//每一个远征的四项资源每次上线平均获取量
		int timeOfExpedition;
		
		//System.err.println("0");
		try{
			//读取excel初始化和设置
			WriteExcel test = new WriteExcel();
			//System.err.println("1");
		book = Workbook.getWorkbook(new File("数据.xls"));
			//System.err.println("2");
		sheet = book.getSheet(0);//设置工作表
		
		
		//获取上线间隔时间
		cell=sheet.getCell(3,9);
		//String string = cell.getContents();
		int timeOfSet = Integer.parseInt(cell.getContents());
		//System.out.println(timeOfExpedition);
		
		//获取资源预期比例
		int[] proportion = new int[4];
		for(int k = 0 ; k < 4 ; k++) {
			cell=sheet.getCell(k+1,11);//设置列、行
			String string = cell.getContents();
			//System.out.println("内容为：" + string);
			proportion[k] = Integer.parseInt(string);
		}
		
		sheet = book.getSheet(1);//设置工作表
		for(int j = 0 ; j < 43; j++){//1136
		//获取远征id
		cell=sheet.getCell(0,j+1);
		//String string = cell.getContents();
		String idOfExpedition = cell.getContents();
		//System.out.println(timeOfExpedition);

		//获取远征名称
		cell = sheet.getCell(1,j+1);
		String nameOfExpedition = cell.getContents();
		
		//获取远征时间
		cell=sheet.getCell(2,j+1);
		//String string = cell.getContents();
		timeOfExpedition = Integer.parseInt(cell.getContents());
		//System.out.println(timeOfExpedition);

		//获取每次远征的四项资源获取量
		//System.out.println("0");
		for(int i = 0 ; i < 4 ; i++){
		cell=sheet.getCell(i+3,j+1);//设置列、行
		//System.out.println("1");
		String string = cell.getContents();
		//System.out.println("内容为：" + string);
		Task[i] = Integer.parseInt(string);
		//System.out.println("value " + string);
		//System.out.println("2");
		}//for
		//System.out.println("100");
		
		//初始化计算器
		TimeAvailability JiSr = new TimeAvailability(idOfExpedition,Task,timeOfExpedition,timeOfSet);

		//设置资源预期比例
		test.SetProportion(proportion[0],proportion[1],proportion[2],proportion[3]);
		//将每一个远征的数据写入
		resultData = JiSr.getResultData();
		test.Write(idOfExpedition,nameOfExpedition,resultData[0],resultData[1],resultData[2],resultData[3]);
		//test.Write(20, 50, 50, 400);
		
		}//for1136
		
		//写入文件并关闭
		test.CloseExcel();
		
		}catch(Exception e){}
		



		
	}
}
