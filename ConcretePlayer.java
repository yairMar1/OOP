import java.util.Objects;

public class ConcretePlayer implements Player{//player one = true = defender.
    private int Wins;
    private boolean _WhichPlayer;
    private boolean _ifYouWinGetTrue;//A value that will change if your team wins

    public ConcretePlayer (boolean player){
        Wins = 0;
        _WhichPlayer = player;
        _ifYouWinGetTrue = false;
    }
    @Override
    //The name 'isPlayerOne' suggests that the method returns true for playerOne and false for playerTwo
    public boolean isPlayerOne() {return _WhichPlayer;}
    @Override
    public int getWins() {return Wins;}
    public void addWins() {Wins++;}
    public void setIfYouWinGetTrue(boolean ifYouWinGetTrue) {this._ifYouWinGetTrue = ifYouWinGetTrue;}
    public boolean isIfYouWinGetTrue() {return _ifYouWinGetTrue;}
}
