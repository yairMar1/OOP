import java.util.Comparator;
public class CompareByKills implements Comparator <Pawn>{
    public int compare (Pawn a,Pawn b){
        int ans = a.get_kills() - b.get_kills();
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
