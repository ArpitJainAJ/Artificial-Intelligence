package n_puzzle_test1;

import java.lang.Math;
import java.util.Random;

public class SimulatedAnnealing {
	
	/*Taking the probability function as e^(dE/T)*/
	double Temperature, dE, minTemperature, p, dectemp;		/*Temperature, State Energy Difference and max Temperature difference, probability*/
	Node initial_node, goal_node;
	Random rand;
	stat result;
	public SimulatedAnnealing()
	{
		result = new stat();
		Temperature=25;
		dE=0;
		p=1;
		dectemp=0.00001;
		minTemperature = 1;
		rand = new Random();
	}
	
	public void initialize(Node goal_node, Node initial_node)
	{
		this.initial_node = new Node(initial_node);
		this.goal_node = new Node(goal_node);
		/*System.out.println('\n'+"Initializing Simulated Annealing ---------->");*/
		run();
		/*System.out.println('\n'+"Completed Simulated Annealing <---------->");*/
	}
	public void run()
	{
		Node current_node = new Node(initial_node);
		/*System.out.println("Taking the probability function as e^(dE/T), dE = difference in h value, T range in [1,"+Temperature+"]");*/
		for(double i = Temperature; i>=minTemperature; i-=dectemp)
		{
			int flag=1;
			while(flag!=0)
			{
				if(i<minTemperature)
				{
					break;
				}
				flag=1;
				i-=dectemp;
				/*System.out.println('\n'+"Step: "+result.steps);
				printemptytileandh(current_node);
				current_node.display();*/
				int xy[] = current_node.locateemptytile();
				if(current_node.issame(goal_node))
				{
					/*System.out.println("We have reached the goal in "+result.steps+" steps");*/
					flag=0;
					result.isSuccessful=true;
				}
				switch(rand.nextInt(4))
				{
				case 0:
				{
					if((xy[0]>0)&&(flag==1))
					{
						/*System.out.print("Checking up, ");*/
						Node up_node = current_node.moveup(goal_node);
						/*printemptytileandh(up_node);
						up_node.display();*/
						p = probability((current_node.h - up_node.h),i);
						if(p > Math.random())
						{
							/*System.out.println("Found a move in up with probability p = "+p);*/
							flag=2;
							result.steps+=1;
							current_node=up_node;
							continue;
						}
					}
					/*Else case denotes a selection of a position which doesn't exist*/
					break;
				}
				case 1:
				{
					if((xy[0]<current_node.K-1)&&(flag==1))
					{
						/*System.out.print("Checking down, ");*/
						Node down_node = current_node.movedown(goal_node);
						/*printemptytileandh(down_node);
						down_node.display();*/
						p = probability((current_node.h - down_node.h),i);
						if(p > Math.random())
						{
							/*System.out.println("Found a move in down with probability p = "+p);*/
							flag=2;
							result.steps+=1;
							current_node=down_node;
							continue;
						}
					}
					/*Else case denotes a selection of a position which doesn't exist*/
					break;
				}
				case 2:
				{
					if((xy[1]>0)&&(flag==1))
					{
						/*System.out.print("Checking left, ");*/
						Node left_node = current_node.moveleft(goal_node);
						/*printemptytileandh(left_node);
						left_node.display();*/
						p = probability((current_node.h - left_node.h),i);
						if(p > Math.random())
						{
							/*System.out.println("Found a move in left with probability p = "+p);*/
							flag=2;
							result.steps+=1;
							current_node=left_node;
							continue;
						}
					}
					/*Else case denotes a selection of a position which doesn't exist*/
					break;
				}
				case 3:
				{
					if((xy[1]<current_node.K-1)&&(flag==1))
					{
						/*System.out.print("Checking right, ");*/
						Node right_node = current_node.moveright(goal_node);
						/*printemptytileandh(right_node);
						right_node.display();*/
						p = probability((current_node.h - right_node.h),i);
						if(p > Math.random())
						{
							/*System.out.println("Found a move in right with probability p = "+p);*/
							flag=2;
							result.steps+=1;
							current_node=right_node;
							continue;
						}
					}
					/*Else case denotes a selection of a position which doesn't exist*/
					break;
				}
				}
			}
			if(result.isSuccessful)
			{
				break;
			}
		}
		/*if(!(result.isSuccessful))
		{
			System.out.println("The algorithm could not reach the goal after performing Steps: "+result.steps+".");
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
	public double probability(double dE, double T)
	{
		double p = Math.exp(dE/T);
		/*System.out.println("Probability = "+p+", dE = "+dE+", T = "+T);*/
		return p;
	}
}
