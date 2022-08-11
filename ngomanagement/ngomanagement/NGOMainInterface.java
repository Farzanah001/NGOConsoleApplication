package ngomanagement;

import java.util.Scanner;
import java.util.regex.Pattern;

public interface NGOMainInterface {
    void getPersonalDetails();
    void generateID();
    void insertDetails();
   // void displayDetails();
    Scanner scan=new Scanner(System.in);
    public default String checkEmail(String email){

        System.out.println("Enter Your Email:");
        //scan.nextLine();
        email= scan.next();
        String emailRegex="^[A-Za-z0-9+_.-]+@[a-z]+(.+)$";
        boolean emailResult= Pattern.matches(emailRegex,email);
        if(emailResult==false){
            System.out.println("Invalid Email Address!");

            checkEmail(email);
        }
        return email;
    }
    public default String checkMobile(String mobileNo){
        System.out.println("Enter Your Mobile Number:");
        mobileNo= scan.next();
        String mobileRegex="(0/91)?[6-9][0-9]{9}";
        boolean mobileResult=Pattern.matches(mobileRegex,mobileNo);
        if(mobileResult==false){
            System.out.println("Invalid Mobile Number!");
            //mobileNo="";
            checkMobile(mobileNo);
        }
        return mobileNo;
    }

}
