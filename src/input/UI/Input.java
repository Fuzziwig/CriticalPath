package input.UI;

import input.Validator.NameValidator;
import input.Validator.NumberValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    public String readName(String msg, String errmsg, int maxlen) {
        Scanner reader = new Scanner(System.in);
        NameValidator v = new NameValidator();
        while (true) {
            try {
                System.out.println(msg);
                String input = reader.nextLine();
                if (input.length()>maxlen){
                    throw new IndexOutOfBoundsException(Integer.toString(input.length()));
                }
                if (v.isNameValid(input)){
                    return input;
                }
                else throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println(errmsg);
            } catch (IndexOutOfBoundsException e){
                System.out.println("You have entered "+e.getMessage()+" characters. Supported length is "+maxlen+".");
            }
        }
    }

    public int readNumber(String msg, String errmsg) {
        Scanner reader = new Scanner(System.in);
        NumberValidator v = new NumberValidator();
        while (true) {
            try {
                System.out.println(msg);
                String input = reader.nextLine();
                if (v.isNumberValid(input)){
                    return Integer.parseInt(input);
                }
                else throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println(errmsg);
            }
        }
    }

}
