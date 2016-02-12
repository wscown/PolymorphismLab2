import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by wscown on 1/20/16.
 * Bug causes a null pointer exception when trying to prove function runs
 */

enum Colors{
    RED, GREEN, BLUE, YELLOW, PURPLE;

    public static Colors getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}

enum Texture{
    GLOSSY, MATTE, SPECKLED, SMOOTH;

    public static Texture getRandom() {
        return values()[(int) (Math.random() * values().length)];}

}

class BoringThing implements Thing{
    @Override
    public ArrayList<Object> getProperties() {
        return null;
    }
};

class ColorfulThing implements Thing{
    Colors color;

    ColorfulThing(Colors color){
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }

    @Override
    public ArrayList<Object> getProperties() {
        return new ArrayList<Object>(){{add(color);}};
    }
}

class CoatedThing extends ColorfulThing{
    Texture texture;

    CoatedThing(Texture texture, Colors color){
        super(color);
        this.texture = texture;
    }

    @Override
    public ArrayList<Object> getProperties() {
        return new ArrayList<Object>(){{add(color); add(texture);}};
    }
}

class ThingContainer{
    private Thing[] colorfulThings;
    private int arrayCounter = 0;
    private String contName;

    ThingContainer(int arraySize){
        colorfulThings = new Thing[arraySize];
    }

    ThingContainer(int arraySize, String contName){
        this(arraySize);
        this.contName = contName;
    }

    ThingContainer(Thing[] colorfulThings){
        this(colorfulThings.length);

        for(Thing c: colorfulThings){
            if(c != null) {
                this.add(c);
            }
        }
    }

    ThingContainer(Thing[] colorfulThings, String contName){
        this(colorfulThings);
        this.contName = contName;
    }

    public String add(Thing c){

        String fullMessage = "ThingContainer " + contName + " is full.";

        if(arrayCounter == colorfulThings.length){
            System.out.println(fullMessage);
            return fullMessage;
        }else
        {
            colorfulThings[arrayCounter] = c;
            if(arrayCounter - 1 != colorfulThings.length) {
                arrayCounter++;
            }
            return null;
        }
    }

    public void printThings(){

        for(int i = 0; i < arrayCounter; i++){
            Thing printobj = colorfulThings[i];

            if(printobj == null){
                System.out.println(printobj);
            }else if(printobj instanceof BoringThing){
                System.out.println("BoringThing");
            }else if(printobj instanceof ColorfulThing){
                System.out.println("ColorfulThing " + colorfulThings[i].getProperties().get(0));
            }else if(printobj instanceof CoatedThing){
                System.out.println("CoatedThing " + colorfulThings[i].getProperties().get(0) + " " + colorfulThings[i].getProperties().get(1));
            }
        }
    }

    public Thing pop(){
        if(arrayCounter > 0) {
            Thing c = colorfulThings[arrayCounter - 1];
            deleteEntry(arrayCounter - 1);
            return c;
        }
        return null;
    }

    public Thing remove(Colors c){
        Thing result;

        for(int i = 0; i < arrayCounter; i++) {
            if(colorfulThings[i].getProperties() != null) {
                if (c == colorfulThings[i].getProperties().get(0)) {
                    result = colorfulThings[i];
                    deleteEntry(i);
                    return result;
                }
            }
        }
        return null;
    }

    //Method to search and remove things by coating
    public Thing remove(Texture t){
        Thing result;

        for(int i = 0; i < arrayCounter; i++) {
            if(colorfulThings[i].getProperties() != null && colorfulThings[i].getProperties().size() == 2) {
                if (t == colorfulThings[i].getProperties().get(1)) {
                    result = colorfulThings[i];
                    deleteEntry(i);
                    return result;
                }
            }
        }
        return null;
    }

    public Thing remove(Thing ct){
        return remove((Colors)ct.getProperties().get(0));
    }

    //Deletes object[i] from the array
    public void deleteEntry(int i){

        for(int k = i+1; k < colorfulThings.length; k++) {
            colorfulThings[k-1] = colorfulThings[k];
        }

        colorfulThings[colorfulThings.length - 1] = null;

        arrayCounter--;

    }

    public int getArrayCounter(){
        return arrayCounter;
    }

    public Thing getColorfulThingEntry(int i){
        return colorfulThings[i];
    }

    public Thing[] getColorfulThings(){
        return colorfulThings;
    }
}

public class PolymorphismLab2 {

