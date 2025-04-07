package cinema;

import java.util.*;

public class Cinema {
    private Map<String, Session> sessions = new HashMap<>();
    private Map<Integer, Ticket> tickets = new HashMap<>();
    private int nextTicketId = 1;

    // Session CRUD
    public void addSession(Session session) {
        sessions.put(session.getName(), session);
    }

    public Session getSession(String name) {
        return sessions.get(name);
    }

    public void updateSession(String oldName, Session newSession) {
        sessions.remove(oldName);
        sessions.put(newSession.getName(), newSession);
    }

    public void removeSession(String name) {
        sessions.remove(name);
    }

    public Collection<Session> getAllSessions() {
        return sessions.values();
    }

    // Ticket CRUD
    public Ticket createTicket(String sessionName, double price) {
        Session session = sessions.get(sessionName);
        if (session == null) throw new IllegalArgumentException("Сеанс не знайдено.");
        session.sellTicket();
        Ticket ticket = new Ticket(nextTicketId++, sessionName, price);
        tickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public Ticket getTicket(int id) {
        return tickets.get(id);
    }

    public void updateTicket(int id, String newSessionName, double newPrice) {
        Ticket ticket = tickets.get(id);
        if (ticket != null) {
            ticket.setSessionName(newSessionName);
            ticket.setPrice(newPrice);
        }
    }

    public void deleteTicket(int id) {
        tickets.remove(id);
    }

    public Collection<Ticket> getAllTickets() {
        return tickets.values();
    }

    public double getTotalRevenue() {
        return tickets.values().stream().mapToDouble(Ticket::getPrice).sum();
    }
}
