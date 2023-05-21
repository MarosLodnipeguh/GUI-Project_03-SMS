package SMS;

public class NullRecipentException extends RuntimeException{

    public NullRecipentException () {
        super("No VRDs found!");
    }
}
