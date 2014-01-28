package pentagon.cfs.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class TestBean {
	int id;
	int a;
	int b;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
}
