public class King extends ConcretePiece{
    private Player _player;
    private String _ID;
    public King (Player attackerOrDefender, String id){
        super(attackerOrDefender,id);
        _player = attackerOrDefender;
        _ID = id;
    };
    @Override
    public String getType() {
        return "♔";
    }// white king
    @Override
    public Player getOwner() {return super.getOwner();}

}
