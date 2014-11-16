package game_engine;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc.. 
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgramWindow {

	private JFrame guiFrame;
	private JPanel buttonPanel;
	private JComboBox hGap;
	private JComboBox vGap;
	private JRadioButton leftAlign;
	private JRadioButton rightAlign;
	private JRadioButton centerAlign;

	// Not used currently
	public static void createWindow() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ProgramWindow();
			}
		});

	}

	public ProgramWindow() {
		// Calls the constructor and labels the window with a default title.
		this("Default title");
	}

	public ProgramWindow(String title) {

		guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle(title);
		guiFrame.setSize(700, 300);
		guiFrame.setLocationRelativeTo(null);

		JPanel optionPanel = new JPanel();

		FlowLayout f = (FlowLayout) optionPanel.getLayout();
		leftAlign = new JRadioButton("Left");
		leftAlign.setActionCommand("Left");

		rightAlign = new JRadioButton("Right");
		rightAlign.setActionCommand("Right");

		centerAlign = new JRadioButton("Center");
		centerAlign.setActionCommand("Center");
		centerAlign.setSelected(true);

		ButtonGroup group = new ButtonGroup();
		group.add(leftAlign);
		group.add(rightAlign);
		group.add(centerAlign);

		optionPanel.add(leftAlign);
		optionPanel.add(centerAlign);
		optionPanel.add(rightAlign);

		// Array holding the options for the dropdown boxes
		Integer[] options = { 0, 20, 40, 60, 80, 100 };
		hGap = new JComboBox(options);
		hGap.setSelectedIndex(0);
		vGap = new JComboBox(options);
		vGap.setSelectedIndex(0);

		JLabel horizontalGap = new JLabel("Horizontal Gap:");
		JLabel verticalGap = new JLabel("Vertical Gap:");
		// Sets the layout
		JButton setLayout = new JButton("Set Layout");
		setLayout.setActionCommand("Set Layout");
		setLayout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FlowLayout flow = new FlowLayout();
				flow.setHgap((Integer) hGap.getSelectedItem());
				flow.setVgap((Integer) vGap.getSelectedItem());
				int alignment = FlowLayout.CENTER;
				if ( leftAlign.isSelected() ) {
					alignment = FlowLayout.LEFT;
				}
				else if ( rightAlign.isSelected() ) {
					alignment = FlowLayout.RIGHT;
				}
				flow.setAlignment(alignment);
				buttonPanel.setLayout(flow);
				buttonPanel.revalidate();
				buttonPanel.repaint();
			}
		});

		optionPanel.add(horizontalGap);
		optionPanel.add(hGap);
		optionPanel.add(verticalGap);
		optionPanel.add(vGap);
		optionPanel.add(setLayout);
		buttonPanel = new JPanel();
		JButton button1 = new JButton("Button One");
		button1.setActionCommand("Button One");
		JButton button2 = new JButton("Button Two");
		button2.setActionCommand("Button Two");
		JButton button3 = new JButton("Button Three");
		button3.setActionCommand("Button Three");
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		guiFrame.add(optionPanel, BorderLayout.NORTH);
		guiFrame.add(buttonPanel, BorderLayout.SOUTH);
		guiFrame.setVisible(true);
	}
}
