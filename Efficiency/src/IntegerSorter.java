
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IntegerSorter implements Sorter {
	protected int[] theList;
	@Override
	public void setList(int[] list) {
		list = theList;
	}

	@Override
	public int[] getList() {
		return theList;
	}

	@Override
	public void sort(int type) {
		if(type == 1)
			theList = sort_method1(getList());
		if(type == 2)
			theList = sort_method2(getList());
		if(type == 3) {
			theList = sort_method3(getList());
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
			
			int current = list[k];
			
			for(int i = k; i < list.length-1; i++) {
				
				current = list[k];
				
				int next = list[i+1]; 
				//System.out.println(current + " " + next);
				if(list[i+1] < list[k]) {
					
					list[k] = next;
					
					list[i+1] = current;
					
				}
			}
		}
		return list;
	}
	
	public int[] sort_method3(int[] list) {
		return null;
	}
	
	
	
	
	public static void main(String[] args) {
		IntegerSorter sorter = new IntegerSorter();
		
		File file = new File("src\\2power18.txt");
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = scan.nextLine();
		String[] list0 = line.split(" ");
		sorter.theList = new int[list0.length];
		for(int i = 0; i < sorter.theList.length; i++) {
			sorter.theList[i] = Integer.parseInt(list0[i]);
		}
		scan.close();
		
		
		int[] List = sorter.getList();
		sorter.sort_method2(List);

		for(int i = 0; i < List.length; i++) {
			System.out.println(List[i]);
		}
	}

}