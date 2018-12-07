package testSuite;

import au.edu.sccs.csp3105.BookingPlanner.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.easymock.EasyMock.createNiceMock;
import org.easymock.EasyMock;

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.AfterClass;
import org.junit.Test;

@RunWith(Enclosed.class)
public class TestCalendar {
	
	public static class Test28day {
	
		Calendar calendar = new Calendar();
		// Test that 28th day is not "busy"
		@Test
		public void CL26() throws TimeConflictException {
			assertEquals("Error checking blocked off day", false, calendar.isBusy(2,  28,  10,  15));
		}
		// Test that rest of days returned as "busy"
		@Test
		public void CL27() throws TimeConflictException {
			assertEquals("Error checking blocked off day", true, calendar.isBusy(2,  29,  10,  12));
		}
		@Test
		public void CL28() throws TimeConflictException {
			assertEquals("Error checking blocked off day", true, calendar.isBusy(2,  30,  0,  1));
		}
		@Test
		public void CL29() throws TimeConflictException {
			assertEquals("Error checking blocked off day", true, calendar.isBusy(2,  31,  22,  23));
		}
	}
	
	public static class Test30Day {
		
		Calendar calendar = new Calendar();
		
		// Test that 30th day of 30 day months are not busy
		@Test
		public void CL30() throws TimeConflictException {
			assertEquals("Error checking blocked off day", false, calendar.isBusy(4,  30,  12,  13));
		}
		@Test
		public void CL32() throws TimeConflictException {
			assertEquals("Error checking blocked off day", false, calendar.isBusy(6,  30,  10,  12));
		}
		@Test
		public void CL34() throws TimeConflictException {
			assertEquals("Error checking blocked off day", false, calendar.isBusy(9,  30,  10,  12));
		}
		@Test
		public void CL36() throws TimeConflictException {
			assertEquals("Error checking blocked off day", false, calendar.isBusy(11,  30,  13,  14));
		}
		
		// Test that the 31st day is busy
		@Test
		public void CL31() throws TimeConflictException {
			assertEquals("Error checking blocked off day", true, calendar.isBusy(4,  31,  0,  23));
		}
		@Test
		public void CL33() throws TimeConflictException {
			assertEquals("Error checking blocked off day", true, calendar.isBusy(6,  31,  13,  16));
		}
		@Test
		public void CL35() throws TimeConflictException {
			assertEquals("Error checking blocked off day", true, calendar.isBusy(9,  31,  10,  15));
		}
		@Test
		public void CL37() throws TimeConflictException {
			assertEquals("Error checking blocked off day", true, calendar.isBusy(11,  31,  11,  12));
		}
	}
	
	public static class TestCalendarModifiersValidTransitions {
		
		//unit under test
		private Calendar calendar;
				
		//mock objects
		private Meeting meeting1;	
		private Meeting meeting2;

		// functions to drive the mock objects in tests
		public void setupMeeting1(int d, int m, int s, int e, String desc) {
			EasyMock.expect(meeting1.getDay()).andStubReturn(d);
			EasyMock.expect(meeting1.getMonth()).andStubReturn(m);
			EasyMock.expect(meeting1.getStartTime()).andStubReturn(s);
			EasyMock.expect(meeting1.getEndTime()).andStubReturn(e);
			EasyMock.expect(meeting1.getDescription()).andStubReturn(desc);
		}
		public void setupMeeting2(int d, int m, int s, int e, String desc) {
			EasyMock.expect(meeting2.getDay()).andStubReturn(d);
			EasyMock.expect(meeting2.getMonth()).andStubReturn(m);
			EasyMock.expect(meeting2.getStartTime()).andStubReturn(s);
			EasyMock.expect(meeting2.getEndTime()).andStubReturn(e);
			EasyMock.expect(meeting2.getDescription()).andStubReturn(desc);
		}
		
		@Before public void init() {
			
			//Instantiate unit under test
			calendar = new Calendar();
			
			//Instantiate mock meetings
			meeting1 = createNiceMock(Meeting.class); //createNiceMock inherits the classes methods rather than returning default values
			meeting2 = createNiceMock(Meeting.class);
		}
		
