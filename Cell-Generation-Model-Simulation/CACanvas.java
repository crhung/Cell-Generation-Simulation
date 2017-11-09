package project_test;

import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CACanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(CACanvas.class.getName());
    private int cellSize = 20;
    private Color col = null;
    private long counter = 0L;
    private int rule =0;
    private int rows=0;
    
    private int currentRow = 0;
	
    
    private int offset = 0; //change when size change
    
    public int getCurrentRow() {
    	return currentRow;
    }
    public void setCurrentRow(int row) {
    	currentRow = row;
    }
    
    /**
     * CellAutCanvas constructor
     */
	public CACanvas() {
		col = Color.WHITE;
	}

	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		drawCellularAutomaton(g); // Our Added-on drawing
		//repaint();
    }
	
	/**
	 * Draw the CA graphics panel
	
	 */
	public void drawCellularAutomaton(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
		log.info("Drawing cell automation " + counter++);
	
		Dimension size = getSize();
		if(size.width > cellSize * 64) {
			offset = (size.width - (cellSize * 63))/2;
		} else {
			offset = 0;
		}
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, size.width, size.height);
		
		g2d.setColor(Color.RED);
		g2d.drawString("Cellular Automaton 2D", 10, 15);
		
		for(int i = 0; i <currentRow; i++) {
			double[] k = CAGenerationSet.getCellArray().get(i);
			int columnSize = 63;
			paintrectangle(g2d, k, columnSize, i);
		}
		
	}
	
    public void paintrectangle(Graphics2D g2d,double[]k,int column, int row){
        
               double n;
               int val1=255;
               int val2=255;
               int val3=255;
               
		   for (int i = 0; i < column; i++) {
                     if (rule==0){
			  n=255*(1-k[i]);
                          val1=(int)n;
                          val2=(int)n;
                          val3=(int)n;
                     }
                     else if (rule==1){
                        n=255*(1-k[i]);
                        val1=(int)n;
                        n=255*(k[i]);
                        val2=(int)n;
                         val3=0;
                     }
                      else if( rule==2){
                          if(k[i]<0.5){
                          val1=255;    
                          n= n=255*((2*(0.5-k[i])));
                          val2=(int)n;
                           n=255*(1-2*(0.5-k[i]));
                           val3=(int)n;
                          }
                          if(k[i]>=0.5){
                           n=255*(2*(k[i]-0.5));
                           val1=(int)n;   
                           n=255*(1-(2*(k[i]-0.5)));
                           val2=(int)n;                                                              
                           val3=255;
                          }
                         
                     }
                     else if( rule==3){
                         if (row%2==1){
                               n=255*(1-k[i]);
                          val1=(int)n;
                          val2=(int)n;
                          val3=(int)n;
                         }
                         else {
                                n=255*(1-k[i]);
                        val1=(int)n;
                        n=255*(k[i]);
                        val2=(int)n;
                         val3=0;
                         }
                     }
                     
			   col = new Color(val1, val2,val3);
			  paintRect( g2d, offset+i*cellSize, row*cellSize + 20, cellSize-1, col);
		  }
                   
	
        }
     public void painting(Graphics2D g2d){
          log.info("Drawing cell automation " + counter++);
		
		Dimension size = getSize();
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, size.width, size.height);
		
		g2d.setColor(Color.RED);
		g2d.drawString("Cellular Automaton 2D", 10, 15);
                          
		
                   
		
        }
	/*
	 * A local routine to ensure that the color value is in the 0 to 255 range;
	 */
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}
	
       public void setRule( int n){
           rule=n;
        }

	private void paintRect(Graphics2D g2d, int x, int y, int size, Color color) {
		g2d.setColor(color);
		g2d.fillRect(x, y, size, size);
	}
	
}
