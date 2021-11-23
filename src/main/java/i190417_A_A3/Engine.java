package i190417_A_A3;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Engine extends Thread{
	
	public static ArrayList<Vector> v1 = new ArrayList<Vector>();
	public static ArrayList<Word> coll_Words = new ArrayList<Word>();
	public static BST voc_bst = new BST();
	
	public void run() {
		try {
			Charset charset = Charset.forName("UTF-8");
			Path path = Paths.get(Thread.currentThread().getName());
			BufferedReader reader = Files.newBufferedReader(path,charset);
			String read_Line = "";
		    while((read_Line = reader.readLine()) != null)
		    {
		    	if (Thread.currentThread().getName().compareTo("vocabulary.txt") == 0)
	    		{
	    			voc_bst.insert(read_Line);
	    		}
		    	for (int m = 0 ; m < v1.size() ; m++)
		    	{
		    		if (v1.get(m).get(0) == Thread.currentThread().getName())
		    		{
		    			String[] data_Arr = read_Line.split(" ");
		    			for (int k = 0 ; k < data_Arr.length ; k++)
				    	{
		    				v1.get(m).add(data_Arr[k]);
				    	}
		    		}
		    	}
		    }
		} catch (IOException e) {}
	}

	
	public static boolean checkingChoice(int ch)
	{
		if (ch == 1 || ch == 2 || ch == 3 || ch == 4 || ch == 5)
		{
			return true;
		}
		throw new ExceptionInvalidChoice("Entered choice is incorrect. Try Again");
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		// Print number of files and their names
		System.out.println("Total Number of files : " + args.length);
		for (int i = 0 ; i < args.length ; i++)
		{
			System.out.println("File # " + (i+1) + " : " + args[i]);
		}
		
		// Adding data in BST
		Engine t2 = new Engine();
		t2.setName(args[0]);
		t2.start();
		
		
		// Adding Data in Vectors
		v1.clear();
		for (int i = 1 ; i < args.length ; i++)
		{
			Vector v2 = new Vector();
			v1.add(v2);
		}
		for (int i = 1 ; i < args.length ; i++)
		{
			Engine t1 = new Engine();
			t1.setName(args[i]);
			v1.get(i-1).add(args[i]);
			t1.start();
		}
		
		
		while (true)
		{
			System.out.println("\n");
			System.out.println("Enter 1 to display BST built from vocabulary file");
			System.out.println("Enter 2 to display vectors built from input files");
			System.out.println("Enter 3 to view matching words and their frequencies");
			System.out.println("Enter 4 to search a query");
			System.out.println("Enter 5 to exit program");
			boolean checkChoice = false;
			int choice = 0;
			while (checkChoice != true)
			{
				try {
					System.out.print("Enter choice : ");
					Scanner input_Object = new Scanner(System.in);
					choice = input_Object.nextInt();
					checkChoice = checkingChoice(choice);
				} catch (ExceptionInvalidChoice error) {
					System.out.println(error);
				}
			}
			if (choice == 1)
			{
				System.out.println("BST Tree : ");
				voc_bst.Display();
			}
			else if (choice == 2)
			{
				System.out.println("Vectors : ");
				for (int i = 0 ; i < v1.size() ; i++)
				{
					System.out.print("Vector " + (i+1) + " : [");
					for (int j = 1 ; j < v1.get(i).size() ; j++)
					{
						if (j == (v1.get(i).size() - 1))
						{
							System.out.print(v1.get(i).get(j));
						}
						else
						{
							System.out.print(v1.get(i).get(j) + ",");
						}
					}
					System.out.println("]\n");
				}
			}
			else if (choice == 3)
			{
				coll_Words.clear();
				for (int i = 0 ; i < v1.size() ; i++)
				{
					for (int j = 0 ; j < v1.get(i).size() ; j++)
					{
						String temp = (String)v1.get(i).get(j);
						boolean check1 = voc_bst.Search(temp);
						if (check1 == true)
						{
							Word w1 = new Word(temp);
							boolean check2 = coll_Words.contains(w1);
							if (check2 == false)
							{
								coll_Words.add(w1);
							}
							else
							{
								for (int z = 0 ; z < coll_Words.size() ; z++)
								{
									if (coll_Words.get(z).collec_Letters.compareTo(temp) == 0)
									{
										coll_Words.get(z).frequency = coll_Words.get(z).frequency + 1;
									}
								}
							}
						}
					}
				}
				System.out.println("\nPrinting Word instances : ");
				for (int i = 0 ; i < coll_Words.size() ; i++)
				{
					System.out.print("Word : " + coll_Words.get(i).collec_Letters + "\t");
					System.out.println("Frequency : " + coll_Words.get(i).frequency);
				}
			}
			else if (choice == 4)
			{
				System.out.println("Enter your query : ");
				Scanner input_Object1 = new Scanner(System.in);
				String query = input_Object1.nextLine();
				String[] data_Arr = query.split(" ");
				for (int i = 0 ; i < data_Arr.length ; i++)
				{
					System.out.println("Word : " + data_Arr[i]);
					for (int j = 1 ; j < args.length ; j++)
					{
						int frequency_Count = 0;
						System.out.println("File Name : " + args[j]);
						for (int k = 0 ; k < v1.size() ; k++)
						{
							String tempo_str = (String) v1.get(k).get(0);
							if (tempo_str.compareTo(args[j]) == 0)
							{
								for (int l = 1 ; l < v1.get(k).size() ; l++)
								{
									String tempq_str = (String) v1.get(k).get(l);
									if (data_Arr[i].compareTo(tempq_str) == 0)
									{
										frequency_Count++;
									}
								}
							}
						}
						System.out.println("Frequency : " + frequency_Count);
					}
					System.out.println("\n*************************************\n");
				}
			}
			else if (choice == 5)
			{
				System.out.println("\nProgram Ended\n");
				System.exit(0);
			}
		}
	}

}
