package n_puzzle_test1;

import java.util.Scanner;

public class Test_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in =new Scanner(System.in);
		int N=1, test=1;
		Result r[], finalresult, successrate;
		while (N!=0)
		{
			System.out.print("Enter the value of N for N-puzzle. Enter 0 to exit. Input:");
			N = in.nextInt();
			if(N==0)
			{
				break;
			}
			System.out.print("Enter the number of times to test the puzzle with each method. Enter 0 to exit. Input:");
			test = in.nextInt();
			if(test==0)
			{
				break;
			}
			else if(!(checkPerfectSquare(N+1)))
			{
				System.out.println("The value of N should follow N=(k*k)-1, try again.");
			}
			else if(test<0)
			{
				System.out.println("The number of tests should be positive.");
			}
			else
			{
				r = new Result[test];
				for(int i=0; i<test; ++i)
				{
					/*System.out.println('\n'+"<====================>"+'\n'+"Creating a puzzle for N = "+N+", Test = "+(i+1));*/
					N_Puzzle puzzle_1 = new N_Puzzle((int) Math.sqrt(N+1));
					/*System.out.println("Initializing the puzzle");*/
					puzzle_1.initialize();
					r[i] = puzzle_1.r;
					/*System.out.println("Completed the puzzle for N = "+N+", Test = "+test+'\n'+"<====================>"+'\n');*/
				}
				/*System.out.println('\n'+"Final stats: N = "+N+", No. of tests = "+test);*/
				finalresult = new Result();
				successrate = new Result();
				for(int i=0; i<test;++i)
				{
					/*System.out.println("Test: "+i+", SHC (steps="+r[i].shc.steps+", success= "+ r[i].shc.isSuccessful+"), HCR (steps="+r[i].hcr.steps+", success= "+ r[i].hcr.isSuccessful+"), SA (steps="+r[i].sa.steps+", success= "+ r[i].sa.isSuccessful+")");*/
					(finalresult.shc.steps)+=((r[i]).shc.steps);
					(finalresult.hcr.steps)+=((r[i]).hcr.steps);
					(finalresult.sa.steps)+=((r[i]).sa.steps);
					(finalresult.as.steps)+=((r[i]).as.steps);
					if((r[i]).shc.isSuccessful)
						(successrate.shc.steps)+=1;
					if((r[i]).hcr.isSuccessful)
						(successrate.hcr.steps)+=1;
					if((r[i]).sa.isSuccessful)
						(successrate.sa.steps)+=1;
					if((r[i]).as.isSuccessful)
						(successrate.as.steps)+=1;
				}
				System.out.println("In SHC, Total steps = "+finalresult.shc.steps+", no of success = "+successrate.shc.steps);
				System.out.println("In HRC, Total steps = "+finalresult.hcr.steps+", no of success = "+successrate.hcr.steps);
				System.out.println("In SA, Total steps = "+finalresult.sa.steps+", no of success = "+successrate.sa.steps);
				System.out.println("In A*, Total steps = "+finalresult.as.steps+", no of success = "+successrate.as.steps+'\n');
			}
		}
		in.close();
	}
	public static boolean checkPerfectSquare(double n)
	{
		double sq = Math.sqrt(n);
		return ((sq-Math.floor(sq))==0);
	}

}
