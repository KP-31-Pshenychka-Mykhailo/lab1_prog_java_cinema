
package cinema;

import java.util.Objects;

public class Session {
    private String name;
    private int availableTickets;
    private int soldTickets;

    public Session(String name, int availableTickets) {
        this.name = name;
        this.availableTickets = availableTickets;
        this.soldTickets = 0;
    }

    public String getName() { return name; }
    public int getAvailableTickets() { return availableTickets; }
    public int getSoldTickets() { return soldTickets; }

    public void sellTicket() {
        if (availableTickets <= 0) {
            throw new IllegalStateException("Немає доступних квитків.");
        }
        availableTickets--;
        soldTickets++;
    }

    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return name.equals(session.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
