package gradle_exam.dto;

import java.sql.Date;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private int gender;
	private Department dno;
	private Date hire_date;
	
	public Employee() {
	}

	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, int gender, Department dno,
			Date hire_date) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.gender = gender;
		this.salary = salary;
		this.dno = dno;
		this.hire_date = hire_date;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee [empNo=%s, empName=%s, title=%s, gender=%s, salary=%s, dno=%s, hire_date=%s]",
				empNo, empName, title, gender, salary, dno, hire_date);
	}

	public Object[] toArray() {
		return new Object[] {empNo, empName, title, String.format("%,d", salary), gender==0?"여자":"남자", dno, hire_date} ;
	}
	
}
