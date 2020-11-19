package n_puzzle_test1;

public class N_Puzzle {

	int K;
	public N_Puzzle(int K) {
		// TODO Auto-generated constructor stub
		this.K = K;								//Dimensions of the puzzle
		/*System.out.println("K="+K);*/
	}
	
	Node initial_node;
	Node goal_node;
	
	/*Create each Algorithm's object*/
	SimpleHillClimb shc;
	HillClimbingWithRandomRestart hcr;
	SimulatedAnnealing sa;
	AStar as;
	Result r;
	
	public void initialize()
	{
		/* Goal node is the first in order to calculate heuristic value for others*/
		goal_node = new Node(K);
		goal_node.fill_random_values();
		/*System.out.println('\n'+"Goal State:");
		goal_node.display();*/
		initial_node = new Node(K);
		do
		{
			initial_node.fill_random_values();
		}while(!(initial_node.isSolvable(goal_node)));
		
		initial_node.seth(goal_node);
		/*System.out.println('\n'+"Initial State:");
		initial_node.display();*/
		r = new Result();
		
		try
		{
			shc = new SimpleHillClimb();
			shc.initialize(goal_node, initial_node);
			r.shc = new stat(shc.result);
			if( r.shc.isSuccessful==false)
			{
				r.shc.steps = 0;
			}
		}
		catch(Exception e)
		{
			System.out.println("Found some errors in Simple Hill Climb");
			e.printStackTrace();
		}
		try
		{
			hcr = new HillClimbingWithRandomRestart();
			hcr.initialize(goal_node, initial_node);
			r.hcr = new stat(hcr.result);
			if( r.hcr.isSuccessful==false)
			{
				r.hcr.steps = 0;
			}
		}
		catch(Exception e)
		{
			System.out.println("Found some errors in Hill Climb with Random Restart");
			e.printStackTrace();
		}
		try
		{
			sa = new SimulatedAnnealing();
			sa.initialize(goal_node, initial_node);
			r.sa = new stat(sa.result);
			if( r.sa.isSuccessful==false)
			{
				r.sa.steps = 0;
			}
		}
		catch(Exception e)
		{
			System.out.println("Found some errors in Simulated Annealing");
			e.printStackTrace();
		}
		try
		{
			as = new AStar();
			as.initialize(goal_node, initial_node);
			r.as = new stat(as.result);
			if( r.as.isSuccessful==false)
			{
				r.as.steps = 0;
			}
		}
		catch(Exception e)
		{
			System.out.println("Found some errors in A*");
			e.printStackTrace();
		}
	}

}
