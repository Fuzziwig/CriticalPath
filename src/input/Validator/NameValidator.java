package input.Validator;

public class NameValidator {
    //for checking names
    public boolean isNameValid(String input){
        if(input == null){
            return false;
        }
        return input.matches("\\w+");
    }
}
