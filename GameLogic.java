import java.util.ArrayList;
import java.util.Arrays;

public class GameLogic implements PlayableLogic{

    private boolean flagTurns = false;//A Boolean value that will help transfer turns
    ConcretePiece [][] map = new ConcretePiece [11][11];//Initializing the map

    ConcretePlayer defender = new ConcretePlayer(true);// this player own the king
    ConcretePlayer attacker = new ConcretePlayer(false);// need to play first

    //This is a set (without repetitions) of locations where the tools have stepped
    private ArrayList <Position> forCompare4 = new ArrayList<>();

    ConcretePiece[] attackPositions = {
            new Pawn(attacker, "A1", 1, new Position(3, 0)),// "♟" - black
            new Pawn(attacker, "A2", 2, new Position(4, 0)),
            new Pawn(attacker, "A3", 3, new Position(5, 0)),
            new Pawn(attacker, "A4", 4, new Position(6, 0)),
            new Pawn(attacker, "A5", 5, new Position(7, 0)),
            new Pawn(attacker, "A6", 6, new Position(5, 1)),
            new Pawn(attacker, "A7", 7, new Position(0, 3)),
            new Pawn(attacker, "A9", 9, new Position(0, 4)),
            new Pawn(attacker, "A11", 11, new Position(0, 5)),
            new Pawn(attacker, "A15", 15, new Position(0, 6)),
            new Pawn(attacker, "A17", 17, new Position(0, 7)),
            new Pawn(attacker, "A12", 12, new Position(1, 5)),
            new Pawn(attacker, "A8", 8, new Position(10, 3)),
            new Pawn(attacker, "A10", 10, new Position(10, 4)),
            new Pawn(attacker, "A14", 14, new Position(10, 5)),
            new Pawn(attacker, "A16", 16, new Position(10, 6)),
            new Pawn(attacker, "A18", 18, new Position(10, 7)),
            new Pawn(attacker, "A13", 13, new Position(9, 5)),
            new Pawn(attacker, "A20", 20, new Position(3, 10)),
            new Pawn(attacker, "A21", 21, new Position(4, 10)),
            new Pawn(attacker, "A22", 22, new Position(5, 10)),
            new Pawn(attacker, "A23", 23, new Position(6, 10)),
            new Pawn(attacker, "A24", 24, new Position(7, 10)),
            new Pawn(attacker, "A19", 19, new Position(5, 9))
    };
    ConcretePiece[] defencePositions = {
            new King(defender, "K7", 7, new Position(5, 5)), // white
            new Pawn(defender, "D1", 1, new Position(5, 3)), // "♙" - white
            new Pawn(defender, "D2", 2, new Position(4, 4)),
            new Pawn(defender, "D3", 3, new Position(5, 4)),
            new Pawn(defender, "D4", 4, new Position(6, 4)),
            new Pawn(defender, "D5", 5, new Position(3, 5)),
            new Pawn(defender, "D6", 6, new Position(4, 5)),
            new Pawn(defender, "D8", 8, new Position(6, 5)),
            new Pawn(defender, "D9", 9, new Position(7, 5)),
            new Pawn(defender, "D10", 10, new Position(4, 6)),
            new Pawn(defender, "D11", 11, new Position(5, 6)),
            new Pawn(defender, "D12", 12, new Position(6, 6)),
            new Pawn(defender, "D13", 13, new Position(5, 7))
    };

    public GameLogic (){
        startGame();
    }

