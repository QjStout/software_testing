package testSuite;

import au.edu.sccs.csp3105.BookingPlanner.Organization;
import au.edu.sccs.csp3105.BookingPlanner.Room;
import au.edu.sccs.csp3105.BookingPlanner.Person;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.createNiceMock;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class TestOrganization {
	
	public static class testGetRoom{
		//unit under test
		private Organization organization;
		
		//mock room object
		private Room room;
		
		//Field object for reflection
		Field field;
		
		//params
		private String validString = "A real room";
		private String invalidString = "Not a real room";
		private ArrayList<Room> rooms;
		
		@Before
		public void init(){
			//Instantiate unit under test
			organization = new Organization();
			
			//Instantiate mock room
			room = createNiceMock(Room.class); //createNiceMock inherits the classes methods rather than returning default values
			
			// instantiate ArrayList param
			rooms = new ArrayList<Room>();
			rooms.add(room);
			
			//expect tells EasyMock to listen for an object call, i.e. room.getID()
			EasyMock.expect(room.getID()).andStubReturn(validString); //andStubReturn means the expected method will return the given value on every call
			EasyMock.replay(room);
			
			//Reflection
			//Find field declaration & turn off the private keyword
			try {
				field = organization.getClass().getDeclaredField("rooms");
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			field.setAccessible(true);
			try {
				field.set(organization, rooms);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		@After
		public void cleanUp(){
			//Null objects so garbage cleaner removes after scope
			organization = null;
			room = null;
			room = null;
			field = null;
		}
		
		@Test
		public void O01() throws Exception{
			assertEquals("Error in Organization.getRoom(String)", validString, organization.getRoom(validString).getID());
		}
		
		@Test(expected = Exception.class )
		public void O02() throws Exception{
			assertNotEquals("Error in Organization.getRoom(String)", invalidString, organization.getRoom(invalidString));
		}
	}
	
	public static class testGetEmployee{
		//unit under test
		private Organization organization;
		
		//mock employee object
		private Person employee;
		
		//field object for reflection
		Field field;
		
		//params
		private String validString = "A real employee";
		private String invalidString = "Not a real employee";
		private ArrayList<Person> employees;
		
		@Before
		public void init(){
			//Instantiate unit under test
			organization = new Organization();
			
			//Instantiate mock employee
			employee = createNiceMock(Person.class); //createNiceMock inherits the classes methods rather than returning default values
			
			// instantiate ArrayList param
			employees = new ArrayList<Person>();
			employees.add(employee);
			
			//expect tells EasyMock to listen for an object call, i.e. room.getID()
			EasyMock.expect(employee.getName()).andStubReturn(validString); //andStubReturn means the expected method will return the given value on every call
			EasyMock.replay(employee);
			
			//Reflection
			//Find field declaration & turn off the private keyword
			try {
				field = organization.getClass().getDeclaredField("employees");
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			field.setAccessible(true);
			try {
				field.set(organization, employees);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		@After
		public void cleanUp(){
			//Null objects so garbage cleaner removes after scope
			organization = null;
			employee = null;
			employees = null;
			field = null;
		}
		
		@Test
		public void O03() throws Exception{
			assertEquals("Error in Organization.getEmployee(String)", validString, organization.getEmployee(validString).getName());
		}
		
		@Test(expected = Exception.class)
		public void O04() throws Exception{
			assertNotEquals("Error in Organization.getEmployee(String)", invalidString, organization.getEmployee(invalidString).getName());
		}
	}
}
