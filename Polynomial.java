package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial 
{
	
	double[] poly;
	int [] expo;
	
	public Polynomial() 
	{

		poly = null;
		expo = null;
	}

	public Polynomial(double[] co_arr, int[] ex_arr)
	{
		this.poly = co_arr;
		this.expo = ex_arr;
	}
	
	public Polynomial(File p_file) throws FileNotFoundException
	{
		Scanner inp = new Scanner(p_file);
		String expression = inp.next();	
		inp.close();
		expression = expression.replace("-", "~-");
		String [] parts = expression.split("~|\\+");
		
		int lgth = parts.length;
		double [] co = new double[lgth];
		int [] ex = new int[lgth];
		
		for(int i = 0; i < lgth; i++)
		{
			if(parts[i].contains("x") == false)
			{
				double x = Double.parseDouble(parts[i]);
				ex[i] = 0;
				co[i] = x;
			}
				
			else
			{
				String [] fin = parts[i].split("x");
				double y =  Double.parseDouble(fin[0]);
				int z = Integer.parseInt(fin[1]);
				ex[i] = z;
				co[i] = y;
			}
		}
		
		poly = co;
		expo = ex;
	}
	
	public int find_len(Polynomial one, Polynomial two) 
	{
		int len = one.expo.length;
		int other_len = two.expo.length;
		int new_len = len + other_len;
		for(int i = 0; i < len; i++) 
		{
			for(int j = 0; j < other_len; j++)
			{
				if(one.expo[i] == two.expo[j] && new_len > 1)
				{
					new_len--;
				}
				
				if(one.expo[i] == two.expo[j] && one.poly[i] + two.poly[j] == 0&& new_len > 1)
				{
					new_len--;
				}
			}
		}
		
		
		return new_len;
	}
	
	public int contains1(int [] arr, int check, int len)
	{
		for(int i = 0; i < len; i++)
		{
			if(arr[i] == check)
			{
				return i;
			}
		}
		return -1;
	}
	
	public Polynomial add(Polynomial other) 
	{	
		int lgth = find_len(this, other);
		Polynomial ret = new Polynomial();
		ret.poly = new double[lgth];
		ret.expo = new int[lgth];
		
		for(int i = 0; i < lgth; i++)
		{
			ret.poly[i] = -1;
			ret.expo[i] = -1;
		}
	
		int len = this.expo.length;
		int other_len = other.expo.length;
		int c = 0;
		int e = 0;
		
		for(int i = 0; i < len; i++) 
		{
			for(int j = 0; j < other_len; j++)
			{
				if(this.expo[i] == other.expo[j] && c < lgth && e < lgth)
				{
					
					
					if(contains1(ret.expo, this.expo[i], lgth) != -1) 
					{
						int ind = contains1(ret.expo, this.expo[i], lgth);
						ret.poly[ind] = this.poly[i] + other.poly[j];
					}
					else
					{
						
						ret.poly[c] = this.poly[i] + other.poly[j];
						ret.expo[e] = this.expo[e];
						c++;
						e++;
					}		
				}
				else if(this.expo[i] != other.expo[j] && c < lgth && e < lgth)
				{
					if(contains1(ret.expo, this.expo[i], lgth) == -1)
					{	
						ret.poly[c] = this.poly[i];
						c++;
						ret.expo[e] = this.expo[i];
						e++;
					}
					if(contains1(ret.expo, other.expo[j], lgth) == -1)
					{
						ret.poly[c] = other.poly[j];
						c++;
						ret.expo[e] = other.expo[j];
						e++;	
					}
				}
			}
		}
		return ret;
	}


	public double evaluate(double x) 
	{
		double total = 0.0;
		int len = poly.length;
		double val;
		for(int i = 0; i < len; i++) {
			val = Math.pow(x, expo[i]);
			total = (poly[i] * val) + total;
		}
		return total;
	}

	public boolean hasRoot(double r) {
		return evaluate(r) == 0.0;
	}
	
	
	public int find_len_mult(Polynomial one, Polynomial two) 
	{
		int len = one.expo.length;
		int other_len = two.expo.length;
		int new_len = len * other_len;
		
		for(int i = 0; i < len; i++) 
		{
			for(int j = 0; j < other_len; j++)
			{
				if(one.expo[i] == two.expo[j] && new_len > 1)
				{
					new_len--;
				}
			}
		}
		
		return new_len;
	}
	
	public Polynomial multiply(Polynomial other)
	{	
		int len = this.expo.length;
		int other_len = other.expo.length;
		int lgth = find_len_mult(this, other);
		Polynomial ret = new Polynomial();
		ret.poly = new double[lgth];
		ret.expo = new int[lgth];
		
		for(int i = 0; i < lgth; i++)
		{
			ret.poly[i] = -1;
			ret.expo[i] = -1;
		}
		int c = 0;
		
		for(int i = 0; i < len; i++) 
		{
			for(int j = 0; j < other_len; j++)
			{
				int check = this.expo[i] + other.expo[j];
				
				if(contains1(ret.expo, check, lgth) != -1)
				{
					int ind = contains1(ret.expo, check, lgth);
					ret.poly[ind] += this.poly[i] * other.poly[j];
						
				}
				
				else if(c < lgth)
				{
					ret.poly[c] = this.poly[i] * other.poly[j];
					ret.expo[c] = this.expo[i] + other.expo[j];
					c++;
				}
			}
		}
		
		return ret;
	}
	
	public void saveToFile(String file_name) throws IOException
	{
		int len = this.poly.length;
		File f = new File(file_name);
		
	
		if(f.exists())
		{
			FileWriter writer = new FileWriter(f);
			for(int i = 0; i < len; i++)
			{
				double hold = this.poly[i];
				String str = Double.toString(hold);
				int hold2 = this.expo[i];
				String str2 = Integer.toString(hold2);
				
				if(hold2 == 0)
				{
					writer.write(str);
				}
				
				else if(str.contains("-") == true)
				{
					
					writer.write(str);
					writer.write("x");
					writer.write(str2);
				}
				
				else
				{
					writer.write("+");
					writer.write(str);
					writer.write("x");
					writer.write(str2);
				}
				
			}
			
			writer.close();
		}
		
		else if(!f.exists())
		{
			FileWriter writer = new FileWriter(f);
			for(int i = 0; i < len; i++)
			{
				double hold = this.poly[i];
				String str = Double.toString(hold);
				int hold2 = this.expo[i];
				String str2 = Integer.toString(hold2);
				
				if(hold2 == 0)
				{
					writer.write(str);
				}
				
				else if(str.contains("-") == true)
				{
					
					writer.write(str);
					writer.write("x");
					writer.write(str2);
				}
				
				else
				{
					writer.write("+");
					writer.write(str);
					writer.write("x");
					writer.write(str2);
				}
				
			}
			
			writer.close();
		}
		
	}
	
	
}
