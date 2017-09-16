package org.dcm4che3.storescp.web.common;


import javax.swing.*;
import java.awt.*;

/**
 * Created by ASUS1 on 5/14/2017.
 */
public class GuiLoader {
    public GuiLoader(String name) {
        JFrame frame = new JFrame(name);
        JPanel panel = new JPanel();

        JSmartTextArea jt = new JSmartTextArea(12, 550, true);
        jt.setAutoscrolls(true);

        panel.add(jt);
        final JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        jt.setBackground(new Color(jt.getParent().getBackground().getRGB()));


        scrollPane.setOpaque(false);

        jt.setBackground(jt.getParent().getBackground());

        TextAreaAppender.setComponents(jt, scrollPane);

//        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                if (e.getAdjustmentType() != AdjustmentEvent.TRACK)
//                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//            }
//        });

        scrollPane.setBounds(50, 10, 570, 570);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(650, 600));
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
