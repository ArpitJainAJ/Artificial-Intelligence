package n_puzzle_test1;

import java.util.Scanner;

public class Test_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in =new Scanner(System.in);
		int N=1;
		while (N!=0)
		{
			System.out.println("Enter the value of N for N-puzzle. Enter 0 to exit.");
			N = in.nextInt();
			if(N==0)
			{
				break;
			}
			else if(!(checkPerfectSquare(N+1)))
			{
				System.out.println("The value of N should follow N=(k*k)-1, try again.");
			}
			else
			{
				System.out.println('\n'+"<====================>"+'\n'+"Creating a puzzle for N = "+N);
				N_Puzzle puzzle_1 = new N_Puzzle((int) Math.sqrt(N+1));
				System.out.println("Initializing the puzzle");
				puzzle_1.initialize();
				System.out.println("Completed the puzzle for N = "+N+'\n'+"<====================>"+'\n');
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