       public void startGame(){// Default opening

           for (int i = 0; i <= 10; i++) {//Applying all cells as null
               for (int j = 0; j <= 10; j++) {
                   map[i][j] = null;
               }
           }
       //Manual application of the cells, on which game pieces sit
       map[5][5] = defencePositions[0];// King
       map[5][3] = defencePositions[1];// "♙" - white
       map[4][4] = defencePositions[2];
       map[5][4] = defencePositions[3];
       map[6][4] = defencePositions[4];
       map[3][5] = defencePositions[5];
       map[4][5] = defencePositions[6];
       map[6][5] = defencePositions[7];
       map[7][5] = defencePositions[8];
       map[4][6] = defencePositions[9];
       map[5][6] = defencePositions[10];
       map[6][6] = defencePositions[11];
       map[5][7] = defencePositions[12];

       map[3][0]  = attackPositions[0];// "♟" - black
       map[4][0]  = attackPositions[1];
       map[5][0]  = attackPositions[2];
       map[6][0]  = attackPositions[3];
       map[7][0]  = attackPositions[4];
       map[5][1]  = attackPositions[5];
       map[0][3]  = attackPositions[6];
       map[0][4]  = attackPositions[7];
       map[0][5]  = attackPositions[8];
       map[0][6]  = attackPositions[9];
       map[0][7]  = attackPositions[10];
       map[1][5]  = attackPositions[11];
       map[10][3] = attackPositions[12];
       map[10][4] = attackPositions[13];
       map[10][5] = attackPositions[14];
       map[10][6] = attackPositions[15];
       map[10][7] = attackPositions[16];
       map[9][5]  = attackPositions[17];
       map[3][10] = attackPositions[18];
       map[4][10] = attackPositions[19];
       map[5][10] = attackPositions[20];
       map[6][10] = attackPositions[21];
       map[7][10] = attackPositions[22];
       map[5][9]  = attackPositions[23];

           for (int i = 0; i <= 10; i++) {//Applying the strings (e.g. A3) of those players who are on a certain position
               for (int j = 0; j <= 10; j++) {
                  if(map[i][j]!=null){
                      Position p = new Position(i,j);
                      forCompare4.add(p);
                      p.addString(map[i][j].get_ID());
                  }
               }
           }
    }

    /**The `move` function represents the movement logic for a chess piece on a game board. It checks various
     * conditions such as whose turn it is, whether the destination is occupied, if the move is diagonal or within
     * a row/column, and special cases related to the board corners. If all conditions are met, the function updates
     * the board, switches turns, handles pawn captures, and checks for the end of the game. The function returns
     * `true` if the move is valid and done, and `false` otherwise.*/
    @Override
    public boolean move(Position a, Position b) {
        if(isSecondPlayerTurn()){if(getPieceAtPosition(a).getOwner().equals(defender)){return false;}}//Who can't move (whose turn is it)
        if(!isSecondPlayerTurn()){if(getPieceAtPosition(a).getOwner().equals(attacker)){return false;}}

            if (map[b.get_x()][b.get_y()] != null) {return false;}//The desired place is taken

            if ((a.get_x() != b.get_x()) && (a.get_y() != b.get_y())) {return false;}//Diagonal displacement
            if ((b.get_x() == 0 && b.get_y() == 0) || (b.get_x() == 0 && b.get_y() == 10) || (b.get_x() == 10 && b.get_y() == 0) || (b.get_x() == 10 && b.get_y() == 10)) {
                if (!getPieceAtPosition(a).getType().equals("♔")) {return false;}//Pawn wants to go to the corner
            }
            //You want to move to another empty square, and you are not a Pawn who wants to go to a corner
            if (a.get_x() == b.get_x()) {
                int min = Math.min(a.get_y(), b.get_y());
                int max = Math.max(a.get_y(), b.get_y());
                for (int i = min + 1; i < max; i++) {if (map[a.get_x()][i] != null) {return false;} }

                map[b.get_x()][b.get_y()] = map[a.get_x()][a.get_y()];//column update
                map[a.get_x()][a.get_y()] = null;
                    flagTurns = !flagTurns;//change of turn

                map[b.get_x()][b.get_y()].AddPosition(b);

                eat(b);

                map[b.get_x()][b.get_y()].add_square(b.distance(a,b));

                //An attempt to put a player's Position and a string (which distinguishes each player, for example A6)
                // into Position if he has not already walked there
                boolean apperance = false;
                for (int i = 0; i < forCompare4.size(); i++) {
                    if(b.get_x() == forCompare4.get(i).get_x() && b.get_y() == forCompare4.get(i).get_y()) {
                        forCompare4.get(i).addString(map[b.get_x()][b.get_y()].get_ID());
                            apperance = true;
                    }
                }
                if(apperance == false){forCompare4.add(b);b.addString(map[b.get_x()][b.get_y()].get_ID());}

                isGameFinished();
                return true;
            }
            if (a.get_y() == b.get_y()) {
                int min = Math.min(a.get_x(), b.get_x());
                int max = Math.max(a.get_x(), b.get_x());
                for (int i = min + 1; i < max; i++) {if (map[i][a.get_y()] != null) {return false;}}

                map[b.get_x()][b.get_y()] = map[a.get_x()][a.get_y()];//row update
                map[a.get_x()][a.get_y()] = null;
                    flagTurns = !flagTurns;//change of turn

                map[b.get_x()][b.get_y()].AddPosition(b);

                eat(b);

                map[b.get_x()][b.get_y()].add_square(b.distance(a,b));

                //An attempt to put a player's Position and a string (which distinguishes each player, for example A6)
                // into Position if he has not already walked there
                boolean apperance = false;
                for (int i = 0; i < forCompare4.size(); i++) {
                    if(b.get_x()==(forCompare4.get(i).get_x()) && b.get_y()==(forCompare4.get(i).get_y())) {
                            forCompare4.get(i).addString(map[b.get_x()][b.get_y()].get_ID());
                            apperance = true;
                    }
                }
                if(apperance == false){forCompare4.add(b);b.addString(map[b.get_x()][b.get_y()].get_ID());}

                isGameFinished();

                return true;
            }
        return true;//Moving to a free space without moving over other tools
    }

