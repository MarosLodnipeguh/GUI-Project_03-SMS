package SMS;

public class InvalidRecipentException extends RuntimeException{

    public InvalidRecipentException () {
        super("Invalid Recipent Number!");
    }
}
