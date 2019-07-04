package gradle_exam.dto;

public class Title {
	private int titleNo;
	private String titleName;
	
	public Title() {
	}

	public Title(int titleNo) {
		this.titleNo = titleNo;
	}

	public Title(int titleNo, String titleName) {
		this.titleNo = titleNo;
		this.titleName = titleName;
	}

	public int getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public String toString() {
		return String.format("%s(%d)", titleName, titleNo);
	}

	public Object[] toArray() {
		return new Object[] {String.format("T%03d", titleNo), titleName};
	}
	
}