		@Test
		public void CL39() throws TimeConflictException { // Adding meeting to free slot in valid day 
			setupMeeting1(5, 10, 13, 14, "CL39");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			assertEquals("Error adding meeting - CL39", "CL39", calendar.getMeeting(10, 5, 0).getDescription()); // returns description of exact meeting added
		}
		
		@Test
		public void CL40() throws TimeConflictException { // Adding meeting that immediately precedes existing meeting
			setupMeeting1(2, 2, 14, 15, "CL40a");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			setupMeeting2(2, 2, 13, 14, "CL40b");
			EasyMock.replay(meeting2);
			calendar.addMeeting(meeting2);
			assertEquals("Error adding meeting(s) - CL40", "CL40b", calendar.getMeeting(2, 2, 1).getDescription()); // returns description of exact meeting added
		}
		
		@Test
		public void CL41() throws TimeConflictException { // Adding meeting that immediately follows existing meeting
			setupMeeting1(3, 3, 14, 15, "CL41a");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			setupMeeting2(3, 3, 15, 16, "CL41b");
			EasyMock.replay(meeting2);
			calendar.addMeeting(meeting2);
			assertEquals("Error adding meeting(s) - CL41", "CL41b", calendar.getMeeting(3, 3, 1).getDescription());
		}
		
		@Test(expected = IndexOutOfBoundsException.class)
		public void CL51() throws TimeConflictException { // ClearSchedule of day with meeting(s) 
			setupMeeting1(1, 1, 1, 2, "CL50");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			// check the day/time is now busy
			if(calendar.isBusy(1, 1, 1, 2)) {
				calendar.clearSchedule(1, 1);
				assertNull("CL50 - Schedule not cleared", calendar.getMeeting(1, 1, 0)); // indexoutofbounds exception proves the day is empty
			}
			else {
				fail("CL50 - Unsuccessful set up");
			}
		}
		
		@Test
		public void CL53() throws TimeConflictException { // Call isBusy() on times overlapping start of existing meeting 
			setupMeeting1(1, 1, 10, 14, "CL53");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("Error in isBusy()", true, calendar.isBusy(1, 1, 9, 11));
		}
		
		@Test
		public void CL54() throws TimeConflictException { // Call isBusy() on times subsumed by existing meeting 
			setupMeeting1(1, 1, 10, 14, "CL54");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("Error in isBusy()", true, calendar.isBusy(1, 1, 11, 13));
		}
		@Test
		public void CL55() throws TimeConflictException { // Call isBusy() on times overlapping end of existing meeting 
			setupMeeting1(1, 1, 10, 14, "CL55");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("Error in isBusy()", true, calendar.isBusy(1, 1, 13, 15));
		}
		@Test
		public void CL56() throws TimeConflictException { // Call isBusy() on times that subsume existing meeting 
			setupMeeting1(1, 1, 10, 14, "CL56");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("Error in isBusy()", true, calendar.isBusy(1, 1, 9, 15));
		}
		@Test
		public void CL57() throws TimeConflictException { // Call isBusy() on free slot preceding existing meeting 
			setupMeeting1(1, 1, 10, 14, "CL57");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("Error in isBusy()", false, calendar.isBusy(1, 1, 9, 10)); // should be able to have adjacent meetings
		}
		@Test
		public void CL58() throws TimeConflictException { // Call isBusy() on free slot following existing meeting 
			setupMeeting1(1, 1, 10, 14, "CL58");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("Error in isBusy()", false, calendar.isBusy(1, 1, 14, 15)); // should be able to have adjacent meetings
		}
}
	
	public static class TestCalendarModifiersInvalidTransitions {
		
		//unit under test
		private Calendar calendar;
				
		//mock objects
		private Meeting meeting1;	
		private Meeting meeting2;

