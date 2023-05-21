package SMS;

public class Message { // obiekt przesyłany między VBD i VRD
    private String
            sender,
            recipient,
            content;

    public Message(String sender, String recipent, String content) {
        this.sender = null;
        this.recipient = recipent;
        this.content = content;
    }

    // implementacja throws InvalidRecipentException

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    // todo: metody do walidacji numerów telefonów, formatowania wiadomości, czy kodowania/dekodowania w standardzie PDU
}

