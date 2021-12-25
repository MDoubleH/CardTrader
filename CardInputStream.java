import java.io.*;

public class CardInputStream {
    private BufferedReader reader;

    public CardInputStream(InputStream input){
        reader = new BufferedReader(new InputStreamReader(input));
    }

    public void close() throws IOException {
        reader.close();
    }

    public Card readCard() {
        String cardCheck;
        long id = 0;
        String name = null;
        Rank rank = null;
        long price = 0;
        try {
            cardCheck = reader.readLine();
                if (cardCheck.equals("CARD")) {
                    id = Long.parseLong(reader.readLine());
                    name = reader.readLine();
                    rank = Rank.valueOf(reader.readLine());
                    price = Long.parseLong(reader.readLine());
            }
                else if (cardCheck.equals("OK")){
                return null;
            }
        } catch (IOException e) {
            System.err.println("I/O failed" + e.getMessage());
        }
        return new Card(id, name, rank, price);
    }

    public String readResponse(){
        String responseReader = null;
        try {
            responseReader = reader.readLine();
        } catch (IOException e) {
            System.err.println("I/O failed" + e.getMessage());
        }
        return responseReader;
    }
}
