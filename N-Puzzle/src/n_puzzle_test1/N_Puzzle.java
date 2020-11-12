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
	
	public void initialize()
	{
		/* Goal node is the first in order to calculate heuristic value for others*/
		goal_node = new Node(K);
		goal_node.fill_random_values();
		System.out.println('\n'+"Goal State:");
		goal_node.display();
		initial_node = new Node(K);
		initial_node.fill_random_values();
		initial_node.seth(goal_node);
		System.out.println('\n'+"Initial State:");
		initial_node.display();
		
		System.out.println("Generating output through Simple Hill Climb");
		shc.initialize(goal_node, initial_node, K);
		/*
		try
		{
			System.out.println("Generating output through Simple Hill Climb");
			shc.initialize(goal_node, initial_node, K);
		}
		catch(Exception e)
		{
			System.out.println("Found some errors in Simple Hill Climb");
			e.printStackTrace();
		}
		
		try
		{
			System.out.println("Generating output through Hill Climb with Random Restart");
			hcr.initialize(goal_node, initial_node, K);
		}
		catch(Exception e)
		{
			System.out.println("Found some errors in Hill Climb with Random Restart");
			System.out.println(e.getMessage());
		}
		
		try
		{
			System.out.println("Generating output through Simulated Annealing");
			sa.initialize(goal_node, initial_node, K);
		}
		catch(Exception e)
		{
			System.out.println("Found some errors in Simulated Annealing");
			System.out.println(e);
		}*/
	}

}
