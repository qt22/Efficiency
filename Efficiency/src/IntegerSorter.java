import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class IntegerSorter implements Sorter {
	protected int[] list;
	@Override
	public void setList(int[] list) {
		this.list = list;
	}

	@Override
	public int[] getList() {
		return this.list;
	}

	@Override
	public void sort(int type) {
		if(type == 1)
			this.list = sort_method1(getList());
		if(type == 2)
			this.list = sort_method2(getList());
		if(type == 3) {
			
			//this.list = sort_method3(getList());
		}
	}

	
	
	public int[] sort_method1(int[] list) {
		for(int k = 0; k < list.length-1; k++) {// list.length - 1 or list.length
			
			for(int i = 0; i < list.length-1; i++) {
				
				int current = list[i];
				
				int next = list[i+1];
				
				if(list[i+1] < list[i]) {
					
					list[i] = next;
					
					list[i+1] = current;
					
				}
			}
		}
		return list;
	}
	
	public int[] sort_method2(int[] list) {
		for(int k = 0; k < list.length-1; k++) {
			
			for(int i = k; i < list.length-1; i++) {
				
				int current = list[k];
				
				int next = list[i+1]; 
			
				if(list[i+1] < list[k]) {
					
					list[k] = next;
					
					list[i+1] = current;
					
				}
			}
		}
		return list;
	}
	
	public void sort_method3(int[] list, int start, int end) {
		if(start - end == 0) {
			return;
		}else {
			int middle = (end + start) / 2;
		
			sort_method3(list, start, middle);
			sort_method3(list, middle+1, end);
			
			combineArrays(list, start, end);
		}
	}
	
	public int[] combineArrays(int[] list, int start, int end) {
	
		int middle = (end + start) / 2;
		int[] list2 = Arrays.copyOfRange(list, start, middle+1);
		int[] list3 = Arrays.copyOfRange(list, middle+1, end+1);
		
		int counter1 = 0, counter2 = 0, counter3 = 0; 
		while(counter1 < list2.length && counter1 < list3.length) { 
			if(list2[counter2] < list3[counter3]) { 
				list[counter1 + start] = list2[counter2];
				counter2++; 
				counter1++;
			}
			else { 
				list[counter1 + start] = list3[counter3];
				counter3++; 
				counter1++;
			} 
		}
		return list;
	}
	
	@Override
	public String toString() {
		return null;
	}
	
	public static void main(String[] args) {
		IntegerSorter sorter = new IntegerSorter();
		
		File file = new File("src\\2power5.txt");
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = scan.nextLine();
		String[] list0 = line.split(" ");
		sorter.list = new int[list0.length];
		for(int i = 0; i < sorter.list.length; i++) {
			sorter.list[i] = Integer.parseInt(list0[i]);
		}
		scan.close();
		
		
		int[] List = sorter.getList();
		long startTime = System.nanoTime();
		sorter.sort_method3(List, 0, List.length-1);
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1e9);
		
		
		sorter.setList(List);
		
		sorter.toString();
		 
	}

}