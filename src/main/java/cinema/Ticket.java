
package cinema;

import java.util.Objects;

public class Ticket {
    private int id;
    private String sessionName;
    private double price;

    public Ticket(int id, String sessionName, double price) {
        this.id = id;
        this.sessionName = sessionName;
        this.price = price;
    }

    public int getId() { return id; }
    public String getSessionName() { return sessionName; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
    public void setSessionName(String sessionName) { this.sessionName = sessionName; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
