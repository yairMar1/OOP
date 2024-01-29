import java.util.Comparator;

public class CmparatoBySquare implements Comparator<ConcretePiece> {
        public int compare (ConcretePiece a,ConcretePiece b){
            int ans = a.get_square() - b.get_square();
            if(ans > 0){return -1;}
            else if (ans < 0){return 1;}

            int ans2  = a.get_number() - b.get_number();
            if(ans2 > 0){return 1;}
            else if (ans2 < 0){return -1;}


            int ans3 =  a.get_number() - b.get_number();
            if(a.getOwner().isIfYouWinGetTrue()==true){

                ans3 = -1;
                return ans3;
            }
            else{
                ans3 = 1;
                return ans3;
            }

        }
    }
