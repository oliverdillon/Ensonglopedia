package com.ensonglopedia.view.factories;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormattedBordersFactory {

    public final static Border RedLine = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red);
    public final static Border GreyLine =BorderFactory.createMatteBorder(4, 4, 4, 4, FormattedColorsFactory.Background);
    public final static Border GreenLine =BorderFactory.createMatteBorder(4, 4, 4, 4, FormattedColorsFactory.Foreground);
    public final static Border WhiteLine =BorderFactory.createMatteBorder(4, 4, 4, 4, FormattedColorsFactory.White);
}

