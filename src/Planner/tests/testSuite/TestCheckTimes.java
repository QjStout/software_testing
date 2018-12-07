package testSuite;

import au.edu.sccs.csp3105.BookingPlanner.*;

import org.junit.Test;

public class TestCheckTimes {
	

	// Tests with valid inputs
	
	@Test
	public void CL01() throws TimeConflictException {
		Calendar.checkTimes(1, 1, 0, 1);
	}
	@Test
	public void CL03() throws TimeConflictException {
		Calendar.checkTimes(2, 27, 22, 23);
	}
	@Test
	public void CL04() throws TimeConflictException {
		Calendar.checkTimes(11, 30, 1, 22);
	}
	@Test
	public void CL05() throws TimeConflictException {
		Calendar.checkTimes(12, 31, 1, 2);
	}
	@Test
	public void CL08() throws TimeConflictException {
		Calendar.checkTimes(8, 2, 3, 4);
	}
	@Test
	public void CL16() throws TimeConflictException {
		Calendar.checkTimes(4, 20, 5, 7);
	}
	@Test
	public void CL21() throws TimeConflictException {
		Calendar.checkTimes(7, 31, 13, 15);
	}
	
	
	// Tests that should break prog
	@Test (expected = TimeConflictException.class)
	public void CL02() throws TimeConflictException {
		Calendar.checkTimes(0, 2, 2, 3);
	}
	@Test (expected = TimeConflictException.class)
	public void CL06() throws TimeConflictException {
		Calendar.checkTimes(13, 5, 3, 4);
	}
	@Test (expected = TimeConflictException.class)
	public void CL07() throws TimeConflictException {
		Calendar.checkTimes(3, 0, 7, 8);
	}
	@Test (expected = TimeConflictException.class)
	public void CL09() throws TimeConflictException {
		Calendar.checkTimes(5, 32, 10, 11);
	}
	@Test (expected = TimeConflictException.class)
	public void CL10() throws TimeConflictException {
		Calendar.checkTimes(8, 13, -1, 4);
	}
	@Test (expected = TimeConflictException.class)
	public void CL11() throws TimeConflictException {
		Calendar.checkTimes(9, 8, 24, 20);
	}
	@Test (expected = TimeConflictException.class)
	public void CL12() throws TimeConflictException {
		Calendar.checkTimes(6, 23, 23, 18);
	}
	@Test (expected = TimeConflictException.class)
	public void CL13() throws TimeConflictException {
		Calendar.checkTimes(4, 25, 9, 24);
	}
	@Test (expected = TimeConflictException.class)
	public void CL14() throws TimeConflictException {
		Calendar.checkTimes(2, 5, 2, -1);
	}
	@Test (expected = TimeConflictException.class)
	public void CL15() throws TimeConflictException {
		Calendar.checkTimes(9, 21, 0, 0);
	}
	@Test (expected = TimeConflictException.class)
	public void CL17() throws TimeConflictException {
		Calendar.checkTimes(-5, 15, 5, 12);
	}
	@Test (expected = TimeConflictException.class)
	public void CL18() throws TimeConflictException {
		Calendar.checkTimes(15, 17, 10, 13);
	}
	@Test (expected = TimeConflictException.class)
	public void CL19() throws TimeConflictException {
		Calendar.checkTimes(8, -7, 15, 16);
	}
	@Test (expected = TimeConflictException.class)
	public void CL20() throws TimeConflictException {
		Calendar.checkTimes(5, 40, 11, 14);
	}
	@Test (expected = TimeConflictException.class)
	public void CL22() throws TimeConflictException {
		Calendar.checkTimes(3, 22, -6, 3);
	}
	@Test (expected = TimeConflictException.class)
	public void CL23() throws TimeConflictException {
		Calendar.checkTimes(4, 6, 29, 21);
	}
	@Test (expected = TimeConflictException.class)
	public void CL24() throws TimeConflictException {
		Calendar.checkTimes(5, 14, 3, -4);
	}
	@Test (expected = TimeConflictException.class)
	public void CL25() throws TimeConflictException {
		Calendar.checkTimes(12, 9, 10, 31);
	}

}
