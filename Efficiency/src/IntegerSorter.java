// Jerry Tian
// ICS4U1
// Nov. 11, 2019
// For Mr. Radulovic
// Comparing Sorting Algorithms Assignment

/*
 * The program compares the time that takes to
 * sort a list of positive integers in increasing 
 * order using three different ways.
 * Those three methods can be invoked by sort(int type) method. 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class IntegerSorter implements Sorter {
	protected int[] list; // the global list

	@Override
	public void setList(int[] list) {
		this.list = list; // set the current to the global list
	}

	@Override
	public int[] getList() {
		return list; // get the global list
	}

	@Override
	/**
	 * @see Sorter#sort(int)
	 */
	public void sort(int type) {
		int[] list = getList(); // get the current global list
		if (type == 1)
			sort_method1(list);
		// if the chosen sorting type is 1, use method1 to sort
		if (type == 2)
			sort_method2(list);
		// if the chosen sorting type is 2, use method2 to sort
		if (type == 3) {
			sort_method3(list, 0, list.length - 1);
		// if the chosen sorting type is 3, use method3 to sort
		}
		setList(list); // set the sorted list to the global list
	}

	/**
	 * The sorting method1 compares two numbers 
	 * which are next to each other in the list.
	 * If the second one is smaller, swap the two numbers.
	 * We continue doing this for every number.
	 * But after the first round, we can stop at the last number.
	 * Because the last pair is already sorted.
	 * @param list
	 * list is the unsorted list.
	 */
	public void sort_method1(int[] list) {
		for (int k = 0; k < list.length-1; k++) {
			// k represents the current index of the list.
			// Every time when the k value increases, 
			// the method check through the numbers
			// (compare and swap) from 0 to the last number.
			for (int i = 0; i < list.length-1; i++) {
				// the limit for i is list.length-1 is because 
				// i+1 has to be smaller than list.length
				int current = list[i];

				int next = list[i + 1];

				// If the next one is smaller, swap the two numbers.
				if (list[i + 1] < list[i]) {

					list[i] = next;

					list[i + 1] = current;

				}
			}
		}
	}
	
	/** Method2 sorts the number from the smallest to biggest too.
	 * It starts at the first number and compares it to the other numbers in the list.
	 * If there is a number smaller than the current number, swap them.
	 * After the method goes through the whole list, it goes back to the second number.
	 * The above procedure goes on for all index of the list.
	 * @param list
	 * list is the unsorted list.
	 */
	public void sort_method2(int[] list) {
		for (int k = 0; k < list.length - 1; k++) {
			// when i = k, i + 1 has to be smaller than list.length
			for (int i = k; i < list.length - 1; i++) {

				int current = list[k];

				int next = list[i + 1];
				// If the next one is smaller, swap the two numbers.
				if (list[i + 1] < list[k]) {

					list[k] = next;

					list[i + 1] = current;

				}
			}
		}
	}
	
	/**
	 * 
	 * @param list
	 * int[] list is the unsorted list.
	 * @param start
	 * integer start is the starting index
	 * @param end
	 * integer end is the ending index
	 * 
	 * The method first splits the index of the list into 2 equal parts
	 * and it keeps splitting after each part has a length of 2.
	 * Then the program starts to combine the lists 
	 * and sort them in increasing order.
	 */
	public void sort_method3(int[] list, int start, int end) {
		if (start - end == 0) {
			return;
			/*
			 * when the program first reaches here,
			 * it means the method finished splitting up the index
			 * and is ready to combine the lists
			 * 
			 * The program reaches here also when it finishes combining the lists.
			 */
		} else {
			// middle is the ending index of the first part of the list 
			// middle+1 is the starting index of the second part of the list
			int middle = (end + start) / 2;
			
			// split the list into two parts by using recursion
			// Each list will reach a length of 2 at the end.
			sort_method3(list, start, middle);
			sort_method3(list, middle + 1, end);
			
			
			/* Create two temporary lists with same length and later sort them together. 
			 * Both lists are sorted in increasing order already
			 */
			int[] list2 = Arrays.copyOfRange(list, start, middle + 1);
			// list2 is from start(inclusive) to middle(inclusive)
			int[] list3 = Arrays.copyOfRange(list, middle + 1, end + 1);
			// list3 is from middle+1(inclusive) to end(inclusive)

			
			// counter1 is for the global list
			// counter2 is for list2 & counter3 is for list3
			int counter1 = 0, counter2 = 0, counter3 = 0;
			
			// while both indices of list2 and list3 are within the length of their lists
			while (counter2 <= list2.length && counter3 <= list3.length) {
				
				/* this if statement will be reached 
				 after all elements in list2 are sorted into the global list*/
				if (counter2 == list2.length) {
					
					// since list3 is in increasing order, 
					// the rest of list3 will be put into the global list in its original order
					for (int i = counter3; i < list3.length; i++) {
						list[counter1 + start] = list3[i];
						counter1 += 1;
					}
					break; 
				}
		
				/* this if statement will be reached 
				 after all elements in list3 are sorted into the global list*/
				if (counter3 == list3.length) {
					
					// since list2 is in increasing order, 
					// the rest of list2 will be placed into the global list in its original order
					for (int i = counter2; i < list2.length; i++) {
						list[counter1 + start] = list2[i];
						counter1 += 1;
					}
					break;
				}
				/*
				 *  We start with the smallest number in both lists
				 *   and pick the smaller one of the two to be placed in the global list.
				 *   It goes on till one list reaches its end.
				 */
				if (list2[counter2] < list3[counter3]) {
					list[counter1 + start] = list2[counter2];
					counter2++;
					counter1++;
				} else {
					list[counter1 + start] = list3[counter3];
					counter3++;
					counter1++;
				}
			}
		}
	}

	@Override
	/**
	 * @see Sorter#toString()
	 */
	public String toString() {
		String toString = Integer.toString(list[0]);
		for (int i = 1; i < list.length; i++) {
			toString += ", " + list[i];
		}
		return toString;
	}

	public static void main(String[] args) {
		IntegerSorter sorter = new IntegerSorter();
		// to access the global variable

		String filepath = "src\\2power4.txt"; // avoid magic string
		File file = new File(filepath);
		Scanner scan = null; // I use scanner to read the file
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = scan.nextLine();
		// The numbers in the string are splitted by space 
		// and they are all in one line. So I used an array to 
		// store the numbers in string form and later convert them to integers.
		String[] list0 = line.split(" ");
		sorter.list = new int[list0.length];
		for (int i = 0; i < sorter.list.length; i++) {
			sorter.list[i] = Integer.parseInt(list0[i]);
		}
		scan.close();

		// Use getList method to get the global list.
		int[] List = sorter.getList();
		
		// Use nanoTime to measure the time used to sort the list.
		long startTime = System.nanoTime();
		sorter.sort(1);
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime) / 1e9);

		// Set the sorted list to the global list.
		sorter.setList(List);
		
		// Print out the sorted global list.
		sorter.toString();
	}
}
