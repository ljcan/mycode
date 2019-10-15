package cu.just.spark.domain;

public class GradeCount {
	private String grade;
	private Integer count;
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "GradeCount [grade=" + grade + ", count=" + count + "]";
	}
	

}
