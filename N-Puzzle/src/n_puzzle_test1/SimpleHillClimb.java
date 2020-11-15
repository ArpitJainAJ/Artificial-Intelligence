package n_puzzle_test1;

public class SimpleHillClimb {

	Node initial_node, goal_node;
	stat result;
	public SimpleHillClimb()
	{
		result=new stat();
	}
	
	public void initialize(Node goal_node, Node initial_node)
	{
		this.initial_node = new Node(initial_node);
		this.goal_node = new Node(goal_node);
		/*System.out.println('\n'+"Initializing Simple Hill Climb ---------->");*/
		run();
		/*System.out.println('\n'+"Completed Simple Hill Climb <---------->");*/
	}
	public void run()
	{
		int flag=1;						// 0 = exit loop, 1 = Keep loop on(value not yet found), 2 = found value
		Node current_node = new Node(initial_node);
		while(flag!=0)
		{
			flag=1;
			/*System.out.println('\n'+"Step: "+result.steps);*/
			/*printemptytileandh(current_node);*/
			/*current_node.display();*/
			int xy[] = current_node.locateemptytile();
			if(current_node.issame(goal_node))
			{
				/*System.out.println("We have reached the goal in "+result.steps+" steps");*/
				flag=0;
				result.isSuccessful=true;
			}
			if((xy[0]>0)&&(flag==1))
			{
				/*System.out.print("Checking up, ");*/
				Node up_node = current_node.moveup(goal_node);
				/*printemptytileandh(up_node);*/
				/*up_node.display();*/
				if((up_node.h) < (current_node.h))
				{
					/*System.out.println("Found the better move in up");*/
					flag=2;
					result.steps+=1;
					current_node=up_node;
					continue;
				}
			}
			if((xy[0]<current_node.K-1)&&(flag==1))
			{
				/*System.out.print("Checking down, ");*/
				Node down_node = current_node.movedown(goal_node);
				/*printemptytileandh(down_node);*/
				/*down_node.display();*/
				if((down_node.h) < (current_node.h))
				{
					/*System.out.println("Found the better move in down");*/
					flag=2;
					result.steps+=1;
					current_node=down_node;
					continue;
				}
			}
			if((xy[1]>0)&&(flag==1))
			{
				/*System.out.print("Checking left, ");*/
				Node left_node = current_node.moveleft(goal_node);
				/*printemptytileandh(left_node);*/
				/*left_node.display();*/
				if((left_node.h) < (current_node.h))
				{
					/*System.out.println("Found the better move in left");*/
					flag=2;
					result.steps+=1;
					current_node=left_node;
					continue;
				}
			}
			if((xy[1]<current_node.K-1)&&(flag==1))
			{
				/*System.out.print("Checking right, ");*/
				Node right_node = current_node.moveright(goal_node);
				/*printemptytileandh(right_node);*/
				/*right_node.display();*/
				if((right_node.h) < (current_node.h))
				{
					/*System.out.println("Found the better move in right");*/
					flag=2;
					result.steps+=1;
					current_node=right_node;
					continue;
				}
			}
			if(flag==1)
			{
				/*System.out.println("The algorithm has reached either a plateau or a local minima and hence could not reach the goal after performing Steps: "+result.steps+".");*/
				flag=0;
				result.isSuccessful=false;
			}
		}
	}
	public void printemptytileandh(Node x)
	{
		printemptytile(x);
		System.out.println(", h="+x.h);
	}
	public void printemptytile(Node x)
	{
		System.out.print("Empty tile: ");
		printcords(x.locateemptytile());
	}
	public void printcords(int x[])
	{
		System.out.print("("+x[0]+","+x[1]+")");
	}
	
}
