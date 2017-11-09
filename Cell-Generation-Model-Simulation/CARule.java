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
public class CARule {
   private double i;
   public  void Rule(double[] cell, double[] replacementcell, int j){

   if(j==0&&cell.length>=2){  
       i=(cell[j]+cell[j+1])/2;
    }
      else if(j==cell.length-1&&cell.length>=2)
       {
      i=(cell[j-1]+cell[j])/2; 
      }
     else{
          i=(cell[j-1]+cell[j]+cell[j+1])/2; 
           }
     if(i>1.0){
        i=i%1;
     }
 replacementcell[j]=i;
       
   }
   public String[] selectrule(){
           String [] rules={"RULE1","RULE2","RULE3","RULE4"};
           return rules;
       
   }
   
  
    
}
