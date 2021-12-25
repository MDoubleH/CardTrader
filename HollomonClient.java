import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HollomonClient {
    Socket socket;
    BufferedReader reader = null;
    BufferedWriter writer = null;
    CardInputStream input;

    public HollomonClient(String server, int port) {
        try {
            socket = new Socket(server, port);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void writer(String input) {
        try {
            OutputStream os = socket.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write(input + "\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("I/O failed: " + e.getMessage());
        }
    }

    public List<Card> login(String username, String password) {
        String responseReader = null;
        Card line;
        ArrayList<Card> list = new ArrayList<Card>();
        try {
            InputStream is = socket.getInputStream();
            input = new CardInputStream(is);
            reader = new BufferedReader(new InputStreamReader(is));

            writer(username.toLowerCase());
            writer(password);
            responseReader = reader.readLine();
        } catch (IOException e) {
            System.err.println("I/O failed: " + e.getMessage());
        }
        if (responseReader != null && responseReader.equals("User " + username + " logged in successfully.")) {
            while ((line = input.readCard()) != null) {
                list.add(line);
            }
            Collections.sort(list);
            return list;
        } else {
            return null;
        }
    }

    public void close() throws IOException {
        reader.close();
        socket.close();
    }

    public long getCredits() {
        long amount = 0;
        try {
            InputStream is = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is));
            writer("CREDITS");
            amount = Long.parseLong(reader.readLine());
            input.readResponse();
        } catch (IOException e) {
            System.err.println("I/O failed: " + e.getMessage());
        }
        return amount;
    }

    public List<Card> getCards() {
        Card cards;
        ArrayList<Card> list = new ArrayList<Card>();
        writer("CARDS");
        while ((cards = input.readCard()) != null) {
            list.add(cards);
        }
        Collections.sort(list);
        return list;
    }

    public List<Card> getOffers() {
        Card cardsOnSale;
        ArrayList<Card> list = new ArrayList<Card>();
        writer("OFFERS");
        while ((cardsOnSale = input.readCard()) != null) {
            list.add(cardsOnSale);
        }
        Collections.sort(list);
        return list;
    }

    public boolean buyCard(Card card) {
        boolean value = false;
        if (getCredits() > card.price) {
            writer("BUY " + card.id);
            String response = input.readResponse();
            if (response.equals("OK")) {
                value = true;
            }
        }
        return value;
    }

    public boolean sellCard(Card card, long price) {
        boolean value = false;
        writer("SELL " + card.id + " " + price);
        String response = input.readResponse();
        if (response.equals("OK")) {
            value = true;
        }
        return value;
    }
}
