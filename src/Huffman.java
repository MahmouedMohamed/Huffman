import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Huffman {
	String Sentence;
	Vector<Double> prob=new Vector<Double>();
	Vector NewProb=new Vector();
	Vector charr=new Vector();
	Vector newcharr=new Vector();
	Vector<String> Code=new Vector();
	public Huffman() 
	{
		String s="";
		File file=new File("ReadFrom.txt");
		try
	    {
			s = new String ( Files.readAllBytes( Paths.get("ReadFrom.txt") ) );
	    }
	    catch (IOException e)
	    {
	    }
		Sentence=s;
	}
	public int Search(char x)
	{
		for(int i=0;i<charr.size();i++)
		{
			if(charr.elementAt(i).equals(x))
			{
				return i;
			}
		}
		return -1;
	}
	public int newSearch(Object object)
	{
		for(int i=0;i<newcharr.size();i++)
		{
			if(newcharr.elementAt(i).equals(object))
			{
				return i;
			}
		}
		return -1;
	}
	public int newnewSearch(String s)
	{
		for(int i=0;i<Code.size();i++)
		{
			if(Code.elementAt(i).equals(s))
			{
				return i;
			}
		}
		return -1;
	}
	public void getprob()
	{
		for(int i=0;i<Sentence.length();i++)
		{
			if(Search(Sentence.charAt(i))<0) 
			{
				charr.add(Sentence.charAt(i));
				double Counter=0;
			for(int j=0;j<Sentence.length();j++)
				{
					if(Sentence.charAt(j)==Sentence.charAt(i))
					{
						Counter++;
					}
				}
			double Sum=Counter/Sentence.length();
			prob.add(Sum);
			}
		}
	
	}
		public void Sort()
		{
			for(int i=0;i<prob.size();i++)
			{
				for(int j=i+1;j<prob.size();j++)
				{
					if(prob.elementAt(i)>prob.elementAt(j))
					{
						double temp=prob.elementAt(i);
						Object temp1= charr.elementAt(i);
						prob.setElementAt(prob.elementAt(j),i);
						charr.setElementAt(charr.elementAt(j),i);
						prob.setElementAt(temp,j);
						charr.setElementAt(temp1,j);
					}
				}
			}
			
		}
	public void Compress()
	{
		getprob();
		Sort();
		if(prob.size()==1)
		{
			Code.add("1");
			newcharr.add(charr.elementAt(0));
		}
		else 
		{
			for(int i=0;i<charr.size();i++)
			{
				Code.add("");
				newcharr.add(charr.elementAt(i));
			}
			if(charr.size()==0)
			{
				System.exit(0);
			}
		while(charr.size()!=1)
		{
			String sum=charr.elementAt(0)+""+charr.elementAt(1);
			if(sum.contains("+")!=true) {
			double left=prob.elementAt(0);
			double right=prob.elementAt(1);
			double root=left+right;
			sum=charr.elementAt(0)+"+"+charr.elementAt(1);		 
			charr.add(sum);
			String code=Code.elementAt(newSearch(charr.elementAt(0)));	//generate code
			code+="0";
			Code.setElementAt(code,newSearch(charr.elementAt(0)));
			code=Code.elementAt(newSearch( charr.elementAt(1))); 	//generate code
			code+="1";
			Code.setElementAt(code,newSearch( charr.elementAt(1)));
			charr.removeElementAt(0);
			charr.removeElementAt(0);
			prob.add(root);
			prob.removeElementAt(0);
			prob.removeElementAt(0);
			Sort();
			}
			else
			{
				double left=prob.elementAt(0);
				double right=prob.elementAt(1);
				double root=left+right; 
				String l= charr.elementAt(0)+"";
				String r= charr.elementAt(1)+"";
				if(l.contains("+"))
				{
					for(int x=0;x<l.length();x++)
					{
						if(l.charAt(x)!='+')
						{
							String code="0";
							code+=Code.elementAt(newSearch(l.charAt(x)));
							Code.setElementAt(code, newSearch(l.charAt(x)));
						}
					}
					if(r.contains("+"))
					{
						for(int x=0;x<r.length();x++)
						{
							if(r.charAt(x)!='+')
							{
								String code1="1";
								code1+=Code.elementAt(newSearch(r.charAt(x)));
								Code.setElementAt(code1, newSearch(r.charAt(x)));
							}
						}
					}
					else
					{
						String code1="1";
						code1+=Code.elementAt(newSearch(charr.elementAt(1)));
						Code.setElementAt(code1,newSearch(charr.elementAt(1)));
					}
					sum=charr.elementAt(0)+"+"+charr.elementAt(1);
					charr.add(sum);
					charr.removeElementAt(0);
					charr.removeElementAt(0);
					prob.add(root);
					prob.removeElementAt(0);
					prob.removeElementAt(0);
					Sort();
				}
				else if(l.contains("+")!=true)
				{
					String code="0";
					code+=Code.elementAt(newSearch(charr.elementAt(0)));
					Code.setElementAt(code,newSearch(charr.elementAt(0)));
				if(r.contains("+"))
				{
					for(int x=0;x<r.length();x++)
					{
						if(r.charAt(x)!='+')
						{
							String code1="1";
							code1+=Code.elementAt(newSearch(r.charAt(x)));
							Code.setElementAt(code1, newSearch(r.charAt(x)));
						}
					}
				}
				sum=charr.elementAt(0)+"+"+charr.elementAt(1);
				charr.add(sum);
				charr.removeElementAt(0);
				charr.removeElementAt(0);
				prob.add(root);
				prob.removeElementAt(0);
				prob.removeElementAt(0);
				Sort();
				}
				
			}
		}
		}
		System.out.println(newcharr);
		System.out.println(Code);
		String x="";
		for(int i=0;i<Sentence.length();i++)
		{
			x+=Code.elementAt(newSearch(Sentence.charAt(i)));
			
		}
			File newTextFile = new File("WriteThenRead.txt");
			try {
	            

	            FileWriter fw = new FileWriter(newTextFile);
	            fw.write(x);
	            fw.close();

	        } catch (IOException d) {
	        }
	    }
	
	public void Decompress()
	{
		String Sentence="";
		File file = new File("WriteThenRead.txt");
		try
		{
			Sentence =new String (Files.readAllBytes(Paths.get("WriteThenRead.txt")));
		}
		catch(IOException e)
		{
		}
		System.out.println(Sentence);
		String Sub="";
		String SubSub="";
		for(int i=0;i<Sentence.length();i++)
		{
			Sub+=Sentence.charAt(i);
			if(newnewSearch(Sub)>-1)
			{
				SubSub+=newcharr.elementAt(newnewSearch(Sub));
				System.out.print(newcharr.elementAt(newnewSearch(Sub)));
				File newTextFile = new File("WriteTo.txt");
				try {
		            

		            FileWriter fw = new FileWriter(newTextFile);
		            fw.write(SubSub);
		            fw.close();

		        } catch (IOException d) {
		        }
				Sub="";
			}
		}
	}
	public static void main(String[] args) {
		Huffman obj=new Huffman();
		obj.Compress();
		obj.Decompress();
	}
}