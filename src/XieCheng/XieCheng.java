package XieCheng;

/*
 * 携程--酒店信息采集
 * 结合selenium完成!
 * 已经写好相关api，可以单独拿出来使用
 * 此程序已经模块化，可以随意更改跟使用
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
	
	public void city_list(ArrayList<String> list) //对接某个网站api，获取国内所有城市
	{
		str = "http://www.maps7.com/china_province.php";
		Hotel_tool ht = new Hotel_tool(str);
		tmp = ht.getCode();
		Matcher m1 = Pattern.compile("\">(.+?</a>)").matcher(tmp);
		while(m1.find())
		{
			if(m1.group().indexOf("市") != -1)
			{
				if(m1.group().indexOf("各国") == -1)
				{
					tmp = m1.group().replaceAll("\">|</a>|h4>|<|/", "");
					list.add(tmp.replaceAll("市", ""));
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
	
	public void write(String path , String data) //把读取内容存入到本地
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
	
	public void run_false(String city_name ) //当你不想将信息存放到本地的时候，可以采用这个选项
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
		System.out.println("一共有: "+max_page+" 页");
		for(int i = 1;i<=max_page;i++)
		{
			str = hotel_url+c.getEn()+c.getID()+"/p"+i;
			 ht2 = new Hotel_tool(str);
			ht2.getAllList(hotel_list);
			System.out.println("已经到了第: "+i+" 页");
		}
		size = hotel_list.size();
		System.out.println("\n商店已经存储完成! : "+size+"\n");
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
	
	public void run(String path , String city_name ) //当你需要保存这些信息的时候，你可以用这个
	{
		List<Detailed> str_list = new ArrayList<>();
		ArrayList<Hotel_Info> hotel_list = new ArrayList<>();
		c = new city(city_name);
		int size = -1 , str_list_size = -1 , max_page = -1;
		str = hotel_url+c.getEn()+c.getID();
		ht = new Hotel_tool(str);
		ht.getAllList(hotel_list);
		max_page = Integer.parseInt(ht.getMaxPageNum());
		System.out.println("一共有: "+max_page+" 页");
		for(int i = 1;i<=max_page;i++)
		{
			str = hotel_url+c.getEn()+c.getID()+"/p"+i;
			Hotel_tool ht2 = new Hotel_tool(str);
			ht2.getAllList(hotel_list);
			System.out.println("已经到了第: "+i+" 页");
		}
		size = hotel_list.size();
		System.out.println("\n商店已经存储完成! : "+size+"\n");
		for(int i = 0;i<size;i++)
		{
			System.out.println("开始写入: "+hotel_list.get(i).getTitle()+" 的评论信息到本地");
			this.write(path, hotel_list.get(i).getAll());
			tmp = hotel_url+hotel_list.get(i).getId()+".html";
			hid = new Hotel_Info_Detailed(tmp);
			hid.getInfoDetailedListPro(str_list);
			str_list_size = str_list.size();
			for(int j = 0;j<str_list_size;j++)
			{
				this.write(path, str_list.get(j).getAll());
			}
			System.out.println("写入: "+hotel_list.get(i).getTitle()+" 的评论信息到本地ok!");
		}
		
		
		
	}
	
	public void start(String path , boolean flag) //一切从这里开始,如果flag为true则将获取的信息保存到本地否则就直接打印出来
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
