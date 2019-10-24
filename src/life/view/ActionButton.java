package life.view;

import life.utils.IconPath;

import javax.swing.*;
import java.awt.*;

public class ActionButton extends JButton {
    private ActionButton() {
        super();
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public ActionButton(IconPath pathIcon) {
        this();
        Image icon = new ImageIcon(pathIcon.getPath())
                .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(icon);
        this.setIcon(imageIcon);
        this.setSize(30, 30);

    }

    public ActionButton(String name) {
        this();
        this.setText(name);
        this.setSize(70, 30);

    }
}
