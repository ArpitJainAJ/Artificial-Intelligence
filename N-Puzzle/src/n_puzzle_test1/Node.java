package n_puzzle_test1;

import java.util.Random;

public class Node {

	int h;												/* Heuristic value of the node*/
	int K;												/* Dimension of puzzle node*/
	
	int values[][];						/*Automatically initialized with 0*/
	public Node(int K)
	{
		this.K = K;
		this.h = 0;
		values= new int [K][K];
		System.out.println("Creating node with K="+K);
	}
	public void fill_random_values()
	{
		System.out.println("Filling in random values.....");
		fill_values();
		randomize_values(100);							/*Randomize values 100 times*/
		System.out.println("Successful");
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
		System.out.println("In random, value of K="+this.K);
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
			System.out.print("-----"+'\n');
		}
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
}
