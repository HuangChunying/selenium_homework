package com.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadCSV {
	public static ArrayList<String[]> readFlie(String path) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(path));

			String line = null;

			while ((line = reader.readLine()) != null) {

				String item[] = line.split(",");
				if (item.length > 0) {

					list.add(item);
					//String last = item[0];
					//System.out.println(last);
				} else {
					//System.out.println("null");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	public static void main(String[] args) {
//		String path = "C:/Users/Administrator/Desktop/info.csv";
//		readFlie(path);
//
//	}

}
