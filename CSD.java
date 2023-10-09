package studentmanagement;



import java.util.*;
public class CSD {
	private ChairPerson chair;
	private List<Faculty> faculties = new ArrayList<Faculty>();
	private List<UGrad> ugrads = new ArrayList<UGrad>();
	private List<Grad> grads = new ArrayList<Grad>();

	/**this is the constructor for class CSD
	 * @param chair	chair is an object of type Chairperson
	 **/
	public CSD(ChairPerson chair) {
		this.chair = chair; 
	}
	/**this is setter method 
	 * @param chair	chair an object of Chairperson class
	 * 
	 **/
	public void setChairPerson(ChairPerson chair) {
		this.chair = chair;
	}
	/**this is the getter method
	 * @returns chair an object of Chairperson
	 *
	 **/

	public ChairPerson getChairPerson() {
		return chair;
	}
	/**@param f is an object of class Faculty
	 * maxfaculty is the maximum number of faculty members
	 * Check for any duplicate and interate through the loop completely
	 * Add faculty members if there is space left in the list and if not then
	 * @throws NoSpaceException
	 * 
	 * 
	 */

	public void HireFaculty(Faculty f) throws NoSpaceException {
		int maxFaculty = 70;
		// check for duplicates
		// if duplicate faculty is there then do not add just return
		for(Faculty faculty : faculties) {
			if(faculty.equals(f)) return;
		}

		int facultysize = faculties.size();
		if(facultysize < maxFaculty) {
			faculties.add(f);
		}
		else throw new NoSpaceException();

	}
	/**this getter method 
	 * @return faculties faculties is a List of type Faculty class.
	 */

	public List<Faculty> getFaculty() {
		return faculties;
	}

	/**this is the getter that 
	 * @return size of faculty list`
	 */
	public Integer getNumOfFaculty() {
		
		return faculties.size();
	}
/**this method checks the number of student by iterating over a list for every index/iteration
 * @param ug ug is an object of UGrad(Undergraduate) class
 * @throws NoSpaceException when the maximum number of studets are already admitted is 
 *  */
	public void AdmitStudent(UGrad ug) throws NoSpaceException {
		int maxugrads = 500;
		// check duplicate 
		// if duplicate student is there then do not add just return
		for(UGrad uGrad : ugrads) {
			if(uGrad.equals(ug)) return;
		}

		int ugradsize = ugrads.size();
		if(ugradsize < maxugrads) {
			ugrads.add(ug);
			for(Faculty faculty : faculties) {
				if(faculty.addUgrads(ug)) {
					ug.setAdvisor(faculty);
					return;
				}
			}
		}

		else throw new NoSpaceException();

	}

	/**this is the getter that 
	 * @return size of ugrad list which gives us number of undergradraduate students`
	 */
	public Integer getNumOfUGradStudents() {
		return ugrads.size();
	}
	/**
	 * this is the method that hires graduate students as TAs.
	 * the method traverses throughout the list by using for each loop
	 * then it checks fro duplication equals() an obligatory method.
	 * @param g g is an object of Graduate/Grad class.
	 * @throws NoSpaceException
	 */

	public void HireTA(Grad g) throws NoSpaceException {	
		int maxgrads = 150;
		// check duplicate 
		// if duplicate student is there then do not add just return
		for(Grad grad : grads) {
			if(grad.equals(g)) return;
		}

		int gradsize = grads.size();
		if(gradsize < maxgrads) {
			grads.add(g);
			for(Faculty faculty : faculties) {
				if(faculty.addTAs(g)) {
					g.setAdvisor(faculty);
					return;
				}
			}
		}

		else throw new NoSpaceException();
	}
	/**
	 * @return size of graduate list
	 */

	public Integer getNumOfGradStudents() {
		return grads.size();
	}
/**
 * this method first check if the undergraduates are removed or not once they graduate
 * if they are then we remove them from their respective faculties too.
 * @param ug is an object of class UGrad
 */
	public void AlumnusUGrad(UGrad ug) {
		boolean removeug = ugrads.remove(ug);
		if(removeug) {
			Faculty f = ug.getAdvisor();
			f.removeUgrads(ug);
		}
	}
/**
 * this method first check if the graduates are removed or not once they graduate
 * if they are then we remove them from their respective faculties by:
 * removing them from  faculties by using methods that remove them from their TA position
 * 
 * @param g is the object of Grad class.
 * @throws NoTAException NoTAException is thrown when the faculty does not have enough TAs.
 */
	public void AlumnusGrad(Grad g) throws NoTAException {
		boolean removeg = grads.remove(g);
		if(removeg) {
			Faculty f = g.getAdvisor();
			f.removeTAs(g);
		}

	}
/**Sorts the Grad students using .toArray() and .sort() method
 * @return sortgrads sortgrads is a List of type Grad which contains Grad students sorted alphabeticaly.
 */
	public List<Grad> ExtractAllGradDetails() {
		List<Grad> sortgrads = new ArrayList<Grad>();

		Object[] obj = grads.toArray();
		Arrays.sort(obj);

		for(int i = 0; i < obj.length; i++) {
			Grad g = (Grad)obj[i];
			sortgrads.add(g);
		}

		return sortgrads;
	}
	/**Sorts the undergrad student using .toArray() and .sort() method
	 * @return sortgrads sortgrads is a List of type UGrad which contains Grad students sorted alphabeticaly.
	 */

