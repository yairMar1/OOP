public abstract class ConcretePiece implements Piece{
    private Player _owner;
    private String _ID;

    public ConcretePiece (Player player , String id){
        _owner = player;
        _ID = id;
    }
    @Override
    public Player getOwner() {return _owner;}
    public void set_owner(Player player) {this._owner = player;}
    @Override
    public String getType() {return null;}

}
