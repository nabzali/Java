import java.awt.*;
import java.util.Random;


public class Board {

    private BoardSquareButton buttonArray[][] = new BoardSquareButton[PsynsaMain.HEIGHT][PsynsaMain.WIDTH];

    public BoardSquareButton getButton(int x, int y){
        return buttonArray[x][y];
    }

    public void storeButton(int x, int y, BoardSquareButton button){
        buttonArray[x][y] = button;
    }

    public void initialiseAll(){
        for (int i = 0; i < PsynsaMain.HEIGHT; i++){
            for (int j = 0; j < PsynsaMain.WIDTH; j++){
                buttonArray[i][j].initialise(Color.gray);
            }
        }
    }

    public void createMines(int n) {
        Random rand = new Random();
        int num = 0;
        while (num < n) {
            int rand1 = rand.nextInt(PsynsaMain.HEIGHT);
            int rand2 = rand.nextInt(PsynsaMain.WIDTH);
            if (!buttonArray[rand1][rand2].isMine) {
                buttonArray[rand1][rand2].setMine();
                num++;
            }

        }
    }

    public void finished(){
        for (int i = 0; i < PsynsaMain.HEIGHT; i++){
            for (int j = 0; j < PsynsaMain.WIDTH; j++){
                if (buttonArray[i][j].isMine){
                    buttonArray[i][j].setBackground(Color.red);
                    buttonArray[i][j].setText("X");
                }
            }
        }
    }

    public boolean hasWon(){
        for (int i = 0; i < PsynsaMain.HEIGHT; i++){
            for (int j = 0; j < PsynsaMain.WIDTH; j++){
                if ((!buttonArray[i][j].investigated) && (!buttonArray[i][j].isMine)){
                    return false;
                }
            }
        }
        return true;
    }

    public int countSurrounding(int x, int y){
        int mine_count = 0;

        for (int i = x-1; i < x+2; i++){
            for (int j = y-1; j < y+2; j++){
                try{
                    getButton(i, j);
                }
                catch (IndexOutOfBoundsException e){
                    continue;
                }
                if ((getButton(i, j) != getButton(x, y)) && (getButton(i, j).isMine)){
                    mine_count++;
                }
            }
        }

        /*
        if ((x > 0) && (x < PsynsaMain.HEIGHT - 1) && (y > 0) && (y < PsynsaMain.WIDTH - 1)){ //checking middle buttons
            for (int i = y-1; i < y+2; i++){
                if (getButton(x-1, i).isMine) mine_count++;
                if (getButton(x+1, i).isMine) mine_count++;
            }
            if (getButton(x, y-1).isMine) mine_count++;
            if (getButton(x, y+1).isMine) mine_count++;
        }
        if ((x == 0) && (y < PsynsaMain.WIDTH - 1) && (y > 0)){
            for (int i = y-1; i < y+2; i++) if (getButton(x+1, i).isMine) mine_count++;
            if (getButton(x, y-1).isMine) mine_count++;
            if (getButton(x, y+1).isMine) mine_count++;
        }

        if ((x == PsynsaMain.HEIGHT - 1) && (y < PsynsaMain.WIDTH - 1) && (y > 0)){
            for (int i = y-1; i < y+2; i++) if (getButton(x-1, i).isMine) mine_count++;
            if (getButton(x, y-1).isMine) mine_count++;
            if (getButton(x, y+1).isMine) mine_count++;
        }


        if ((y == 0) && (x < PsynsaMain.HEIGHT - 1) && (x > 0)){
            for (int i = x-1; i < x+2; i++) if (getButton(i, y+1).isMine) mine_count++;
            if (getButton(x-1, y).isMine) mine_count++;
            if (getButton(x+1, y).isMine) mine_count++;
        }

        if ((y == PsynsaMain.WIDTH - 1) && (x < PsynsaMain.HEIGHT - 1) && (x > 0)){
            for (int i = x-1; i < x+2; i++) if (getButton(i, y-1).isMine) mine_count++;
            if (getButton(x-1, y).isMine) mine_count++;
            if (getButton(x+1, y).isMine) mine_count++;
        }

        if ((x == 0) && (y == 0)){
            if (getButton(x+1,y).isMine) mine_count++;
            if (getButton(x+1,y+1).isMine) mine_count++;
            if (getButton(x,y+1).isMine) mine_count++;
        }
        if ((x == 0) && (y == PsynsaMain.WIDTH - 1)){
            if (getButton(x+1,y).isMine) mine_count++;
            if (getButton(x+1,y-1).isMine) mine_count++;
            if (getButton(x,y-1).isMine) mine_count++;
        }
        if ((x == PsynsaMain.HEIGHT - 1) && (y == 0)){
            if (getButton(x,y+1).isMine) mine_count++;
            if (getButton(x-1,y+1).isMine) mine_count++;
            if (getButton(x-1,y).isMine) mine_count++;
        }
        if ((x == PsynsaMain.HEIGHT - 1) && (y == PsynsaMain.WIDTH - 1)){
            if (getButton(x,y-1).isMine) mine_count++;
            if (getButton(x-1,y-1).isMine) mine_count++;
            if (getButton(x-1,y).isMine) mine_count++;
        }
        */
        return mine_count;
    }

}