	public List<UGrad> ExtractAllUGradDetails() {
		List<UGrad> sortugrads = new ArrayList<UGrad>();

		Object[] obj = ugrads.toArray();
		Arrays.sort(obj);

		for(int i = 0; i < obj.length; i++) {
			UGrad ug = (UGrad)obj[i];
			sortugrads.add(ug);
		}

		return sortugrads;
	}
	/**Sorts the faculties using .toArray() and .sort() method
	 * @return sortfaculty sortfaculty of type Faculty is a List which contains Faculty sorted alphabeticaly.
	 */

	public List<Faculty> ExtractAllFacultyDetails() {
		List<Faculty> sortfaculty = new ArrayList<Faculty>();

		Object[] obj = faculties.toArray();
		Arrays.sort(obj);

		for(int i = 0; i < obj.length; i++) {
			Faculty f = (Faculty)obj[i];
			sortfaculty.add(f);
		}

		return sortfaculty;
	}
	/**
	 * Checks the faculty name and adds it to the faculty list
	 * firts we iterate through the list and we get the program name ,and compare it to input argument.
	 * @param departmentname departmentname is the string we compare to program and then we add it to the Faculty list
	 * Create an list sortfaculty which contains the sorted names of faculty.
	 * Use toArray to returns an array containing all of the elements in this list in propersequence (from first to last element).
	 * Then traverse through very element in Obj array and add it to the sortfacultylist.
	 * @return sortfacultylist.
	 */

	public List<Faculty> ExtractFacultyDetails(String departmentname) {
		// find correct department faculty 
		List<Faculty> df = new ArrayList<Faculty>();
		for(Faculty f : faculties) {
			String dpartname = f.getProgram();
			if(departmentname.equals(dpartname)) {
				df.add(f);
			}
			
		}
		
		
		// sort faculty.
		List<Faculty> sortfaculty = new ArrayList<Faculty>();

		Object[] obj = df.toArray();
		Arrays.sort(obj);

		for(int i = 0; i < obj.length; i++) {
			Faculty f = (Faculty)obj[i];
			sortfaculty.add(f);
		}

		return sortfaculty;
		
	}
/**
 * In faculty we have getAdvisingUgrads which returns the list of Undergraduate class
 * This is assigned to faculty as we have advisees of a particular faculty(for eg:advisee of Computer Science)
 * we then sort the arrays
 * @param f f is faculty to which undergraduate student(advisees) are assigned to.
 * @return sortugrads sortugrads is sorted list of UG students.
 */
	public List<UGrad> ExtractAdviseesDetails(Faculty f) {
		//sort this list
		List<UGrad> ugrads = f.getAdvisingUgrads();
		
		// sort algo
		List<UGrad> sortugrads = new ArrayList<UGrad>();

		Object[] obj = ugrads.toArray();
		Arrays.sort(obj);

		for(int i = 0; i < obj.length; i++) {
			UGrad ug = (UGrad)obj[i];
			sortugrads.add(ug);
		}

		return sortugrads;
		
	}
	/**
	 * In faculty we have geTAs which returns the list of Graduate class
	 * This is assigned to faculty as we have TAs in particular faculty(for eg:TA in Computer Science)
	 * we then sort the arrays
	 * @param f f is faculty to which undergraduate student(advisees) are assigned to.
	 * @return sortugrads sortugrads is sorted list of UG students.
	 */
	

	public List<Grad> ExtractTAsDetails(Faculty f) {
		List<Grad> grads = f.getTAs();
		
		List<Grad> sortgrads = new ArrayList<Grad>();

		Object[] obj = grads.toArray();
		Arrays.sort(obj);

		for(int i = 0; i < obj.length; i++) {
			Grad g = (Grad)obj[i];
			sortgrads.add(g);
		}

		return sortgrads;
	}

	
	public void addProgramDirector(ProgramDirector p) {
		
	}

	public void RetireFaculty(Faculty f1) throws NoSpecialtyException{
		// TODO Auto-generated method stub
		
	}
	

}

