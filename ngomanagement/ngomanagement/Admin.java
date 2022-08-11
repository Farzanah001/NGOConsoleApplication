package ngomanagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Admin {
    static final String adminUser = "Admin";
    static final String adminPassword = "admin123";

    Volunteer volunteer = new Volunteer();
    Requester requester = new Requester();
    Donor donor = new Donor();
    Scanner scan = new Scanner(System.in);

    public void adminManagement() {
        System.out.println("Enter the Username:");
        String userName = scan.next();
        System.out.println("Enter the Password:");
        String password = scan.next();

        if (userName.equals(adminUser) && password.equals(adminPassword)) {
            System.out.println("------ADMIN DASHBOARD------");
            System.out.println("\t1. Create Volunteer");
            System.out.println("\t2. Create Request/Requester");
            System.out.println("\t3. Create Donor");
            System.out.println("\t4. View Volunteer Details");
            System.out.println("\t5. Search Volunteer");
            System.out.println("\t6. View Donor Details");
            System.out.println("\t7. Search Donor");
            System.out.println("\t8. View Donation Details");
            System.out.println("\t9. View Requester Details");
            System.out.println("\t10. Search Requester.");
            System.out.println("\n Enter Your Choice:");
            byte choice = scan.nextByte();
            switch (choice) {
                case 1:
                    createVolunteer();
                    break;
                case 2:
                    createRequester();
                    break;
                case 3:
                    createDonor();
                    break;
                case 4:
                    viewVolunteers();
                    break;
                case 5:
                    searchVolunteer();
                    break;
                case 6:
                    viewDonors();
                    break;
                case 7:
                    searchDonor();
                    break;
                case 8:
                    viewDonations();
                    break;
                case 9:
                    viewRequester();
                    break;
                case 10:
                    searchRequester();
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } else {
            System.out.println("Invalid Username Or Password! Please Try Again!");
        }
    }

    public void createVolunteer() {
        volunteer.getPersonalDetails();
        volunteer.generateID();
        volunteer.insertDetails();
    }

    public void createRequester() {
        requester.getPersonalDetails();
        requester.generateID();
        requester.getRequirements();
        requester.insertDetails();
    }

    public void createDonor() {
        donor.getPersonalDetails();
        donor.generateID();
        donor.typeOfDonation();
        donor.insertDetails();
    }

    public void viewVolunteers() {
        try {
            Connection con = GetConnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from volunteer");
            System.out.println("Volunteer ID\t\tNAME\t\tAGE\t\tGENDER\t\tMOBILE\t\tEMAIL\t\tCITY\t\tAVAILABILITY\t\tOFFICE");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" + resultSet.getString(8) + "\t" + resultSet.getString(9) + "\t" + resultSet.getString(10));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDonors() {
        try {
            Connection con = GetConnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from donor");
            System.out.println("DONOR ID\t\tNAME\t\tAGE\t\tGENDER\t\tMOBILE\t\tEMAIL\t\tCITY");
            while (resultSet.next()) {

                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" + resultSet.getString(8));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDonations() {
        try {
            Connection con = GetConnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from donations");
            System.out.println("DONOR ID\t\tDONATION ITEM\t\tDONATION AMOUNT\t\tDROP LOCATION");
            while (resultSet.next()) {

                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewRequester() {
        try {
            Connection con = GetConnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from requester");
            System.out.println("REQUESTER ID\t\tNAME\t\tAGE\t\tGENDER\t\tMOBILE\t\tEMAIL\t\tCITY\t\tOCCUPATION\t\tPURPOSE\t\tOFFICE");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4) + "\t" + resultSet.getString(5) + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" + resultSet.getString(8) + "\t" + resultSet.getString(9) + "\t" + resultSet.getString(10) + "\t" + resultSet.getString(11));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchVolunteer() {
        System.out.println("Enter the Volunteer ID:");
        String id = scan.next();

        try {
            Connection con = GetConnection.getConnection();
            String sql = "select * from volunteer where volId=\'" + id + "\'";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("Volunteer ID\t:" + id);
                System.out.println("Volunteer Name\t:" + resultSet.getString(2) + " " + resultSet.getString(3));
                System.out.println("Age\t:" + resultSet.getString(4));
                System.out.println("Gender\t:" + resultSet.getString(5));
                System.out.println("Mobile Number\t:" + resultSet.getString(6));
                System.out.println("Email ID\t:" + resultSet.getString(7));
                System.out.println("City\t:" + resultSet.getString(8));
                System.out.println("Availability\t:" + resultSet.getString(9));
                System.out.println("Office\t:" + resultSet.getString(10));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchDonor(){
        System.out.println("Enter the Donor ID:");
        String id=scan.next();

        try {
            Connection con = GetConnection.getConnection();
            String sql = "select * from donor where volId=\'" + id + "\'";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("Donor ID\t:" + id);
                System.out.println("Donor Name\t:" + resultSet.getString(2) + " " + resultSet.getString(3));
                System.out.println("Age\t:" + resultSet.getString(4));
                System.out.println("Gender\t:" + resultSet.getString(5));
                System.out.println("Mobile Number\t:" + resultSet.getString(6));
                System.out.println("Email ID\t:" + resultSet.getString(7));
                System.out.println("City\t:" + resultSet.getString(8));
                System.out.println("Office\t:" + resultSet.getString(9));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchRequester(){
        System.out.println("Enter the Requester ID:");
        String id=scan.next();

        try {
            Connection con = GetConnection.getConnection();
            String sql = "select * from requester where volId=\'" + id + "\'";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("Requester ID\t:" + id);
                System.out.println("Requester Name\t:" + resultSet.getString(2) + " " + resultSet.getString(3));
                System.out.println("Age\t:" + resultSet.getString(4));
                System.out.println("Gender\t:" + resultSet.getString(5));
                System.out.println("Mobile Number\t:" + resultSet.getString(6));
                System.out.println("Email ID\t:" + resultSet.getString(7));
                System.out.println("City\t:" + resultSet.getString(8));
                System.out.println("Office\t:" + resultSet.getString(9));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
