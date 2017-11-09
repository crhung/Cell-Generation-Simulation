/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Derek
 */
public class CAGenerationSet {

	final public static int CELLS_ROW_MAX_SIZE = 64;
	final public static int CELLS_COLUMN_MAX_SIZE = 63;
	
	private static ArrayList<double[]> Cellarrays = new ArrayList();
	
	private CAGeneration Cells =new CAGeneration();


	public void CAarrayplus(double array[])
	{
		Cellarrays.add(array);

	}
	public void CAarraydelete(double array[])
	{
		Cellarrays.remove(array);

	}
	public static ArrayList<double[]> getCellArray() { return Cellarrays; }
	public void CAstart(){

		Cellarrays.clear();
		Cells.CALoad();
		double [] A=Cells.returncellarrary();
		double[]B=Arrays.copyOf(A, A.length);
		
		Cellarrays.add(B);
		Cells.CANextarray();
		Cells.setarray();
		while(Cellarrays.size() <= CELLS_ROW_MAX_SIZE) {

			NextArray(); 
			
		}

	}
	
	public void NextArray(){
		double [] A=Cells.returncellarrary();
		double[]B=new double[A.length];
		System.arraycopy( A, 0, B, 0, A.length );
		Cellarrays.add(B);
		Cells.CANextarray();
		Cells.setarray();

	}

	public int rows(){
		return Cellarrays.size();

	}
	public int columns(){

		return Cells.returncellarrary().length;
	}

	public double[] latestarray(){
	double [] A=Cells.returncellarrary();
		return A.clone();
	}
	
}
