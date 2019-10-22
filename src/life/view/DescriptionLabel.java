package life.view;

import life.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class DescriptionLabel extends JLabel {

    public DescriptionLabel(){
        super();
        this.setFont(new Font("Calibri", Font.BOLD, Constants.FONT_SIZE));
        this.setSize(140, 20);
    }
}
