package com.stem.springwebapp.demo.model;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PictureUtil {
	
	public static String Frequency(Double happyCount, Double sadCount, Double neutralCount) {		
		//rewrite to access DB
		//count
		return " happy: "+ happyCount + "  sad: "+ sadCount + "  neutral: "+ neutralCount;
	}
	
	private static final Comparator<Picture> happyLocationComparator =
		      new Comparator<Picture>() {
		@Override
		public int compare(Picture x, Picture y) {
		      return 0;
		   }
		};
		
	public static String sortHappyLocations(List<Picture> lists){
		
		Queue<Picture> queue = new PriorityQueue<>(lists.size(), happyLocationComparator); for (Picture picture : lists) {
		      if (picture != null) {
		         queue.add(picture);
		} }
		return queue.toString();
	}
	
}
