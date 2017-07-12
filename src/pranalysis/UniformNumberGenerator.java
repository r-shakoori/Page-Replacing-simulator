/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pranalysis;

import java.util.Random;

/**
 *
 * @author Y50-70
 * the uniform distribution creating class  which uses the java.util.random for creating the uniform distrobution which the chance of every memeber is equal!
 */
public class UniformNumberGenerator {
    
    int count;//number of the output sets members
    int start;
    int end;
    
    public UniformNumberGenerator(){// default constructor with given default values 
    
        count = 5000;
        start = 0;
        end = 50;
    }
    
    public UniformNumberGenerator(int count, int start, int end){ //constructor for getting the user defined values
    
        this.count=count;
        this.start=start;
        this.end=end;
        
    }
    
    public int[] getNum(){ //creates the set of output numbers
        
        Random rand = new Random();
        int[] outArray = new int[count];
        
        for(int i=0;i<count;i++)
            outArray[i]=rand.nextInt(end-start+1)+start;
        
        return outArray;
    }
   
}