class Person {
	protected String FirstName;
	protected String LastName;
	protected int Age;
	protected String Gender;
	protected String Addess;
	/**
	 * 
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	
	public Person(String FirstName, String LastName, int Age, String Gender, String Addess) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Age = Age;
		this.Gender = Gender;
		this.Addess = Addess;
	}
	/**
	 * 
	 * @return FirstName
	 */
	public String getFirstName() {
		return FirstName;
	}
	/**
	 * 
	 * @param firstName
	 */

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	/**
	 * 
	 * @return LastName;
	 */

	public String getLastName() {
		return LastName;
	}
	/**
	 * 
	 * @param lastName
	 */

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	/**
	 * 
	 * @return Age
	 */

	public int getAge() {
		return Age;
	}
	/**
	 * 
	 * @param age
	 */

	public void setAge(int age) {
		Age = age;
	}
	/**
	 * 
	 * @return Gender;
	 */

	public String getGender() {
		return Gender;
	}
	/**
	 * 
	 * @param gender
	 */

	public void setGender(String gender) {
		Gender = gender;
	}
	/**
	 * 
	 * @return Addess Address of a person
	 */

	public String getAddess() {
		return Addess;
	}
	/**
	 * 
	 * @param addess address of a person
	 */

	public void setAddess(String addess) {
		Addess = addess;
	}
}

class Academics extends Person {

	private static int ID = 100;
	private int EmployeeID;
	private double salary;
/**
 * 
 * @param FirstName
 * @param LastName
 * @param Age
 * @param Gender
 * @param Addess
 */
	public Academics(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
		setEmployeeID(ID);
	}
	/**
	 * 
	 * @return EmployeeID
	 */

	public int getEmployeeID() {
		return EmployeeID;
	}
	/**
	 * In this method we use non static variable,
	 * @param ID ID is the Employee ID 
	 */

	public void setEmployeeID(int ID) {
		EmployeeID = ID;
		Academics.ID++;
	}
	/**
	 * 
	 * @param salary
	 */
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	/**
	 * 
	 * @return salary
	 */
	
	public double getSalary() {
		return salary;
	}
}

class Student extends Person implements Comparable<Person> {
	
	private static int ID = 1000;
	private int StudentID;
	/**
	 * 
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	public Student(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
		setStudentID(ID);
	}
	/**
	 * 
	 * @return StudentID;
	 */
	public int getStudentID() {
		return StudentID;
	}
	/**
	 * 
	 * @param ID
	 */

	public void setStudentID(int ID) {
		StudentID = ID;
		Student.ID++;
	}
	/**
	 * @param p p is an object of type Person
	 * @return<pre> It returns 0, if the two students have same name
	 * It returns -1, if this object(p) is less than the object(p) that is passed as a parameter into the method. 
	 * It returns 1, if this object(p) is greater than the object(p) that is passed as a parameter into the method<pre>.
	 */

	@Override
	public int compareTo(Person p) {
		String studentA = getFirstName() + "," + getLastName();
		String studentB = p.getFirstName() + "," + p.getLastName();
		
		if(studentA.compareTo(studentB) == 0) return 0;
		else if(studentA.compareTo(studentB) < 0) return -1;
		else return 1;
	}

}
class Administrators extends Academics {
	/**
	 * 
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	public Administrators(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
	}

}

class Faculty extends Academics implements Comparable<Person> {

	private String program;
	private List<UGrad> ugrads = new ArrayList<UGrad>();
	private List<Grad> grads = new ArrayList<Grad>();
	/**
	 * 
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	public Faculty(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
	}
	/**
	 * 
	 * @param program
	 */

	public void setProgram(String program) {
		this.program = program;
	}
	/**
	 * 
	 * @return program
	 */

	public String getProgram() {
		return program;
	}

	@Override
	public String toString() {
		return "Faculty Computer Science[[" + getEmployeeID() + ", " + String.format("%.1f", getSalary()) + 
				"[" + getFirstName()+ ", "+ getLastName() + ", "+ getAge() +", "+ getGender() +", "+getAddess()+"]]]";
	}
	/**
	 * 
	 * @return upgrads upgrads is a List of UGrad
	 */
	public List<UGrad> getAdvisingUgrads() {
		return ugrads;
	}
	/**
	 * Adding undergruate student to a program in Faculty.
	 * @param ug
	 * @return <pre> true if the limit is not excedded and is already added to ugrad List,
	 * else returns false
	 */

	public boolean addUgrads(UGrad ug) {
		int maxugrads = 8;
		int ugradsize = ugrads.size();
		if(ugradsize < maxugrads) {
			ugrads.add(ug);
			return true;
		}
		return false;
	}
	/**
	 * @param ug
	 * @return true or false
	 */
	public boolean removeUgrads(UGrad ug) {
		return ugrads.remove(ug);
	}
	
