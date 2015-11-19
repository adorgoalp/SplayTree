package com.adorgolap.splaytree;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static SplayTreeController controller = new SplayTreeController();
	public static void main(String[] args) {
		int choice = -1;
		int key;
		
		do {
			choice = showMenu();
			switch (choice) {
			case 1:
//				while (true) {
//					System.out.println("Enter key to insert , '-99' to end inserting");
//					key = sc.nextInt();
//					if(key == -99)
//					{
//						break;
//					}
//					controller.splayInsert(key);
//					controller.print();
//				}
				makeTree();
				break;
			case 2:
				while (true) {
					System.out.println("Enter key to delete , '-99' to end deleting");
					key = sc.nextInt();
					if(key == -99)
					{
						break;
					}
					controller.delete(key);
					controller.print();
				}
				
				break;
			case 3:
				while (true) {
					System.out.println("Enter key to splay , '-99' to end splay");
					key = sc.nextInt();
					if(key == -99)
					{
						break;
					}
					controller.splay(key);
					controller.print();
				}
				break;
			case 4:
				System.out.println("Enter key to search");
				key = sc.nextInt();
				SplayNode result = controller.search(key);
				System.out.println(result);
				controller.print();
				break;
			case 5:
				System.out.println("Bye bye!");
				break;
			case -1:
				System.out.println("Error");
				break;
			default:
				break;
			}
		} while (choice != 5);

	}

	private static int showMenu() {
		System.out.println("====Splay Tree====");
		System.out.println("Enter your operation by #");
		System.out.println("1.Insert");
		System.out.println("2.Delete");
		System.out.println("3.Splay");
		System.out.println("4.Search");
		System.out.println("5.Exit");
		int ch = -1;
		try {
			ch = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Enter vaild choice");
			ch = -1;
		}

		return ch;
	}
	public static void makeTree() {
//		int[] n = {10,15,12,11,13,5,3,2,1,4,6,20,25,23,24,28,29,21,22,18,17,19};
		int[] n = {10,15,3,11,13,};
		for(int i = 0 ; i < n.length ;i++)
		{
			controller.splayInsert(n[i]);
		}
		controller.print();
	}
}