		// functions to drive the mock objects in tests
		public void setupMeeting1(int d, int m, int s, int e, String desc) {
			EasyMock.expect(meeting1.getDay()).andStubReturn(d);
			EasyMock.expect(meeting1.getMonth()).andStubReturn(m);
			EasyMock.expect(meeting1.getStartTime()).andStubReturn(s);
			EasyMock.expect(meeting1.getEndTime()).andStubReturn(e);
			EasyMock.expect(meeting1.getDescription()).andStubReturn(desc);
		}
		public void setupMeeting2(int d, int m, int s, int e, String desc) {
			EasyMock.expect(meeting2.getDay()).andStubReturn(d);
			EasyMock.expect(meeting2.getMonth()).andStubReturn(m);
			EasyMock.expect(meeting2.getStartTime()).andStubReturn(s);
			EasyMock.expect(meeting2.getEndTime()).andStubReturn(e);
			EasyMock.expect(meeting2.getDescription()).andStubReturn(desc);
		}
		
		@Before public void init() {
			
			//Instantiate unit under test
			calendar = new Calendar();
			
			//Instantiate mock meetings
			meeting1 = createNiceMock(Meeting.class); //createNiceMock inherits the classes methods rather than returning default values
			meeting2 = createNiceMock(Meeting.class);
		}
		
