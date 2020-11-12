package n_puzzle_test1;

public class HillClimbingWithRandomRestart {

	int steps;
	
	public HillClimbingWithRandomRestart(Node goal_node, Node initial_node, int K)
	{
		steps=0;
	}
	
	public void initialize(Node goal_node, Node initial_node, int K)
	{
		System.out.println("Initializing Hill Climbing with Random Restart");
	}
}
