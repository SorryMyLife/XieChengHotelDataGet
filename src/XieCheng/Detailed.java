package XieCheng;

/*
 * ��ϸ��Ϣ��
 * ��������û����ۡ����ۡ�ס��ʱ���Լ�ס������
 * */

public class Detailed {
	private String num , text , time , type;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getAll()
	{
		return "�Ƶ�ס�͵���: "+getText()+"\n�Ƶ�ס������: "+getNum()+"\n�Ƶ�ס����סʱ��: "+getTime()+"\n�Ƶ�ס����ס����: "+getType()+"\n";
	}
	
}