    @Override
    public Piece getPieceAtPosition(Position position) {return map[position.get_x()][position.get_y()];}
   @Override
   public Player getFirstPlayer() {return defender;}
    @Override
    public Player getSecondPlayer() {return attacker;}//He plays first

    /**In this function, two cases must be checked:
     1. Did the king reach one of the corners (the defender wins)
     2. Is the king surrounded from 4 directions or 3 at the edge of the board (the attacker wins)
     If there is an entry for one of the above two options, someone has won and we will activate the 4 comparators we built.
     Explanation of the way of realization:
     Since I populated each side (defenders and attackers) in a separate array, it was easier for me to do the
     first comparator manually (meaning print the winning side first and then the losing side). In the other comparators,
     I poured each side's array into an array to hold both of them, so I implemented them in separate functions.*/
    @Override
    public boolean isGameFinished() {
       if(isKingAtEdge()){//Possibility of victory for the defender

           ConcretePiece [] copy = new ConcretePiece[defencePositions.length];
           for (int i = 0; i < defencePositions.length; i++) {
               copy[i] = defencePositions[i];
           }
           Arrays.sort(copy,new ComparatorBySteps());
           for (int i = 0; i < copy.length; i++) {
               if(copy[i]._positions.size() == 1){continue;}
               System.out.print(copy[i].get_ID() + ":" + " ");
               System.out.print("[");
               copy[i].getAllPositions();
               System.out.println("]");
           }
           ConcretePiece [] copy1 = new ConcretePiece[attackPositions.length];
           for (int i = 0; i < attackPositions.length; i++) {
               copy1[i] = attackPositions[i];
           }
           Arrays.sort(copy1,new ComparatorBySteps());
           for (int i = 0; i < copy1.length; i++) {
               if(copy1[i]._positions.size() == 1){continue;}
               System.out.print(copy1[i].get_ID() + ":" + " ");
               System.out.print("[");
               copy1[i].getAllPositions();
               System.out.println("]");

           }
           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           defender.setIfYouWinGetTrue(true);
           sortByKills();

           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           sortBySquare();

           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           sortByPiece();

           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           return true;}

       if(theKingIsSurrunder()){//Possibility of victory for the attacker

           ConcretePiece [] copy1 = new ConcretePiece[attackPositions.length];
           for (int i = 0; i < attackPositions.length; i++) {
               copy1[i] = attackPositions[i];
           }
           Arrays.sort(copy1,new ComparatorBySteps());
           for (int i = 0; i < copy1.length; i++) {
               if(copy1[i]._positions.size() == 1){continue;}
               System.out.print(copy1[i].get_ID() + ":" + " ");
               System.out.print("[");
               copy1[i].getAllPositions();
               System.out.println("]");

           }
           ConcretePiece [] copy = new ConcretePiece[defencePositions.length];
           for (int i = 0; i < defencePositions.length; i++) {
               copy[i] = defencePositions[i];
           }
           Arrays.sort(copy,new ComparatorBySteps());
           for (int i = 0; i < copy.length; i++) {
               if(copy[i]._positions.size() == 1){continue;}
               System.out.print(copy[i].get_ID() + ":" + " ");
               System.out.print("[");
               copy[i].getAllPositions();
               System.out.println("]");
           }
           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           attacker.setIfYouWinGetTrue(true);
           sortByKills();

           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           sortBySquare();

           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           sortByPiece();

           for (int i = 0; i < 75; i++) {System.out.print("*");}
           System.out.println();

           return true;}

        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {return !flagTurns;}//The use of this function is to transfer turns
    @Override
    public void reset() {
        //Added a win to the last player who played
        if(!isSecondPlayerTurn() && isGameFinished()){attacker.addWins();}
        if(isSecondPlayerTurn() & isGameFinished()){defender.addWins();}

        //startGame();
        flagTurns = false;
        for (int i = 0; i < attackPositions.length; i++) {//Reset all the variables of a player on the attacker's side
            attackPositions[i]._square = 0;
            ((Pawn) attackPositions[i]).set_kills(0);
            //TODO remove _position
            attackPositions[i]._positions.clear();
        }
        for (int i = 0; i < defencePositions.length; i++) {//Reset all player variables on the defender side
            defencePositions[i]._square = 0;
            if(!defencePositions[i].getType().equals("♔")){
                ((Pawn) defencePositions[i]).set_kills(0);
                //TODO remove _position
                defencePositions[i]._positions.clear();
            }
        }
        //Reset all the names of the different tools, which walked in a certain location
        for (int i = 0; i < forCompare4.size(); i++) {
            forCompare4.get(i)._howManyPiecesWalkHere.clear();
        }
        forCompare4.clear();//Resetting the places where tools have walked

        //Initializing the game by initializing the builder, and all the variables added during the game
        startGame();

        //Initializing the starting position in the arraylist of the positions where each player was, for example: A6: [(5, 1)]
        for (int i = 0; i <= 10 ; i++) {
            for (int j = 0; j <= 10 ; j++) {
                if (map[i][j] != null) {
                    map[i][j].AddPosition(new Position(i, j));
                }
            }
        }


        defender.setIfYouWinGetTrue(false);
        attacker.setIfYouWinGetTrue(false);
    }
    @Override
    public void undoLastMove() {}
    @Override
    public int getBoardSize() {return 11;}
    public boolean isKingAtEdge (){//An auxiliary function, which checks if the king has reached one of the corners
        if (map[0][0] != null && map[0][0].getType().equals("♔")){return true;}
        if (map[10][0] != null && map[10][0].getType().equals("♔")){return true;}
        if (map[0][10] != null && map[0][10].getType().equals("♔")){return true;}
        if (map[10][10] != null && map[10][10].getType().equals("♔")){return true;}
       return false;
    }

    /**The `eat' function represents the logic for a chess piece (pawn) capturing opponent pieces based on specific
     * rules. It checks adjacent positions in various directions and removes enemy pieces if the conditions for
     * capture are met. The function also handles special cases related to the pawn's movement and capture abilities,
     * considering the board boundaries and the king's corner as additional conditions for capturing.*/
    public void eat (Position b){
    if (getPieceAtPosition(b).getType().equals("♔")) {return ;}//the king do not eat any piece

            if(b.get_y() != 1 && b.get_y() !=0){

            if(map[b.get_x()][b.get_y()-1]!=null && !map[b.get_x()][b.get_y()-1].getType().equals("♔") && !map[b.get_x()][b.get_y()-1].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                if(map[b.get_x()][b.get_y()-2]!=null && !map[b.get_x()][b.get_y()-2].getType().equals("♔") && map[b.get_x()][b.get_y()-2].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())) {
                    map[b.get_x()][b.get_y() - 1] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
            }

            if(b.get_y() != 9 && b.get_y() != 10){

            if(map[b.get_x()][b.get_y()+1]!=null && !map[b.get_x()][b.get_y()+1].getType().equals("♔") && !map[b.get_x()][b.get_y()+1].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                if(map[b.get_x()][b.get_y()+2]!=null&& !map[b.get_x()][b.get_y()+2].getType().equals("♔") && map[b.get_x()][b.get_y()+2].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                    map[b.get_x()][b.get_y()+1] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
            }

            if(b.get_x() != 0 && b.get_x() != 1){

            if(map[b.get_x()-1][b.get_y()]!=null && !map[b.get_x()-1][b.get_y()].getType().equals("♔") && !map[b.get_x()-1][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())) {
                if (map[b.get_x() - 2][b.get_y()] != null && !map[b.get_x() - 2][b.get_y()].getType().equals("♔") && map[b.get_x() - 2][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())) {
                    map[b.get_x() - 1][b.get_y()] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
            }

            if(b.get_x() != 9 && b.get_x() != 10){

            if(map[b.get_x()+1][b.get_y()]!=null && !map[b.get_x()+1][b.get_y()].getType().equals("♔") && !map[b.get_x()+1][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                if(map[b.get_x()+2][b.get_y()]!=null && !map[b.get_x()+2][b.get_y()].getType().equals("♔") && map[b.get_x()+2][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())) {
                    map[b.get_x() + 1][b.get_y()] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
            }
            if(b.get_x() == 1){
                if(map[b.get_x()-1][b.get_y()]!=null && !map[b.get_x()-1][b.get_y()].getType().equals("♔") && !map[b.get_x()-1][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                    map[b.get_x() - 1][b.get_y()] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                }
            }
            if(b.get_x() == 9){
                if(map[b.get_x()+1][b.get_y()]!=null && !map[b.get_x()+1][b.get_y()].getType().equals("♔") && !map[b.get_x()+1][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                    map[b.get_x() + 1][b.get_y()] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                }
            }
            if(b.get_y() == 1){
                if(map[b.get_x()][b.get_y()-1]!=null && !map[b.get_x()][b.get_y()-1].getType().equals("♔") && !map[b.get_x()][b.get_y()-1].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                    map[b.get_x()][b.get_y() - 1] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                }
            }
            if(b.get_y() == 9){
                if(map[b.get_x()][b.get_y()+1]!=null && !map[b.get_x()][b.get_y()+1].getType().equals("♔") && !map[b.get_x()][b.get_y()+1].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                    map[b.get_x()][b.get_y()+1] = null;
                    ((Pawn) getPieceAtPosition(b)).addKills();
                }
            }
            if(b.get_x() == 0 || b.get_x() == 10){//The king's corner is also considered the edge (and you can eat with it)
                if(b.get_y() == 8){
                    if(map[b.get_x()][b.get_y()+1]!=null && !map[b.get_x()][b.get_y()+1].getType().equals("♔") && !map[b.get_x()][b.get_y()+1].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                        map[b.get_x()][b.get_y()+1] = null;
                        ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
                if(b.get_y() == 2){
                    if(map[b.get_x()][b.get_y()-1]!=null && !map[b.get_x()][b.get_y()-1].getType().equals("♔") && !map[b.get_x()][b.get_y()-1].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                        map[b.get_x()][b.get_y() - 1] = null;
                        ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
            }
            if(b.get_y() == 0 || b.get_y() == 10){//The king's corner is also considered the edge (and you can eat with it)
                if(b.get_x() == 2){
                    if(map[b.get_x()-1][b.get_y()]!=null && !map[b.get_x()-1][b.get_y()].getType().equals("♔") && !map[b.get_x()-1][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                        map[b.get_x() - 1][b.get_y()] = null;
                        ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
                if(b.get_x() == 8){
                    if(map[b.get_x()+1][b.get_y()]!=null && !map[b.get_x()+1][b.get_y()].getType().equals("♔") && !map[b.get_x()+1][b.get_y()].getOwner().equals(map[b.get_x()][b.get_y()].getOwner())){
                        map[b.get_x() + 1][b.get_y()] = null;
                        ((Pawn) getPieceAtPosition(b)).addKills();
                    }
                }
            }
    }
/**The function `theKingIsSurrunder` checks if the king, represented by its last known position on a game map,
 * is surrounded by enemy units belonging to the "attacker" player. It examines neighboring positions in the
 * cardinal directions and returns `true' if the king has enemy units
 * on all four sides or three sides, excluding the side facing the board boundary.
 * The function returns `false` otherwise.*/
   public boolean theKingIsSurrunder (){
       //Saving the x and y values of the last position where the king was
       int x = defencePositions[0].get_LastPosition().get_x();//
       int y = defencePositions[0].get_LastPosition().get_y();

      if((x > 0 && x < 10) && (y >0 && y <10)) {
        if (map[x + 1][y] != null && map[x + 1][y].getOwner().equals(attacker)) {
            if (map[x - 1][y] != null && map[x - 1][y].getOwner().equals(attacker)) {
                if (map[x][y + 1] != null && map[x][y + 1].getOwner().equals(attacker)) {
                    if (map[x][y - 1] != null && map[x][y - 1].getOwner().equals(attacker)) {
                        return true;
                    }
                }
            }
        }
      }
      if(x == 0 && y!=10 && y!=0){
          if (map[x + 1][y] != null && map[x + 1][y].getOwner().equals(attacker)) {
                  if (map[x][y + 1] != null && map[x][y + 1].getOwner().equals(attacker)) {
                      if (map[x][y - 1] != null && map[x][y - 1].getOwner().equals(attacker)) {
                          return true;
                      }
                  }
              }
          }
      if(x == 10 && y!=10 && y!=0){
              if (map[x - 1][y] != null && map[x - 1][y].getOwner().equals(attacker)) {
                  if (map[x][y + 1] != null && map[x][y + 1].getOwner().equals(attacker)) {
                      if (map[x][y - 1] != null && map[x][y - 1].getOwner().equals(attacker)) {
                          return true;
                      }
                  }
              }
          }
      if(y == 0 && x!=0 && x!=10){
          if (map[x + 1][y] != null && map[x + 1][y].getOwner().equals(attacker)) {
              if (map[x - 1][y] != null && map[x - 1][y].getOwner().equals(attacker)) {
                  if (map[x][y + 1] != null && map[x][y + 1].getOwner().equals(attacker)) {
                          return true;
                  }
              }
          }
      }
      if(y == 10 && x!=0 && x!=10){
          if (map[x + 1][y] != null && map[x + 1][y].getOwner().equals(attacker)) {
              if (map[x - 1][y] != null && map[x - 1][y].getOwner().equals(attacker)) {
                      if (map[x][y - 1] != null && map[x][y - 1].getOwner().equals(attacker)) {
                          return true;
                }
              }
          }
        }
       return false;
   }

    /**A function of the kills comparator*/
    public void sortByKills() {
    Pawn[] copy = new Pawn[36];
    for (int i = 0; i < attackPositions.length; i++) {
        copy[i] = (Pawn) attackPositions[i];
    }
    for (int i = 1; i < defencePositions.length; i++) {
        copy[23 + i] = (Pawn) defencePositions[i];
    }
    Arrays.sort(copy, new CompareByKills());
    for (int i = 0; i < copy.length; i++) {
        if (copy[i].get_kills() == 0) {continue;}
        System.out.print(copy[i].get_ID() + ":" + " ");
        System.out.println(copy[i].get_kills()+ " " + "kills");
        }
    }

    /**Function of the comparator of the squares*/
    public void sortBySquare() {
        ConcretePiece[] copy = new ConcretePiece[37];
        for (int i = 0; i < attackPositions.length; i++) {
            copy[i] = attackPositions[i];
        }
        for (int i = 0; i < defencePositions.length; i++) {
            copy[24 + i] = defencePositions[i];
        }

        Arrays.sort(copy, new CmparatoBySquare());
        for (int i = 0; i < copy.length; i++) {
            if (copy[i].get_square() == 0) {continue;}
            System.out.print(copy[i].get_ID() + ":" + " ");
            System.out.println(copy[i].get_square()+ " " + "squares");
        }
    }

    /**A function that uses the last comparator (several different tools walk on a certain slot)*/
    public void sortByPiece() {
        Position [] copy = new Position[forCompare4.size()];

        for (int i = 0; i < copy.length; i++) {
            copy[i] = forCompare4.get(i);
        }

        Arrays.sort(copy, new CompareByPosition());
        for (int i = 0; i < copy.length; i++) {
            if (copy[i]._howManyPiecesWalkHere.size() == 1) {continue;}
            System.out.print("(" + copy[i].get_x() +"," + " " + copy[i].get_y() + ")");
            System.out.println(copy[i]._howManyPiecesWalkHere.size()+ " " + "pieces");
        }
    }

}
