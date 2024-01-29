import java.util.Comparator;

public class CompareByPosition  implements Comparator<Position> {
    public int compare (Position a,Position b){
        int ans = a._howManyPiecesWalkHere.size() - b._howManyPiecesWalkHere.size();
        if(ans > 0){return 1;}
        else if (ans < 0) {return  -1;}

        int ans1 = a.get_x() - b.get_x();
        if(ans1 > 0){return 1;}
        else if (ans1 < 0) {return  -1;}

        int ans2 = a.get_y() - b.get_y();
        if(ans2 > 0){return 1;}
        return  -1;
    }

}
