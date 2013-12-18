import java.io.*;

public class gol
{
    boolean grid[][];
    int xsize, ysize;
    String lineVector[];
    String line = "";
    boolean tempGrid [][];
    
    gol(int xsize, int ysize)
    {
	this.xsize = xsize;
	this.ysize = ysize;
	grid = new boolean[xsize][ysize];
	tempGrid = new boolean[xsize][ysize];
	lineVector = new String[ysize];
    }
    
    public void readFile(String fname) throws Exception
    {
	FileReader fr = new FileReader(fname);
	BufferedReader br = new BufferedReader(fr);
	int i =0;
	System.out.println("Initial State: ");
	while((line = br.readLine()) != null)
	    {
		lineVector[i++] = line;
		System.out.println(lineVector[i-1]);
	    }
	
    }
    
    public void setgrid()
    {
	for(int i =0; i<xsize;i++)
	    {
		for(int j =0 ; j<ysize; j++)
		    {
			grid[i][j] = lineVector[i].charAt(j) == '0' ? false : true;
			
		    }

	    }
    }
       
    public void createnexgen()
    {

	createTempGrid();

	for(int i =0; i < xsize; i++)
	    {
		for(int j=0;j<ysize; j++)
		    {
			if(grid[i][j] == false && countnbrs(i,j) == 3)
			    tempGrid[i][j] = true;
			if(grid [i][j] == true && countnbrs(i,j) < 2 || countnbrs(i,j) > 3)
			    tempGrid[i][j] =  false;
		    }
	    }
	saveNexGen();
    }
       public void createTempGrid()
    {
	for(int i =0; i<xsize ; i++)
	    {
		for(int j =0; j<ysize; j++)
		    {
			tempGrid[i][j]=grid[i][j];
		    }
	    }
    }

   
    public void saveNexGen()
    {
	for(int i =0; i<xsize ; i++)
	    {
		for(int j =0; j<ysize; j++)
		    {
			grid[i][j]=tempGrid[i][j];
		    }
	    }
    }

    public int countnbrs(int x, int y)
    {
	int ncount =0;
	if(x != 0 && y != 0 && grid[x-1][y-1])
	    ncount ++;
	if(x !=0 && grid[x -1][y])
	    ncount++;
	if(x != 0 && y!= ysize -1  && grid[x-1][y+1])
	    ncount++;
	if (y != 0 && grid[x][y -1])
	    ncount ++;
	if(y != ysize -1  && grid[x][y+1])
	    ncount++;
	if(x != xsize-1 && y!= 0 && grid[x+1][y-1])
	    ncount++;
	if(x != xsize -1 && grid[x+1][y])
	    ncount++;
	if(x != xsize -1 && y != ysize -1 && grid[x+1][y+1])
	    ncount++;
	return ncount;
    }
    
    public static void main(String[] args) throws Exception
    {
	gol g = new gol(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	g.readFile(args[3]);
	g.setgrid();
	for(int i = 0;i<Integer.parseInt(args[2]); i++)
	  {
		g.createnexgen();
	  }
	System.out.println("\nAfter "+args[2]+" generations: ");
	for(int i =0;i < Integer.parseInt(args[0]);i++)
	    {
		for(int j = 0;j < Integer.parseInt(args[1]); j++)
		    System.out.print(g.grid[i][j] ? "*" : "-");
		System.out.println();
			
	    }
		
    }
}

