public class Cthulhu implements Contract{

    //TODO
    public void grab(String item) {
        System.out.println("Grab" + item);
    }

    //TODO
    public String drop(String item){
        return item;
    }

    //TODO
    public void examine(String item){
        System.out.println("Examining...San Check!");
    }

    //TODO
    public void use(String item){
        System.out.println("Use..." + item + "!");
    }

    //TODO
    public boolean walk(String direction){
        System.out.println("Emmm not sure how to walk with a hundred and twenty-nine tentacles... but yeah cthulhu can walk");
        return true;
    }

    //TODO
    public boolean fly(int x, int y){
        System.out.println("Warning! Cthulhu is approaching (" + x + ", " + y +")......Well he's there. Good luck!" );
    }

    //TODO
    public Number shrink() {
        int n = 1;
        return n;
    }

    //TODO
    public Number grow() {
        int n = 1;
        return n;
    }

    //TODO
    public void rest(){
        System.out.println("GOOD NEWS! He's resting!");
    }

    //TODO
    public void undo(){
        System.out.println("UNDO");
    }
}