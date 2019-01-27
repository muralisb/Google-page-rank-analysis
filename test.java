//Author: Murali S Badiger
//ucid: mb687

import java.util.ArrayList;


public class test {

	public static void main(String [] args) {
		int webp[] = {10, 20, 50, 80, 100, 150, 200, 300, 400, 500};
		int outg[]= {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		long time[][] = new long [webp.length][outg.length];
		boolean ns[][] = new boolean [webp.length][outg.length]; 
		for( int i=0; i<webp.length; i++) {
			for(int k=0; k<outg.length;k++) {
				System.out.println("for n= "+ webp[i]+ " and out degree percentage= "+ outg[k]);
				long st = System.nanoTime();
				coin a = new coin(webp[i], outg[k]);
				a.auto();
				for(int j=0; j<a.n; j++) {
					int out= a.outdegree(a.edges[j]);
					a.outs.add(out);
				}

				for( int g=0; g<a.n;g++) {
					ArrayList<Integer> o = a.indegree(g);
					a.ind.add(o);
				}

				menu b = new menu(a.ind, a.n,a.outs, 10, 0.05);
				boolean sol = b.setInit(a.n);
				long sp = System.nanoTime();
				long rt = (sp-st);
				time [i][k] = rt;
				if(sol==false) {
					ns[i][k] = false;
				}
				else {
					ns[i][k] = true;
				}
			}
		}
		System.out.println();
		System.out.println();

		System.out.println("\"Below is the table containg running time for various values of n and different out degree percentage\"");
		System.out.println("Iteration limit= 10");
		System.out.println("error limit=0.05");
		System.out.println("rows indicate different values of n from 10 to 500");
		System.out.println("columns indicate different out degree percentage, from 10 to 100");
		System.out.println("SF: Indicates solution was obtained");
		System.out.println("SN: Indicates solution was not found, iteration limit was reached");
		System.out.println("NOTE: Time in nanoseconds");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("out % >  |");

		for(int j=0; j<outg.length; j++) {

			System.out.printf("%18d", outg[j]);
			System.out.print("%");
		}
		System.out.println();
		System.out.println("n val    |");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int row=0; row<10; row++) {
			System.out.printf("%9d",webp[row]);
			System.out.printf("|");
			for(int col=0; col<10; col++) {
				if (ns[row][col]==false) {
					System.out.printf("%16d-SN", time[row][col]);	
				}
				else {
					System.out.printf("%16d-SF", time[row][col]);
				}		
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
}