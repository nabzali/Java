import java.awt.*;
import java.util.Random;


public class Board {

    private BoardSquareButton buttonArray[][] = new BoardSquareButton[5][5];

    public BoardSquareButton getButton(int x, int y){
        return buttonArray[x][y]; //
    }

    public void storeButton(int x, int y, BoardSquareButton button){
        buttonArray[x][y] = button; //
    }

    public void initialiseAll(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                buttonArray[i][j].initialise(Color.gray);
            }
        }
        //call .initialise() method for all elements in buttonArray using a loop
    }

    public void createMines(int n) {
        Random rand = new Random();
        int num = 0;
        while (num < n) {
            int rand1 = rand.nextInt(5);
            int rand2 = rand.nextInt(5);
            if (!buttonArray[rand1][rand2].isMine) {
                buttonArray[rand1][rand2].setMine();
                num++;
            }
        //randomly make mines by setting isMine to true for random elements in buttonArray.
        //use a while loop to do this, ensuring that it stops once there is n mines.
        //will need to use random library
        }
    }

    public void finished(){
    }

    public boolean hasWon(){
        //do this by looping through each button in buttonArray, and if (investigated = false and isMine = true) for any button,
        //then break out of the loop and return false.
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (!buttonArray[i][j].investigated){
                    return false;
                }
            }
        }
        return true; //if each and every button is either investigated, or has a mine
    }

    public int countSurrounding(int x, int y){
        int mine_count = 0;
        if ((x == 1 || x == 2 || x == 3) && (y == 1 || y == 2 || y == 3)){ //checking middle buttons
            for (int i = y-1; i < y+2; i++){
                if (getButton(x-1, i).isMine) mine_count++;
                if (getButton(x+1, i).isMine) mine_count++;
            }
            if (getButton(x, y-1).isMine) mine_count++;
            if (getButton(x, y+1).isMine) mine_count++;
        }
        if ((x == 0) && (y < 4) && (y > 0)){
            for (int i = y-1; i < y+2; i++) if (getButton(x+1, i).isMine) mine_count++;
            if (getButton(x, y-1).isMine) mine_count++;
            if (getButton(x, y+1).isMine) mine_count++;
        }

        if ((x == 4) && (y < 4) && (y > 0)){
            for (int i = y-1; i < y+2; i++) if (getButton(x-1, i).isMine) mine_count++;
            if (getButton(x, y-1).isMine) mine_count++;
            if (getButton(x, y+1).isMine) mine_count++;
        }


        if ((y == 0) && (x < 4) && (x > 0)){
            for (int i = x-1; i < x+2; i++) if (getButton(i, y+1).isMine) mine_count++;
            if (getButton(x-1, y).isMine) mine_count++;
            if (getButton(x+1, y).isMine) mine_count++;
        }

        if ((y == 4) && (x < 4) && (x > 0)){
            for (int i = x-1; i < x+2; i++) if (getButton(i, y-1).isMine) mine_count++;
            if (getButton(x-1, y).isMine) mine_count++;
            if (getButton(x+1, y).isMine) mine_count++;
        }

        if ((x == 0) && (y == 0)){
            if (getButton(x+1,y).isMine) mine_count++;
            if (getButton(x+1,y+1).isMine) mine_count++;
            if (getButton(x,y+1).isMine) mine_count++;
        }
        if ((x == 0) && (y == 4)){
            if (getButton(x+1,y).isMine) mine_count++;
            if (getButton(x+1,y-1).isMine) mine_count++;
            if (getButton(x,y-1).isMine) mine_count++;
        }

        if ((x == 4) && (y == 0)){
            if (getButton(x,y+1).isMine) mine_count++;
            if (getButton(x-1,y+1).isMine) mine_count++;
            if (getButton(x-1,y).isMine) mine_count++;
        }
        if ((x == 4) && (y == 4)){
            if (getButton(x,y-1).isMine) mine_count++;
            if (getButton(x-1,y-1).isMine) mine_count++;
            if (getButton(x-1,y).isMine) mine_count++;
        }
        return mine_count;
    }

}
