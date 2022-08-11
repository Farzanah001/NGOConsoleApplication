package ngomanagement;

import ngomanagement.location.OfficeMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Donor implements NGOMainInterface {
    String firstName,lastName,email,city,mobileNo,typeOfDonation,gender;
    int age,donationAmount;
    String good,quantity,office;
    String donorID="";

    Scanner scan=new Scanner(System.in);

//    final String DB_URL="jdbc:mysql://localhost/ngoapplication";
//    final String USER="root";
//    final String PASS="admin";
    @Override
    public void getPersonalDetails() {
        System.out.println("Enter Your First Name:");
        firstName=scan.next();
        System.out.println("Enter Your Last Name:");
        lastName=scan.next();
        System.out.println("Enter Your Age:");
        age=scan.nextInt();
        System.out.println("Enter Your Gender:");
        gender=scan.next();
        String mail=checkEmail(email);
        email=mail;
        String mobile=checkMobile(mobileNo);
        mobileNo=mobile;
        System.out.println("Enter Your District/City:");
        city=scan.next().toLowerCase();
        office= OfficeMapping.findNearestHub(city);
    }

    @Override
    public void generateID() {
        Random random=new Random();
        int randID=random.nextInt(10000);
        donorID=Integer.toString(randID);
        donorID="NGO-D"+donorID;
        System.out.println("----------YOU HAVE REGISTERED SUCCESSFULLY-----------");
        System.out.println("\t Name\t \t\t : "+firstName+" "+lastName);
        System.out.println("\t Donor ID : "+donorID);
        System.out.println("------------------------------------------------------");
    }
    public void typeOfDonation(){
        byte choice;
        System.out.println("TYPES OF DONATION");
        System.out.println("\n\t1.FOOD|CLOTHES|OTHER THINGS\n\t2.FINANCIAL DONATION\n\t3.BOTH");
        System.out.println("Enter the Type Of Donation, You wish to make:");
        choice= scan.nextByte();
        switch (choice){
            case 1:
                System.out.println("Enter the Type of Good:");
                scan.nextLine();
                good= scan.nextLine();
//                System.out.println("Enter the Quantity(with metric):");
//                quantity= scan.nextLine();
                System.out.println("We request you to send/drop off the donation goods to/in our nearest hub:"+office);
                break;
            case 2:
                System.out.println("Enter the Donation Amount:");
                donationAmount= scan.nextInt();
                modeOfTransaction();
                break;
            case 3:
                System.out.println("Enter the Type of Good:");
                scan.nextLine();
                good= scan.nextLine();
                System.out.println("Enter the Quantity(with metric):");
                quantity= scan.nextLine();
                System.out.println("We request you to send/drop off the donation goods to/in our nearest hub:"+office);
                System.out.println("Enter the Donation Amount:");
                donationAmount= scan.nextInt();
                modeOfTransaction();
                break;
            default:
                System.out.println("Invalid Choice!");
                typeOfDonation();

        }
    }

    private void modeOfTransaction() {
        System.out.println("AVAILABLE MODES OF TRANSACTION");
        System.out.println("\n\t 1. Cash Donation\t 2. Bank Transfer\t 3. UPI Transaction");
        System.out.println("\n\nEnter Your Choice Of Donation:");
        byte choice= scan.nextByte();
        switch (choice){
            case 1:
                System.out.println("\n Please Send in Your Donations to "+office+" Office.");
                break;
            case 2:
                System.out.println("BANK DETAILS");
                System.out.println("Name of the Bank: Union Bank Of India");
                System.out.println("Account Holder Name: Care Club Organisation");
                System.out.println("Account Number: 199600000048597");
                System.out.println("IFSC Code: UBINO53410");
                System.out.println("Branch: Tenkasi");
                break;
            case 3:
                System.out.println("\tUPI ID");
                System.out.println("careclubngo@okaxis");
                System.out.println("careclub@okaxis");
                System.out.println("careclubdonations@okaxis");
                break;
            default:
                System.out.println("Enter a Valid Choice!");
                modeOfTransaction();
        }
    }

    @Override
    public void insertDetails() {
        try {

            Connection con = GetConnection.getConnection();
            String sql1="insert into donor(donorId,firstName,lastName,age,gender,mobile,email,city) values(?,?,?,?,?,?,?,?)";
            PreparedStatement statement=con.prepareStatement(sql1);
            statement.setString(1,donorID);
            statement.setString(2,firstName);
            statement.setString(3,lastName);
            statement.setInt(4,age);
            statement.setString(5,gender);
            statement.setString(6,mobileNo);
            statement.setString(7,email);
            statement.setString(8,city);
            //statement.setString(9,office);
            statement.execute();

            String sql2="insert into donations(donorId,donationItem,donationAmount,dropLocation) value(?,?,?,?)";
            PreparedStatement statement1=con.prepareStatement(sql2);
            statement1.setString(1,donorID);
            statement1.setString(2,typeOfDonation);
            statement1.setInt(3,donationAmount);
            statement1.setString(4,office);
            statement1.execute();
            System.out.println("\nData Inserted Successfully!\n");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
