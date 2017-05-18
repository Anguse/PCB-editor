package Start;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoadFrame extends JFrame implements Serializable{
	
	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 200;
	private PcbGraph bGraph;
	private GraphPanel gPanel;

	/**This frame is produced when the user wants to load a file.
	 * The text field assumes the file is saved in the src folder.
	 * @param CircleGraph graph, GraphPanel panel; used to reach and manipulate the used classes CircleGraph and GraphPanel
	 */
	private static final long serialVersionUID = 1L;
	
	public LoadFrame(PcbGraph graph, GraphPanel panel){
		
		bGraph = graph;
		gPanel = panel;
		
		this.setTitle("Load file");
		this.setBounds(100, 100, 400, 130);
		this.getContentPane().setLayout(null);
		
		JLabel lblFilename = new JLabel("File name:");
		lblFilename.setBounds(10, 11, 63, 14);
		this.getContentPane().add(lblFilename);
		
		JLabel lblWarning = new JLabel("");
		lblWarning.setBounds(10, 30, 100, 14);
		this.getContentPane().add(lblWarning);
		
		
		JTextField textField = new JTextField();
		textField.setBounds(83, 8, 269, 20);
		this.getContentPane().add(textField);
		textField.setColumns(10);
		
		Button okButton = new Button("Ok");
		okButton.setBounds(66, 60, 70, 22);
		this.getContentPane().add(okButton);

		okButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bGraph.loadComponents(textField.getText());
					gPanel.repaint();
					dispose();
				} catch (ClassNotFoundException | IOException e1) {
					lblWarning.setText("Could not find file.");
				}
				
			}
			
		});

		this.setVisible(true);
	}
}
