import java.util.ArrayList;

public class Cthulhu implements Contract{
    private int xLoc;
    private int yLoc;
    private int size;
    private int nTenacle;
    private ArrayList<String> storage;
    private String lastAction = null;
    private ArrayList<String> lastPara;

    public Cthulhu () {
        this.xLoc = 0;
        this.yLoc = 0;
        this.size = 10;
        this.nTenacle = 8;
        this.storage = new ArrayList<String>();
        this.lastPara = new ArrayList<String>();
    }

    /**
     * Constructor using when the int size and int number of tenacle are given
     * @param size
     * @param nTenacle
     */
    public Cthulhu (int size, int nTenacle) {
        this();
        this. size = size;
        this.nTenacle = nTenacle;
    }


    public void grab(String item) {
        if (this.storage.size() < nTenacle) {
            this.storage.add(item);
            System.out.println("Grab " + item);
            this.lastAction = "grab";
            this.lastPara.clear();
            this.lastPara.add(item);
            System.out.println(this.storage);
        } else {
            System.out.println("Number of tenacles: " + nTenacle);
            System.out.println("Storage:" + this.storage);
            throw new RuntimeException("No more tenacles to grab things!"); 
        } 
    }

    public String drop(String item){
        if (this.storage.contains(item)) {
            this.storage.remove(item);
            System.out.println("Drop " + item);
            this.lastPara.clear();
            this.lastAction = "drop";
            this.lastPara.add(item);
            System.out.println(this.storage);
            return item;
        } else {
            System.out.println("Here is the storage:");
            System.out.println(this.storage);
            throw new RuntimeException("No such item to drop");
        }
        
    }

    public void examine(String item){
        if (this.storage.contains(item)) {
            String exItem = item + " (examined)";
            int iIndex = this.storage.indexOf(item);
            this.lastPara.clear();
            this.lastPara.add(item);
            this.lastPara.add(exItem);
            this.storage.set(iIndex, exItem);
            this.lastAction = "examine";
            System.out.println(this.storage);
        } else {
            System.out.println("Here is the storage:");
            System.out.println(this.storage);
            throw new RuntimeException("No such item to examine");
        }
        System.out.println("Examining...San Check!");    
    }

    public void use(String item){
        if (this.storage.contains(item)) {
            String useItem = item + " (used)";
            int iIndex = this.storage.indexOf(item);
            this.storage.set(iIndex, useItem);
            this.lastAction = "use";
            this.lastPara.clear();
            this.lastPara.add(item);
            this.lastPara.add(useItem);
            System.out.println(this.storage);
        } else {
            System.out.println("Here is the storage:");
            System.out.println(this.storage);
            throw new RuntimeException("No such item to use");
        }
        System.out.println("Use..." + item + "!");
    }

    public boolean walk(String direction){
        if (direction.equals("south")) {
            this.yLoc -= 10;
            System.out.println("Walk 10 units to south.");
        } else if (direction.equals("north")) {
            this.yLoc += 10;
            System.out.println("Walk 10 units to north.");
        } else if (direction.equals("east")) {
            this.xLoc += 10;
            System.out.println("Walk 10 units to east.");
        } else if (direction.equals("west")) {
            this.xLoc -= 10;
            System.out.println("Walk 10 units to west.");
        } else {
            throw new RuntimeException("Needs to be \"north\", \"south\", \"east\", or \"west\"");
        }  
        this.lastAction = "walk";
        this.lastPara.clear();
        this.lastPara.add(direction);
        return true; 
    }

    public boolean fly(int x, int y){
        this.xLoc = x;
        this.yLoc = y;
        this.lastPara.clear();
        this.lastPara.add(String.valueOf(x));
        this.lastPara.add(String.valueOf(y));
        this.lastAction = "fly";
        System.out.println("Warning! Cthulhu is approaching (" + this.xLoc + ", " + this.yLoc +")......Well he's there. Good luck!" );
        return true;
    }

    public Number shrink() {
        if (this.storage.size() == this.nTenacle) {
            throw new RuntimeException("The storage is at its full load. Cannot reduce the number of tenacles!");
        } else {
            this.size -= 1;
            this.nTenacle -= 1;
            this.lastAction = "shrink";
            this.lastPara.clear();
            this.lastPara.add(null);
            System.out.println("The cthulhu shrinks! The size is " + this.size + "; The number of tenacles is: " + this.nTenacle);
            return this.size;
        }    
    }