    public static void main(String [] args){
        ThingContainer thingContainer1 = new ThingContainer(12, "thingContainer1");
        ThingContainer thingContainer2 = new ThingContainer(13, "thingContainer2");
        ThingContainer thingContainer3 = new ThingContainer(14, "thingContainer3");

        for(int i = 0; i < 5; i++){

            thingContainer1.add(new ColorfulThing(Colors.getRandom()));
            thingContainer1.add(new BoringThing());
            thingContainer1.add(new CoatedThing(Texture.getRandom(), Colors.getRandom()));

            thingContainer2.add(new ColorfulThing(Colors.getRandom()));
            thingContainer2.add(new BoringThing());
            thingContainer2.add(new CoatedThing(Texture.getRandom(), Colors.getRandom()));

            thingContainer3.add(new ColorfulThing(Colors.getRandom()));
            thingContainer3.add(new BoringThing());
            thingContainer3.add(new CoatedThing(Texture.getRandom(), Colors.getRandom()));
        }

        System.out.println("Used pop on thingContainer1 and removed a Thing from thingContainer1 of type " + thingContainer1.pop().getClass().toString());
        System.out.println("Used pop on thingContainer1 and removed a Thing from thingContainer1 of type " + thingContainer1.pop().getClass().toString());
        System.out.println("Used pop on thingContainer1 and removed a Thing from thingContainer1 of type " + thingContainer1.pop().getClass().toString());

        System.out.println("Used pop on thingContainer2 and removed a Thing from thingContainer2 of type " + thingContainer2.pop().getClass().toString());
        System.out.println("Used pop on thingContainer2 and removed a Thing from thingContainer2 of type " + thingContainer2.pop().getClass().toString());
        System.out.println("Used pop on thingContainer2 and removed a Thing from thingContainer2 of type " + thingContainer2.pop().getClass().toString());

        System.out.println("Used pop on thingContainer3 and removed a Thing from thingContainer3 of type " + thingContainer3.pop().getClass().toString());
        System.out.println("Used pop on thingContainer3 and removed a Thing from thingContainer3 of type " + thingContainer3.pop().getClass().toString());
        System.out.println("Used pop on thingContainer3 and removed a Thing from thingContainer3 of type " + thingContainer3.pop().getClass().toString());

        //Test removing Things based on the Colors enum
        Thing testA = thingContainer1.remove(Colors.getRandom());
        Thing testB = thingContainer2.remove(Colors.getRandom());
        Thing testC = thingContainer3.remove(Colors.getRandom());

        //Test removing Things based on the Textures enum
        Thing testD = thingContainer1.remove(Texture.getRandom());
        Thing testE = thingContainer2.remove(Texture.getRandom());
        Thing testF = thingContainer3.remove(Texture.getRandom());

        if(testA != null){
            System.out.println("Used remove on thingContainer1 with an enum as a parameter to remove the first entry of type " + testA.getProperties().get(0));
        }

        if(testB != null){
            System.out.println("Used remove on thingContainer2 with an enum as a parameter to remove the first entry of type " + testB.getProperties().get(0));
        }

        if(testC != null){
            System.out.println("Used remove on thingContainer3 with an enum as a parameter to remove the first entry of type " + testC.getProperties().get(0));
        }

        if(testD != null){
            System.out.println("Used remove on thingContainer1 with an enum as a parameter to remove the first entry of type " + testD.getProperties().get(1));
        }

        if(testE != null){
            System.out.println("Used remove on thingContainer2 with an enum as a parameter to remove the first entry of type " + testE.getProperties().get(1));
        }

        if(testF != null){
            System.out.println("Used remove on thingContainer3 with an enum as a parameter to remove the first entry of type " + testF.getProperties().get(1));
        }


        thingContainer3.add(new ColorfulThing(Colors.PURPLE));
        System.out.println("Used remove on thingContainer3 with a as its parameter to remove the first entry of type " + thingContainer3.remove(new ColorfulThing(Colors.PURPLE)).getProperties().get(0));



        ThingContainer thingContainer4 = new ThingContainer(thingContainer3.getColorfulThings());

        System.out.println("ThingCounter3 " + thingContainer3.getArrayCounter());

        System.out.println("thingCounter4 " + thingContainer4.getArrayCounter());

        System.out.println("A test of the constructor that accepts an array of Things. The following two ThingContainers should be equal:");
        System.out.println("\nThingContainer1");
        thingContainer3.printThings();
        System.out.println("\nThingContainer2");
        thingContainer4.printThings();
    }
}




