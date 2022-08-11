package ngomanagement;

import ngomanagement.location.OfficeMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.Scanner;

public class Requester implements NGOMainInterface {
    Scanner scan = new Scanner(System.in);

//    final String DB_URL="jdbc:mysql://localhost/ngoapplication";
//    final String USER="root";
//    final String PASS="admin";

    String firstName, lastName, gender, city, reqID;
    String requestItem, office, mobileNo, email, occupation, purpose, bankName, accHolderName, ifsc, branch, upiId, accNo;
    int quantity, age;
    long maxAmount, amount;

    @Override
    public void getPersonalDetails() {

        System.out.println("Enter the First Name:");
        firstName = scan.next();
        System.out.println("Enter the Last Name:");
        lastName = scan.next();
        System.out.println("Enter Your Age:");
        age = scan.nextByte();
        System.out.println("Enter Your Gender(M|F|T|NB):");
        gender = scan.next();
        System.out.println("Enter Your Occupation:");
        occupation = scan.next();
        String mail = checkEmail(email);
        email = mail;
        String mobile = checkMobile(mobileNo);
        mobileNo = mobile;
        System.out.println("Enter Your City:");
        city = scan.next().toLowerCase();
        office= OfficeMapping.findNearestHub(city);
    }

    public void getRequirements() {
        byte choice;
        System.out.println("CHOOSE YOUR REQUIREMENT");
        System.out.println("\n\t 1. FOOD|CLOTHES|OTHER THINGS\t 2. MONEY\t 3. BOTH");
        System.out.println("Enter Your Requirement:");
        choice = scan.nextByte();
        switch (choice) {
            case 1:
                System.out.println("Enter the Item You Need:");
                requestItem = scan.next();
                System.out.println("You can collect the items from our " + office + " Office.");
                break;
            case 2:
                receiveMoney();
                modeOfTransaction();
                break;
            case 3:
                System.out.println("Enter the Item You Need:");
                requestItem = scan.next();
                System.out.println("You can collect the items from our " + office + " Office.");
                receiveMoney();
                modeOfTransaction();
                break;
            default:
                System.out.println("Invalid Choice.");
        }
    }

    private void receiveMoney() {
        System.out.println("Choose the Reason for Your Financial Need.");
        System.out.println("\n\t1. Education\n\t2. Medical\n\t3. To Pay Debts\n\t4. Other Purposes");
        byte choice = scan.nextByte();
        switch (choice) {
            case 1:
                purpose = "Education";
                maxAmount = 100000;
                break;
            case 2:
                purpose = "Medical";
                maxAmount = 200000;
                break;
            case 3:
                purpose = "Debts";
                maxAmount = 75000;
                break;
            case 4:
                purpose = "Others";
                //System.out.println("Enter Your Required Amount:");
                maxAmount = 50000;
                break;
            default:
                System.out.println("Invalid Choice! We need a Valid Reason to Help You :(");
                break;
        }
        //modeOfTransaction();
    }

    private void modeOfTransaction() {
        System.out.println("Enter the Amount You Need:");
        amount = scan.nextLong();
        if (amount > maxAmount) {
            amount = maxAmount;
            System.out.println("Sorry! " + maxAmount + " is the Maximum Amount you can Request.");
            System.out.println("Your Requested Amount: " + maxAmount);
        } else {
            System.out.println("Your Requested Amount: " + amount);
        }
        System.out.println("\n\nChoose Your Mode of Transaction.");
        System.out.println("\n1.Cash\t2. UPI Transaction\t3. Bank Transaction");
        System.out.println("Enter Your Choice:");
        byte choice = scan.nextByte();
        switch (choice) {
            case 1:
                System.out.println("The Cash Donation will be sanctioned to you, after Background Verification. You will be Notified once the process finishes.");
                System.out.println("After Successful Verification, You can collect the cash from our " + office + " Office.");
                break;
            case 2:
                System.out.println("Enter Your UPI ID:");
                upiId = scan.next();
                break;
            case 3:
                System.out.println("ENTER YOUR BANK ACCOUNT DETAILS");
                System.out.println("\n**Please be careful while entering the information, as these details will be used to send you the Money, you requested.");
                System.out.println("---------------------------------");

                System.out.println("Enter Account Holder Name:");
                accHolderName = scan.next();
                System.out.println("Enter Bank Name:");
                scan.nextLine();
                bankName = scan.nextLine();
                System.out.println("Enter Account Number:");
                accNo = scan.next();
                System.out.println("Enter IFSC Code:");
                ifsc = scan.next();
                System.out.println("Enter Bank Branch:");
                branch = scan.next();
                break;
            default:
                System.out.println("Invalid Choice");
        }

    }

    @Override
    public void generateID() {
        Random random = new Random();
        int rn = random.nextInt(1000000);
        reqID = Integer.toString(rn);
        reqID = "NGO-REQ" + reqID;
        System.out.println("------------YOU HAVE REGISTERED SUCCESSFULLY----------");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Requester ID: " + reqID);
        System.out.println("------------------------------------------------------");
    }

    @Override
    public void insertDetails() {
        PreparedStatement statement=null;
        try {
            Connection con = GetConnection.getConnection();
            String sql1 = "insert into requester(reqId,fname,lname,age,gender,mobile,email,city,occupation,purpose,nearbylocation) values(?,?,?,?,?,?,?,?,?,?,?)";
            statement = con.prepareStatement(sql1);
            statement.setString(1, reqID);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setInt(4, age);
            statement.setString(5, gender);
            statement.setString(6, mobileNo);
            statement.setString(7, email);
            statement.setString(8, city);
            statement.setString(9, occupation);
            statement.setString(10, purpose);
            statement.setString(11, office);
            statement.execute();

            String sql2 = "insert into requesterbankdetails(acHolderName,accountNumber,ifsc,bankName,branch,upiId,reqId) values(?,?,?,?,?,?,?)";
            statement = con.prepareStatement(sql2);
            statement.setString(1, accHolderName);
            statement.setString(2, accNo);
            statement.setString(3, ifsc);
            statement.setString(4, bankName);
            statement.setString(5, branch);
            statement.setString(6, upiId);
            statement.setString(7, reqID);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

