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
 * ��������д���µ�excel������
 * @author v592128947
 *
 */

public class WriteExcel {
	WritableWorkbook wwb;
	WritableSheet wws;
	
	int dataArrange = 1;
	
	//Ԥ�ڵ���Դ��ȡ����
	int FuelProportion; int AmmoProportion; int SteelProportion; int BauxiteProportion;
	
	/*
	//������
	public static void main(String args[]) throws RowsExceededException, WriteException, IOException {
		WriteExcel test = new WriteExcel();
		test.SetProportion(1, 1, 1, 3);
		test.Write(100,100,100,100);
		test.Write(20, 50, 50, 400);
		test.CloseExcel();
	}
	*/
	
	public WriteExcel() {//���췽��
		try {
			//�����ļ�
			wwb = Workbook.createWorkbook(new File("������.xls"));
			//System.out.println("��������ļ����");
			
			//����������
			wws = wwb.createSheet("������", 0);
			//System.out.println("�������������");
			
			this.Typesetting();//��ͷ�Ű�
			SetProportion(1,1,1,1);//��¼����
			
			//�����ļ�
			//wwb.write();// д������
			//wwb.close();// �ر��ļ�
			//System.out.println("д�뵽�ļ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}//���췽��
	
	public void Typesetting() throws RowsExceededException, WriteException {//����ͷ��Ϣ����
		Label id = new Label(0,0,"id");
		Label name = new Label(1,0,"Զ������");
		wws.addCell(id);wws.addCell(name);
		Label Fuel = new Label(2,0,"ȼ��/��");
		Label Ammo = new Label(3,0,"��ҩ/��");
		Label Steel = new Label(4,0,"�ֲ�/��");
		Label Bauxite = new Label(5,0,"����/��");
		wws.addCell(Fuel);wws.addCell(Ammo);wws.addCell(Steel);wws.addCell(Bauxite);
		Label Evaluation = new Label(10,0,"����");
		wws.addCell(Evaluation);
	}//Typesetting
	
	//��ȡ�趨��������ӱ�ͷ
	public void SetProportion(int FuelProportion , int AmmoProportion , int SteelProportion , int BauxiteProportion) throws RowsExceededException, WriteException {
		this.FuelProportion = FuelProportion;
		this.AmmoProportion = AmmoProportion;
		this.SteelProportion = SteelProportion;
		this.BauxiteProportion = BauxiteProportion;
		Label cellFuelProportion = new Label(6,0,"ȼ��Ԥ��ռ�ȣ�" + FuelProportion);
		Label cellAmmoProportion = new Label(7,0,"��ҩԤ��ռ�ȣ�" + AmmoProportion);
		Label cellSteelProportion = new Label(8,0,"�ֲ�Ԥ��ռ�ȣ�" + SteelProportion);
		Label cellBauxiteProportion = new Label(9,0,"����Ԥ��ռ�ȣ�" + BauxiteProportion);
		wws.addCell(cellFuelProportion);wws.addCell(cellAmmoProportion);wws.addCell(cellSteelProportion);wws.addCell(cellBauxiteProportion);
	}
	
	public void Write(String id , String name ,int Fuel , int Ammo , int Steel , int Bauxite) throws RowsExceededException, WriteException, IOException {
		//д��id������
		Label cellOfId = new Label(0 , dataArrange , id);
		Label cellOfName = new Label(1 , dataArrange , name);
		wws.addCell(cellOfId);wws.addCell(cellOfName);
		
		//Զ����Դƽ����ȡ��
		Number cellOfFuel = new Number(2 , dataArrange , Fuel);
		Number cellOfAmmo = new Number(3 , dataArrange , Ammo);
		Number cellOfSteel = new Number(4 , dataArrange , Steel);
		Number cellOfBauxite = new Number(5 , dataArrange , Bauxite);
		wws.addCell(cellOfFuel);wws.addCell(cellOfAmmo);wws.addCell(cellOfSteel);wws.addCell(cellOfBauxite);
		//�������������
		Number cellOfFuelProportion = new Number(6 , dataArrange , Fuel*FuelProportion);
		Number cellOfAmmoProportion = new Number(7 , dataArrange , Ammo*AmmoProportion);
		Number cellOfSteelProportion = new Number(8 , dataArrange , Steel*SteelProportion);
		Number cellOfBauxiteProportion = new Number(9 , dataArrange , Bauxite*BauxiteProportion);
		wws.addCell(cellOfFuelProportion);wws.addCell(cellOfAmmoProportion);wws.addCell(cellOfSteelProportion);wws.addCell(cellOfBauxiteProportion);
		//д�����۷���
		//Formula formaul = new Formula(10 , dataArrange , "Sum(G"+ dataArrange +":J" + dataArrange + ")");
		//wws.addCell(formaul);
		
		dataArrange ++;
		//System.err.println(dataArrange);
	}//Write
	
	public void CloseExcel() throws WriteException, IOException {
		//�����ļ�
		wwb.write();// д������
		wwb.close();// �ر��ļ�
		//System.out.println("�ر��ļ�");
	}
}
