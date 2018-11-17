package XieCheng;

/*
 * Я��--�Ƶ���Ϣ�ɼ�
 * ���selenium���!
 * �Ѿ�д�����api�����Ե����ó���ʹ��
 * �˳����Ѿ�ģ�黯������������ĸ�ʹ��
 * */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class XieCheng extends Hotel {
	
	private Hotel_tool ht;
	private city c;
	private Hotel_Info_Detailed hid;
	
	public void sleep(int timeout)
	{
		try {
			new Thread().sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void city_list(ArrayList<String> list) //�Խ�ĳ����վapi����ȡ�������г���
	{
		str = "http://www.maps7.com/china_province.php";
		Hotel_tool ht = new Hotel_tool(str);
		tmp = ht.getCode();
		Matcher m1 = Pattern.compile("\">(.+?</a>)").matcher(tmp);
		while(m1.find())
		{
			if(m1.group().indexOf("��") != -1)
			{
				if(m1.group().indexOf("����") == -1)
				{
					tmp = m1.group().replaceAll("\">|</a>|h4>|<|/", "");
					list.add(tmp.replaceAll("��", ""));
				}
			}
		}
		
	}
	
	public String[] city_list_()
	{
		ArrayList<String> list = new ArrayList<>();
		city_list(list);
		int size = list.size();
		citylist = new String[size];
		for(int i = 0;i<size;i++)
		{
			citylist[i] = list.get(i);
		}
		return citylist;
	}
	
	public void write(String path , String data) //�Ѷ�ȡ���ݴ��뵽����
	{
		try {
			File f = new File(path);
			if(!f.exists())
			{
				f.mkdirs();
			}
			BufferedWriter fos = new BufferedWriter(new FileWriter(f+"/info.txt",true));
			fos.write(data);
			fos.newLine();
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void run_false(String city_name ) //���㲻�뽫��Ϣ��ŵ����ص�ʱ�򣬿��Բ������ѡ��
	{
		List<Detailed> str_list = new ArrayList<>();
		ArrayList<Hotel_Info> hotel_list = new ArrayList<>();
		c = new city(city_name);
		Hotel_tool ht2;
		int size = -1 , str_list_size = -1 , max_page = -1;
		str = hotel_url+c.getEn()+c.getID();
		ht = new Hotel_tool(str);
		ht.getAllList(hotel_list);
		max_page = Integer.parseInt(ht.getMaxPageNum());
		System.out.println("һ����: "+max_page+" ҳ");
		for(int i = 1;i<=max_page;i++)
		{
			str = hotel_url+c.getEn()+c.getID()+"/p"+i;
			 ht2 = new Hotel_tool(str);
			ht2.getAllList(hotel_list);
			System.out.println("�Ѿ����˵�: "+i+" ҳ");
		}
		size = hotel_list.size();
		System.out.println("\n�̵��Ѿ��洢���! : "+size+"\n");
		for(int i = 0;i<size;i++)
		{
			System.out.println(hotel_list.get(i).getAll());
			tmp = hotel_url+hotel_list.get(i).getId()+".html";
			hid = new Hotel_Info_Detailed(tmp);
			hid.getInfoDetailedListPro(str_list);
			str_list_size = str_list.size();
			for(int j = 0;j<str_list_size;j++)
			{
				System.out.println(str_list.get(j).getAll());
			}
		}
		
		
	}
	
	public void run(String path , String city_name ) //������Ҫ������Щ��Ϣ��ʱ������������
	{
		List<Detailed> str_list = new ArrayList<>();
		ArrayList<Hotel_Info> hotel_list = new ArrayList<>();
		c = new city(city_name);
		int size = -1 , str_list_size = -1 , max_page = -1;
		str = hotel_url+c.getEn()+c.getID();
		ht = new Hotel_tool(str);
		ht.getAllList(hotel_list);
		max_page = Integer.parseInt(ht.getMaxPageNum());
		System.out.println("һ����: "+max_page+" ҳ");
		for(int i = 1;i<=max_page;i++)
		{
			str = hotel_url+c.getEn()+c.getID()+"/p"+i;
			Hotel_tool ht2 = new Hotel_tool(str);
			ht2.getAllList(hotel_list);
			System.out.println("�Ѿ����˵�: "+i+" ҳ");
		}
		size = hotel_list.size();
		System.out.println("\n�̵��Ѿ��洢���! : "+size+"\n");
		for(int i = 0;i<size;i++)
		{
			System.out.println("��ʼд��: "+hotel_list.get(i).getTitle()+" ��������Ϣ������");
			this.write(path, hotel_list.get(i).getAll());
			tmp = hotel_url+hotel_list.get(i).getId()+".html";
			hid = new Hotel_Info_Detailed(tmp);
			hid.getInfoDetailedListPro(str_list);
			str_list_size = str_list.size();
			for(int j = 0;j<str_list_size;j++)
			{
				this.write(path, str_list.get(j).getAll());
			}
			System.out.println("д��: "+hotel_list.get(i).getTitle()+" ��������Ϣ������ok!");
		}
		
		
		
	}
	
	public void start(String path , boolean flag) //һ�д����￪ʼ,���flagΪtrue�򽫻�ȡ����Ϣ���浽���ط����ֱ�Ӵ�ӡ����
	{
		
		ArrayList<String> city_l = new ArrayList<>();
		city_list(city_l);
		int size = city_l.size();
		if(flag)
		{
			for(int i = 0;i<size;i++)
			{
				run(path , city_l.get(i));
			}
		}else
		{
			for(int i = 0;i<size;i++)
			{
				run_false(city_l.get(i));
			}
		}
	}
	
	
}
