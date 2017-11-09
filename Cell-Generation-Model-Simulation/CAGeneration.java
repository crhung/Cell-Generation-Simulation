/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_test;




/**
 *
 * @author Derek
 */
public class CAGeneration {
	final static private double celllist[]=new double[CAGenerationSet.CELLS_COLUMN_MAX_SIZE];  //cellList_
	final static private double replacementlist[]=new double[CAGenerationSet.CELLS_COLUMN_MAX_SIZE]; //replacementList
	
	private  CARule Rule= new CARule(); 

	public void CALoad(){
		for (int i=0;i<celllist.length;i++){
			if(i==31){
				celllist[i]=1;
			}
			else{
				celllist[i]=0;
			}
			replacementlist[i]=0;
		}
	}
	public void CANextarray(){

		for (int i=0; i<celllist.length;i++){
			Rule.Rule(celllist,replacementlist,i);
		}
	}

	public void setarray(){
		System.arraycopy( replacementlist, 0, celllist, 0, replacementlist.length );
		

	}
	
	public double[] returncellarrary(){  //should getCelllist() ****

		return celllist;
	} 
	public double[]  returnreplacementarry ()   {  // getReplacementArray()
		return replacementlist;
	} 

}








