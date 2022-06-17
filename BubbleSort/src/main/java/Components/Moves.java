package Components;

/**
 * class Moves specifies number of moves in game, and determines if movement
 * between two containers is currently active
 */

public class Moves {
    private int moves;
    private boolean isActive;

    /**
     * Constructor of class Moves
     */

    public Moves(){
        this.moves = 0;
        this.isActive = false;
    }

    /**
     * getter
     * @return number of moves which were done
     */

    public int getMoves() {
        return moves;
    }

    /**
     * moves incrementing void
     */

    public void inc(){
        this.moves++;
    }

    /**
     * this is lock when transition is playing
     * @return if there is movement between two containers
     */

    public boolean isActive() {
        return isActive;
    }

    /**
     * setter
     * when transition has started isActive is set to true
     */

    public void setActiveTrue() {
        isActive = true;
    }

    /**
     * setter
     * when transition has done isActive is set to false
     */

    public void setActiveFalse(){
        isActive = false;
    }

    /**
     * toString method
     * @return output for scene after level
     */

    @Override
    public String toString() {
        return "You made " + moves + " moves";
    }
}
