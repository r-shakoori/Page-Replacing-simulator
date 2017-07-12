/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pranalysis;


public class Optimal {
    
    int maxPageNumber;// the number of states upto
    int minPageNumber;
    int[] counter; //priorities of the members which the -1 means is not filled till now
    int[] memory; //array of memory which holds the numbers came
    int[] dataArray; //the input array which is a set of numbers with uniform distribution
   
    public Optimal(int[] inputArray){
        minPageNumber=1;
        maxPageNumber=10; //which is given by the questionn PHASE 1!
        this.dataArray=inputArray;
        
    }
    
    public Optimal(int[] inputArray , int minPagenumber , int maxPageNum){
        minPageNumber=minPagenumber;
        maxPageNumber=maxPageNum; //which is given by the questionn PHASE 1!
        this.dataArray=inputArray;
        
    }
    
    public int[] getFaultsNumber(){ //return the  data set for the states that we have 1 to 10 pages
        
        int temp=0;
        int[] outArray=new int[maxPageNumber-minPageNumber+1];
        for(int i=minPageNumber; i<maxPageNumber+1; i++)
            outArray[temp++]=calculatePF(i);
        return outArray;
        
    }
    
    public int calculatePF(int count){  //the function for calculating the page faults for the definite number(count) of pages
        int pfn = 0;
        memory = new int [count];
        counter = new int[count];
        for(int i=0 ; i<count ; i++)
        {
            counter[i]=-10;
            memory [i]=-10;
        }
        int temp;
        int index;
        for(int j=0; j< dataArray.length ; j++)
        {
            if((index=indexOF(dataArray[j]))<0)
            {
                
                if((temp=isThereAnyEmpty())>=0)
                {
                    memory[temp]=dataArray[j];
                    counter[temp]=0;
                    pfn++;
                }
                else
                {
                    memory[temp=getOptimalNumber(j)]=dataArray[j];
                    counter[temp]=0;
                    pfn++;    
                }
                
                
            }

        }
        return pfn;
    }

    
    public int isThereAnyEmpty(){
        
        for(int i=0 ; i < memory.length ; i++)
            if(memory[i]<0)
                return i;
        return -10;
    }
    
    public int indexOF(int number){
    
        for(int i=0;i<memory.length;i++)
            if(memory[i]== number)
                return i;
        return -10;
    }
    
    public int numExists(int input){//checks the whole memory array to see if the input exists in that or no
    
            for(int i=0;i<memory.length;i++)
                if(memory[i]==input)
                    return i;
            return -1;
    }
    
    public int indexOfInputArray(int number , int start){//finds the first place of input number in the input array
        
        for(int i=start;i< dataArray.length;i++)
            if(dataArray[i]==number)
                return i;
        return -1;
    }
    
    public int getOptimalNumber(int start){    //returns the index of the latest number in the future!
        
        int max = -100;
        int selected = -100;
        int temp = -100;
        
        for(int i=0 ; i<memory.length ;i++)
            if((temp=indexOfInputArray(memory[i], start))>max)
            {
                max = temp;
                selected = i;
            }
        return selected;
    }
    
    
    
    
    
}
