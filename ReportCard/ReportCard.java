/**
  *this class represent student grade for course.
*/

public class ReportCard {
  private String mStudentName;
  private String mCourse;
  private String mSemester;
  private int mGrade;
  private int mStudentID;
  private int mYear;
  private char mLetterGrade;

  /**
   *constructor for the class.
  */
  public ReportCard(String studentName, String course, String semester,int id, int year, int grade) {
  	mStudentName = studentName;
  	mCourse = course;
  	mSemester = semester;
  	mStudentID = id;
  	mYear = year;
  	mGrade = grade;
  	setLetterGrade();
  }

  /**
	*convert numerical grade to letter grade.
	*/
  private void setLetterGrade() {
  	if(mGrade > 79){
	    mLetterGrade = 'A';
    } else if (mGrade > 69){
        mLetterGrade = 'B';
    } else if (mGrade > 59){
	    mLetterGrade = 'C';
	}  else if (mGrade > 49){
	    mLetterGrade = 'D';
	} else {
	    mLetterGrade = 'F';
	}
  }

  /*
   * Getter methods for states.
   */

  public String getStudentName() {
  	return mStudentName;
  }

  public String getCourseName() {
  	return mCourse;
  }

  public String getSemester() {
  	return mSemester;
  }

  public int getStudentId() {
  	return mStudentID;
  }

  public int getYear() {
  	return mYear;
  }

  public int getNumericalGrade() {
  	return mGrade;
  }

  public char getLetterGrade() {
  	return mLetterGrade;
  }

  /**
  	* Setter method for grade
  	*/
  public void setGrade(int grade) {
  	mGrade = grade;
  	setLetterGrade();
  }

   @Override
    public String toString() {
        return "ReportCard for Student: " + mStudentName + "\n" +
                "Student ID: " + mStudentID + "\n"+
                "Year: " + mYear + "\n"+ 
                "Semester: " + mSemester + "\n"+ 
                "Course: " + mCourse + "\n" +
                "Numerical grade: " + mGrade + "\n" +
                 "Letter grade= " + mLetterGrade;
    }

}
