package XieCheng;

/*
 * 这个工具是仅限于携程的
 * 使用起来很是方便
 * 输入网址进去就可以使用了
 * 
 * */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel_tool extends Hotel{
	private String url_name;
	public Hotel_tool(String url_name) {
		// TODO Auto-generated constructor stub
		this.url_name = url_name;
	}
	
	public HttpURLConnection check() throws Exception //配置网络链接以及配置一些必要参数
	{
		HttpURLConnection con = (HttpURLConnection) new URL(url_name).openConnection();
		if(url_name.indexOf("http") == -1)
		{
			url_name = "https://"+url_name;
		}
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		con.setRequestProperty("Accept-Encoding", "deflate");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Content-Encoding", "gzip");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
	
		
		
		return con;
	}
	
	public String getCode() //获取网页源码
	{
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(check().getInputStream(),"utf-8"));
			while((str = br.readLine()) != null)
			{
				tmp += str +"\n";
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	
	public String getMaxPageNum() //获取最大页数
	{
		line = getCode();
		Matcher m = Pattern.compile("\\d*</a></div><a").matcher(line);
		if(m.find())
		{
			tmp = m.group().replaceAll("</a></div><a", replacement);
		}
		return tmp;
	}
	
	public void getAll() //获取某个地区当前页面的所有酒店信息
	{
		str = getCode();
		hotel_info = new Hotel_Info();
		Matcher m1 = Pattern.compile("haspic\" title=\"(.+?\")").matcher(str);
		Matcher m2 = Pattern.compile("data-hotel=\"(.+?\")").matcher(str);
		Matcher m3 = Pattern.compile("htladdress\">(.+?\\<a)").matcher(str);
		Matcher m4 = Pattern.compile("num\">(.+?<)").matcher(str);
		Matcher m5 = Pattern.compile("hotel_value\">(.+?<)").matcher(str);
		Matcher m6 = Pattern.compile("00;'>(.+?<)").matcher(str);
		Matcher m7 = Pattern.compile("9933;\'>(.+?用户推荐)").matcher(str);
		while(m1.find()&&m2.find()&&m3.find()&&m4.find()&&m5.find()&&m6.find()&&m7.find())
		{
			hotel_title = m1.group().replaceAll("haspic\" title=\"|\"", replacement);
			hotel_id = m2.group().replaceAll("data-hotel=\"|\"", replacement);
			if(hotel_id.indexOf("hotel") == -1)
			{
				hotel_link = hotel_url+hotel_id;
				hotel_address = m3.group().replaceAll("htladdress\">|<a", replacement);
				hotel_num = m4.group().replaceAll("num\">|<", replacement);
				if(hotel_num.indexOf("400") == -1&&hotel_num.indexOf("$") ==-1)
				{
					hotel_level = m5.group().replaceAll("hotel_value\">|<", replacement);
					user_num = m6.group().replaceAll("00;'>|<", replacement);
					user_ok = m7.group().replaceAll("9933;\'>|</span>用户推荐", replacement);
					hotel_info.setTitle(hotel_title);
					hotel_info.setId(hotel_id);
					hotel_info.setLink(hotel_link);
					hotel_info.setAddress(hotel_address);
					hotel_info.setNum(hotel_num);
					hotel_info.setLevel(hotel_level);
					hotel_info.setUser_num(user_num);
					hotel_info.setUser_ok(user_ok);
				}
			}
			if(hotel_info.getAddress() != null)
			{
				System.out.println(hotel_info.getAll());
			}
			
		}
	}
	
	public void getAllList(List<Hotel_Info> list)
	{
		str = getCode();
		Matcher m1 = Pattern.compile("haspic\" title=\"(.+?\")").matcher(str);
		Matcher m2 = Pattern.compile("data-hotel=\"(.+?\")").matcher(str);
		Matcher m3 = Pattern.compile("htladdress\">(.+?\\<a)").matcher(str);
		Matcher m4 = Pattern.compile("num\">(.+?<)").matcher(str);
		Matcher m5 = Pattern.compile("hotel_value\">(.+?<)").matcher(str);
		Matcher m6 = Pattern.compile("00;'>(.+?<)").matcher(str);
		Matcher m7 = Pattern.compile("9933;\'>(.+?用户推荐)").matcher(str);
		while(m1.find()&&m2.find()&&m3.find()&&m4.find()&&m5.find()&&m6.find()&&m7.find())
		{
			hotel_info = new Hotel_Info();
			hotel_title = m1.group().replaceAll("haspic\" title=\"|\"", replacement);
			hotel_id = m2.group().replaceAll("data-hotel=\"|\"", replacement);
			if(hotel_id.indexOf("hotel") == -1)
			{
				hotel_link = hotel_url+hotel_id;
				hotel_address = m3.group().replaceAll("htladdress\">|<a", replacement);
				hotel_num = m4.group().replaceAll("num\">|<", replacement);
				if(hotel_num.indexOf("400") == -1&&hotel_num.indexOf("$") ==-1)
				{
					hotel_level = m5.group().replaceAll("hotel_value\">|<", replacement);
					user_num = m6.group().replaceAll("00;'>|<", replacement);
					user_ok = m7.group().replaceAll("9933;\'>|</span>用户推荐", replacement);
					hotel_info.setTitle(hotel_title);
					hotel_info.setId(hotel_id);
					hotel_info.setLink(hotel_link);
					hotel_info.setAddress(hotel_address);
					hotel_info.setNum(hotel_num);
					hotel_info.setLevel(hotel_level);
					hotel_info.setUser_num(user_num);
					hotel_info.setUser_ok(user_ok);
				}
			}
			if(hotel_info.getAddress() != null)
			{
				list.add(hotel_info);
			}
		}
	}
	
	
	
}
