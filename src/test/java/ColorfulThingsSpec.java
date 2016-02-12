import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wscown on 1/26/16.
 */
public class ColorfulThingsSpec {
    @Test
    public void testColorfulThing(){

        ColorfulThing testRed = new ColorfulThing(Colors.RED);
        ColorfulThing testGreen = new ColorfulThing(Colors.GREEN);
        ColorfulThing testBlue = new ColorfulThing(Colors.BLUE);
        ColorfulThing testYellow = new ColorfulThing(Colors.YELLOW);
        ColorfulThing testPurple = new ColorfulThing(Colors.PURPLE);

        assertEquals("Should return an enum type within the group enumTester", Colors.RED.toString(), testRed.getColor().toString());
        assertEquals("Should return an enum type within the group enumTester", Colors.GREEN.toString(), testGreen.getColor().toString());
        assertEquals("Should return an enum type within the group enumTester", Colors.BLUE.toString(), testBlue.getColor().toString());
        assertEquals("Should return an enum type within the group enumTester", Colors.YELLOW.toString(), testYellow.getColor().toString());
        assertEquals("Should return an enum type within the group enumTester", Colors.PURPLE.toString(), testPurple.getColor().toString());
    }
}
