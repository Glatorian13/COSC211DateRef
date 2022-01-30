import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import org.junit.*;

public class DateTest {

  // stuff testing user input
  // https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
  private final InputStream sysIn = System.in;
  private final PrintStream sysOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;

  @Before
  public void setUpOut() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  private void provideIn(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  /*
   * private String getOut() { return testOut.toString(); }
   */
  @After
  public void restoreOriginalInOut() {
    System.setIn(sysIn);
    System.setOut(sysOut);
  }

  /*
   * Cannot test methods that use System.exit because Eclipse is using JUnit 4.11. If on version 4.9
   * or newer, testing System.exit is simple Java 17 deprecated the security manager that you can
   * use to test
   */

  @Test
  public void testDate() {
    Date testDate = new Date();
    assertEquals(1, testDate.getMonth());
    assertEquals(1, testDate.getDay());
    assertEquals(1000, testDate.getYear());
  }

  @Test
  public void testSetDateIntIntInt() {
    Date mySetDateIntIntInt = new Date();
    mySetDateIntIntInt.setDate(2, 3, 1994);
    assertEquals(2, mySetDateIntIntInt.getMonth());
    assertEquals(3, mySetDateIntIntInt.getDay());
    assertEquals(1994, mySetDateIntIntInt.getYear());
  }

  @Test
  public void testSetDateStringIntInt() {
    Date mySetDateStringIntInt = new Date();
    mySetDateStringIntInt.setDate("July", 8, 2001);
    // .getMonth() always returns number
    assertEquals(7, mySetDateStringIntInt.getMonth());
    assertEquals(8, mySetDateStringIntInt.getDay());
    assertEquals(2001, mySetDateStringIntInt.getYear());
  }

  @Test
  public void testSetDateInt() {
    Date mySetDateYearInt = new Date();
    mySetDateYearInt.setDate(1990);
    assertEquals(1990, mySetDateYearInt.getYear());
  }

  @Test
  public void testSetYear() {
    Date mySetYearInt = new Date();
    for (int i = 1000; i < 9999; i++) {
      mySetYearInt.setYear(i);
      assertEquals(i, mySetYearInt.getYear());
    }
  }

  @Test
  public void testSetMonth() {
    Date mySetMonthInt = new Date();
    for (int i = 1; i < 13; i++) {
      mySetMonthInt.setMonth(i);
      assertEquals(i, mySetMonthInt.getMonth());
    }
    /*
     * mySetMonthInt.setMonth(0); assertEquals("Fatal Error", mySetMonthInt.getMonth());
     * mySetMonthInt.setMonth(14); assertEquals("Fatal Error", mySetMonthInt.getMonth());
     */
  }

  @Test
  public void testSetDay() {
    Date mySetDayInt = new Date();

    for (int i = 1; i < 32; i++) {
      mySetDayInt.setDay(i);
      assertEquals(i, mySetDayInt.getDay());
    }
    /*
     * mySetDayInt.setDay(0); assertEquals("Fatal Error", mySetDayInt.getDay());
     * mySetDayInt.setDay(44); assertEquals("Fatal Error", mySetDayInt.getDay());
     */
  }

  @Test
  public void testGetMonth() {
    Date myGetMonthInt = new Date();

    for (int i = 1; i < 13; i++) {
      myGetMonthInt.setMonth(i);
      assertEquals(i, myGetMonthInt.getMonth());
    }
    /*
     * myGetMonthInt.setMonth(13); assertEquals(0, myGetMonthInt.getMonth());
     */
  }

  @Test
  public void testGetDay() {
    Date myGetDay = new Date();
    int i = 1;
    while (i < 32) {
      myGetDay.setDay(i);
      assertEquals(i, myGetDay.getDay());
      i++;
    }
  }

  @Test
  public void testGetYear() {
    Date myGetYear = new Date();
    assertEquals(1000, myGetYear.getYear());
  }

  @Test
  public void testToString() {
    Date myToString = new Date();
    assertEquals("January 1, 1000", myToString.toString());
  }

  @Test
  public void testEqualsDate() {
    Date testDate1 = new Date();
    Date testDate2 = new Date();
    assertTrue(testDate1.equals(testDate2));
  }

  @Test
  public void testPrecedes() {
    Date testDate1 = new Date();
    Date testDate2 = new Date(2, 1, 1000);
    Date testDate3 = new Date(1, 2, 1000);
    Date testDate4 = new Date(1, 1, 2000);
    assertTrue(testDate1.precedes(testDate2));
    assertTrue(testDate1.precedes(testDate3));
    assertTrue(testDate1.precedes(testDate4));

  }

  @Test
  public void testReadInput() {
    final String testString = "March 12 2002";
    provideIn(testString);

    Date testInOutDate = new Date();

    testInOutDate.readInput();
    assertEquals(3, testInOutDate.getMonth());
    assertEquals(12, testInOutDate.getDay());
    assertEquals(2002, testInOutDate.getYear());

  }

}
