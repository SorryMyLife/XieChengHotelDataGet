package XieCheng;

/*
 * ��ȡ�Ƶ�ǰ̨�����õ�
 * �Ƶ����ơ����ӡ���ַ���������ȼ����û������������û����ۡ��Ƶ�ID
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
		return "�Ƶ�����: "+getTitle()+"\n�Ƶ�����: "+getLink()+"\n�Ƶ��ַ: "+getAddress()+"\n�Ƶ�����: "+getNum()+"\n�Ƶ�ID: "+getId()+"\n�Ƶ�����(����5��): "+getLevel()+"\n�Ƶ��Ƽ�����: "+getUser_ok()+"\n�Ƶ������������: "+getUser_num()+"\n";
	}
	
}
