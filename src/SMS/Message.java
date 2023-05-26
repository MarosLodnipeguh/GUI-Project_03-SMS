package SMS;

public class Message { // obiekt przesyłany

    private int sender;
    private int recipient;
    private String content;

    public Message(int sender, int recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public int getSender() {
        return sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString () {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    // ==================================================== PDU ENCODING =============================================================

    public String encodeToPDU() {
        StringBuilder pduBuilder = new StringBuilder();

        // Kodowanie pola sender
        String senderPDU = convertToSemiOctet(sender);
        pduBuilder.append(senderPDU);

        // Kodowanie pola recipient
        String recipientPDU = convertToSemiOctet(recipient);
        pduBuilder.append(recipientPDU);

        // Kodowanie pola content
        String contentPDU = convertTo7BitDefaultAlphabet(content);
        pduBuilder.append(contentPDU);

        return pduBuilder.toString();
    }

    private String convertToSemiOctet(int number) {
        String numberString = String.valueOf(number);
        StringBuilder semiOctetBuilder = new StringBuilder();

        for (int i = 0; i < numberString.length(); i += 2) { // bierze 2 liczby numeru
            String digit = numberString.substring(i, i + 2);
            semiOctetBuilder.append(String.format("%02X", Integer.parseInt(digit))); // dodaje 2 liczby numeru jako 2 znaki HEX
        }

        return semiOctetBuilder.toString();
    }

    private String convertTo7BitDefaultAlphabet(String text) {
        StringBuilder pduBuilder = new StringBuilder();
        byte[] bytes = text.getBytes();

        for (byte b : bytes) {
            pduBuilder.append(String.format("%02X", b)); // formatuje Bajt jako 2 znaki HEX
        }

        return pduBuilder.toString();
    }



}

