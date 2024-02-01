import java.util.ArrayList;

public class King extends ConcretePiece{

    public King (ConcretePlayer attackerOrDefender, String id,int number,Position position){
        super(attackerOrDefender,id,number,position);
        _square = 0;
    };
    @Override
    public String getType() {
        return "♔";
    }// white king
    @Override
    public ConcretePlayer getOwner() {return super.getOwner();}

}
