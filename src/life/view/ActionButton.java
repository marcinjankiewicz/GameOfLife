package life.view;

import life.utils.IconPath;

import javax.swing.*;
import java.awt.*;

public class ActionButton extends JButton {
    public ActionButton(IconPath pathIcon){
        super();
        Image icon = new ImageIcon(pathIcon.getPath())
                .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(icon);
        this.setIcon(imageIcon);
        this.setSize(30, 30);
        this.setPreferredSize(new Dimension(30, 30));
        this.setMaximumSize(new Dimension(30, 30));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
