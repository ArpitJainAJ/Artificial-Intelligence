package n_puzzle_test1;

public class HillClimbingWithRandomRestart {

	int steps, noofrestarts;
	Node initial_node, goal_node;
	SimpleHillClimb shc;
	boolean isSuccessful;
	public HillClimbingWithRandomRestart()
	{
		steps=0;
		noofrestarts=100;				/*100 Random Restart can be done until the goal is found*/
		isSuccessful=false;
	}
	
	public void initialize(Node goal_node, Node initial_node)
	{
		this.initial_node = new Node(initial_node);
		this.goal_node = new Node(goal_node);
		System.out.println('\n'+"Initializing Simple Hill Climb with Random Restart ---------->");
		run();
		System.out.println('\n'+"Completed Simple Hill Climb with Random Restart<---------->");
	}
	public void run()
	{
		Node current_node = new Node(initial_node);
		for(int i=1; i<=noofrestarts; ++i)
		{
			System.out.println('\n'+"Initial Start count = "+i+", Total Steps = "+steps);
			try
			{
				/*System.out.println('\n'+"Generating output through Simple Hill Climb");*/
				shc = new SimpleHillClimb();
				shc.initialize(goal_node, current_node);
				steps+=shc.steps;
				if(shc.isSuccessful)
				{
					isSuccessful=true;
				}
			}
			catch(Exception e)
			{
				System.out.println("Found some errors in Simple Hill Climb");
				e.printStackTrace();
			}
			if(isSuccessful)
			{
				break;
			}
			else
			{
				current_node = new Node(initial_node.K);
				current_node.fill_random_values();
				current_node.seth(goal_node);
			}
		}
	}
}
