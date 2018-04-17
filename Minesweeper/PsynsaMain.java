import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PsynsaMain{

    final static int WIDTH = 200, HEIGHT = 200, NUM_MINES = 5;

    Board board = new Board();

    public static void main(String[] args) {
        new PsynsaMain();
    }

    public PsynsaMain() {

        JFrame frame = new JFrame();
        //frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Minesweeper | Nabeel Ali");
        frame.getContentPane().setLayout(new GridLayout(5, 5));

        for (int i = 0; i < 5; i++) { //
            for (int j = 0; j < 5; j++) {
                board.storeButton(i, j, new BoardSquareButton(WIDTH, HEIGHT, i, j));
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
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (board.getButton(i, j) == e.getSource()){
                        BoardSquareButton btn = board.getButton(i, j); //new object REFERENCE btn
                        if (SwingUtilities.isRightMouseButton(e)){
                            btn.userSuggestedMine = true;
                            btn.investigated = true;
                            btn.setText("X");
                            btn.setBackground(Color.orange);

                        }
                        else if (!btn.isMine) {
                            btn.investigated = true;
                            btn.setBackground(Color.green);
                            btn.setText(Integer.toString(board.countSurrounding(i, j)));
                        }
                        else { //i.e if btn isMine
                            btn.setBackground(Color.red);
                            btn.setText("X");
                            // you lose the game
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
    }

}
