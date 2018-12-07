package testSuite;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import au.edu.sccs.csp3105.BookingPlanner.Calendar;
import au.edu.sccs.csp3105.BookingPlanner.Meeting;
import au.edu.sccs.csp3105.BookingPlanner.Person;
import au.edu.sccs.csp3105.BookingPlanner.Room;

public class testCalMeetPers {

	
	
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
	
	
	// Test 
	@Test
	public void CMP01() throws Exception {
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		Person p = new Person("");
		a.add(p);
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CMP01", "No Meetings booked on this date.\n\n", testC.printAgenda(1,1));
	}
	
	@Test
	public void CMP02() throws Exception {
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		Person p = new Person("");
		a.add(p);
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.removeMeeting(1, 1, 0);
		
		assertEquals("failed CMP02", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CMP03() throws Exception {
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		Person p = new Person("");
		a.add(p);
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testC.getMeeting(1, 1, 0);
		testC.clearSchedule(1, 1);
		
		assertEquals("failed CMP03", false, testC.isBusy(1,1,0,1));
	}
	
	// Test 
	@Test
	public void CMP04() throws Exception {
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
		
		assertEquals("failed CMP04", "No Meetings booked on this date.\n\n", testC.printAgenda(1,1));
	}
	
	@Test
	public void CMP05() throws Exception {
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
		
		assertEquals("failed CMP05", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CMP06() throws Exception {
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
		
		assertEquals("failed CMP06", false, testC.isBusy(1,1,0,1));
	}
	
	@Test
	public void CMP07() throws Exception {
		Person p = new Person("testPerson");
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		testM.addAttendee(p);
		testM.removeAttendee(p);
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		ArrayList testList = new ArrayList();
		
		assertEquals("failed CMP07", testList, testC.getMeeting(1, 1, 0).getAttendees());
	}
	
	@Test
	public void CMP08() throws Exception {
		Person p = new Person("testPerson");
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		testM.addAttendee(p);

		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		testM.removeAttendee(p);
		
		ArrayList testList = new ArrayList();
		
		assertEquals("failed CMP08", testList, testC.getMeeting(1, 1, 0).getAttendees());
	}
	
	@Test
	public void CMP09() throws Exception {
		Person p = new Person("testPerson");
		ArrayList<Person> a = new ArrayList<Person>();
		Room r = new Room("testID");
		String desc = "testDesc";
		Meeting testM = testMeeting(1,1, 0, 1, a, r, desc);
		testM.addAttendee(p);
		testM.addAttendee(p); // should not be able to add same twice
		Calendar testC = new Calendar();
		testC.addMeeting(testM);
		assertNotEquals("failed CMP09", testC.getMeeting(1, 1, 0).getAttendees().get(0).getName(), testC.getMeeting(1, 1, 0).getAttendees().get(1).getName());
	}
	
	
}
