package org.dcm4che3.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ASUS1 on 5/14/2017.
 */
@Service
public class GuiLoader {

    @Value("${frametitle:unknown}")
    private String frametitle;
    public void draw() {
        JFrame frame = new JFrame(frametitle);
        JPanel panel = new JPanel();

        JSmartTextArea jt = new JSmartTextArea(12, 550, true);
        jt.setAutoscrolls(true);

        panel.add(jt);
        final JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
