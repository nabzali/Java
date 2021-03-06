import javax.swing.*;
import java.awt.*;

public class BoardSquareButton extends JButton
{

    boolean isMine, investigated, userSuggestedMine;

    public BoardSquareButton() {
        setMinimumSize(new Dimension(120, 120));
        setPreferredSize(new Dimension(120, 120));
        this.setFocusPainted(false);
        this.initialise();
    }

    public void initialise(){
        this.setBackground(Color.gray);
        this.setFont(new Font("Calibri",Font.BOLD, 60));
        this.setText("?"); //"("+this.x+", "+this.y+")"
        this.investigated = false;
        this.userSuggestedMine = false;
        this.isMine = false;
    }

    public void setMine(){
        this.isMine = true;
        //this.setBackground(Color.yellow);
    }

    public void setInvestigated(){
        this.investigated = true;
        this.setBackground(Color.green);
    }

    public void setUserSuggestedMine(){
        this.userSuggestedMine = true;
        this.setText("X");
        this.setBackground(Color.orange);
    }
}
