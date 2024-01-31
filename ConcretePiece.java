import java.util.ArrayList;

public abstract class ConcretePiece implements Piece{
    private ConcretePlayer _owner;
    private String _ID;
    private int _number;
    protected ArrayList <Position> _positions = new ArrayList<>();
    protected int _square;

    public ConcretePiece (ConcretePlayer player , String id, int number, Position position){
        _owner = player;
        _ID = id;
        _number = number;
        _positions.add(position);
        _square = 0;
    }
    @Override
    public ConcretePlayer getOwner() {return _owner;}
    public void set_owner(ConcretePlayer player) {this._owner = player;}
    @Override
    public String getType() {return null;}
    public int get_number() {return _number;}
    public String get_ID() {return _ID;}
    public Position get_LastPosition() {return _positions.get(_positions.size() -1);}//Getting the last member
    public void AddPosition(Position p){_positions.add(p);}//Adding a member to arraylist
    public void getAllPositions (){
        for (int i = 0; i < _positions.size()-1; i++) {
            System.out.print("(" + _positions.get(i).get_x() +","+ " " + _positions.get(i).get_y() + ")" + "," + " ");
        }
        System.out.print("(" + _positions.get(_positions.size()-1).get_x() +"," + " " + _positions.get(_positions.size()-1).get_y() + ")");
    }
    public void add_square(int square) {this._square += square;}
    public int get_square() {return _square;}

}
