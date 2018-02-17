package s4.b173378; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity,
}
*/


public class TestCase {
    public static void main(String[] args) {
	     try {
	        FrequencerInterface  Object;
	        int freq;
	        System.out.println("\nchecking s4.b173378.Frequencer");
	        Object = new s4.b173378.Frequencer();
	        Object.setSpace("Hi Ho Hi Ho".getBytes());
	        Object.setTarget("H".getBytes());
	        freq = Object.frequency();
	        System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		     if(4 == freq) { System.out.println("OK");
         } else {System.out.println("WRONG"); }

		     Object.setTarget("k".getBytes());
	       freq = Object.frequency();
	       System.out.print("\"k\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
		     if(0 == freq) { System.out.println("OK -->When TARGET's length is 0");
        } else {System.out.println("WRONG"); }

	    }
      catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	   }
	   //When TARGET's length is 0.
	   try {
	      FrequencerInterface  Object;
	      int freq;
	      System.out.println("\nchecking s4.b173378.Frequencer(when TARGET is not set)");
	      Object = new s4.b173378.Frequencer();
	      Object.setSpace("Hi Ho Hi Ho".getBytes());
	      Object.setTarget("".getBytes());
	      freq = Object.frequency();
	      System.out.print("\"\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	      if(-1 == freq) { System.out.println("OK");
        } else {System.out.println("WRONG"); }
	   }
	      catch(Exception e) {
	      System.out.println("Exception occurred: STOP");
	  }

	   //When SPACE's length is 0.
	    try {
	       FrequencerInterface  Object;
	       int freq;
	       System.out.println("\nchecking s4.b173378.Frequencer(When SPACE's length is zero)");
	       Object = new s4.b173378.Frequencer();
	       Object.setSpace("".getBytes());
	       Object.setTarget("W".getBytes());
	       freq = Object.frequency();
	       System.out.print("\"W\" in \"\" appears "+freq+" times. ");
	       if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
	    }
	     catch(Exception e) {
		   System.out.println("Exception occurred: STOP");
     }
   }
}
