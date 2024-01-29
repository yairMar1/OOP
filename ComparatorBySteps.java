import java.util.Comparator;
public class ComparatorBySteps implements Comparator<ConcretePiece> {
    public int compare(ConcretePiece a, ConcretePiece b){
        int ans = a._positions.size() - b._positions.size();
        if (ans > 0){return 1;}
        else if (ans < 0) {return -1;}

        int ans1 = a.get_number() - b.get_number();
        if(ans1 > 0) {return 1;}
        return -1;
    }
}
