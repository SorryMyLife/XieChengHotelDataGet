package XieCheng;

/*
 * �Խ�Я�̳���api
 * ����ֱ�ӻ�ȡ����ID���������ơ�����Ӣ��
 * */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class city {
	
	private Hotel_tool ht;
	private String str = " " , name;
	
	public city(String name ) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public String getAll() //��ȡ���е���������
	{
		ht = new Hotel_tool("http://hotels.ctrip.com/Domestic/Tool/AjaxGetCitySuggestion.aspx");
		Matcher m1 = Pattern.compile(name+"\",data:\"(.+?\",)").matcher(ht.getCode());
		if(m1.find())
		{
//			System.out.println(m1.group().replaceAll(name+"\",data:\"|\",", ""));
			str = m1.group(0).replaceAll(name+"\",data:\"|\",", "");
		}
		return str;
		
	}
	
	public String[] getAll_()
	{
		String s[] = getAll().split("\\|");
		return s;	
	}
	
	public String getEn() //��ȡ����ID������Я��
	{
		return getAll_()[0];
	}
	
	public String getCity() //��ȡ��������,���Ǳ����
	{
		str = getAll_()[1];
		return str;
	}
	
	public String getID() //��ȡ����ID,����Я��
	{
		str = getAll_()[2];
		return str;
	}
	
	
}
