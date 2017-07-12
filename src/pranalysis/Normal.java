
package pranalysis;

import java.util.*;
public class Normal 
{
    int count;
    int start;
    int end;
    double mean,variance;
    
    public Normal(int count, int start, int end,double m,double v){
    
        this.count=count;
        this.start=start;
        this.end=end;
        this.mean=m;
        this.variance=v;
        
    }
    public Normal(double m,double v)
    {
        count = 5000;
        start = 0;
        end = 50;
        this.mean=m;
        this.variance=v;
    }
    
     private Random fRandom = new Random();

     private double getGaussian(double aMean, double aVariance)
        {
        return (aMean + fRandom.nextGaussian()*aVariance);
        }

  
    public double[] getNum()
    {
        double [] a = new double [count];
        double [] b = new double [count];
        double k = (6*Math.sqrt(variance))/(end-start+1);
        
        Normal gaussian = new Normal(mean,variance);
        
        for (int i = 0; i<count; ++i)
          a[i]=gaussian.getGaussian(mean, variance); 
        
        int n;
        for (int i=0; i<count; i++)
        {
          n=0;
        for (int j=start; j<end ; j++)
        {
            if ( ((a[i]-(mean-3*Math.sqrt(variance)))/k) <= j)
                break;
            n++;
        }
        b[i]=n;
        }
  
        return b;
    }
    
    
   
 
}
