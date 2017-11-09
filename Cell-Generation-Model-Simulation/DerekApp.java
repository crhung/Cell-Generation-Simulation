/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_test;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Derek
 */
public final class DerekApp extends CAApp{
	protected JPanel northPanel = null;
	protected JButton startBtn = null;
	protected JButton stopBtn = null;
	protected JButton resetBtn=null;
	private CACanvas caPanel = null;
	private CAGenerationSet Set= new CAGenerationSet();
	private Status status_ = Status.INIT;
        protected JScrollPane scroll;
        private int index=1;
        protected JComboBox select;
        protected JTextField rows;
        private int maxrow=0;
        private int setRule=1;
        protected JLabel Label;
	
	int displayRowSize_ = 0;
	public enum Status {
		INIT(0),
		START(1),
		STOP(2),
		RESET(3);
             

		private final int value;

		private Status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	static Logger log = Logger.getLogger(DerekApp.class.getName());

	public DerekApp(){
		frame.setSize(1500, 1500);
		frame.setTitle("DerekCA");
		frame.add(getNorthPanel(),BorderLayout.NORTH);
		caPanel = new CACanvas();
		Set.CAstart();
		frame.add(caPanel, BorderLayout.CENTER); // Add to the center of our frame
                scroll= new JScrollPane(caPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

                frame.add(scroll);

		frame.setVisible(true); // The UI is built, so display it
                
	}
	@Override
	public JPanel getNorthPanel() {
            
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
                CARule Rules=new CARule();
                
                Label= new JLabel("Max Rows:");
                northPanel.add(Label);
                
                
                 rows= new JTextField("0",5);
                rows.addActionListener(this);
                northPanel.add(rows);
                
                
                String [] rules=Rules.selectrule();
                select =new JComboBox(rules);
                select.setSelectedIndex(0);
                select.addActionListener(this);
                northPanel.add(select);
                
              
                    
		startBtn = new JButton("Start");
		startBtn.addActionListener(this); // Allow the app to hear about button pushes
		northPanel.add(startBtn);
                

		stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
		stopBtn.addActionListener(this);
		northPanel.add(stopBtn);
		stopBtn.setEnabled(false);

		resetBtn=new JButton("Reset");
		resetBtn.addActionListener(this);
		northPanel.add(resetBtn);
		resetBtn.setEnabled(false);

		return northPanel;
	}
        public int selectchoice(){
            return (int)select.getSelectedIndex();
        }
        public int selecttext(){
        int n=rows.getDocument().getLength();
            if (n==0){
                maxrow=0;
            }
            else{
               maxrow= Integer.parseInt(rows.getText());;
            }
            return maxrow;
        }
	@Override
	public void actionPerformed(ActionEvent ae) {


		log.info("We received an ActionEvent " + ae);
		if (ae.getSource() == startBtn){
			status_ = Status.START;
			startBtn.setEnabled(false);
                        select.setEnabled(false);
			stopBtn.setEnabled(true);
			resetBtn.setEnabled(true);
                        rows.setEnabled(false);
			Set.CAstart();
			System.out.println("Start pressed");


			//frame.setVisible(true); 

		}

		else if (ae.getSource() == stopBtn){

			System.out.println("Stop pressed");
			startBtn.setEnabled(true);
                          select.setEnabled(false);
			stopBtn.setEnabled(false);
			resetBtn.setEnabled(true);
                           rows.setEnabled(false);
			status_ = Status.STOP;
			// caPanel. painting(g2d);

			//  frame.setVisible(true);
		}
		else if (ae.getSource() == resetBtn){
			status_ = Status.RESET;
			startBtn.setEnabled(true);
                        select.setEnabled(true);
			stopBtn.setEnabled(false);
			resetBtn.setEnabled(false);
                           rows.setEnabled(true);

		}



	}

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}



	@Override
	public void windowClosing(WindowEvent e) {	
	}



	@Override
	public void windowClosed(WindowEvent e) {
	}



	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}



	@Override
	public void windowDeiconified(WindowEvent e) {	
		log.info("Window deiconified");
	}



	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}



	@Override
	public void windowDeactivated(WindowEvent e) {	
		log.info("Window deactivated");
	}

	public static void main(String[] args) {
		DerekApp wapp = new DerekApp();
            
		Thread paintingThread = new Thread() {
                    
			public void run() {
				Graphics g=wapp.caPanel.getGraphics();
				Graphics2D g2d=(Graphics2D) g;
                               
                                
				while(true) {
					//for (int i=0; i<15;i++){
					try{
						sleep(500);
					} catch(InterruptedException ex) {
						Logger.getLogger(DerekApp.class.getName()).log(null);
					}
					switch(wapp.status_) {

					case STOP:
						break;
					case RESET:
						wapp.caPanel.setCurrentRow(0);
						wapp.caPanel. painting(g2d);
						wapp.caPanel.repaint();
						wapp.Set.CAstart();
						wapp.status_ = Status.INIT;
						break;
					case START:
                                            
                                            

						
						//newarray= wapp.Set.latestarray();
						int rowCurrent = wapp.caPanel.getCurrentRow();
                                              
						if(rowCurrent >= wapp.selecttext()) {
							wapp.status_ = Status.INIT; // stop drawing
							wapp.startBtn.setEnabled(false);
							wapp.stopBtn.setEnabled(false);
							wapp.resetBtn.setEnabled(true);
							break;
						} 
						
						double[]newarray = wapp.Set.getCellArray().get(rowCurrent);
                                                wapp.caPanel.setRule(wapp.selectchoice());
						wapp.caPanel.paintrectangle(g2d,newarray,63,rowCurrent);
						wapp.caPanel.setCurrentRow(rowCurrent + 1);
						wapp.caPanel.repaint();
						wapp.Set.NextArray(); 
						break;
					case INIT:
					default:
						break;

					}

				}


			}


		};
		paintingThread.start();
	}

}
