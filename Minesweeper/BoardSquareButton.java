import javax.swing.*;
import java.awt.*;

public class BoardSquareButton extends JButton
{
    private int x, y;
    public boolean isMine, investigated, userSuggestedMine;

    public BoardSquareButton(int width, int height, int x, int y) {//, Color bgColour){
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        this.x = x;
        this.y = y;
        initialise(Color.gray);

    }

    public void initialise(Color bgColour){
        this.setBackground(bgColour);
        this.setFont(new Font("Calibri",Font.BOLD, 60));
        this.setText("?"); //"("+this.x+", "+this.y+")"
        this.investigated = false;
        this.userSuggestedMine = false;
        this.isMine = false;
    }

    public void setMine(){
        this.isMine = true;
    }

    public void setInvestigated(){
        this.investigated = true;
    }

    public void setUserSuggestedMine(){
        this.userSuggestedMine = true;
    }


}
