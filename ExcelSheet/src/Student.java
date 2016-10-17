
public class Student {

	private String name;
	private int id;
	private int marks;
	private String notes;
	private String email;
	public Student() {}
	public  Student(String name,int id,int marks,String notes,String email)
	{
		this.notes = notes;
		this.name = name;
		this.marks = marks;
		this.notes = notes;
		this.email = email;
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", marks=" + marks + ", notes=" + notes + ", email=" + email
				+ "]";
	}
	
	

}
