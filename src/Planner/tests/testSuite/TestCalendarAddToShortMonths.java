package testSuite;

import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import au.edu.sccs.csp3105.BookingPlanner.Calendar;
import au.edu.sccs.csp3105.BookingPlanner.Meeting;
import au.edu.sccs.csp3105.BookingPlanner.TimeConflictException;

@RunWith(Enclosed.class)
public class TestCalendarAddToShortMonths {

	public static class TestCalendarAddToValid {
		
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
				
				
	}
	public static class TestCalendarAddToInvalid {
		
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
