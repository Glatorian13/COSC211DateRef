import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import org.junit.*;

public class DateRefTest {

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
    DateRef testDateRef = new DateRef();
    assertEquals(1, testDateRef.getMonth());
    assertEquals(1, testDateRef.getDay());
    assertEquals(1000, testDateRef.getYear());
  }

  @Test
  public void testSetDateIntIntInt() {
    DateRef mySetDateRefIntIntInt = new DateRef();
    mySetDateRefIntIntInt.setDate(2, 3, 1994);
    assertEquals(2, mySetDateRefIntIntInt.getMonth());
    assertEquals(3, mySetDateRefIntIntInt.getDay());
    assertEquals(1994, mySetDateRefIntIntInt.getYear());
  }

  @Test
  public void testSetDateStringIntInt() {
    DateRef mySetDateRefStringIntInt = new DateRef();
    mySetDateRefStringIntInt.setDate("July", 8, 2001);
    // .getMonth() always returns number
    assertEquals(7, mySetDateRefStringIntInt.getMonth());
    assertEquals(8, mySetDateRefStringIntInt.getDay());
    assertEquals(2001, mySetDateRefStringIntInt.getYear());
  }

  @Test
  public void testSetDateInt() {
    DateRef mySetDateRefYearInt = new DateRef();
    mySetDateRefYearInt.setDate(1990);
    assertEquals(1990, mySetDateRefYearInt.getYear());
  }

  @Test
  public void testSetYear() {
    DateRef mySetYearInt = new DateRef();
    for (int i = 1000; i < 9999; i++) {
      mySetYearInt.setYear(i);
      assertEquals(i, mySetYearInt.getYear());
    }
  }

  @Test
  public void testSetMonth() {
    DateRef mySetMonthInt = new DateRef();
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
    DateRef mySetDayInt = new DateRef();

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
    DateRef myGetMonthInt = new DateRef();

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
    DateRef myGetDay = new DateRef();
    int i = 1;
    while (i < 32) {
      myGetDay.setDay(i);
      assertEquals(i, myGetDay.getDay());
      i++;
    }
  }

  @Test
  public void testGetYear() {
    DateRef myGetYear = new DateRef();
    assertEquals(1000, myGetYear.getYear());
  }

  @Test
  public void testToString() {
    DateRef myToString = new DateRef();
    assertEquals("January 1, 1000", myToString.toString());
  }

  @Test
  public void testEqualsDate() {
    DateRef testDateRef1 = new DateRef();
    DateRef testDateRef2 = new DateRef();
    assertTrue(testDateRef1.equals(testDateRef2));
  }

  @Test
  public void testPrecedes() {
    DateRef testDateRef1 = new DateRef();
    DateRef testDateRef2 = new DateRef(2, 1, 1000);
    DateRef testDateRef3 = new DateRef(1, 2, 1000);
    DateRef testDateRef4 = new DateRef(1, 1, 2000);
    assertTrue(testDateRef1.precedes(testDateRef2));
    assertTrue(testDateRef1.precedes(testDateRef3));
    assertTrue(testDateRef1.precedes(testDateRef4));

  }

  @Test
  public void testReadInput() {
    final String testString = "March 12 2002";
    provideIn(testString);

    DateRef testInOutDateRef = new DateRef();

    testInOutDateRef.readInput();
    assertEquals(3, testInOutDateRef.getMonth());
    assertEquals(12, testInOutDateRef.getDay());
    assertEquals(2002, testInOutDateRef.getYear());

  }

}
