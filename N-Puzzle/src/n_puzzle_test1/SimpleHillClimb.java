package n_puzzle_test1;

public class SimpleHillClimb {

	int steps;
	Node initial_node, goal_node;
	
	public SimpleHillClimb()
	{
		steps=0;
	}
	
	public void initialize(Node goal_node, Node initial_node, int K)
	{
		this.initial_node=initial_node;
		this.goal_node=goal_node;
		System.out.println('\n'+"Initializing Simple Hill Climb");
		run();
	}
	public void run()
	{
		int flag=1;
		Node current_node = initial_node;
		while(flag!=0)
		{
			System.out.println("Step:"+steps);
			printemptytile(current_node);
			int xy[] = current_node.locateemptytile();
			if(current_node.same(goal_node))
			{
				System.out.println("We have reached the goal in "+steps+" steps");
				flag=0;
			}
			else if(xy[0]>0)
			{
				Node up_node = current_node.moveup(goal_node);
				if(up_node.h < goal_node.h)
				{
					++steps;
					continue;
				}
			}
			else if(xy[0]<current_node.K-1)
			{
				Node down_node = current_node.movedown(goal_node);
				if(down_node.h < goal_node.h)
				{
					++steps;
					continue;
				}
			}
			else if(xy[1]>0)
			{
				Node left_node = current_node.moveleft(goal_node);
				if(left_node.h < goal_node.h)
				{
					++steps;
					continue;
				}
			}
			else if(xy[0]<current_node.K-1)
			{
				Node right_node = current_node.moveright(goal_node);
				if(right_node.h < goal_node.h)
				{
					++steps;
					continue;
				}
			}
			else
			{
				System.out.println("The algorithm has reached a plateau and hence could not reach the goal");
				flag=0;
			}
			
		}
	}
	public void printemptytile(Node x)
	{
		System.out.print("Empty tile: ");
		printcords(x.locateemptytile());
		System.out.print('\n');
	}
	public void printcords(int x[])
	{
		System.out.print("("+x[0]+","+x[1]+")");
	}
	
}
