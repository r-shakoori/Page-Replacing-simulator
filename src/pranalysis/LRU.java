
package pranalysis;

/**
 *
 * @author Y50-70
 */
public class LRU {
    
    int minPageNumber;
    int maxPageNumber;
    int[] dataArray;
    int[] memory;
    int[] counter;
    public LRU(int[] inputArray){
        minPageNumber=1;
        maxPageNumber=10;
        dataArray=inputArray;
    }
    
    public LRU(int[] inputArray , int minPagenumber , int maxPageNum){
        
        minPageNumber=minPagenumber;
        maxPageNumber=maxPageNum;
        dataArray=inputArray;
    }
    
    public int[] getFaultsNumber(){
    
        int temp=0;
        int[] pfnumber = new int [maxPageNumber-minPageNumber+1];
        for(int i=minPageNumber ; i<maxPageNumber+1 ; i++)
            pfnumber[temp++]= calculatePF(i);
        return pfnumber;
    }
    
    public int calculatePF(int count){
        
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
                countup();
                if(isThereAnyEmpty()>=0)
                {
                    memory[temp=isThereAnyEmpty()]=dataArray[j];
                    counter[temp]=0;
                    pfn++;
                }
                else
                {
                    memory[temp=getleastRecent()]=dataArray[j];
                    counter[temp]=0;
                    pfn++;    
                }
                
                
            }else{
                    countup();
                    counter[index]=0;
                                    
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
    
    public void countup(){
    
        for(int i=0;i<memory.length;i++)
            if(counter[i]>=0)
                counter[i]++;
    }
    public int getleastRecent(){
        int max = -10;
        int selected = -10;
        for(int i=0;i<memory.length;i++)        
            if(counter[i]>max)
            {
                max = counter[i];
                selected = i;
            }
        return selected;
    }
    
}
