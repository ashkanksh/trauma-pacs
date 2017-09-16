package org.dcm4che3.common;

import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;

/**
 * @author Russell Shingleton <shingler@oclc.org>
 */
public class TextAreaAppender extends WriterAppender {

    private static volatile JTextArea textArea = null;

    private static volatile JScrollPane jScrollPane = null;

    /**
     * Set the target TextArea for the logging information to appear.
     *
     * @param textArea
     */
    public static void setComponents(final JTextArea textArea, final JScrollPane jScrollPane) {
        TextAreaAppender.textArea = textArea;
        TextAreaAppender.jScrollPane = jScrollPane;
    }

    /**
     * Format and then append the loggingEvent to the stored TextArea.
     *
     * @param loggingEvent
     */
    @Override
    public void append(final LoggingEvent loggingEvent) {
        final String message = this.layout.format(loggingEvent);

        if(textArea == null){
            return;
        }

        if (textArea.getText().length() == 0) {
            textArea.setText(message);
        } else {
            textArea.append(/*textArea.getText().length(),*/
                    message);
        }
        jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMaximum() + 500);
    }
}