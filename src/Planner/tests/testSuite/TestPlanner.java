package testSuite;

import au.edu.sccs.csp3105.BookingPlanner.Meeting;
import au.edu.sccs.csp3105.BookingPlanner.Organization;
import au.edu.sccs.csp3105.BookingPlanner.Person;
import au.edu.sccs.csp3105.BookingPlanner.Planner;
import au.edu.sccs.csp3105.BookingPlanner.Room;
import au.edu.sccs.csp3105.BookingPlanner.TimeConflictException;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import org.easymock.EasyMock;

import static org.powermock.api.easymock.PowerMock.*;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Enclosed.class)
@PrepareForTest(Planner.class)
public class TestPlanner {
	
	/**
	 * Test Planner.mainMenu()
	 */
	public static class testMainMenu{
		private String mthdToReplace = "inputOutput";
		private String expectedStringID = "Please enter the number that corresponds to the option that you want to proceed with.";
		
		//Class under test
		private Planner planner;
		
		@Rule
		//setup for expect System.exit(int)
		public final ExpectedSystemExit exit = ExpectedSystemExit.none();
		
		@Test
		public void P01(){
			//Partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace, "scheduleMeeting");
			
			//expect private method planner.inputOutput(String)
			//Stub private method planner.inputOutput(String)
			try {
				PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("1");
			} catch (Exception e) {
				System.out.println("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			//expect scheduleMeeting() : void
			planner.scheduleMeeting();
			EasyMock.expectLastCall(); //expectLastCall tells replay() to expect the call made immediately prior. Default 'at least once'.
			
			//finish recording mock
			replay(planner);
			
			//execute test
			planner.mainMenu();
			
			//verify that execution is equal to replay
			verify(planner);
			
		}
		
		@Test
		public void P02(){
			//Partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace, "scheduleVacation");
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			try{
				PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("2");
			} catch (Exception e){
				System.out.print("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			//expect scheduleVacation() : void
			planner.scheduleVacation();
			EasyMock.expectLastCall();
			
			//finish recording mock
			replay(planner);
			
			//execute test
			planner.mainMenu();
			
			//verify that execution is equal to replay
			verify(planner);
		}
		
		@Test
		public void P03(){
			//Partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace, "checkRoomAvailability");
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			try{
				PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("3");
			} catch (Exception e){
				System.out.println("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			//expect checkRoomAvailability() : void
			planner.checkRoomAvailability();
			EasyMock.expectLastCall();
			
			//finish recording mock
			replay(planner);
			
			//execute test
			planner.mainMenu();
			
			//verify that execution is equal to replay
			verify(planner);
		}
		
		@Test
		public void P04(){
			//partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace, "checkEmployeeAvailability");
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			try{
				PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("4");
			} catch (Exception e){
				System.out.println("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			//expect checkEmployeeAvailability() : void
			planner.checkEmployeeAvailability();
			EasyMock.expectLastCall();
			
			//finish recording mock
			replay(planner);
			
			//execute test
			planner.mainMenu();
			
			//verify that execution is equal to replay
			verify(planner);
		}
		
		@Test
		public void P05(){
			//partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace, "checkAgendaRoom");
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			try{
				PowerMock.expectPrivate(planner,  mthdToReplace, expectedStringID).andReturn("5");
			} catch (Exception e){
				System.out.println("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			//expect checkAgendaRoom() : void
			planner.checkAgendaRoom();
			EasyMock.expectLastCall();
			
			//finish recording mock
			replay(planner);
			
			//execute test
			planner.mainMenu();
			
			//verify that execution is equal to replay
			verify(planner);
		}
		
		@Test
		public void P06(){
			//partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace, "checkAgendaPerson");
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			try{
				PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("6");
			} catch (Exception e) {
				System.out.println("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			//expect checkAgendaPerson() : void
			planner.checkAgendaPerson();
			EasyMock.expectLastCall();
			
			//finish recording mock
			replay(planner);
			
			//execute test
			planner.mainMenu();
			
			//verify that execution is equal to replay
			verify(planner);
		}
		
		@Test
		public void P07(){
			//partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace);
			
			//expect private method planner.inputOutput(String)
			//Stub private method planner.inputOutput(String)
			try{
				PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("0");
			} catch (Exception e){
				System.out.println("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			//expect System.exit(int)
			exit.expectSystemExitWithStatus(0);
			
			//finish recording mock
			replay(planner);
			
			//execute test
			planner.mainMenu();
			
			//verify that execution is equal to replay
			verify(planner);
		}
		
		@Test
		public void P08() throws Exception{
			//partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace);
			
			//expect private method planner.inputOutput(String)
			//Stub private method planner.inputOutput(String)
			PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("7");			
			PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("0");
			
			exit.expectSystemExitWithStatus(0);
			
			replay(planner);
			planner.mainMenu();
			verify(planner);
		}
		
		@Test
		public void P09() throws Exception{
			//partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace);
			
			//expect private method planner.inputOutput(String)
			//Stub private method planner.inputOutput(String)
			PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("not numeric");			
			PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andReturn("0");
			
			exit.expectSystemExitWithStatus(0);
			
			replay(planner);
			planner.mainMenu();
			verify(planner);
		}
		
		/*
		 * infinite loop attempting to trigger StackOverFlowError 
		 */
		@Test
		public void P10(){
			//partial mock unit under test
			planner = createPartialMock(Planner.class, mthdToReplace);
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			try{
				PowerMock.expectPrivate(planner, mthdToReplace, expectedStringID).andStubReturn("7");
			} catch (Exception e){
				System.out.println("Exception occurred while stubbing private method \"planner.inputOutput(String)\": " + e);
			}
			
			replay(planner);
			
			planner.mainMenu();
		}	
	}
	
	/**
	 * Test Planner.scheduleMeeting()
	 */
	public static class testScheduleMeeting{
		//Unit under test
		private Planner planner;
		
		//Mock Organization
		private Organization organization;
		
		//Mock Room & ArrayList declarations
		private Room room;
		private ArrayList<Room> rooms;
		
		//Mock Person & ArrayList declarations
		private Person employee;
		private ArrayList<Person> employees;
		
		//Mock Meeting
		private Meeting meeting;
		
		//Shared constants
		private final String nonNum = "Non-numeric";
		private final String num = "1";
		private final String mRoomId = "ID: mockRoom";
		private final String mEmplName = "Name: mockEmployee";
		private final String mMeetDesc = "Description: mockMeeting";
		private final String replaceInputOutput = "inputOutput";
		
		private final String expectedMonthId = "\nEnter the month of the meeting (1-12): ";
		private final String expectedDayId = "\nEnter the day of the meeting (1-31): ";
		private final String expectedStartTimeId = "\nEnter the starting hour of the meeting (0-23): ";
		private final String expectedEndTimeId = "\nEnter the ending hour of the meeting (0-23): ";
		private final String expectedRoomId = "\nEnter the desired room ID, or cancel to cancel: ";
		private final String expectedMainMenuId = "Please enter the number that corresponds to the option that you want to proceed with.";
		private final String expectedEmpNameId = "\nEnter a person's name, or done if finished: ";
		private final String expectedMeetDescId = "\nEnter a description for the meeting: ";
				
		public Planner setupPlannerMock(Planner pMock, String m, String d, String sT, String eT) throws Exception{
			//Partial mock unit under test
			pMock = createPartialMock(Planner.class, replaceInputOutput);
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedMonthId).andReturn(m);
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedDayId).andReturn(d);
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedStartTimeId).andReturn(sT);
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedEndTimeId).andReturn(eT);		
			
			return pMock;			
		}
		
		public Planner setupPlannerMock(Planner pMock, String m, String d, String sT, String eT, String roomId, String empName, String meetDesc) throws Exception{
			//Partial mock unit under test
			pMock = createPartialMock(Planner.class, replaceInputOutput);
			
			//expect private method planner.inputOutput(String)
			//stub private method planner.inputOutput(String)
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedMonthId).andStubReturn(m);
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedDayId).andStubReturn(d);
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedStartTimeId).andStubReturn(sT);
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedEndTimeId).andStubReturn(eT);
			
			//room id
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedRoomId).andStubReturn(roomId);
			
			//mainMenu option
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedMainMenuId).andStubReturn("1");
			
			//employee name
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedEmpNameId)
			.andReturn(empName)
			.andStubReturn("done");
			
			//meeting description
			PowerMock.expectPrivate(pMock, replaceInputOutput, expectedMeetDescId).andStubReturn(meetDesc);
			
			return pMock;
		}
		
		/**
		 * TODO:
		 * 	x	1. instantiate Room
		 * 	x	2. instantiate ArrayList<Room>
		 * 	x	3. populate ArrayList<Room>
		 * 	x	4. instantiate Organization
		 * 	x	5. mock Organization.getRooms() : ArrayList<Room>
		 * 	-	5. inject ArrayList<Room> into Organization - Not necessary
		 * 	x	6. inject Organization into Planner
		 */
		
		public Room setupRoomMock(Room r, int m, int d, int sT, int eT, boolean isBusy, String roomId) throws TimeConflictException{
			//instantiate mock room
			r = PowerMock.createNiceMock(Room.class);
			
			if(isBusy){
				EasyMock.expect(r.isBusy(m, d, sT, eT))
				.andStubThrow(new TimeConflictException());
			}else{
				EasyMock.expect(r.isBusy(m, d, sT, eT))
				.andStubReturn(isBusy);
			}
			EasyMock.expect(r.getID())
					.andStubReturn(roomId);
			
			replay(r);
			
			return r;
		}
		
		public Room setupRoomMock(Room r, int m, int d, int sT, int eT, boolean isBusy, String roomId, Meeting meet) throws TimeConflictException{
			//instantiate mock room
			r = PowerMock.createNiceMock(Room.class);
			
			if(isBusy){
				EasyMock.expect(r.isBusy(m, d, sT, eT))
				.andStubThrow(new TimeConflictException());
			}else{
				EasyMock.expect(r.isBusy(m, d, sT, eT))
				.andStubReturn(isBusy);
			}
			EasyMock.expect(r.getID())
					.andStubReturn(roomId);

			r.addMeeting(meet);
			expectLastCall().anyTimes();			
			
			replay(r);
			
			return r;
		}
		
		public Person setupEmployeeMock(Person p, int m, int d, int sT, int eT, boolean isBusy, String employeeName) throws TimeConflictException{
			//instantiate mock person
			p = PowerMock.createNiceMock(Person.class);
			
			if(isBusy){
				EasyMock.expect(p.isBusy(m, d, sT, eT))
						.andStubThrow(new TimeConflictException());
			}else{
				EasyMock.expect(p.isBusy(m, d, sT, d))
						.andStubReturn(isBusy);
			}
			EasyMock.expect(p.getName())
					.andStubReturn(employeeName);
			
			replay(p);			
			
			return p;
		}
		
		public Person setupEmployeeMock(Person p, int m, int d, int sT, int eT, boolean isBusy, String employeeName, Meeting meet) throws TimeConflictException{
			//instantiate mock person
			p = PowerMock.createNiceMock(Person.class);
			
			if(isBusy){
				EasyMock.expect(p.isBusy(m, d, sT, eT))
						.andStubThrow(new TimeConflictException());
			}else{
				EasyMock.expect(p.isBusy(m, d, sT, d))
						.andStubReturn(isBusy);
			}
			EasyMock.expect(p.getName())
					.andStubReturn(employeeName);
			
			p.addMeeting(meet);
			expectLastCall().anyTimes();
			
			replay(p);			
			
			return p;
		}
		
		public Organization setupOrgMock(Organization org, ArrayList<Room> rList){
			//nice mock organization instantiation
			org = PowerMock.createNiceMock(Organization.class);
			EasyMock.expect(org.getRooms()).andStubReturn(rList);
			
			return org;
		}
		
		public Organization setupOrgMock(Organization org, ArrayList<Room> rList, Room r, String roomId, ArrayList<Person> pList, Person p, String employeeName) throws Exception{
			//nice mock organization instantiation
			org = PowerMock.createNiceMock(Organization.class);
			
			//room related stubs
			EasyMock.expect(org.getRooms()).andStubReturn(rList);
			EasyMock.expect(org.getRoom(roomId)).andStubReturn(r);
			
			//employee related stubs
			EasyMock.expect(org.getEmployees()).andStubReturn(pList);
			EasyMock.expect(org.getEmployee(employeeName)).andStubReturn(p);
			
			return org;
		}
		
		/*
		 * inject mock Organization into planner private attribute 'org'
		 */
		public Planner injectOrgInPMock(Planner pMock, Organization org) throws Exception{
			MemberModifier.field(Planner.class, "org").set(pMock, org);
			
			return pMock;
		}
		
		@Test(expected = NumberFormatException.class)
		public void P11() throws Exception{
			planner = setupPlannerMock(planner, num, num, num, nonNum );
			
			replay(planner);
			planner.scheduleMeeting();
			
		}
		
		@Test(expected = NumberFormatException.class)
		public void P12() throws Exception{
			planner = setupPlannerMock(planner, num, num, nonNum, num);
			
			replay(planner);
			planner.scheduleMeeting();
		}
		
		@Test(expected = NumberFormatException.class)
		public void P13() throws Exception{
			planner = setupPlannerMock(planner, num, nonNum, num, num);
			
			replay(planner);
			planner.scheduleMeeting();
		}
		
		@Test(expected = NumberFormatException.class)
		public void P14() throws Exception{
			planner = setupPlannerMock(planner, nonNum, num, num, num);
			
			replay(planner);
			planner.scheduleMeeting();
		}
		
		@Test
		public void P15() throws Exception{
			planner = setupPlannerMock(planner, num, num, num, num, "cancel", mEmplName, mMeetDesc);
			
			rooms = new ArrayList<Room>();
			rooms.add(setupRoomMock(room, 1, 1, 1, 1, true, mRoomId));
			
			organization = setupOrgMock(organization, rooms);
			
			planner = injectOrgInPMock(planner, organization);
			
			//instantiate mocks
			replay(organization);
			replay(planner);
			
			//start test
			planner.scheduleMeeting();
		}
		
		@Test
		public void P16() throws Exception{
			planner = setupPlannerMock(planner, num, num, num, num, "cancel", mEmplName, mMeetDesc);
			
			rooms = new ArrayList<Room>();
			rooms.add(setupRoomMock(room, 1, 1, 1, 1, false, mRoomId));
			
			organization = setupOrgMock(organization, rooms);
			
			//inject org -> planner
			planner = injectOrgInPMock(planner, organization);
			
			//instantiate mocks
			replay(organization);	
			replay(planner);
			
			//start test
			planner.scheduleMeeting();
			
		}
		
		@Test
		public void P17() throws Exception{
			planner = setupPlannerMock(planner, num, num, num, num, mRoomId, mEmplName, mMeetDesc);
			
			rooms = new ArrayList<Room>();
			rooms.add(setupRoomMock(room, 1, 1, 1, 1, false, mRoomId));
			
			employees = new ArrayList<Person>();
			employees.add(setupEmployeeMock(employee, 1, 1, 1, 1, true, mEmplName));
			
			organization = setupOrgMock(organization, rooms, rooms.get(0), mRoomId, employees, employees.get(0), mEmplName);
			
			//inject org -> planner
			planner = injectOrgInPMock(planner, organization);
			
			//instantiate mocks
			replay(organization);
			replay(planner);
			
			//start test
			planner.scheduleMeeting();
			
		}
		
		@Test
		public void P18() throws Exception{
			planner = setupPlannerMock(planner, num, num, num, num, mRoomId, mEmplName, mMeetDesc);
			
			meeting = new Meeting();
			
			rooms = new ArrayList<Room>();
			rooms.add(setupRoomMock(room, 1, 1, 1, 1, false, mRoomId, meeting));
			
			employees = new ArrayList<Person>();
			employees.add(setupEmployeeMock(employee, 1, 1, 1, 1, false, mEmplName));
			
			organization = setupOrgMock(organization, rooms, rooms.get(0), mRoomId, employees, employees.get(0), mEmplName);
			
			//inject org -> planner
			planner = injectOrgInPMock(planner, organization);
			
			//instantiate mocks
			replay(organization);
			replay(planner);
			
			//start test
			planner.scheduleMeeting();
			
		}
	}
	
//	public static class testScheduleVacation{
//		
//	}
}






















