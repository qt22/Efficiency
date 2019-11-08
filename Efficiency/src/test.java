public class test {
	public static void main(String[] arg0) {
		
		int[] list2 = {1, 78, 23};
		int[] list3 = {22, 77};
		
		int[] list = new int[5];
		int counter1 = 0, counter2 = 0, counter3 = 0; 
		while(counter2 < list2.length && counter3 < list3.length) { 
			if(list2[counter2] < list3[counter3]) { 
				list[counter1] = list2[counter2];
				counter2++; 
				counter1++;
			}
			else{ 
				list[counter1] = list3[counter3];
				counter3++; 
				counter1++;
			} 
			
		}
	}
}
