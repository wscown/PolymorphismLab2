import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by wscown on 1/20/16.
 */

public class ThingContainerSpec {

    //With
    int testValue1 = 5;
    int testValue2 = 10;
    int testValue3 = 10;

    //When
    ThingContainer testContainer1 = new ThingContainer(testValue1);
    ThingContainer testContainer2 = new ThingContainer(testValue2);
    ThingContainer testContainer3 = new ThingContainer(testValue3);

    @Test
    public void testThingContainerConstructor() {

        //Then
        assertEquals("Should return an initialised ArrayCounter value of 0", 0, testContainer1.getArrayCounter());
        assertEquals("Should return an initialised ArrayCounter value of 0", 0, testContainer2.getArrayCounter());

        assertEquals("Should return and initialised array length as specified by the constructor, in this case " + testValue1, testValue1, testContainer1.getColorfulThings().length);
        assertEquals("Should return and initialised array length as specified by the constructor, in this case " + testValue2, testValue2, testContainer2.getColorfulThings().length);
    }

    @Test
    public void testAdd() {

        //When

        //Populate testContainer1 Array using the add method
        for (int j = 0; j < testValue1; j++) {
            assertEquals("Ensure that adding a new ColorfulThing returns null and has side effect of entering the new ColorfulThing object", null, testContainer1.add(new ColorfulThing(Colors.RED)));
        }

        //Populate testContainer2 Array using the add method
        for (int j = 0; j < testValue2; j++) {
            assertEquals("Ensure that adding a new ColorfulThing returns null and has side effect of entering the new ColorfulThing object", null, testContainer2.add(new ColorfulThing(Colors.RED)));
        }

        //Then

        //Test that trying to enter another ColorfulThing produces the String "ThingContainer is full"
        assertEquals("Should return the String \"ThingContainer 1 is full\"", "ThingContainer is full", testContainer1.add(new ColorfulThing(Colors.RED)));
        assertEquals("Should return the String \"ThingContainer 2 is full\"", "ThingContainer is full", testContainer2.add(new ColorfulThing(Colors.RED)));

        //Check that all the entries in the two test arrays are of enum type RED
        for (int j = 0; j < testValue1; j++) {
            assertEquals("Should return an enum type of RED within entry" + j + "testContainer1's array of ColorfulThings", Colors.RED, testContainer1.getColorfulThings()[j].getProperties().get(0));
        }
        for (int j = 0; j < testValue2; j++) {
            assertEquals("Should return an enum type of RED within entry" + j + "testContainer2's array of ColorfulThings", Colors.RED, testContainer2.getColorfulThings()[j].getProperties().get(0));
        }

        //Array length of ThingContainer's array should remain stable
        assertEquals("Should return and initialised array length as specified by the constructor, in this case " + testValue1, testContainer1.getColorfulThings().length, testValue1);
        assertEquals("Should return and initialised array length as specified by the constructor, in this case " + testValue2, testContainer2.getColorfulThings().length, testValue2);
    }

    @Test
    public void testPrintThings(){

        ByteArrayOutputStream test = new ByteArrayOutputStream();

        PrintStream ps = System.out;
        System.setOut(new PrintStream(test));
        String testString = "";
        //When

        //Populate testContainer1 with all the enum values
        for (Colors c: Colors.values()) {
            testContainer3.add(new ColorfulThing(c));
            testString += c.toString()+ "\n";
        }

        testContainer3.printThings();

        //Reassign System.setOut to the original value (so you can print to screen again rather than the ByteArrayOutputStream!)
        System.setOut(ps);

        assertEquals("Printing the contents of testContainer1 does not return expected results", testString, test.toString());
    }


    @Test
    public void testPop() {

        //With

        ThingContainer testContainer5 = new ThingContainer(20);

        //Then

        //testContainer5 should be empty. Lets check that pop returns null
        assertEquals("Should return the last ColorfulThing object", null, testContainer5.pop());

        //When

        //Adding all the colors
        for (Colors c : Colors.values()) {
            testContainer5.add(new ColorfulThing(c));
        }

        //Then

        //Check there are 5 colors in the testContainer5 array
        assertEquals("Should have returned 5 colors in the array of testContainer5", 5, testContainer5.getArrayCounter());

        //Check that pop returns the last entered color object (which should be PURPLE)
        assertEquals("Should have returned the last entered color object in the array which is purple", Colors.PURPLE, testContainer5.pop().getProperties().get(0));

        //Check the array counter now records 4 color objects
        assertEquals("Should have returned 4 colors in the array of testContainer5", 4, testContainer5.getArrayCounter());
    }

    @Test
    public void testRemove(){

        testContainer3.add(new ColorfulThing(Colors.RED));
        testContainer3.add(new ColorfulThing(Colors.RED));
        testContainer3.add(new ColorfulThing(Colors.RED));
        testContainer3.add(new ColorfulThing(Colors.BLUE));
        testContainer3.add(new ColorfulThing(Colors.RED));
        testContainer3.add(new ColorfulThing(Colors.RED));
        testContainer3.add(new ColorfulThing(Colors.RED));
        testContainer3.add(new ColorfulThing(Colors.RED));
        testContainer3.add(new ColorfulThing(Colors.YELLOW));
        testContainer3.add(new ColorfulThing(Colors.RED));


        //Test ColorfulThing remove(ColorfulThing.Colors c) to ensure that it returns the first instance of an object with the enum type BLUE
        assertEquals("Should return the first object in the array that has enum type BLUE", new ColorfulThing(Colors.BLUE).getColor(), testContainer3.remove(Colors.BLUE).getProperties().get(0));
        assertEquals("Should return null as searching for enum type not contained in the array", null, testContainer3.remove(Colors.PURPLE));

        //Test ColorfulThing remove(ColorfulThing ct) to ensure that it returns the first instance of an object ct
        assertEquals("Should return the first object in the array that matches the input Object", new ColorfulThing(Colors.YELLOW).getColor(), testContainer3.remove(new ColorfulThing(Colors.YELLOW)).getProperties().get(0));
        assertEquals("Should return null as searching for enum type not contained in the array", null, testContainer3.remove(Colors.PURPLE));

    }
}
