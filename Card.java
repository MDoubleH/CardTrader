public class Card implements Comparable<Card> {
    long id;
    private String name;
    private Rank rank;
    long price;

    public Card(long id, String name, Rank rank){
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = 0;
    }

    //Because in q4 i need price ughhhhhh so i just added this
    public Card(long id, String name, Rank rank, long price){
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = price;
    }

    public String toString(){
        return "\n" + "CARD" + "\n" + id + "\n" + name + "\n" + rank + "\n" + price;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Card))
            return false;
        Card other = (Card) o;
        return id == other.id;
    }

    public int hashCode() {
        return (int)((id >> 32) ^ id);
    }

    public int compareTo(Card c) {
        int rankResult = this.rank.compareTo(c.rank);
        if (rankResult == 0) {
            int nameResult = this.name.compareTo(c.name);
            if (nameResult == 0){
                return Long.compare(this.id, c.id);
            } else {
                return nameResult;
            }
        } else {
            return rankResult;
        }
    }
}