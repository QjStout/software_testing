package testSuite;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import au.edu.sccs.csp3105.BookingPlanner.Meeting;
import au.edu.sccs.csp3105.BookingPlanner.Room;
import au.edu.sccs.csp3105.BookingPlanner.Person;

@RunWith(Enclosed.class)
public class TestMeeting {	
	
	/**
	 * Test cases for state of: Default meeting constructor, meeting()
	 */
	public static class Test1{
		//attributes
		private Meeting meeting;
		private Person p1;
		private Room r1;
		
		//define setup code
		@Before public void init(){
			meeting = new Meeting();
			p1 = new Person("Jeff");
			r1 = new Room();
			
		}
		
		@Test 
		public void M00(){
			meeting.addAttendee(p1);
			assertEquals("Error in Meeting.addAttendee(Person)", p1, meeting.getAttendees().get(0)); 
		}
		
		@Test
		public void M01(){
			meeting.removeAttendee(p1);
			assertEquals("Error in Meeting.removeAttendee(Person)", null, meeting.getAttendees());
		}
		
		@Test
		public void M02(){
			assertNotNull("Error in Meeting.toString()", meeting.toString());
		}
		
		@Test
		public void M03(){
			assertEquals("Error in Meeting.getMonth()", 0, meeting.getMonth());
		}
		
		@Test
		public void M04(){
			meeting.setMonth(1);
			assertEquals("Error in Meeting.setMonth(int)", 1, meeting.getMonth());
		}
		
		@Test
		public void M05(){
			assertEquals("Error in Meeting.getDay()", 0, meeting.getDay());
		}
		
		@Test
		public void M06(){
			meeting.setDay(2);
			assertEquals("Error in Meeting.setDay(int)", 2, meeting.getDay());
		}
		
		@Test
		public void M07(){
			assertEquals("Error in Meeting.getStartTime()", 0, meeting.getStartTime());
		}
		
		@Test
		public void M08(){
			meeting.setStartTime(3);
			assertEquals("Error in Meeting.setStartTime(3)", 3, meeting.getStartTime());
		}
		
		@Test
		public void M09(){
			assertEquals("Error in Meeting.getEndTime()", 0, meeting.getEndTime());
		}
		
		@Test
		public void M10(){
			meeting.setEndTime(4);
			assertEquals("Error in Meeting.setEndTime(int)", 4, meeting.getEndTime());
		}
		
		@Test
		public void M11(){
			assertNull("Error in Meeting.getAttendees()", meeting.getAttendees());
		}
		
		@Test
		public void M12(){
			assertNull("Error in Meeting.getRoom()", meeting.getRoom());
		}
		
		@Test
		public void M13(){
			meeting.setRoom(r1);
			assertEquals("Error in Meeting.setRoom()", r1, meeting.getRoom());
		}
		
		@Test
		public void M14(){
			assertNull("Error in Meeting.getDescription()", meeting.getDescription());
		}
		
		@Test
		public void M15(){
			String t = "Test description";
			meeting.setDescription(t);
			assertEquals("Error in Meeting.setDescription()", t, meeting.getDescription());
		}
	}
	
	
	/**
	 * Test cases for state of: constructor meeting(int, int, int, int, String
	 */
	public static class Test2{
		private Meeting meeting;
		private Person p2;
		private Person p3;
		private Room r2;

		
		@Before
		public void init(){
			
			p2 = new Person("Jeff");
			p3 = new Person("Gary");
			r2 = new Room("JO.123");

			ArrayList<Person> people = new ArrayList<Person>();
			people.add(p2);
			meeting = new Meeting(1, 2, 3, 4, people, r2, "description");

			
			
		}
		
		
		@After
		public void cleanUp(){
			meeting = null;
		}
		
		@Test
		public void M16(){
			
			meeting.addAttendee(p3);
			assertEquals("Error: addAttendee(Person)", 2, meeting.getAttendees().size());

		}
		
		@Test
		public void M17(){
			
			meeting.removeAttendee(p2);
			assertEquals("Error: removeAttendee(Person)", 0, meeting.getAttendees().size());

		}
		
		@Test
		public void M18(){
			assertEquals("Error: toString()", "Month is 1, Day is 2, Time slot:3 - 4, Room No:JO.123: description\nAttending: Jeff", meeting.toString());
		}
		
		
	}
}
















