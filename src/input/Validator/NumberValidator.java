package input.Validator;

public class NumberValidator {

    //for checking number
    public boolean isNumberValid(String input){
        if(input == null){
            return false;
        }
        return input.matches("\\d+");
    }
}
