//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.dcm4che3.common;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class JSmartTextArea extends JTextArea {
    int preferred_width;
    private Image img;

    public JSmartTextArea(String var1) {
        this();
        this.setText(var1);
    }

    public JSmartTextArea() {
        this.preferred_width = 480;
        JLabel var1 = new JLabel();
        this.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFont(var1.getFont());
        this.setFocusable(false);
        this.setRows(0);
        this.invalidate();

        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("FanniLogo2.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public JSmartTextArea(int var1, int var2, boolean var3) {
        this.preferred_width = 480;
        this.preferred_width = var2;
        JLabel var4 = new JLabel();
        Font var5 = var4.getFont();
        Font var6;
        if (var3) {
            var6 = var5.deriveFont(1, (float) var1);
        } else {
            var6 = var5.deriveFont(var5.getStyle(), (float) var1);
        }

        this.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        this.setEditable(false);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFont(var6);
        this.setRows(0);
        this.setFocusable(false);
        this.invalidate();
        try {
            img = ImageIO.read(getClass().getClassLoader().getResource("FanniLogo.png"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void setText(String var1) {
        super.setText(var1);
        this.invalidate();
    }

    public void paintComponent(Graphics var1) {
        if (this.getHeight() < 50)
            this.getParent().getGraphics().drawImage(img, 0, 0, null);
        var1.setColor(getBackground());
        var1.fillRect(0, 0, getWidth(), getHeight());
//        var1.drawImage(img, 0, (int) getSize().getHeight() / 2, this);
//        this.setBackground(new Color(this.getParent().getBackground().getRGB()));
        super.paintComponent(var1);
    }

    public Dimension getPreferredSize() {
        Dimension var1;
        if (this.getRows() == 0 && this.getColumns() == 0) {
            int var2 = this.preferred_width / this.getColumnWidth();
            this.setColumns(var2);
            var1 = super.getPreferredSize();
            this.setColumns(0);
        } else {
            var1 = super.getPreferredSize();
        }

        return var1;
    }
}
