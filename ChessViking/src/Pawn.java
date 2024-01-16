public class Pawn extends ConcretePiece {
    private int _kills;
    private String _ID;
    private Player _player;

    //constructor
    public Pawn (Player attackerOrDefender, String id){
        super(attackerOrDefender,id);
        _kills = 0;
         _player = attackerOrDefender;
         _ID = id;
    }

    //function
    public int get_kills() {return _kills;}
    public void set_kills(int _kills) {this._kills = _kills;}
    @Override
    public String getType() {
        if (!getOwner().isPlayerOne()){return "♟";}// Black pawn
        return "♙";// White pawn
    }
    @Override
    public Player getOwner() {return super.getOwner();}

    public void addKills(){
        _kills++;
    }

}
