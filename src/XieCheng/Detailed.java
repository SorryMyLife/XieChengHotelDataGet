package XieCheng;

/*
 * 详细信息类
 * 用来存放用户评价、评论、住店时间以及住店类型
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
		return "酒店住客点评: "+getText()+"\n酒店住客评论: "+getNum()+"\n酒店住客入住时间: "+getTime()+"\n酒店住客入住类型: "+getType()+"\n";
	}
	
}
