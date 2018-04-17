import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PsynsaMain{

    public final static int WIDTH = 10, HEIGHT = 10, NUM_MINES = 5;
    public Board board = new Board();

    public static void main(String[] args) {
        new PsynsaMain();
    }

    public PsynsaMain() {

        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Minesweeper | Developed by Nabeel Ali");
        frame.getContentPane().setLayout(new GridLayout(HEIGHT, WIDTH));

        for (int i = 0; i < HEIGHT; i++) { //
            for (int j = 0; j < WIDTH; j++) {
                board.storeButton(i, j, new BoardSquareButton());
                frame.add(board.getButton(i, j));
                board.getButton(i, j).addMouseListener(new MyMouseHandler());
            }
        }
        board.createMines(NUM_MINES);
        frame.pack();
        frame.setVisible(true);
    }

    public class MyMouseHandler extends MouseAdapter {

        public void checkZeros(int x, int y){
            for (int k = x-1; k < x+2; k++){
                for (int l = y-1; l < y+2; l++){
                    try{
                        board.getButton(k, l);
                    }
                    catch (Exception e2){
                        continue;
                    }
                    if ((board.getButton(k, l) != board.getButton(x, y)) && (!board.getButton(k, l).investigated)){
                        board.getButton(k, l).setInvestigated();
                        if (board.countSurrounding(k, l) == 0) this.checkZeros(k, l); // recursive call, runs on any neighbouring zero's to the current zero button.
                        board.getButton(k, l).setText(Integer.toString(board.countSurrounding(k, l)));
                    }
                }
            }
        }

        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < HEIGHT; i++){
                for (int j = 0; j < WIDTH; j++){
                    if (board.getButton(i, j) == e.getSource()){
                        BoardSquareButton btn = board.getButton(i, j); //new object REFERENCE btn
                        if (SwingUtilities.isRightMouseButton(e) && !btn.investigated){
                            if (btn.userSuggestedMine){
                                btn.setBackground(Color.gray);
                                btn.setText("?");
                                btn.userSuggestedMine = false;
                            }
                            else btn.setUserSuggestedMine();
                        }
                        else if (!btn.isMine) { /// runs from here if left click
                            btn.setInvestigated();
                            if (board.countSurrounding(i, j) == 0) this.checkZeros(i, j);
                            btn.setText(Integer.toString(board.countSurrounding(i, j)));
                        }
                        else { //i.e if btn isMine, then you've lost and the game will restart after clicking "OK" on dialog box.
                            board.finished();
                            JOptionPane.showMessageDialog(null, "Click OK to continue.", "You lose!", JOptionPane.ERROR_MESSAGE);
                            board.initialiseAll();
                            board.createMines(NUM_MINES);
                        }
                        if (board.hasWon()){
                            JOptionPane.showMessageDialog(null, "Click OK to continue.", "You Win!", JOptionPane.ERROR_MESSAGE);
                            board.initialiseAll();
                            board.createMines(NUM_MINES);
                        }
                    }
                }
            }
        }
    }
}
