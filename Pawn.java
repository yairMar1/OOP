import java.util.ArrayList;

public class Pawn extends ConcretePiece {
    private int _kills;

    //constructor
    public Pawn (ConcretePlayer attackerOrDefender, String id,int number,Position position){
        super(attackerOrDefender,id,number,position);
        _kills = 0;
        _square = 0;
    }

    //function
    public int get_kills() {return _kills;}
    public void set_kills(int kills) {this._kills = kills;}
    @Override
    public String getType() {
        if (!getOwner().isPlayerOne()){return "♟";}// Black pawn
        return "♙";// White pawn
    }
    @Override
    public ConcretePlayer getOwner() {return super.getOwner();}
    public void addKills(){
        _kills++;
    }

}
