/* CS 5381 Ananlysis of Algorithms */
/* Instructor: Dr. Rattikorn Hewett */
/* Author: Abhishek Kumar */
/* Project Requirement: A priority queue for a simplified agenda manager in a rule-based expert system shell */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AgendaManager {
	/* Part 2 of the Project requirement */
	/* Begin time to calculate performance of simplified agenda manager */
	public static long timebegin = System.currentTimeMillis();
	/* To keep a record of the rule list in the given input file */
	static ArrayList<String> namelist = new ArrayList<String>();
	/* Function 1: Build Heap - Builds a heap from the cycles of the input file */
	public static ArrayList<Integer> buildHeap(ArrayList<Integer> prioritylist){
		int n=prioritylist.size();
		for(int index = n/2; index >=0; index--){
			prioritylist = heapify(prioritylist, index, n);
			}
		return prioritylist;
		}
	/* Function 2: Heapify - Builds a max heap from the given heap */
	public static ArrayList<Integer> heapify(ArrayList<Integer> prioritylist, int index, int n){
		int l = 2*index + 1;
		int r = 2*index + 2;
		int largest = 0;
		if (l <n && prioritylist.get(l) > prioritylist.get(index))
			largest = l;
		else 
			largest = index;
		if (r < n && prioritylist.get(r) > prioritylist.get(largest))
			largest = r;
		if (largest != index){
			int temp = prioritylist.get(index);
			prioritylist.set(index, prioritylist.get(largest));
			prioritylist.set(largest, temp);
	        String tempName = namelist.get(index);
			namelist.set(index, namelist.get(largest));
			namelist.set(largest, tempName);
			heapify(prioritylist, largest, n);
			}
	    return prioritylist;
		}
	/* Function 3: ExtractMax - Extracts the maximum value from the heap after it is heapified */
	public static int extractMax(ArrayList<Integer> prioritylist){
		int max = prioritylist.get(0);
		String max_name = namelist.get(0);
		System.out.print("[" + (max_name) + ", ");
		return max; 
		}
	/* Function 4: Delete Max - Deletes the maximum element of the heap */
	public static ArrayList<Integer> deleteMax(ArrayList<Integer> prioritylist){
		prioritylist.remove(0);
		namelist.remove(0);
		return prioritylist;
		}
	
	/* MAIN FUNCTION */
	public static void main(String args[]){
		int counter = 0; 
		int lineNum = 0;
		int maximum = 0;
		//String max_name;
		ArrayList<String> list = new ArrayList<String>();
		/* To keep a record of the priority list in the given input file */
		ArrayList<Integer> prioritylist1 = new ArrayList<Integer>();
		// The name of the file to open.
        String fileName = "C:/Users/Abhishek/Desktop/AgendaManager/src/test3.txt";
        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
        	Scanner inFile = new Scanner(new File(fileName));
            FileReader fileReader = 
                new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            int arlist;
            int cycle = 0;
			//while((line = bufferedReader.readLine()) != null && cycle < 30) {
           // while(cycle < 30 && (line = bufferedReader.readLine()) != null && line.length()!=0) {
            while (cycle < 30 && inFile.hasNext()) {
            	//System.out.println(" LINE  "+line);
            	line = inFile.nextLine();
            	if(line.length() == 0){
            		continue;
            	}
				cycle++;
            	//System.out.println(line);
				//String[] set = line.split("\\| ");
				String[] set = line.split("(\\)\\,) |\\)");
				//System.out.println(line);
				//System.out.println(Arrays.toString(set));
            	lineNum++;
            	for (String x: set){
            		String[] set2 = x.split(", ");
            		//System.out.println(Arrays.toString(set2));
            		//String set3 = (set2[0]);
            		String set3 = (set2[0]).substring(1);
            		//System.out.println(set3);
            		namelist.add(set3);
            		//int set4 = Integer.parseInt((set2[1].trim()).substring(0,((set2[1].trim()).length()) - 1));
            		int set4 = Integer.parseInt((set2[1]));
            		prioritylist1.add(set4);
            		//System.out.println(set4);
            		//System.out.println(set2[0]);
    				//System.out.println(Arrays.toString(set));
            	  }
            	
            	prioritylist1 =  buildHeap(prioritylist1);
            	System.out.println("########## CYCLE " + (counter + 1) + " ##########");
            	System.out.println("Executed Rule: ");
            	maximum = extractMax(prioritylist1);
            	prioritylist1= deleteMax(prioritylist1);
            	System.out.println(maximum +"]");
            	//System.out.println("Maximum from cycle " + (counter + 1) + " is ------------ " + maximum);
            	System.out.println("Activated Rule after cycle: " + (counter + 1));
            	System.out.println(namelist);
            	//System.out.println("Priority list after cycle " + (counter + 1) + " is ------" + prioritylist1);
            	System.out.println();
            		counter++;
				}
			System.out.println("No. of lines in the text file is " + counter);
			System.out.println();
			/* Cycles after all the cycles in the input file has been executed */
			while (prioritylist1.size() != 0 && counter < 30){
				//System.out.println("COUNTER " + counter);
				prioritylist1 =  buildHeap(prioritylist1);
				System.out.println("########## CYCLE " + (counter + 1) + " ##########");
            	System.out.println("Executed Rule: ");
				maximum = extractMax(prioritylist1);
		        prioritylist1= deleteMax(prioritylist1);
		        System.out.println(maximum +"]");
		        //System.out.println("Maximum from cycle " + (counter + 1) + " is ------------ " + maximum);
		        //System.out.println("Rule list after cycle " + (counter + 1) + " is ----------" + namelist);
		        System.out.println("Activated Rule after cycle: " + (counter + 1));
            	System.out.println(namelist);
		        //System.out.println("Priority list after cycle " + (counter + 1) + " is ------" + prioritylist1);
		        System.out.println();
		        	counter++;
		        	
		        if (prioritylist1.size() == 1){
		        	maximum = prioritylist1.get(0);
		        		System.out.println("Maximum from cycle " + (counter + 1) + " is ------------ " + maximum);
		        		prioritylist1.remove(0);
		        		System.out.println("Rule list after cycle " + (counter + 1) + " is ----------" + " Null");
		        		System.out.println("Priority list after cycle " + (counter + 1) + " is ------" + " Null");
		        		break;
		         	}
		        }
			bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
        /* Part 2 of the Project requirement */
    	/* End time to calculate performance of simplified agenda manager */
        long timeend = System.currentTimeMillis();
    	/* Calculating the run time performance of your simplified agenda manager for the given problem size */
        System.out.println("The RUN TIME PERFORMANCE of the simplified agenda manager is " + (timeend - timebegin) + " milliseconds.");
     	}
	}