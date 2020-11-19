package n_puzzle_test1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {
	
	Node initial_node, goal_node, current_node, left_node, right_node, up_node, down_node;
	stat result;
	
	public AStar()
	{
		result = new stat();
	}
	
	public void initialize(Node goal_node, Node initial_node)
	{
		this.initial_node = new Node(initial_node);
		this.goal_node = new Node(goal_node);
		this.current_node = new Node(goal_node.K);
		this.up_node = new Node(goal_node.K);
		this.down_node = new Node(goal_node.K);
		this.left_node = new Node(goal_node.K);
		this.right_node = new Node(goal_node.K);
		/*System.out.println('\n'+"Initializing Hill Climb using A* ---------->");*/
		run();
		/*System.out.println('\n'+"Completed Hill Climb using A* <---------->");*/
	}
	
	public void run()
	{
		PriorityQueue<Node> pq = new PriorityQueue<Node> (new compareclass());
		current_node = new Node(initial_node);
		current_node.h = Math.max(current_node.h, current_node.expectedcost(goal_node)) + current_node.steps;
		pq.add(current_node);
		while(!(pq.isEmpty()))
		{
			current_node = pq.poll();
			//System.out.println('\n'+"Step: "+current_node.steps);
			//printemptytileandh(current_node);
			//current_node.display();
			if(current_node.steps >25)
			{
				//System.out.println("This node is not solvable");
				continue;
			}
			int xy[] = current_node.locateemptytile();
			if(current_node.issame(goal_node))
			{
				/*System.out.println("We have reached the goal in "+result.steps+" steps");*/
				result.isSuccessful=true;
				result.steps = current_node.steps;
				pq.clear();
				break;
			}
			if(xy[0]>0)
			{
				/*System.out.print("Checking up, ");*/
				up_node = current_node.moveup(goal_node);
				up_node.h = Math.max(up_node.h, up_node.expectedcost(goal_node)) + up_node.steps;
				if(!(isSameLocation(up_node.grandparent_location, up_node.locateemptytile())))
				{	
					pq.add(up_node);
					//printemptytileandh(up_node);
				}
				/*up_node.display();*/
			}
			if(xy[1]>0)
			{
				/*System.out.print("Checking left, ");*/
				left_node = current_node.moveleft(goal_node);
				left_node.h = Math.max(left_node.h, left_node.expectedcost(goal_node)) + left_node.steps;
				if(!(isSameLocation(left_node.grandparent_location, left_node.locateemptytile())))
				{
					pq.add(left_node);
					//printemptytileandh(left_node);
				}
				/*left_node.display();*/
			}
			if(xy[0]<current_node.K-1)
			{
				/*System.out.print("Checking down, ");*/
				down_node = current_node.movedown(goal_node);
				down_node.h = Math.max(down_node.h, down_node.expectedcost(goal_node)) + down_node.steps;
				if(!(isSameLocation(down_node.grandparent_location, down_node.locateemptytile())))
				{
					pq.add(down_node);
					//printemptytileandh(down_node);
				}
				/*down_node.display();*/
			}
			if(xy[1]<current_node.K-1)
			{
				/*System.out.print("Checking right, ");*/
				right_node = current_node.moveright(goal_node);
				right_node.h = Math.max(right_node.h, right_node.expectedcost(goal_node)) + right_node.steps;
				if(!(isSameLocation(right_node.grandparent_location, right_node.locateemptytile())))
				{
					pq.add(right_node);
					//printemptytileandh(right_node);
				}
				/*right_node.display();*/
			}

		}
		/*if(result.isSuccessful==false)
		{
			System.out.println("The algorithm could not reach the goal.");
		}*/
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
	public static boolean isSameLocation(int a[], int b[])
	{
		if((a[0]==b[0])&&(a[1]==b[1]))
			return true;
		else
			return false;
	}
	
}
class compareclass implements Comparator<Node>
{
	public int compare (Node a, Node b)
	{
		if(a.issame(b))
			return 0;
		else if( a.h > b.h)
			return 1;
		else
			return -1;
	}
}
