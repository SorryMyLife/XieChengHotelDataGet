package XieCheng;

/*
 * 获取携程酒店里的评论信息
 * 需要selenium支持
 * 
 * */

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Hotel_Info_Detailed {
	private Detailed dt;
	private String url_name , ss= ""  , max_p , tmp = " ";
	private WebDriver wd;
	
	public Hotel_Info_Detailed(String url_name) {
		// TODO Auto-generated constructor stub
		this.url_name = url_name;
	}
	
	public String check() //配置链接，以防传入错误的链接
	{
		
		ss = url_name;
		if(url_name.indexOf("http") == -1)
		{
			url_name = "https://"+url_name;
			if(url_name.indexOf("/p") == -1)
			{
				ss = url_name+"/p0";
			}else
			{
				ss =url_name;
			}
		}
		return ss;
	}
	
	public void sleep(int timeout) //休眠
	{
		try {
			new Thread().sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void next(WebDriver driver) //自动向下滑动
	{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void getInfoDetailed() //获取信息
	{
		try {
			wd = check_driver();
			wd.get(check());
			sleep(1000);
			ss = wd.getPageSource();
			wd.quit();
			max_ppp(ss);
			Matcher m1 = Pattern.compile("data-value=\"位置(.+?>)").matcher(ss);
			Matcher m2 = Pattern.compile("Detail\">(.+?<)").matcher(ss);
			Matcher m3 = Pattern.compile("date\">(.+?<)").matcher(ss);
			Matcher m4 = Pattern.compile("roomname=\"(.+?</a>)").matcher(ss);
			while(m1.find() && m2.find() &&m3.find() &&m4.find())
			{
				dt = new Detailed();
				dt.setNum(m2.group(0).replaceAll("Detail\">|<", "").replaceAll(" ", ","));
				dt.setText(m1.group().replaceAll("data-value=\"|\">", ""));
				dt.setTime(m3.group().replaceAll("date\">|<", ""));
				dt.setType(m4.group().replaceAll("roomname=\"|</a>", "").replaceAll("\">", "-"));
				System.out.println(dt.getAll());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void getInfoDetailedList(List<Detailed> list)
	{
		try {
			wd = check_driver();
			wd.get(check());
			sleep(1000);
			ss = wd.getPageSource();
			max_ppp(ss);
			wd.quit();
			Matcher m1 = Pattern.compile("data-value=\"位置(.+?>)").matcher(ss);
			Matcher m2 = Pattern.compile("Detail\">(.+?<)").matcher(ss);
			Matcher m3 = Pattern.compile("date\">(.+?<)").matcher(ss);
			Matcher m4 = Pattern.compile("roomname=\"(.+?</a>)").matcher(ss);
			while(m1.find() && m2.find() &&m3.find() &&m4.find())
			{
				dt = new Detailed();
				dt.setNum(m2.group(0).replaceAll("Detail\">|<", "").replaceAll(" ", ","));
				dt.setText(m1.group().replaceAll("data-value=\"|\">", ""));
				dt.setTime(m3.group().replaceAll("date\">|<", ""));
				dt.setType(m4.group().replaceAll("roomname=\"|</a>", "").replaceAll("\">", "-"));
				list.add(dt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void getInfoDetailedText(String text) //获取信息
	{
		ss = text;
		Matcher m1 = Pattern.compile("data-value=\"位置(.+?>)").matcher(ss);
		Matcher m2 = Pattern.compile("Detail\">(.+?<)").matcher(ss);
		Matcher m3 = Pattern.compile("date\">(.+?<)").matcher(ss);
		Matcher m4 = Pattern.compile("roomname=\"(.+?</a>)").matcher(ss);
		while(m1.find() && m2.find() &&m3.find() &&m4.find())
		{
			dt = new Detailed();
			dt.setNum(m2.group(0).replaceAll("Detail\">|<", "").replaceAll(" ", ","));
			dt.setText(m1.group().replaceAll("data-value=\"|\">", ""));
			dt.setTime(m3.group().replaceAll("date\">|<", ""));
			dt.setType(m4.group().replaceAll("roomname=\"|</a>", "").replaceAll("\">", "-"));
			System.out.println(dt.getAll());
		}
		
	}
	
	public void getInfoDetailedTextList(String text , List<Detailed> list) //获取信息
	{
		ss = text;
		Matcher m1 = Pattern.compile("data-value=\"位置(.+?>)").matcher(ss);
		Matcher m2 = Pattern.compile("Detail\">(.+?<)").matcher(ss);
		Matcher m3 = Pattern.compile("date\">(.+?<)").matcher(ss);
		Matcher m4 = Pattern.compile("roomname=\"(.+?</a>)").matcher(ss);
		while(m1.find() && m2.find() &&m3.find() &&m4.find())
		{
			dt = new Detailed();
			dt.setNum(m2.group(0).replaceAll("Detail\">|<", "").replaceAll(" ", ","));
			dt.setText(m1.group().replaceAll("data-value=\"|\">", ""));
			dt.setTime(m3.group().replaceAll("date\">|<", ""));
			dt.setType(m4.group().replaceAll("roomname=\"|</a>", "").replaceAll("\">", "-"));
			list.add(dt);
		}
		
	}
	
	public void getInfoDetailedPro() //getInfoDetailed的升级版，比原来更加强大
	{
		try {
			wd = check_driver();
			Actions ac = new Actions(wd);
			wd.get(url_name);
			tmp = wd.getPageSource();
			max_ppp(tmp);
			if(max_p == null)
			{
				getInfoDetailedText(tmp);
				getInfoDetailedLow(tmp);
			}else
			{
				for(int i = 0;i<Integer.parseInt(max_p);i++)
				{
					next(wd);
					tmp = wd.getPageSource();
					getInfoDetailedText(tmp);
					getInfoDetailedLow(tmp);
					ac.clickAndHold(wd.findElement(By.partialLinkText("下一页"))).click().perform();
					sleep(1000);
				}
			}
			wd.quit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			wd.quit();
		}
	}
	
	public void getInfoDetailedListPro( List<Detailed> list) //getInfoDetailedList的升级版，比原来更加强大
	{
		try {
			wd = check_driver();
			Actions ac = new Actions(wd);
			wd.get(url_name);
			tmp = wd.getPageSource();
			max_ppp(tmp);
			if(max_p == null)
			{
				getInfoDetailedTextList(tmp,list);
				getInfoDetailedLowList(tmp, list);
			}else
			{
				for(int i = 0;i<Integer.parseInt(max_p);i++)
				{
					next(wd);
					tmp = wd.getPageSource();
					getInfoDetailedTextList(tmp,list);
					getInfoDetailedLowList(tmp, list);
					ac.clickAndHold(wd.findElement(By.partialLinkText("下一页"))).click().perform();
					sleep(1000);
				}
				
			}
			wd.quit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			wd.quit();
		}
	}
	
	public void getInfoDetailedLow(String text) //获取普通用户信息
	{
//		wd = check_driver();
//		wd.get(check());
//		next(wd);
//		new Actions(wd).clickAndHold(wd.findElement(By.partialLinkText("下一页"))).click().perform();
//		sleep(1000);
//		String text = wd.getPageSource();
//		wd.quit();
		Matcher m1 = Pattern.compile("<span>(.+?用户</span>)").matcher(text);
		Matcher m2 = Pattern.compile("Detail\">(.+?<)").matcher(text);
		Matcher m3 = Pattern.compile("发表于(.+?<)").matcher(text);
		while(m1.find() && m2.find()&&m3.find())
		{
			dt = new Detailed();
			dt.setType(m1.group().replaceAll("<span>|</span>", ""));
			dt.setNum(m2.group().replaceAll("Detail\">|<", ""));
			dt.setTime(m3.group(0).replaceAll("<", ""));
			dt.setText("无");
			System.out.println(dt.getAll());
//			System.out.println(m3.group());
		}
	}
	
	public void getInfoDetailedLowList(String text ,List<Detailed> list )
	{
//		wd = check_driver();
//		wd.get(check());
//		next(wd);
//		new Actions(wd).clickAndHold(wd.findElement(By.partialLinkText("下一页"))).click().perform();
//		sleep(1000);
//		String text = wd.getPageSource();
//		wd.quit();
		Matcher m1 = Pattern.compile("<span>(.+?用户</span>)").matcher(text);
		Matcher m2 = Pattern.compile("Detail\">(.+?<)").matcher(text);
		Matcher m3 = Pattern.compile("发表于(.+?<)").matcher(text);
		while(m1.find() && m2.find()&&m3.find())
		{
			dt = new Detailed();
			dt.setType(m1.group().replaceAll("<span>|</span>", ""));
			dt.setNum(m2.group().replaceAll("Detail\">|<", ""));
			dt.setTime(m3.group(0).replaceAll("<", ""));
			dt.setText("无");
//			System.out.println(dt.getAll());
			list.add(dt);
//			System.out.println(m3.group());
		}
	}
	
	public void max_ppp(String text) //获取该网页的最大页数
	{
		Matcher max_reg = Pattern.compile("<span>\\d*</span></a></div>").matcher(text);
		if(max_reg.find())
		{
			max_p = max_reg.group(0).replaceAll("<span>|</span></a></div>", "");
		}
	}

	public WebDriver check_driver() //配置selenium
	{
		System.setProperty("webdriver.chrome.driver", "E:\\Java\\chromedriver_win32\\chromedriver.exe"); //配置webdriver类型跟driver驱动路径
		WebDriver wd = new ChromeDriver();
		return wd;
	}

	public String getMaxPage()
	{
		return max_p;
	}
}
