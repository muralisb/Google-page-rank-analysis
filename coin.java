//Author: Murali S Badiger
//ucid: mb687


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class coin {
	int n;
	ArrayList<ArrayList<Integer>> ind = new ArrayList<>(this.n);
	ArrayList<Integer> outs = new ArrayList<Integer>();
	LinkedList<Integer> edges[];
	float outdeg= 0;

	coin(int v, float outd){
		this.outdeg=outd;
		this.n= v;
		this.edges = new LinkedList[this.n];

		for(int i=0; i<this.n; i++) {
			this.edges[i] = new LinkedList<>();
		}

	}


	public void connect(int frm, int to) {
		this.edges[frm].addLast(to);

	}



	public int outdegree(LinkedList s ) {
		int out=0;
		out= s.size();
		return out;
	}

	public ArrayList<Integer> indegree(int g) {
		ArrayList<Integer> in = new ArrayList<Integer>();
		for(int m=0; m<this.edges.length; m++) {
			if(this.edges[m].contains(g)) {
				in.add(m);
			}
			else continue;
		}

		return in; 
	}



	public void manual() {
		float max = (this.outdeg/100)*this.n;
		System.out.println(" Note: The number of out going edges must be less than "+max);

		for( int j=0; j<this.n; j++) {

			System.out.println(" Enter the outgoing edges from the vertex "+ j);
			int a = this.n-1;
			System.out.println(" Available vertices are 0 to "+ a);
			System.out.println(" Enter '-1' when done");
			Scanner scanner1 = new Scanner(System.in);
			int[] oute = new int[(int) max];
			label1: for(int f=0; f<max; f++) {
				oute[f] = scanner1.nextInt();
				if(oute[f]>=this.n) {
					System.out.println("vertex number not present, enter the outgoing edges again");
					break label1;
				}
				if(oute[f]==-1) {
					break;
				}
				else
					this.connect(j,oute[f]);

			}
		}

	}

	public void auto() {
		Random a = new Random();
		float count=(this.outdeg/100)*this.n;
		int d=0;
		for(int row=0; row<this.n; row++) {
			for(int col=0; col<this.n; col++) {
				boolean flip = a.nextBoolean();
				if(flip == true && d<count) {
					this.connect(row, col);
					d++;
				}
			}
			d=0;
		}
	}


	public static void main(String [] args) {

		System.out.println("Enter the number of web pages: ");
		int n=0;
		Scanner scanner = new Scanner(System.in);
		n=scanner.nextInt();
		System.out.println("Enter the out degree in percentage: ");
		float out=0;
		out = scanner.nextFloat();
		System.out.println("Enter the error limit");
		double error = 0;
		error = scanner.nextDouble();
		System.out.println("Enter the iteration limit");
		int iteration = scanner.nextInt();
		coin G = new coin(n, out);
		System.out.println("Do you want to specify the edges connecting to each vertex manually?");
		System.out.println("Select:");
		System.out.println("1. Yes");
		System.out.println("2. No");

		int opt = scanner.nextInt();
		long st = System.nanoTime();
		switch(opt)
		{
		case 1 : G.manual(); 
		break;
		case 2 : G.auto();
		break;

		default : break;

		}


		for(int k=0; k<G.n; k++) {
			int out2 = G.outdegree(G.edges[k]);
			System.out.println(out2 + " is out degree for vertex "+k);
			G.outs.add(out2);
		}


		for( int q=0; q<G.n;q++) {

			ArrayList<Integer> o = G.indegree(q);
			System.out.println("indegree for "+q+" "+ o);
			G.ind.add(o);
		}

		menu try1 = new menu(G.ind, G.n, G.outs, iteration, error);
		try1.setInit(G.n);
		long sp = System.nanoTime();
		long rt = sp-st;
		System.out.println("Running time:   " + rt);
	}
}