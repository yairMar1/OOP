import java.util.ArrayList;
import java.util.Objects;

public class Position {
    private int _x;
    private int _y;
    public ArrayList <String> _howManyPiecesWalkHere;
    //constructor that takes initial coordinates (x, y) to create a position object with a specified starting point.
    public Position (int x, int y){
        _x = x;
        _y = y;
        _howManyPiecesWalkHere = new ArrayList<>();
    }

    //functions
    //public Position getPosition(){return _x,_y;}
    public int get_x() {return _x;}
    public int get_y() {return _y;}
    public void set_x(int _x) {this._x = _x;}
    public void set_y(int _y) {this._y = _y;}

    public int distance (Position a, Position b){
        if(a.get_x()==b.get_x()){return Math.abs(b.get_y() - a.get_y());}
        return Math.abs(b.get_x() - a.get_x());
    }
    /**This function was created, so that we can detect if a tool has already passed through this slot.
     If so, don't add it, otherwise you should add another walk tool in this slot*/
    public void addString (String s){if (!_howManyPiecesWalkHere.contains(s)){_howManyPiecesWalkHere.add(s);}}
    public void getAllString (){
        for (int i = 0; i < _howManyPiecesWalkHere.size(); i++) {
            System.out.print("(" + _howManyPiecesWalkHere.get(i) + ")" + ",");
        }
    }

    //Designed to prevent a player from "moving" to the same slot, where he is already
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return _x == position._x && _y == position._y;
    }
    @Override
    public int hashCode() {return Objects.hash(_x, _y);}
}