		@Test(expected = TimeConflictException.class)
		public void CL38() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(30, 2, 10, 12, "CL38");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); // should throw a time conflict exception
		}
		
		
		
		@Test(expected = TimeConflictException.class)
		public void CL42() throws TimeConflictException { // Add to conflicted slot (new overlaps start time of existing) 
			setupMeeting1(1, 1, 12, 15, "CL42a");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			setupMeeting2(1, 1, 11, 13, "CL42b");
			EasyMock.replay(meeting2);
			calendar.addMeeting(meeting2); // Should throw TCE
		}
		@Test(expected = TimeConflictException.class)
		public void CL43() throws TimeConflictException { // Add to conflicted slot (new is subsumed by existing)
			setupMeeting1(4, 4, 12, 17, "CL43a");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			setupMeeting2(4, 4, 13, 16, "CL43b");
			EasyMock.replay(meeting2);
			calendar.addMeeting(meeting2); // Should throw TCE
		}
		@Test(expected = TimeConflictException.class)
		public void CL44() throws TimeConflictException { // Add to conflicted slot (new overlaps end of existing) 
			setupMeeting1(5, 5, 12, 17, "CL44a");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			setupMeeting2(5, 5, 15, 20, "CL44b");
			EasyMock.replay(meeting2);
			calendar.addMeeting(meeting2); // Should throw TCE
		}
		@Test(expected = TimeConflictException.class)
		public void CL45() throws TimeConflictException { // Add to conflicted slot (new subsumes existing) 
			setupMeeting1(6, 6, 12, 17, "CL45a");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			setupMeeting2(6, 6, 10, 20, "CL45b");
			EasyMock.replay(meeting2);
			calendar.addMeeting(meeting2); // Should throw TCE
		}
		@Test(expected = TimeConflictException.class)
		public void CL46() throws TimeConflictException { // Add to conflicted slot (new matches existing)  
			setupMeeting1(7, 7, 12, 17, "CL46a");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1);
			setupMeeting2(7, 7, 12, 17, "CL46b");
			EasyMock.replay(meeting2);
			calendar.addMeeting(meeting2); // Should throw TCE
		}
		@Test(expected = TimeConflictException.class)
		public void CL47() throws TimeConflictException { // Add to invalid date (out of bounds) 
			setupMeeting1(-3, 4, 0, 5, "CL47");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); // should throw a time conflict exception
		}
		@Test(expected = TimeConflictException.class)
		public void CL48() throws TimeConflictException { // Remove a meeting from free slot 
			calendar.removeMeeting(1,  1,  0); // Should throw TCE as no meetings exist
		}
		@Test(expected = TimeConflictException.class)
		public void CL49() throws TimeConflictException { // Remove meeting from invalid date 
			calendar.removeMeeting(-3,  1,  0); // Should throw TCE
		}
		@Test
		public void CL50() throws TimeConflictException { // Remove meeting from blocked off day in shorter month - (Should not be possible) 
			calendar.removeMeeting(4, 31,  0); 
			try {
				calendar.getMeeting(4, 31, 0);
			}catch (IndexOutOfBoundsException i) {
				fail("Day-blocking meeting was removed from calendar");
			}
		}
		
		@Test(expected = TimeConflictException.class)
		public void CL52() throws TimeConflictException { // ClearSchedule of day out of bounds 
			calendar.clearSchedule(1, 45); // TCE should be thrown 
		}
		
		
		
		// assert meeting WAS added
		
		@Test // We know from CL38 that TCE does not get thrown.
		public void CL59() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(28, 2, 10, 15, "CL59");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("2/28, problems adding meeting", "CL59", calendar.getMeeting(2, 28, 0).getDescription());
		}
		@Test // We know from CL38 that TCE does not get thrown.
		public void CL63() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(30, 4, 12, 13, "CL63");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("4/30, problems adding meeting", "CL63", calendar.getMeeting(4, 30, 0).getDescription());
		}
		@Test // We know from CL38 that TCE does not get thrown.
		public void CL65() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(30, 6, 10, 12, "CL65");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("6/30, problems adding meeting", "CL65", calendar.getMeeting(6, 30, 0).getDescription());
		}
		@Test // We know from CL38 that TCE does not get thrown.
		public void CL67() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(30, 9, 10, 12, "CL67");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("9/30, problems adding meeting", "CL67", calendar.getMeeting(9, 30, 0).getDescription());
		}
		@Test // We know from CL38 that TCE does not get thrown.
		public void CL69() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(30, 11, 13, 14, "CL69");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			assertEquals("11/30, problems adding meeting", "CL69", calendar.getMeeting(11, 30, 0).getDescription());
		}
		
		// test meeting cannot be added to non-day of short month
		
		@Test (expected = IndexOutOfBoundsException.class) // should be thrown if no meeting is added
		public void CL60() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(29, 2, 10, 12, "CL60");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			calendar.getMeeting(2, 29, 1).getDescription(); // tries to access index(1), should be out of bounds if no meeting added
		}
		@Test (expected = IndexOutOfBoundsException.class) // should be thrown if no meeting is added
		public void CL61() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(30, 2, 0, 1, "CL61");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			calendar.getMeeting(2, 30, 1).getDescription(); // tries to access index(1), should be out of bounds if no meeting added
		}
		@Test (expected = IndexOutOfBoundsException.class) // should be thrown if no meeting is added
		public void CL62() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(31, 2, 22, 23, "CL62");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			calendar.getMeeting(2, 31, 1).getDescription(); // tries to access index(1), should be out of bounds if no meeting added
		}
		@Test (expected = IndexOutOfBoundsException.class) // should be thrown if no meeting is added
		public void CL64() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(31, 4, 0, 23, "CL64");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			calendar.getMeeting(4, 31, 1).getDescription(); // tries to access index(1), should be out of bounds if no meeting added
		}
		@Test (expected = IndexOutOfBoundsException.class) // should be thrown if no meeting is added
		public void CL66() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(31, 6, 13, 16, "CL66");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			calendar.getMeeting(6, 31, 1).getDescription(); // tries to access index(1), should be out of bounds if no meeting added
		}
		@Test (expected = IndexOutOfBoundsException.class) // should be thrown if no meeting is added
		public void CL68() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(31, 9, 10, 22, "CL68");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			calendar.getMeeting(9, 31, 1).getDescription(); // tries to access index(1), should be out of bounds if no meeting added
		}
		@Test (expected = IndexOutOfBoundsException.class) // should be thrown if no meeting is added
		public void CL70() throws TimeConflictException { // Adds meeting to a blocked out day (shorter month)
			setupMeeting1(31, 11, 11, 12, "CL70");
			EasyMock.replay(meeting1);
			calendar.addMeeting(meeting1); 
			calendar.getMeeting(11, 31, 1).getDescription(); // tries to access index(1), should be out of bounds if no meeting added
		}
	}
}