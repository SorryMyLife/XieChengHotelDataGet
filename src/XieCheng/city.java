package XieCheng;

/*
 * 对接携程城市api
 * 可以直接获取城市ID、城市名称、城市英文
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
	
	public String getAll() //获取城市的所有内容
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
	
	public String getEn() //获取城市ID，仅限携程
	{
		return getAll_()[0];
	}
	
	public String getCity() //获取城市名称,不是必须的
	{
		str = getAll_()[1];
		return str;
	}
	
	public String getID() //获取城市ID,仅限携程
	{
		str = getAll_()[2];
		return str;
	}
	
	
}
