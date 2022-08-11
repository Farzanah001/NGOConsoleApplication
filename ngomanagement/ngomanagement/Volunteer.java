package ngomanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import ngomanagement.*;
import ngomanagement.location.OfficeMapping;

import java.util.Random;
import java.util.Scanner;

public class Volunteer implements NGOMainInterface {

//    static final String DB_URL="jdbc:mysql://localhost/ngoapplication";
//    static final String USER="root";
//    static final String PASS="admin";
    public String firstName,lastName,city,office,gender,availability;
    String volunteerID="",email="",mobileNo="";
    byte age;
    @Override
    public void getPersonalDetails() {
        //String email,mobileNo;
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter Your First Name:");
        firstName=scan.next();
        System.out.println("Enter Your Last Name:");
        lastName=scan.next();
        System.out.println("Enter Your Gender(M|F|T|NB):");
        gender= scan.next();
        System.out.println("Enter Your Age:");
        age=scan.nextByte();
        if(age<18){
            System.out.println("Sorry! You're Not Eligible to Volunteer.");
            MainManagement.main(null);
        }
        String mail=checkEmail(email);
        email=mail;
        String mobile=checkMobile(mobileNo);
        mobileNo=mobile;
        System.out.println("Enter the Name of Your District/City:");
        scan.nextLine();
        city=scan.nextLine().toLowerCase();
        System.out.println("Enter Your Availability.\n\t1. WeekDays\t2. WeekEnd");
        byte choice=scan.nextByte();
        switch (choice){
            case 1:
                availability="WeekDays";
                break;
            case 2:
                availability="WeekEnd";
                break;
            default:
                System.out.println("Enter Valid Input(1 or 2)");
        }
        office= OfficeMapping.findNearestHub(city);
        System.out.println("\n Your Work Location will be:"+office);
    }

    @Override
    public void generateID() {

        Random random=new Random();
        int rn=random.nextInt(1000000);
        volunteerID=Integer.toString(rn);
        volunteerID = "NGO-V"+volunteerID;
        System.out.println("------------YOU HAVE REGISTERED SUCCESSFULLY------------");
        System.out.println("\t Name : "+firstName+" "+lastName);
        System.out.println("\t Register Number : "+volunteerID);
        System.out.println("-------------------------------------------------------");
    }

    @Override
    public void insertDetails() {
        try {

            Connection con = GetConnection.getConnection();
            String sql="insert into volunteer(volId,fname,lname,age,gender,mobile,email,city,availability,worklocation) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,volunteerID);
            statement.setString(2,firstName);
            statement.setString(3,lastName);
            statement.setInt(4,age);
            statement.setString(5,gender);
            statement.setString(6,mobileNo);
            statement.setString(7,email);
            statement.setString(8,city);
            statement.setString(9,availability);
            statement.setString(10,office);
            statement.execute();
            System.out.println("\nData Inserted Successfully!\n");
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
