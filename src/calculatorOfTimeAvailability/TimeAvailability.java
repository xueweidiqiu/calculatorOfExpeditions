package calculatorOfTimeAvailability;

import java.io.IOException;
import java.text.ParseException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 使用Calculate方法进行计算，传入参数为两个String类型的时间,格式为“hh:mm”
 * 前者为所选远征的时间，后者为所设定的上线时间。
 * 
 * 参考资料：
 * https://blog.csdn.net/jeffleo/article/details/52175998
 * https://blog.csdn.net/lovexiaoxiao/article/details/8931391
 *
 * @author v592128947
 *
 *
 */

public class TimeAvailability {
	private int flag = 1;
	public int[] resultData = new int[4];//每一个远征每次上线的四项资源平均获取量
	
	public TimeAvailability(String idOfExpedition, int[] dataOfExpedition,int stringOfExpeditionTime,int stringOfSetTime) throws RowsExceededException, WriteException, IOException {//构造方法
		try {
			int flag = this.Calculate(stringOfExpeditionTime, stringOfSetTime);
			//System.out.println("平均每次上线获取的四项资源量分别为：");
			//System.out.println( idOfExpedition + "号远征平均每次上线获取的四项资源量分别为：");
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
	
	
	//通过Calculate方法计算出每次远征期间会上线的次数，之后用收益除次数，即可获得每次上线的平均收益
	public int Calculate(int stringOfExpeditionTime , int stringOfSetTime) throws ParseException {//计算一次远征上线几次
		//int flag = 1;
		
		/*
		System.out.println("远征时间"+stringOfExpeditionTime);
		System.out.println("设定时间"+stringOfSetTime);
		
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
			//计算时间差值
			/*
			long iavi = (timeOfExpeditionToCalculation - timeOfSetToCalculation);
			long uijmiavi = timeFormat.parse("00:00").getTime()/(1000*60) + iavi;
			*/
			stringOfExpeditionTime -= stringOfSetTime;
			//System.out.println("用来计算的远征时间 " + timeOfExpeditionToCalculation + "用来计算的设定时间" + timeOfSetToCalculation);
			//timeOfExpeditionToCalculation = timeOfExpeditionToCalculation - timeOfSetToCalculation;
			
			flag++;
			
			//System.out.println("减完之后的 " + timeFormat.format(uijmiavi*(1000*60)) + "设定时间" + timeFormat.format(timeOfSetToCalculation*(1000*60)) + "flag= " + flag);
			//System.err.println("传入的远征时间"+stringOfExpeditionTime);
			this.Calculate(stringOfExpeditionTime, stringOfSetTime );
		}
		return flag;
	}//Calculate(SimpleDateFormat timeOfExpedition,SimpleDateFormat timeOfSet)
	
	public void ReSet() {
		this.flag = 1;
	}
}
