import java.util.ArrayList;

public class Pawn extends ConcretePiece {
    private int _kills;
    private String _ID;
    private ConcretePlayer _player;
    private int _number;
    //private ArrayList <Position> _positions = new ArrayList<>();
    private int _square;

    //constructor
    public Pawn (ConcretePlayer attackerOrDefender, String id,int number,Position position){
        super(attackerOrDefender,id,number,position);
        _kills = 0;
         _player = attackerOrDefender;
         _ID = id;
         _number = number;
      // _positions.add(position);
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
    //public void AddPosition(Position p){_positions.add(p);}//Adding a member to arraylist
    //public Position get_LastPosition() {return _positions.get(_positions.size()-1);}//Getting the last member
    //public void getAllPositions (){for (Position position : _positions) {System.out.print("(" + position.get_x() +","+ position.get_y() + ")");}}
   /* public void set_square(int _square) {this._square = _square;}
    public int get_square() {return _square;}*/
}
