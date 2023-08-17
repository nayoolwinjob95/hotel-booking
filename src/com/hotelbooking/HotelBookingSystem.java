package com.hotelbooking;

import java.util.Scanner;

public class HotelBookingSystem {
	static boolean[] roomsBookingStatus = new boolean[10];
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		initializeRooms();
		displayOptions();
	}

	// 予約のステータスを「true」にする
	public static void initializeRooms() {
		for (int i = 0; i < roomsBookingStatus.length; i++)
			roomsBookingStatus[i] = true;
	}

	// オプションの表示
	public static void displayOptions() {
		do {
			System.out.println(
					"Welcome to the Hotel Booking System!\n1. Book a room\n2. Cancel booking\n3. Display bookings\n4. Exit");
			System.out.print("Enter your choice: ");
			String selectedMenu = scan.nextLine();

			switch (selectedMenu) {
			case "1":
				bookRoom();
				break;
			case "2":
				cancelBooking();
				break;
			case "3":
				displayBookings();
				break;
			case "4":
				exit();
				break;
			default:
				System.out.println("Invalid choice please try again.");
			}
		} while (true);
	}

	// ルームの予約
	public static void bookRoom() {
		int bookRoom;
		do {

			bookRoom = getBookedRoom(false);

			if (bookRoom < 1 || bookRoom > 10 || roomsBookingStatus[bookRoom - 1] == false) {
				System.out.println("This room is already booked, please choose another room.");
			} else {
				roomsBookingStatus[bookRoom - 1] = false;
				System.out.println("Booking successful! Thank you for choosing our hotel.");
				break;
			}
		} while (true);
	}

	// 予約のキャンセル
	public static void cancelBooking() {
		do {
			int cancelRoom = getBookedRoom(true);

			if (cancelRoom < 1 || cancelRoom > 10 || roomsBookingStatus[cancelRoom - 1] == true) {
				System.out.println("This room is not booked, please enter a valid room number.");
			} else {
				roomsBookingStatus[cancelRoom - 1] = true;
				System.out.println("Booking cancelled successfully.");
				break;
			}
		} while (true);
	}

	// 予約の表示
	public static void displayBookings() {
		for (int i = 0; i < roomsBookingStatus.length; i++) {
			if (roomsBookingStatus[i] == false)
				System.out.println("Room " + (i + 1) + ": Booked");
		}
	}

	// システムの終了
	public static void exit() {
		System.exit(0);
	}

	// エラーメッセージを表示する
	public static void showErrorMessage() {
		System.out.println("Invalid choice, please try again.");
	}

	// 利用者の予約するルーム番号を取得する
	public static int getBookedRoom(boolean isCancel) {
		int bookRoom;
		do {
			System.out.println(
					"Please enter the room number you want to " + (isCancel ? "cancel" : "book") + " (1-10): ");
			try {
				bookRoom = scan.nextInt();
				scan.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Please enter the valid input.");
			}
			scan.nextLine();
		} while (true);

		return bookRoom;
	}
}
