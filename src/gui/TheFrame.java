package gui;

import javax.swing.JFrame;

public class TheFrame extends JFrame {
	
	PanelControl plnControl = new PanelControl();
	
	public TheFrame() {
		super("Reading Along");
		this.setVisible(true);
		addPanelsToFrame();
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addPanelsToFrame() {
		this.add(this.plnControl);
	}
}
