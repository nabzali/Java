import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PsynsaMain{

    public final static int WIDTH = 5, HEIGHT = 5, NUM_MINES = 5;
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
                board.storeButton(i, j, new BoardSquareButton(i, j));
                frame.add(board.getButton(i, j));
                board.getButton(i, j).addMouseListener(new MyMouseHandler());
            }
        }
        board.createMines(NUM_MINES);
        frame.pack();
        frame.setVisible(true);
    }

    public class MyMouseHandler extends MouseAdapter
    {

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
                        /// runs from here if left click
                        else if (!btn.isMine) {
                            btn.setInvestigated();

                            if (board.countSurrounding(i, j) == 0){
                                checkZeros(i, j);
                            }
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
        // public void mouseClicked(MouseEvent e) {}
        // public void mousePressed(MouseEvent e) {}
        // public void mouseReleased(MouseEvent e) {}
        // public void mouseEntered(MouseEvent e) {}
        // public void mouseExited(MouseEvent e) {}


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
                        if (board.countSurrounding(k, l) == 0){
                        //if theres a surrounding button with a zero...
                            checkZeros(k, l);
                        }
                        board.getButton(k, l).setText(Integer.toString(board.countSurrounding(k, l)));
                    }
                }
            }
        }
    }
}
