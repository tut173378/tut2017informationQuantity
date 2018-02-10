
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
*/

public class Frequencer implements FrequencerInterface {
	// Code to start with: This code is not working, but good start point to work.
	byte[] myTarget;
	byte[] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;
	int[] suffixArray;
	// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
	// Each suffix is expressed by a interger, which is the starting position in mySpace.
	// The following is the code to print the variable
	private void printSuffixArray() {
		if (spaceReady) {
			for (int i = 0; i < mySpace.length; i++) {
				int s = suffixArray[i];
				for (int j = s; j < mySpace.length; j++) {
					System.out.write(mySpace[j]);
				}
				System.out.write('\n');
			}
		}
	}
	private int suffixCompare(int i, int j) {
		for (int k = 0; k < mySpace.length; k++) {

			if (suffixArray[i] + k == mySpace.length || suffixArray[j] + k == mySpace.length) {
				if (suffixArray[i] > suffixArray[j]) {
					return -1;
				} else if (suffixArray[i] < suffixArray[j]) {
					return 1;
				}
				break;
			}

			if (mySpace[suffixArray[i] + k] > mySpace[suffixArray[j] + k]) {
				return 1;
			} else if (mySpace[suffixArray[i] + k] < mySpace[suffixArray[j] + k]) {
				return -1;
			}

		}

		return 0;
	}
    public void sort(int array[]){
        for (int i = array.length / 2-1; i >= 0; i--)
					downheap(array,array.length,i);

        for (int i=array.length-1; i>=0; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            downheap(array, i, 0);
        }
    }

    void downheap(int array[], int n, int i){
        int max = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && suffixCompare(left, max)== 1)
            max = left;

        if (right < n && suffixCompare(right, max)== 1)
            max = right;

        if (max != i){
            int swap = array[i];
            array[i] = array[max];
            array[max] = swap;
            downheap(array, n, max);
        }
    }
	public void setSpace(byte[] space) {
		mySpace = space;
		if (mySpace.length > 0) spaceReady = true;
		suffixArray = new int[space.length];
		// put all suffixes in suffixArray. Each suffix is expressed by one interger.
		for (int i = 0; i < space.length; i++) {
			suffixArray[i] = i;
		}
		// Sorting is not implmented yet.
		/* Example from "Hi Ho Hi Ho"
		0: Hi Ho
		1: Ho
		2: Ho Hi Ho
		3:Hi Ho
		4:Hi Ho Hi Ho
		5:Ho
		6:Ho Hi Ho
		7:i Ho
		8:i Ho Hi Ho
		9:o
		A:o Hi Ho
		*/
		//
		// int temp;
		// for (int i = 0; i < space.length; i++) {
		// 	for (int j = space.length - 1; j > i; j--) {
		// 		int k = suffixCompare(j, j - 1);
		// 		if (k == -1) {
		// 			temp = suffixArray[j];
		// 			suffixArray[j] = suffixArray[j - 1];
		// 			suffixArray[j - 1] = temp;
		// 		}
		// 	}
		// }
		sort(suffixArray);
		printSuffixArray();
	}
	private int targetCompare(int i, int start, int end) {
		// comparing suffix_i and target_start_end by dictonary order with limitation oflength;
		// if the beginning of suffix_i matches target_i_end, and suffix is longer than target it returns 0;
		// if suffix_i > target_start_end it return 1;
		// if suffix_i < target_start_end it return -1
		// It is not implemented yet.
		// It should be used to search the apropriate index of some suffix.
		// Example of search
		// suffix target
		// "o" > "i"
		// "o" < "z"
		// "o" = "o"
		// "o" < "oo"
		// "Ho" > "Hi"
		// "Ho" < "Hz"
		// "Ho" = "Ho"
		// "Ho" < "Ho " : "Ho " is not in the head of suffix "Ho"
		// "Ho" = "H" : "H" is in the head of suffix "Ho"

		for (int k = 0; k < end - start; k++) {
			if (suffixArray[i] + k == mySpace.length) {
				return -1; //for example, the case for "Ho"<"Ho "
			} else if (mySpace[suffixArray[i] + k] > myTarget[start + k]) {
				return 1;
			} else if (mySpace[suffixArray[i] + k] < myTarget[start + k]) {
				return -1;
			} else {
				continue;
			}
		}
		return 0;

	}
	private int subByteStartIndex(int start, int end) {
		int left = 0, right = suffixArray.length - 1;
        while (left <= right){
            int m = left + (right-left)/2;

            // Check if x is present at mid
            if (targetCompare(m, start,end) == 0 && targetCompare(m - 1, start,end) == -1)
            	return m;
            // If x greater, ignore left half
            if (targetCompare(m, start,end) == -1 )
                left = m + 1;
            // If x is smaller, ignore right half
            else
                right = m - 1;
        }
        // if we reach here, then element was
        // not present
		return suffixArray.length;
	}
	private int subByteEndIndex(int start, int end) {
		int left = 0, right = suffixArray.length - 1;
        while (left <= right){
            int m = left + (right-left)/2;

            // Check if x is present at mid
            if (targetCompare(m, start,end) == 1)
                return m - 1;

            // If x greater, ignore left half
            if (targetCompare(m, start,end) <= 0 )
                left = m + 1;
        }
        // if we reach here, then element was
        // not present
		return suffixArray.length;
	}
	public int subByteFrequency(int start, int end) {

		int first = subByteStartIndex(start, end);
		int last1 = subByteEndIndex(start, end);
		/* inspection code*/
		for (int k = start; k < end; k++) {
			System.out.write(myTarget[k]);
		}
		System.out.printf(" ");
		//System.out.printf(": first=%d last1=%d\n", first, last1);

		return last1 - first;
	}
	public void setTarget(byte[] target) {
		myTarget = target;
		if (myTarget.length > 0) targetReady = true;
	}
	public int frequency() {
		if (targetReady == false) return -1;
		if (spaceReady == false) return 0;
		return subByteFrequency(0, myTarget.length);
	}
	public static void main(String[] args) {
		Frequencer frequencerObject;
		try {
			frequencerObject = new Frequencer();
			frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
			frequencerObject.setTarget("Ho".getBytes());
			int result = frequencerObject.frequency();
			System.out.println("Freq = " + result + " ");
			frequencerObject.setTarget("Ho ".getBytes());
			result = frequencerObject.frequency();
			System.out.println("Freq = " + result + " ");
			frequencerObject.setTarget("H".getBytes());
			result = frequencerObject.frequency();
			System.out.println("Freq = " + result + " ");
			frequencerObject.setTarget("xxxxxxxxxxxxxo ".getBytes());
			result = frequencerObject.frequency();
			//result = frequencerObject.targetCompare(9,13,14);
			System.out.println("Freq = " + result + " ");
			frequencerObject.setTarget("o ".getBytes());
			result = frequencerObject.frequency();
			//result = frequencerObject.targetCompare(9,0,1);
			System.out.println("Freq = " + result + " ");
			if (1 == result) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("STOP");
		}
	}
}
