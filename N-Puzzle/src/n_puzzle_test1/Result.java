package n_puzzle_test1;

public class Result {
	
	stat shc;
	stat hcr;
	stat sa;
	
	public Result(stat a, stat b, stat c)
	{
		this.shc = new stat(a);
		this.hcr = new stat(b);
		this.sa = new stat(c);
	}
	public Result( Result a)
	{
		this.shc = new stat(a.shc);
		this.hcr = new stat(a.hcr);
		this.sa = new stat(a.sa);
	}
	public Result()
	{
		this.shc = new stat();
		this.hcr = new stat();
		this.sa = new stat();
	}
}
