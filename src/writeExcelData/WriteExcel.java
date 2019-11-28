package writeExcelData;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 将计算结果写入新的excel工作表
 * @author v592128947
 *
 */

public class WriteExcel {
	WritableWorkbook wwb;
	WritableSheet wws;
	
	int dataArrange = 1;
	
	//预期的资源获取比例
	int FuelProportion; int AmmoProportion; int SteelProportion; int BauxiteProportion;
	
	/*
	//测试用
	public static void main(String args[]) throws RowsExceededException, WriteException, IOException {
		WriteExcel test = new WriteExcel();
		test.SetProportion(1, 1, 1, 3);
		test.Write(100,100,100,100);
		test.Write(20, 50, 50, 400);
		test.CloseExcel();
	}
	*/
	
	public WriteExcel() {//构造方法
		try {
			//创建文件
			wwb = Workbook.createWorkbook(new File("计算结果.xls"));
			//System.out.println("创建或打开文件完成");
			
			//创建工作表
			wws = wwb.createSheet("计算结果", 0);
			//System.out.println("创建工作簿完成");
			
			this.Typesetting();//表头排版
			SetProportion(1,1,1,1);//记录比重
			
			//更新文件
			//wwb.write();// 写入数据
			//wwb.close();// 关闭文件
			//System.out.println("写入到文件");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}//构造方法
	
	public void Typesetting() throws RowsExceededException, WriteException {//将表头信息填入
		Label id = new Label(0,0,"id");
		Label name = new Label(1,0,"远征名称");
		wws.addCell(id);wws.addCell(name);
		Label Fuel = new Label(2,0,"燃料/次");
		Label Ammo = new Label(3,0,"弹药/次");
		Label Steel = new Label(4,0,"钢材/次");
		Label Bauxite = new Label(5,0,"铝土/次");
		wws.addCell(Fuel);wws.addCell(Ammo);wws.addCell(Steel);wws.addCell(Bauxite);
		Label Evaluation = new Label(10,0,"评价");
		wws.addCell(Evaluation);
	}//Typesetting
	
	//获取设定比例及添加表头
	public void SetProportion(int FuelProportion , int AmmoProportion , int SteelProportion , int BauxiteProportion) throws RowsExceededException, WriteException {
		this.FuelProportion = FuelProportion;
		this.AmmoProportion = AmmoProportion;
		this.SteelProportion = SteelProportion;
		this.BauxiteProportion = BauxiteProportion;
		Label cellFuelProportion = new Label(6,0,"燃料预期占比：" + FuelProportion);
		Label cellAmmoProportion = new Label(7,0,"弹药预期占比：" + AmmoProportion);
		Label cellSteelProportion = new Label(8,0,"钢材预期占比：" + SteelProportion);
		Label cellBauxiteProportion = new Label(9,0,"铝土预期占比：" + BauxiteProportion);
		wws.addCell(cellFuelProportion);wws.addCell(cellAmmoProportion);wws.addCell(cellSteelProportion);wws.addCell(cellBauxiteProportion);
	}
	
	public void Write(String id , String name ,int Fuel , int Ammo , int Steel , int Bauxite) throws RowsExceededException, WriteException, IOException {
		//写入id和名称
		Label cellOfId = new Label(0 , dataArrange , id);
		Label cellOfName = new Label(1 , dataArrange , name);
		wws.addCell(cellOfId);wws.addCell(cellOfName);
		
		//远征资源平均获取量
		Number cellOfFuel = new Number(2 , dataArrange , Fuel);
		Number cellOfAmmo = new Number(3 , dataArrange , Ammo);
		Number cellOfSteel = new Number(4 , dataArrange , Steel);
		Number cellOfBauxite = new Number(5 , dataArrange , Bauxite);
		wws.addCell(cellOfFuel);wws.addCell(cellOfAmmo);wws.addCell(cellOfSteel);wws.addCell(cellOfBauxite);
		//按比例计算分数
		Number cellOfFuelProportion = new Number(6 , dataArrange , Fuel*FuelProportion);
		Number cellOfAmmoProportion = new Number(7 , dataArrange , Ammo*AmmoProportion);
		Number cellOfSteelProportion = new Number(8 , dataArrange , Steel*SteelProportion);
		Number cellOfBauxiteProportion = new Number(9 , dataArrange , Bauxite*BauxiteProportion);
		wws.addCell(cellOfFuelProportion);wws.addCell(cellOfAmmoProportion);wws.addCell(cellOfSteelProportion);wws.addCell(cellOfBauxiteProportion);
		//写入评价分数
		//Formula formaul = new Formula(10 , dataArrange , "Sum(G"+ dataArrange +":J" + dataArrange + ")");
		//wws.addCell(formaul);
		
		dataArrange ++;
		//System.err.println(dataArrange);
	}//Write
	
	public void CloseExcel() throws WriteException, IOException {
		//更新文件
		wwb.write();// 写入数据
		wwb.close();// 关闭文件
		//System.out.println("关闭文件");
	}
}
