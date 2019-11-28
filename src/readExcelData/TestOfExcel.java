package readExcelData;

/**
 * �ο����ϣ�https://www.jianshu.com/p/1da98d77880e
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
		int[] resultData;//ÿһ��Զ����������Դÿ������ƽ����ȡ��
		int timeOfExpedition;
		
		//System.err.println("0");
		try{
			//��ȡexcel��ʼ��������
			WriteExcel test = new WriteExcel();
			//System.err.println("1");
		book = Workbook.getWorkbook(new File("����.xls"));
			//System.err.println("2");
		sheet = book.getSheet(0);//���ù�����
		
		
		//��ȡ���߼��ʱ��
		cell=sheet.getCell(3,9);
		//String string = cell.getContents();
		int timeOfSet = Integer.parseInt(cell.getContents());
		//System.out.println(timeOfExpedition);
		
		//��ȡ��ԴԤ�ڱ���
		int[] proportion = new int[4];
		for(int k = 0 ; k < 4 ; k++) {
			cell=sheet.getCell(k+1,11);//�����С���
			String string = cell.getContents();
			//System.out.println("����Ϊ��" + string);
			proportion[k] = Integer.parseInt(string);
		}
		
		sheet = book.getSheet(1);//���ù�����
		for(int j = 0 ; j < 43; j++){//1136
		//��ȡԶ��id
		cell=sheet.getCell(0,j+1);
		//String string = cell.getContents();
		String idOfExpedition = cell.getContents();
		//System.out.println(timeOfExpedition);

		//��ȡԶ������
		cell = sheet.getCell(1,j+1);
		String nameOfExpedition = cell.getContents();
		
		//��ȡԶ��ʱ��
		cell=sheet.getCell(2,j+1);
		//String string = cell.getContents();
		timeOfExpedition = Integer.parseInt(cell.getContents());
		//System.out.println(timeOfExpedition);

		//��ȡÿ��Զ����������Դ��ȡ��
		//System.out.println("0");
		for(int i = 0 ; i < 4 ; i++){
		cell=sheet.getCell(i+3,j+1);//�����С���
		//System.out.println("1");
		String string = cell.getContents();
		//System.out.println("����Ϊ��" + string);
		Task[i] = Integer.parseInt(string);
		//System.out.println("value " + string);
		//System.out.println("2");
		}//for
		//System.out.println("100");
		
		//��ʼ��������
		TimeAvailability JiSr = new TimeAvailability(idOfExpedition,Task,timeOfExpedition,timeOfSet);

		//������ԴԤ�ڱ���
		test.SetProportion(proportion[0],proportion[1],proportion[2],proportion[3]);
		//��ÿһ��Զ��������д��
		resultData = JiSr.getResultData();
		test.Write(idOfExpedition,nameOfExpedition,resultData[0],resultData[1],resultData[2],resultData[3]);
		//test.Write(20, 50, 50, 400);
		
		}//for1136
		
		//д���ļ����ر�
		test.CloseExcel();
		
		}catch(Exception e){}
		



		
	}
}
