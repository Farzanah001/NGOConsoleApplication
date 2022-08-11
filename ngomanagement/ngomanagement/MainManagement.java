package ngomanagement;

import java.util.Scanner;

public class MainManagement {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        boolean start=true;
        while (start){
            System.out.println("\tMENU");
            System.out.println("1. Admin");
            System.out.println("2. Join as Volunteer");
            System.out.println("3. Donor");
            System.out.println("4. Requester");
            System.out.println("5. Exit");
            System.out.println("Enter Your Role:");
            byte choice= scan.nextByte();
            switch (choice){
                case 1:
                    Admin admin=new Admin();
                    admin.adminManagement();
                    break;
                case 2:
                    Volunteer volunteer=new Volunteer();
                    volunteer.getPersonalDetails();
                    volunteer.generateID();
                    volunteer.insertDetails();
                    break;
                case 3:
                    Donor donor=new Donor();
                    donor.getPersonalDetails();
                    donor.generateID();
                    donor.typeOfDonation();
                    donor.insertDetails();
                    break;
                case 4:
                    Requester requester=new Requester();
                    requester.getPersonalDetails();
                    requester.generateID();
                    requester.getRequirements();
                    requester.insertDetails();
                    break;
                case 5:
                    start=false;
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}