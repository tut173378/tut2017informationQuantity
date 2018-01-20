
public class SuffixArray{

	byte [] myTarget;
	byte [] mySpace;
	boolean targetReady = false;
	boolean spaceReady = false;
	int [] suffixArray; //sorted array of all suffixes of mySpace

	//print the variable
	private void printSuffixArray() {
		if(spaceReady) {
			for(int i=0; i< mySpace.length; i++) {
				int s = suffixArray[i];
				for(int j=s;j<mySpace.length;j++) {
					System.out.write(mySpace[j]);
				}
					System.out.write('\n');
			}
		}
	}

	private int suffixCompare(int i, int j) { //khao jai leo
		// comparing two suffixes by dictionary order.
		// It should be called from setSpace or some method to create suffix.
		// It should not be used for searching indexes.
		// i and j denoetes suffix_i, and suffix_j
		// if suffix_i > suffix_j, it returns 1
		// if suffix_i < suffix_j, it returns -1
		// if suffix_i = suffix_j, it returns 0;
		// Example of dictionary order
		// "i" < "o"	: 	compare by code
		// "Hi" < "Ho" 	; if head is same, compare the next element
		// "Ho" < "Ho"	; if the prefix is identical, longer string is big
		int si = suffixArray[i];
		int sj = suffixArray[j];
		int s = 0; //

		if(si > s) s= si;
		if(sj > s) s= sj;
		int n = mySpace.length -s;
		for(int k=0;k<n;k++) {
			if(mySpace[si+k]>mySpace[sj+k]) return 1;
			if(mySpace[si+k]<mySpace[sj+k]) return -1;
		}
		if(si < sj) return 1;
		if(si > sj) return -1;

		return 0;
	}

	public void setSpace(byte []space) {
		mySpace = space;
		if(mySpace.length>0) spaceReady = true;
		suffixArray = new int[space.length];
		// put all suffixes in suffixArray. Each suffix is expressed by one interger.
		for(int i = 0; i< space.length; i++) {
			suffixArray[i] = i;
		}
		// Sorting is not implemented yet.
		// sort

		// Your are required to sort the suffixArray, by suffixCompare.
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
	int temp;
	for(int i=0; i< mySpace.length; i++){
		for(int j=0; j<mySpace.length-i-1; j++){
			if(suffixCompare(j,j+1) > 0){
				//swap
				temp = suffixArray[j];
				suffixArray[j] = suffixArray[j+1];
				suffixArray[j+1]= temp;
		}
	}
	}
		printSuffixArray();
	}

	private int targetCompare(int i, int start, int end) {
	// It is called from subBytesStarIndex, and subBytesEndIndex.
	// "start" and "end" are same as in subByteStartIndex, and subByteEndIndex **
	// target_start_end is subBytes(start, end) of target **
	// comparing suffix_i and target_start_end by dictonary order with limitation
	//of length; ***

	// if the beginning of suffix_i matches target_start_end, and suffix is longer
	//than target it returns 0;

	// if suffix_i > target_start_end it return 1; **

	for(int l=0; l<end-start; l++){
		if(suffixArray[i] + l == mySpace.length) return -1;
		else if(mySpace[suffixArray[i] + l] > myTarget[start + l])
			return 1;
		else if(mySpace[suffixArray[i] + l] < myTarget[start + l])
			return -1;
		else
			continue;
	}

	// It should be used to search the apropriate index of some suffix.
	// Example of search
	// suffix 	target
	// "o" 	> 	"i"
	// "o" 	< 	"z"
	// "o" 	= 	"o"
	// "o" 	< 	"oo"
	// "Ho" > 	"Hi"
	// "Ho" < 	"Hz"
	// "Ho" =  	"Ho"
	// "Ho" < 	"Ho"	: "Ho" is not in the head of suffix "Ho"
	// "Ho" = 	"H"		: "H" is in the head of suffix "Ho"

		return 0;
	}

	private int subByteStartIndex(int start, int end) {
		// It returns the index of the first suffix which is equal or greater than subBytes;
		// not implemented yet;
		// For "Ho", it will return 5 for "Hi Ho Hi Ho".
		// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
		for(int i=0; i< suffixArray.length; i++){
			if(targetCompare (i, start, end) == 0)
				return i;
		}
		return suffixArray.length;
	}

	private int subByteEndIndex(int start, int end) {
	// It returns the next index of the first suffix which is greater than subBytes;
	// not implemented yet
	// For "Ho", it will return 7 for "Hi Ho Hi Ho".
	// For "Ho ", it will return 7 for "Hi Ho Hi Ho".
		for(int i=0; i< suffixArray.length; i++){
			if(targetCompare (i, start, end) == 1)//&& targetCompare(i-1, start, end)==0)
				return i;
		}
		return suffixArray.length;
	}

	public int subByteFrequency(int start, int end) {
	int first = subByteStartIndex(start,end);
	int last1 = subByteEndIndex(start, end);
	//inspection code
	for(int k=start; k<end; k++) {
		System.out.write(myTarget[k]);
	}
	System.out.printf(": first=%d last1=%d\n", first, last1);

	return last1 - first;
	}

	public void setTarget(byte [] target){
		myTarget = target;
		if(myTarget.length>0) targetReady = true;
	}

	public int frequency() {
		if(targetReady == false) return -1;
		if(spaceReady == false) return 0;
		return subByteFrequency(0, myTarget.length);
	}

	public static void main(String[] args) {

		SuffixArray frequencerObject;
		try {
				frequencerObject = new SuffixArray();
				frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
				//frequencerObject.setSpace("".getBytes());

			frequencerObject.setTarget("H".getBytes());
			int result = frequencerObject.frequency();
				System.out.print("Freq = "+ result+" ");
			if(4 == result) {
				System.out.println("OK");
			}
			else {
				System.out.println("WRONG");
			}


		}
		catch(Exception e) {
		System.out.println("STOP");
	}
	}
}