	/**
	 * @return size of Graduate List
	 */
	public Integer getNumOfAdvisingUGrads() {
		return grads.size();
	}
	/**
	 * 
	 * @return grads grads is a List<Grad>
	 */
	public List<Grad> getTAs() {
		return grads;
	}
	/**@param g
	 * if the the number of TAs hired exceeds maxugrads
	 * @return true
	 * if the nummber of TAs hired are less than maxgrads then 
	 * @return false
	 * Also add it to the List. 
	 */

	public boolean addTAs(Grad g) {
		int maxugrads = 5;
		int gradsize = grads.size();
		if(gradsize < maxugrads) {
			grads.add(g);
			return true;
		}
		return false;
	}
	/**
	 * @param g
	 * @return remove remove grads 
	 * @throws NoTAException NoTAException is thrown when the number of TAs is less than 1
	 */
	public boolean removeTAs(Grad g) throws NoTAException{
		int currentTA = grads.size();
		if(currentTA <= 1) {
			throw new NoTAException();
		}
		
		return grads.remove(g);
	}
	/**
	 * 
	 * @return size size of List of grads
	 */
	public Integer getNumOfTAs() {
		return grads.size();
	}
	/**
	 * @param p p is an object of type Person
	 * @return<pre> It returns 0, if the two students have same name
	 * It returns -1, if this object(p) is less than the object(p) that is passed as a parameter into the method. 
	 * It returns 1, if this object(p) is greater than the object(p) that is passed as a parameter into the method<pre>.
	 */

	@Override
	public int compareTo(Person p) {
		String studentA = getFirstName() + "," + getLastName();
		String studentB = p.getFirstName() + "," + p.getLastName();
		
		if(studentA.compareTo(studentB) == 0) return 0;
		else if(studentA.compareTo(studentB) < 0) return -1;
		else return 1;
	}
	
}
/**
 * 
 * @author Owner
 *
 */
class UGrad extends Student {

	Faculty faculty;
	/**
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	public UGrad(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
	}
	

	@Override
	public String toString() {
		return "Undergraduate [" +getStudentID()+ "[[" + getFirstName() + ", " + getLastName() + ", " + getAge() +", " + getGender() + ", " + getAddess() + "]]]";
	}
	/**
	 * 
	 * @return faculty;
	 */

	public Faculty getAdvisor() {
		return faculty;
	}
	/**
	 * 
	 * @param f
	 */

	public void setAdvisor(Faculty f) {
		faculty = f;
	}
	
}

class Grad extends Student {

	Faculty faculty;
	/**
	 * 
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	public Grad(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
	}
	
	@Override
	public String toString() {
		return "Graduate [" +getStudentID()+ "[[" + getFirstName() + ", " + getLastName() + ", " + getAge() +", " + getGender() + ", " + getAddess() + "]]]";
	}
	/**
	 * @return faculty
	 */

	public Faculty getAdvisor() {
		return faculty;
	}
	/**
	 * 
	 * @param f
	 */
	
	public void setAdvisor(Faculty f) {
		faculty = f;
	}

	
}

class ProgramDirector extends Administrators{
	/**
	 * 
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	public ProgramDirector(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
	}
	

	public void setProgram(String string) {
		// TODO Auto-generated method stub
		
	}

}
class ChairPerson extends Administrators {
	/**
	 * 
	 * @param FirstName
	 * @param LastName
	 * @param Age
	 * @param Gender
	 * @param Addess
	 */
	public ChairPerson(String FirstName, String LastName, int Age, String Gender, String Addess) {
		super(FirstName, LastName, Age, Gender, Addess);
	}

	@Override
	public String toString() {
		return "Chair Person [[["+ getEmployeeID()+ ", " + String.format("%.1f",getSalary())+
				"["+ getFirstName() + ", " + getLastName() +", " + getAge() + ", " + getGender() + ", " + getAddess() + "]]]]";
	}
}


/**
 * This is a user defined exception used
 * when there is no specialty in faculty
 * 
 */
@SuppressWarnings("serial")
class NoSpecialtyException extends Exception {
	public NoSpecialtyException() {
		super();
	}
	/**
	 * 
	 * @param message
	 */
	public NoSpecialtyException(String message) {
		super(message);
	}
}
/**
 * This is a user defined exception used
 * when there less than  or equal 1 TAs in 
 * grads List.
 */
@SuppressWarnings("serial")
class NoTAException extends Exception {
	public NoTAException() {
		super();
	}
	
	public NoTAException(String message) {
		super(message);
	}
}
/**
 * This is a user defined exception used
 * when there is no space for eg: there is no vacancy in List.
 * 
 */
@SuppressWarnings("serial")
class NoSpaceException extends Exception {
	
	public NoSpaceException() {
		super();
	}
	/**
	 * 
	 * @param message
	 */
	
	public NoSpaceException(String message) {
		super(message);
	}
	
}



	
	

	
	
	

	

