package testSuite;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import au.edu.sccs.csp3105.BookingPlanner.Calendar;
import au.edu.sccs.csp3105.BookingPlanner.Meeting;
import au.edu.sccs.csp3105.BookingPlanner.Person;
import au.edu.sccs.csp3105.BookingPlanner.Room;

public class testCalMeeting {

	
	
	// setup
	private Meeting testMeeting(int m, int d) {
		Meeting testM = new Meeting(m, d);
		return testM;
	}
	
	private Meeting testMeeting(int m, int d, String desc) {
		Meeting testM = new Meeting(m, d, desc);
		return testM;
	}
	
	private Meeting testMeeting(int m, int d, int sh, int eh) {
		Meeting testM = new Meeting(m, d, sh, eh);
		return testM;
	}
	
	private Meeting testMeeting(int m, int d, int sh, int eh, ArrayList<Person> a, Room r, String desc) {
		Meeting testM = new Meeting(m, d, sh, eh, a, r, desc);
		return testM;
	}
	
	
	// Test Calendar.printAgenda with all four Meeting Constructors and addMeeting,getMeeting,removeMeeting
	@Test
	public void CM01() throws Exception {
		Meeting testM = testMeeting(1,1);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM01", "No Meetings booked on this date.\n\n", testC.printAgenda(1,1));
	}
	
	@Test
	public void CM02() throws Exception {
		Meeting testM = testMeeting(1,1, "Testdesc");
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM02", "No Meetings booked on this date.\n\n", testC.printAgenda(1,1));
	}
	
	@Test
	public void CM03() throws Exception {
		Meeting testM = testMeeting(1,1, 0, 1);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM03", "No Meetings booked on this date.\n\n", testC.printAgenda(1,1));
	}
	
	@Test
	public void CM04() throws Exception {
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		Person p = new Person("testPerson");
		a.add(p);
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM04", "No Meetings booked on this date.\n\n", testC.printAgenda(1,1));
	}
	
	
	// Test Calendar.printAgenda with all four Meeting Constructors with isBusy
	@Test
	public void CM05() throws Exception {
		Meeting testM = testMeeting(1,1);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM05", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CM06() throws Exception {
		Meeting testM = testMeeting(1,1, "Testdesc");
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM06", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CM07() throws Exception {
		Meeting testM = testMeeting(1,1, 0, 1);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM07", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CM08() throws Exception {
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		Person p = new Person("testPerson");
		a.add(p);
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CM08", false, testC.isBusy(1,1,0,1));
	}
	
	// Test Calendar.clearScheule with all four Meeting Constructors with isBusy
	@Test
	public void CM09() throws Exception {
		Meeting testM = testMeeting(1,1);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.clearSchedule(1, 1);
		
		assertEquals("failed CM09", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CM10() throws Exception {
		Meeting testM = testMeeting(1,1, "Testdesc");
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.clearSchedule(1, 1);
		
		assertEquals("failed CM10", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CM11() throws Exception {
		Meeting testM = testMeeting(1,1, 0, 1);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.clearSchedule(1, 1);
		
		assertEquals("failed CM11", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CM12() throws Exception {
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		Person p = new Person("testPerson");
		a.add(p);
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.clearSchedule(1, 1);
		
		assertEquals("failed CM12", false, testC.isBusy(1,1,0,1));
	}
}
