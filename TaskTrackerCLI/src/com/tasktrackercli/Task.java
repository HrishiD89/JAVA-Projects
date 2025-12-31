package com.tasktrackercli;

public class Task {
	public static void main(String[] args) {
		if(args.length > 0) {
			System.out.println("The cli arguments are : ");
			for(String val : args) {
				System.out.println(val);
			}
		}else {
			System.out.println("no argument found!");
		}
	}
}
