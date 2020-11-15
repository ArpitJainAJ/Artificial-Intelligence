package n_puzzle_test1;

public class Result {
	
	stat shc;
	stat hcr;
	stat sa;
	stat as;
	
	public Result(stat a, stat b, stat c, stat d)
	{
		this.shc = new stat(a);
		this.hcr = new stat(b);
		this.sa = new stat(c);
		this.as = new stat(d);
	}
	public Result( Result a)
	{
		this.shc = new stat(a.shc);
		this.hcr = new stat(a.hcr);
		this.sa = new stat(a.sa);
		this.as = new stat(a.as);
	}
	public Result()
	{
		this.shc = new stat();
		this.hcr = new stat();
		this.sa = new stat();
		this.as = new stat();
	}
}
