//Author: Shishir Sumant
//ucid: ss3688
import java.util.ArrayList;
import java.util.List;

public class menu {

	int n;
	ArrayList<ArrayList<Integer>> ind = new ArrayList<>(n);
	ArrayList<Integer> outs = new ArrayList<Integer>();
	int iterLimit = 0;
	double errorLim =0;
	menu( ArrayList<ArrayList<Integer>> a, int n, ArrayList<Integer> outs, int iteration, double error ) {
		this.outs=outs;
		this.n= n;
		this.ind=a;
		this.iterLimit= iteration;
		this.errorLim= error;
	}

	boolean setInit(int n) {
		int numEdges = n;
		double manual = 1/numEdges;
		double[] pageRank = new double[numEdges];
		for(int i=0;i<n; i++) {
			pageRank[i] = 1/(double)numEdges;
			//	System.out.println("Pagerank initialized: " + pageRank[i]);
		}
		boolean sol = rankCalc(pageRank);
		return sol;
	}

	boolean rankCalc(double[] rankArray) {

		int n = rankArray.length;
		double dampFact = 0.85;
		int iterCount = 0;
		boolean sol = false;
		double newPR=0;// need to change here
		int i=0, Count=0;
		while(iterCount<this.iterLimit) {
			sol = false;
			//for all elements in rankArray
			for(int j=0; j<n; j++) {
				List<Integer> in = new ArrayList<Integer>();
				in = ind.get(j);
				double sum=0;
				//for all elements in the inlist of that vertex
				for(int k : in) {
					//calculate sigma with known values
					sum = sum + rankArray[k]/outs.get(k);
				}
				//store value obtained into newPR
				newPR = (((1-dampFact)/n) + dampFact*sum);
				System.out.println(newPR);
				double diff = java.lang.Math.abs(newPR - rankArray[j]);
				rankArray[j]=newPR;
				if(diff <= this.errorLim) {
					Count++;
				}
			}
			if(Count==n) {
				System.out.println("Pagerank successfully evaluated after iteration "+iterCount);
				sol= true;
				break;
			}

			System.out.println(" Iteration " +iterCount);
			iterCount++;
		}

		if(iterCount==this.iterLimit) {
			System.out.println("Iteration Limit reached");
		}



		//Print all page ranks in a for loop , indicate number of iterations  
		for(int m=0; m<n; m++) {
			System.out.println("Page rank of "+m+ " == "+rankArray[m]);
		}
		return sol;

	}



}
