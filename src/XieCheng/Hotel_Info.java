package XieCheng;

/*
 * 获取酒店前台数据用的
 * 酒店名称、链接、地址、排名、等级、用户评论数量、用户评价、酒店ID
 * 
 * */

public class Hotel_Info {
	private String title ,link , address , num , level , user_num , user_ok , id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUser_num() {
		return user_num;
	}

	public void setUser_num(String user_num) {
		this.user_num = user_num;
	}

	public String getUser_ok() {
		return user_ok;
	}

	public void setUser_ok(String user_ok) {
		this.user_ok = user_ok;
	}

	public String getAll()
	{
		return "酒店名称: "+getTitle()+"\n酒店链接: "+getLink()+"\n酒店地址: "+getAddress()+"\n酒店排名: "+getNum()+"\n酒店ID: "+getId()+"\n酒店评分(满分5分): "+getLevel()+"\n酒店推荐人数: "+getUser_ok()+"\n酒店参与评论人数: "+getUser_num()+"\n";
	}
	
}
