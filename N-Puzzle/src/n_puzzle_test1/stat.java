package n_puzzle_test1;

public class stat
{
	boolean isSuccessful;
	int steps;
	
	public stat()
	{
		isSuccessful=false;
		steps=0;
	}
	public stat(boolean a, int b)
	{
		this.isSuccessful = a;
		this.steps = b;
	}
	public stat(stat a)
	{
		this.isSuccessful = a.isSuccessful;
		this.steps = a.steps;
	}
}