    public Number grow() {
        this.size += 1;
        this.nTenacle += 1;
        this.lastAction = "grow";
        this.lastPara.clear();
        this.lastPara.add(null);
        System.out.println("The cthulhu grows! The size is " + this.size + "; The number of tenacles is: " + this.nTenacle);
        return this.size;
    }

    public void rest(){
        this.lastAction = "rest";
        this.lastPara.clear();
        this.lastPara.add(null);
        System.out.println("GOOD NEWS! He's resting!");
    }

    public void undo(){
        if (this.lastAction == null) {
            throw new RuntimeException("No action can be retrieved");
        } else if (this.lastAction.equals("grab")) {
            this.undoGrab();
            this.lastAction = null;
        } else if (this.lastAction.equals("drop")) {
            this.undoDrop();
            this.lastAction = null;
        } else if (this.lastAction.equals("examine")) {
            this.undoExamine();
            this.lastAction = null;
        } else if (this.lastAction.equals("use")) {
            this.undoUse();
            this.lastAction = null;
        } else if (this.lastAction.equals("walk")) {
            this.undoWalk();
            this.lastAction = null;
        } else if (this.lastAction.equals("fly")) {
            this.undoFly();
            this.lastAction = null;
        } else if (this.lastAction.equals("shrink")) {
            this.undoShrink();
            this.lastAction = null;
        } else if (this.lastAction.equals("grow")) {
            this.undoGrow();
            this.lastAction = null;
        } else {
            this.undoRest();
            this.lastAction = null;
        }
    }

    private void undoGrab () {
        int idx = this.storage.size();
        this.storage.remove(idx-1);
        System.out.println("Undo grab: drop the " + this.storage.get(idx-1));
        System.out.println(this.storage);
    }

    private void undoDrop () {
        String item = this.lastPara.get(0);
        this.storage.add(item);
        System.out.println("Undo drop: grab the " + item);
        System.out.println(this.storage);
    }

    private void undoExamine () {
        String item = this.lastPara.get(0);
        String exItem = this.lastPara.get(1);
        int idx = this.storage.indexOf(exItem);
        this.storage.set(idx, item);
        System.out.println("Undo previous examination");
        System.out.println(this.storage);
    }

    private void undoUse () {
        String item = this.lastPara.get(0);
        String useItem = this.lastPara.get(1);
        int idx = this.storage.indexOf(useItem);
        this.storage.set(idx, item);
        System.out.println("Undo previous use");
        System.out.println(this.storage);
    }

    private void undoWalk () {
        String direction = this.lastPara.get(0);
        if (direction.equals("south")) {
            this.yLoc += 10;
        } else if (direction.equals("north")) {
            this.yLoc -= 10;
        } else if (direction.equals("east")) {
            this.xLoc -= 10;
        } else {
            this.xLoc += 10;
        }
        System.out.println("Undo walk: walk 10 units to " + direction );
    }

    private void undoFly () {
        int x = Integer.parseInt(this.lastPara.get(0));
        int y = Integer.parseInt(this.lastPara.get(1));
        this.xLoc -= x;
        this.yLoc -= y;
        System.out.println("Undo fly: the location is (" + this.xLoc + "," + this.yLoc + ")");
    }

    private void undoShrink () {
        this.size += 1;
        this.nTenacle -= 1;
        System.out.println("Undo shrink: the size is " + this.size + "; the number of tenacles is: " + this.nTenacle);
    }

    private void undoGrow () {
        this.size -= 1;
        this.nTenacle -= 1;
        System.out.println("Undo grow: the size is " + this.size + "; the number of tenacles is: " + this.nTenacle);
    }

    private void undoRest () {
        System.out.println("Undo rest: The monster is waking!");
    }

    public static void main(String[] args) {
        Cthulhu paopao = new Cthulhu();
        System.out.println(paopao.storage.size());
        System.out.println(paopao.nTenacle);
        paopao.grab("human_1");
        paopao.grab("human_2");
        paopao.grab("human_3");
        paopao.grab("human_4");
        paopao.grab("human_5");
        paopao.grab("human_6");
        paopao.grab("human_7");
        paopao.grab("human_8");
        paopao.examine("human_1");
        paopao.grow();
        paopao.undo();
    }

}