package calculatorOfTimeAvailability;

import java.io.IOException;
import java.text.ParseException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * ʹ��Calculate�������м��㣬�������Ϊ����String���͵�ʱ��,��ʽΪ��hh:mm��
 * ǰ��Ϊ��ѡԶ����ʱ�䣬����Ϊ���趨������ʱ�䡣
 * 
 * �ο����ϣ�
 * https://blog.csdn.net/jeffleo/article/details/52175998
 * https://blog.csdn.net/lovexiaoxiao/article/details/8931391
 *
 * @author v592128947
 *
 *
 */

public class TimeAvailability {
	private int flag = 1;
	public int[] resultData = new int[4];//ÿһ��Զ��ÿ�����ߵ�������Դƽ����ȡ��
	
	public TimeAvailability(String idOfExpedition, int[] dataOfExpedition,int stringOfExpeditionTime,int stringOfSetTime) throws RowsExceededException, WriteException, IOException {//���췽��
		try {
			int flag = this.Calculate(stringOfExpeditionTime, stringOfSetTime);
			//System.out.println("ƽ��ÿ�����߻�ȡ��������Դ���ֱ�Ϊ��");
			//System.out.println( idOfExpedition + "��Զ��ƽ��ÿ�����߻�ȡ��������Դ���ֱ�Ϊ��");
			//System.out.println("0");
		for(int i = 0 ; i < dataOfExpedition.length ; i++){
			this.resultData[i] = (dataOfExpedition[i]/flag);
		}
		this.ReSet();
		//System.out.println("100");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//gbzkfhfa
	
	public int[] getResultData() {
		return this.resultData;
	}
	
	
	//ͨ��Calculate���������ÿ��Զ���ڼ�����ߵĴ�����֮������������������ɻ��ÿ�����ߵ�ƽ������
	public int Calculate(int stringOfExpeditionTime , int stringOfSetTime) throws ParseException {//����һ��Զ�����߼���
		//int flag = 1;
		
		/*
		System.out.println("Զ��ʱ��"+stringOfExpeditionTime);
		System.out.println("�趨ʱ��"+stringOfSetTime);
		
		Date timeOfExpeditionDate = timeFormat.parse(stringOfExpeditionTime);
		Date timeOfSetDate = timeFormat.parse(stringOfSetTime);
		
		String timeOfExpedition = timeFormat.format(timeOfExpeditionDate);
		String timeOfSet = timeFormat.format(timeOfSetDate);
		
		long timeOfExpeditionToCalculation = timeFormat.parse(timeOfExpedition).getTime()/(1000*60);
		long timeOfSetToCalculation = timeFormat.parse(timeOfSet).getTime()/(1000*60);
		//this.timeOfSet = timeOfSet;
		
		//System.out.println(iavi);
		*/
		
		if(stringOfExpeditionTime > stringOfSetTime) {
			//����ʱ���ֵ
			/*
			long iavi = (timeOfExpeditionToCalculation - timeOfSetToCalculation);
			long uijmiavi = timeFormat.parse("00:00").getTime()/(1000*60) + iavi;
			*/
			stringOfExpeditionTime -= stringOfSetTime;
			//System.out.println("���������Զ��ʱ�� " + timeOfExpeditionToCalculation + "����������趨ʱ��" + timeOfSetToCalculation);
			//timeOfExpeditionToCalculation = timeOfExpeditionToCalculation - timeOfSetToCalculation;
			
			flag++;
			
			//System.out.println("����֮��� " + timeFormat.format(uijmiavi*(1000*60)) + "�趨ʱ��" + timeFormat.format(timeOfSetToCalculation*(1000*60)) + "flag= " + flag);
			//System.err.println("�����Զ��ʱ��"+stringOfExpeditionTime);
			this.Calculate(stringOfExpeditionTime, stringOfSetTime );
		}
		return flag;
	}//Calculate(SimpleDateFormat timeOfExpedition,SimpleDateFormat timeOfSet)
	
	public void ReSet() {
		this.flag = 1;
	}
}
