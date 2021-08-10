
package tpbank;
import java.util.*;

public class TPBank {
    Scanner sc = new Scanner (System.in);
    public static void main(String[] args) {
        TPBank login = new TPBank();
        login.start();
    }
    
    public void menu(){
        System.out.println("=========Login Tien Phong Bank’s Ebank===========");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");}
    int checkInt( int min, int max) {
        int num;
        while (true) {
            try {
                System.out.print("Please choose one option: ");
                num = Integer.parseInt(sc.nextLine());
                if (num < min || num > max) {
                    System.out.println("Out of range !!");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer number!");
            }
        }
    }
    public void start(){
    while (true) {
            menu();
            int input = checkInt(1, 3); 
            Locale locale;
            if (input == 3) return;
            if (input == 1)  {locale = new Locale.Builder().setLanguage("vi").setRegion("VN").build();}
              else   {locale = new Locale("en","US");}
            ResourceBundle bundle = ResourceBundle.getBundle("tpbank/language", locale);
            checkAccountNumber(bundle);
            checkPassword(bundle);
            checkCaptcha(bundle);
                }}

    public void checkAccountNumber(ResourceBundle bundle) {
        while (true) {
            System.out.print(bundle.getString("account"));
            String accountNumber = sc.nextLine();
            if (accountNumber.matches("^[0-9]{10}$")) {
                break;
            } else {
                System.out.println(bundle.getString("check.account"));
            }
        }
    }

    public void checkPassword(ResourceBundle bundle) {
        while (true) {
            System.out.print(bundle.getString("password"));
            String pw = sc.nextLine();
            if (pw.matches("^[0-9a-zA-Z]{8,31}$")&&pw.matches(".*[a-zA-Z].*") && pw.matches(".*[0-9].*")) {             
                break;
            } else {
                System.out.println(bundle.getString("wrong.password"));
            }
        }
    }

    public String randomCaptcha() {
        String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        String captcha = "";
        for (int i = 0; i < 5; i++) {
            int index = r.nextInt(s.length());
            captcha = captcha + s.charAt(index);
            s = s.substring(0, index) + s.substring(index+1);
        }
        return captcha;
    }

    public void checkCaptcha(ResourceBundle bundle) {
        String captcha = randomCaptcha();
        System.out.println(bundle.getString("captcha") + captcha);
        while (true) {
            System.out.print(bundle.getString("captchaInput"));
            String input = sc.nextLine();
            if (input.length() == 1 && captcha.contains(input)) {
                return;
            } else {
                System.out.println(bundle.getString("wrong.captcha"));
            }
        }
    }
}
