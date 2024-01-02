package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import Database.TimeRecording;
import TimeRecordingElements.TimeRecordingFrame;
import TimeRecordingElements.TimeStartAndStopFrame;

public class TimeListener implements ActionListener {
	private TimeRecordingFrame timeRecordingFrame;
	private TimeStartAndStopFrame timeStartFrame;
	private TimeRecording startTime;
	private ActionListenerMain actionListener;

	private boolean repaintScroll;

	public TimeListener(TimeRecordingFrame timeRecordingFrame) {
		this.timeRecordingFrame = timeRecordingFrame;
	}

	public TimeListener(TimeStartAndStopFrame timeStartFrame, TimeRecording startTime) {
		this.timeStartFrame = timeStartFrame;
		this.startTime = startTime;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonID = ((JButton) e.getSource()).getActionCommand();

		// TimeRecordingFrame
		if ("starten/stoppen".equals(buttonID)) {
			new TimeStartAndStopFrame(buttonID);

		}  else if ("zurück".equals(buttonID)) {
			timeRecordingFrame.dispose();
		}

		// TimeStartFrame
		if ("starten".equals(buttonID)) {
			startTime.setEmployeId(1);
			startTime.timeStampStart();

			if (startTime.getClose() == true) {
				timeStartFrame.dispose();
				repaintScroll = true;
			}

		} else if ("beenden".equals(buttonID)) {
			startTime.setEmployeId(1);
			startTime.timeStampStop();

			if (startTime.getClose() == true) {
				timeStartFrame.dispose();
				repaintScroll = true;
			}
		}
	}

	public boolean getRepaintScroll() {
		return repaintScroll;
	}
}