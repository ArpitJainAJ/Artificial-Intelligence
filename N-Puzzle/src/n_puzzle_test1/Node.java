package n_puzzle_test1;

import java.util.Random;

public class Node {

	int h;												/* Heuristic value of the node*/
	int K;												/* Dimension of puzzle node*/
	int steps;
	int values[][];						/*Automatically initialized with 0*/
	int parent_location[] = {-1,-1};
	int grandparent_location[] = {-1,-1};
	public Node(int K)
	{
		this.K = K;
		this.h = 0;
		values= new int [K][K];
		steps=0;
		/*System.out.println("Creating node with K="+K);*/
	}
	public Node(Node x)
	{
		this.h=x.h;
		this.K=x.K;
		this.steps=x.steps;
		this.values= new int [K][K];
		for (int i=0;i<K;++i)
		{
			for(int j=0; j<K; ++j)
			{
				values[i][j]=x.values[i][j];
			}
		}
	}
	public void fill_random_values()
	{
		/*System.out.println("Filling in random values.....");*/
		fill_values();
		randomize_values(100);							/*Randomize values 100 times*/
		/*System.out.println("Successful");*/
	}
	public void fill_values()
	{
		for(int i=0; i<K; ++i)
		{
			for(int j=0; j<K; ++j)
			{
				values[i][j] = (K*i)+j;
			}
		}
	}
	public void randomize_values(int N)
	{
		Random rand = new Random();
		/*System.out.println("In random, value of K="+this.K);*/
		for(int i=0;i<N; ++i)
		{
			int x1=rand.nextInt(K);
			int y1=rand.nextInt(K);
			int x2=rand.nextInt(K);
			int y2=rand.nextInt(K);
			int temp = values[x1][y1];
			values[x1][y1] = values[x2][y2];
			values[x2][y2] = temp;
		}
	}
	public void display()
	{
		for(int i=0; i<K; ++i)
		{
			for(int j=0; j<K; ++j)
			{
				System.out.print(values[i][j]+"  ");
			}
			System.out.print('\n');
		}
		System.out.println("----------");
	}
	public void seth(Node N)
	{
		this.h = 0;
		for(int i=0; i<K; ++i)
		{
			for(int j=0; j<K; ++j)
			{
				if(values[i][j]!=N.values[i][j])
					++(this.h);
			}
		}
	}
	public int[] locateemptytile()
	{
		int y[] = {0,0};
		for(int i=0;i<K; ++i)
		{
			for(int j=0; j<K; ++j)
			{
				if(values[i][j]==0)
				{
					y[0]=i;
					y[1]=j;
					return y;
				}
			}
		}
		return y;
	}
	public Node moveup(Node a)
	{
		Node x = new Node(this);
		int xy[] = x.locateemptytile();
		x.grandparent_location[0] = this.parent_location[0];
		x.grandparent_location[1] = this.parent_location[1];
		x.parent_location[0] = xy[0];
		x.parent_location[1] = xy[1];
		if (xy[0]>0)
		{
			int y = x.values[xy[0]][xy[1]];
			x.values[xy[0]][xy[1]] = x.values[xy[0]-1][xy[1]];
			x.values[xy[0]-1][xy[1]] = y;
		}
		x.seth(a);
		x.steps+=1;
		return x;
	}
	public Node movedown(Node a)
	{
		Node x = new Node(this);
		int xy[] = x.locateemptytile();
		x.grandparent_location[0] = this.parent_location[0];
		x.grandparent_location[1] = this.parent_location[1];
		x.parent_location[0] = xy[0];
		x.parent_location[1] = xy[1];
		if (xy[0]<K-1)
		{
			int y = x.values[xy[0]][xy[1]];
			x.values[xy[0]][xy[1]] = x.values[xy[0]+1][xy[1]];
			x.values[xy[0]+1][xy[1]] = y;
		}
		x.seth(a);
		x.steps+=1;
		return x;
	}
	public Node moveleft(Node a)
	{
		Node x = new Node(this);
		int xy[] = x.locateemptytile();
		x.grandparent_location[0] = this.parent_location[0];
		x.grandparent_location[1] = this.parent_location[1];
		x.parent_location[0] = xy[0];
		x.parent_location[1] = xy[1];
		if (xy[1]>0)
		{
			int y = x.values[xy[0]][xy[1]];
			x.values[xy[0]][xy[1]] = x.values[xy[0]][xy[1]-1];
			x.values[xy[0]][xy[1]-1] = y;
		}
		x.seth(a);
		x.steps+=1;
		return x;
	}
	public Node moveright(Node a)
	{
		Node x = new Node(this);
		int xy[] = x.locateemptytile();
		x.grandparent_location[0] = this.parent_location[0];
		x.grandparent_location[1] = this.parent_location[1];
		x.parent_location[0] = xy[0];
		x.parent_location[1] = xy[1];
		if (xy[1]<K-1)
		{
			int y = x.values[xy[0]][xy[1]];
			x.values[xy[0]][xy[1]] = x.values[xy[0]][xy[1]+1];
			x.values[xy[0]][xy[1]+1] = y;
		}
		x.seth(a);
		x.steps+=1;
		return x;
	}
	public boolean issame (Node x)
	{
		if (K!=x.K)
			return false;
		for(int i=0; i<K; ++i)
		{
			for( int j=0; j<K; ++j)
			{
				if (values[i][j]!=x.values[i][j])
					return false;
			}
		}
		return true;
	}
	public int expectedcost(Node goal)
	{
		int cost=0;
		int y[] = {0,0};
		for(int i=0; i<K; ++i)
		{
			for(int j=0; j<K; ++j)
			{
				y = locate(goal.values[i][j]);
				cost+= Math.abs(y[0] - i) + Math.abs(y[1] - j);
			}
		}
		return cost;
	}
	public int[] locate(int x)
	{
		int y[] = {0,0};
		for(int i=0;i<K; ++i)
		{
			for(int j=0; j<K; ++j)
			{
				if(values[i][j]==x)
				{
					y[0]=i;
					y[1]=j;
					return y;
				}
			}
		}
		return y;
	}
	public boolean isSolvable (Node a)
	{
		int inv_count = 0;
		int x[] = {-1,-1};
		int y[] = {-1,-1};
		for( int i=0; i<K; ++i)
		{
			for( int j=0; j<K; ++j)
			{
				if(a.values[i][j]!=0)
				{
					for(int k=i; k<K; ++k)
					{
						for(int l=j+1; l<K; ++l)
						{
							if(a.values[k][l]!=0)
							{
								x = locate(a.values[i][j]);
								y = locate(a.values[k][l]);
								if( ((x[0])*K + x[1]) > ((y[0])*K + y[1]) )
								{
									inv_count++;
								}
							}
						}
					}
				}
			}
		}
		return (inv_count%2==0);
	}
}
