import java.util.ArrayList;

public class King extends ConcretePiece{
    private Player _player;
    private String _ID;
    private int _number;
    // public ArrayList <Position> _positions = new ArrayList<>();
    //private int _square;
    public King (Player attackerOrDefender, String id,int number,Position position){
        super(attackerOrDefender,id,number,position);
        _player = attackerOrDefender;
        _ID = id;
        _number = number;
        //_positions.add(position);
       // _square = 0;
    };
    @Override
    public String getType() {
        return "♔";
    }// white king
    @Override
    public Player getOwner() {return super.getOwner();}
    //public Position get_LastPosition() {return _positions.get(_positions.size() -1);}//Getting the last member
    //public void AddPosition(Position p){_positions.add(p);}//Adding a member to arraylist
    //public void getAllPositions (){for (Position position : _positions){System.out.print("(" + position.get_x() +","+ position.get_y() + ")");}}
    /*public void set_square(int _square) {this._square = _square;}
    public int get_square() {return _square;}*/
}
