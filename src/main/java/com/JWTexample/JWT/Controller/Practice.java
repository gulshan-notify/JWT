package com.JWTexample.JWT.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practice {
	
	
	public static void main(String[] args) {
		
		List<Integer> list=Arrays.asList(1,2,3,34,34,3,4,5,23,2,342,235,555);   
		
		List<Integer>list1 =list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		List<Integer> list2 = list.stream().filter(x-> x%2==0).collect(Collectors.toList());
		List<Entry<String, List<Integer>>> list3 = list.stream().collect(Collectors.groupingBy(x-> x%2==0?"even":"odd")).entrySet().stream().filter(a-> a.getKey()=="even").toList();
		Map<Integer, Long> collect = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		List<Integer> collect2 = list.stream().map(String::valueOf).filter(x-> x.startsWith("2")).map(Integer::valueOf).collect(Collectors.toList());
		
		Integer integer = list.stream().reduce((a,b)-> a+b).get();
		
		int sum = list.stream().mapToInt(Integer::intValue).sum();
		Integer integer4 = list.stream().reduce((a,b)-> a+b).get();
		
		
		String st="Welcome Back";
		
	List<Character>	map= st.replace(" ", "").chars().mapToObj(x-> Character.toLowerCase(Character.valueOf((char)x))).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().
		filter(a-> a.getValue()==1).map(b-> b.getKey()).collect(Collectors.toList());
		
		List<Integer> collect4 = IntStream.range(1, 5).boxed().collect(Collectors.toList());
		
		System.out.println(map);
	
		int asInt = list.stream().mapToInt(Integer::intValue).max().getAsInt();
		
		 String[] str= {"7Java","spring","springBoot","hibernate","5HTML"};
		 
		 
		List<Character>xfg= st.replace(" ","").chars().mapToObj(x-> Character.toLowerCase(Character.valueOf((char)x))).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())).entrySet().stream().
		 filter(x-> x.getValue()>1).map(y-> y.getKey()).toList();
		
		 
		 Comparator<String>comp=(a,b)->{
			 
			 int x= a.length();
			 int y=b.length();
			 
			 if(x>y) {
				 return -1;
			 }else if(x<y){
				 return 1;
			 }else {
				 return 0;
			 }
			 
		 };
		 
		String collect3 = Arrays.stream(str).sorted(comp).findFirst().get();
		
		list.stream().filter(x-> x%2==0).collect(Collectors.toList());
		
		
		
		Integer integer2 = list.stream().max(Comparator.naturalOrder()).get();
		
		//Stream.iterate(new int[] {0,1}, t-> new int[] {t[1],t[0]+t[1]}).map(x-> x[0]).limit(10).forEach(x-> System.out.print(x+","));
		
		//System.out.println("Result= "+integer2);
		
//		Stream.iterate(new int[] {0,1},t-> new int[] {t[1],t[1]+t[0]}).limit(10).map(x-> x[0]).forEach(x-> System.out.println(x+","));
		
		Integer integer3 = list.stream().reduce((a,b)-> (a+b)).get();
		
				 
		//Bubble Sorting 
		                                                                                                     
		/*
		 * int[] arr= {1,2,3,34,34,3,4,5,23,2}; int n=arr.length;
		 * 
		 * for (int i = 0; i < n - 1; i++) { for (int j = 0; j < n - i - 1; j++) {
		 * 
		 * if (arr[j] > arr[j + 1]) { int temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1]
		 * = temp; } } }
		 */
	
		
		// Selection Sort
		
		/*
		 * for (int i = 0; i < n - 1; i++) { int smallest = i; for (int j = i + 1; j <
		 * n; j++) { if (arr[smallest] > arr[j]) { smallest = j; } } int temp = arr[i];
		 * arr[i] = arr[smallest]; arr[smallest] = temp;
		 * 
		 * }
		 */
		
		//Insertion Sort 
		
		/*
		 * for(int i=1;i<n;i++) { int current=arr[i]; int j=i-1;
		 * 
		 * while(j>=0 && current<arr[j]) { arr[j+1]=arr[j]; j--; } arr[j+1]=current;
		 * 
		 * }
		 */
		
	//	Arrays.stream(arr).forEach(x-> System.out.print(x+", "));
		
		
		Stream.iterate(new int[] {0,1},t->  new int[] {t[1],t[1]+t[0]}).limit(10).map(x-> x[0]).forEach(x-> System.out.print(x+","));
		
		
	}
	

}
