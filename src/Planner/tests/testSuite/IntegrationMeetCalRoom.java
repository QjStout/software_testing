package testSuite;
import au.edu.sccs.csp3105.BookingPlanner.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

@RunWith(Enclosed.class)
public class IntegrationMeetCalRoom {
	
	private static Room wrongRoom;
	private static Room room;
	private static Meeting meeting;
	private static Meeting conflict;
	private static Meeting meeting2;
	private static Person harry;
	private static Person larry;
	private static ArrayList<Person> a;
	
	static public String meetingStateGenerator(Meeting meeting) {
		
		String state = "";
		ArrayList<Person> attendees = meeting.getAttendees();
		
		try {
			state = state + Integer.toString(meeting.getMonth());
		} catch(Exception e) {
			
		}
		try {
			state = state + Integer.toString(meeting.getDay());
		} catch(Exception e) {
			
		}
		try {
			state = state + Integer.toString(meeting.getStartTime());
		} catch(Exception e) {
			
		}
		try {
			state = state + Integer.toString(meeting.getEndTime());
		} catch(Exception e) {
			
		}
		try {
			for (Person p : attendees) {
				state = state + p.getName();
			}
		} catch(Exception e) {
			
		}
		try {
			state = state + meeting.getRoom().getID();
		} catch(Exception e) {
			
		}
		try {
			state = state + meeting.getDescription();
		} catch(Exception e) {
			
		}
		
		return state;
	}
	
	static public String roomStateGenerator(Room room, int m, int d) {
		
		String state = "";
		
		for (int i = 0; i < 24; i++) {
			try {
				state = state + meetingStateGenerator(room.getMeeting(m, d, i));
			} catch (Exception e) {
				break;
			}
		}
		
		return state;
	}
	
	

	
	public static class MCRdefaultMeetingConstructorTests {
		
		
		@Before
		public void init() {
			
			room = new Room("room");
			larry = new Person("Larry");
			a = new ArrayList<Person>();
			a.add(larry);
			meeting = new Meeting(1, 10);
			conflict = new Meeting(1, 10);

			
		}
		
		
		@Test
		public void MCR01() throws TimeConflictException {
			room.addMeeting(meeting);
			Meeting test = room.getMeeting(1, 10, 0);
			assertEquals("Error in getMeeting()", meetingStateGenerator(meeting), meetingStateGenerator(test));
		}
		
		@Test
		public void MCR02() throws TimeConflictException {
			room.addMeeting(meeting);
			String agenda = room.printAgenda(1);
			assertNotEquals("Error printing agenda", "No Meetings booked on this date.\n\n", agenda);
		}
		
		@Test
		public void MCR03() throws TimeConflictException {
			room.addMeeting(meeting);
			String agenda = room.printAgenda(1, 10);
			assertNotEquals("Error printing agenda", "No Meetings booked on this date.\n\n", agenda);
		}
		
		@Test
		public void MCR04() throws TimeConflictException {
			room.addMeeting(meeting);
			String startState = roomStateGenerator(room, 1, 10);
			assertEquals("Error adding meeting to room", meetingStateGenerator(meeting), meetingStateGenerator(room.getMeeting(1,  10, 0)));
			room.removeMeeting(1, 10, 0);
			String newState = roomStateGenerator(room, 1, 10);
			assertNotEquals("Error removing meeting", newState, startState);
		}
		
		@Test
		public void MCR05() throws TimeConflictException { // **** This should be in unit testing of meeting!
			meeting.addAttendee(larry);
			assertEquals("Error adding attendee to meeting", "larry", meeting.getAttendees().get(0).getName());
		}
		
		@Test
		public void MCR06() throws TimeConflictException { 
			room.addMeeting(meeting);
			meeting.setRoom(room);
			assertEquals("Error setting room for meeting", "room", room.getMeeting(1, 10, 0).getRoom().getID());
		}
		
		// Tests expected to throw TCE
		
		@Test (expected = TimeConflictException.class)      // Test to make sure you can't change meetings attributes to invalid state
		public void MCR07() throws TimeConflictException {  // AFTER you have added it to room calendar in valid state
			room.addMeeting(meeting);
			meeting.setDay(100);
		}
		
		@Test (expected = TimeConflictException.class) // still should not be able to add conflicting meetings
		public void MCR08() throws TimeConflictException {
			room.addMeeting(meeting);
			room.addMeeting(conflict);
		}	
	}
	
	public static class MCRfullMeetingConstructorTests {		
		
		@Before
		public void init() {
			
			wrongRoom = new Room("wrong");
			room = new Room("room");
			harry = new Person("harry");
			larry = new Person("Larry");
			a = new ArrayList<Person>();
			a.add(larry);
			meeting = new Meeting(1, 10, 10, 12, a, room, "desc");
			conflict = new Meeting(1, 10, 10, 12, a, room, "desc");
			meeting2 = new Meeting(1, 20, 12, 14, a, room, "hello");
		}
		
		@Test
		public void MCR09() throws TimeConflictException { // add meeting, print agenda of month, add meeting, print agenda of day, remove meeting
			room.addMeeting(meeting);
			assertEquals("Meeting not added to Room calendar", meetingStateGenerator(meeting), roomStateGenerator(room, 1, 10));
			String agenda = room.printAgenda(1);
			assertEquals("Error printing agenda", "Agenda for 1:\nMonth is 1, Day is 10, Time slot:10 - 12, Room No:room: desc\nAttending: Larry\n", agenda);
			room.addMeeting(meeting2);
			agenda = room.printAgenda(1, 20);
			assertEquals("Error printing agenda", "Agenda for 1/20 are as follows:\nMonth is 1, Day is 20, Time slot:12 - 14, Room No:room: hello\nAttending: Larry\n", agenda);
			String stateBefore = roomStateGenerator(room, 1, 10) + roomStateGenerator(room, 1, 20);
			room.removeMeeting(1, 20, 0);
			String stateAfter = roomStateGenerator(room, 1, 10) + roomStateGenerator(room, 1, 20);
			assertNotEquals("Error removing meeting from room calendar", stateAfter, stateBefore);
		}
		
		@Test
		public void MCR10() throws TimeConflictException { // test you can't change room in meeting to something other than the room its booked to
			room.addMeeting(meeting);
			room.getMeeting(1, 10, 0).setRoom(wrongRoom);
			assertEquals("Error: meeting's room doesn't match room.getRoomID", room.getID(), room.getMeeting(1, 10, 0).getRoom().getID());
		}
		
		@Test
		public void MCR11() throws TimeConflictException {
			room.addMeeting(meeting);
			room.getMeeting(1, 10, 0).addAttendee(harry);
			assertEquals("Error adding person", "harry", room.getMeeting(1, 10, 0));
			
		}
		
	}
}

