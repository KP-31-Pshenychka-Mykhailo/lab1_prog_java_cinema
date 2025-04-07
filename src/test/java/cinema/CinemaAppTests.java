package cinema;
import cinema.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CinemaAppTests {

    private Cinema cinema;

    @BeforeEach
    public void setUp() {
        cinema = new Cinema();
        cinema.addSession(new Session("Avatar", 10));
        cinema.addSession(new Session("Matrix", 5));
    }

    @Test
    public void testAddAndGetSession() {
        Session session = new Session("Titanic", 20);
        cinema.addSession(session);
        assertEquals(session, cinema.getSession("Titanic"));
    }

    @Test
    public void testUpdateSession() {
        cinema.updateSession("Avatar", new Session("Avatar 2", 15));
        assertNull(cinema.getSession("Avatar"));
        assertNotNull(cinema.getSession("Avatar 2"));
    }

    @Test
    public void testRemoveSession() {
        cinema.removeSession("Matrix");
        assertNull(cinema.getSession("Matrix"));
    }

    @Test
    public void testCreateTicketValidSession() {
        Ticket ticket = cinema.createTicket("Avatar", 100.0);
        assertNotNull(ticket);
        assertEquals("Avatar", ticket.getSessionName());
        assertEquals(100.0, ticket.getPrice());
    }

    @Test
    public void testCreateTicketInvalidSession() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cinema.createTicket("Nonexistent", 50.0);
        });
        assertEquals("Сеанс не знайдено.", exception.getMessage());
    }

    @Test
    public void testUpdateTicket() {
        Ticket ticket = cinema.createTicket("Avatar", 100.0);
        cinema.updateTicket(ticket.getId(), "Matrix", 200.0);
        Ticket updated = cinema.getTicket(ticket.getId());
        assertEquals("Matrix", updated.getSessionName());
        assertEquals(200.0, updated.getPrice());
    }

    @Test
    public void testDeleteTicket() {
        Ticket ticket = cinema.createTicket("Avatar", 100.0);
        cinema.deleteTicket(ticket.getId());
        assertNull(cinema.getTicket(ticket.getId()));
    }

    @Test
    public void testGetTotalRevenue() {
        cinema.createTicket("Avatar", 100.0);
        cinema.createTicket("Matrix", 150.0);
        assertEquals(250.0, cinema.getTotalRevenue());
    }

    @Test
    public void testSellTicketDecreasesAvailability() {
        Session session = cinema.getSession("Avatar");
        int availableBefore = session.getAvailableTickets();
        cinema.createTicket("Avatar", 50.0);
        assertEquals(availableBefore - 1, session.getAvailableTickets());
    }

    @Test
    public void testExportToJson() throws IOException {
        FileService fileService = Mockito.mock(FileService.class);
        doNothing().when(fileService).exportToJson(any(Cinema.class), anyString());

        fileService.exportToJson(cinema, "test.json");

        verify(fileService, times(1)).exportToJson(cinema, "test.json");
    }

    @Test
    public void testImportFromJson() throws IOException {
        FileService fileService = Mockito.mock(FileService.class);
        when(fileService.importFromJson("test.json")).thenReturn(cinema);

        Cinema loaded = fileService.importFromJson("test.json");

        assertNotNull(loaded);
        assertEquals(2, loaded.getAllSessions().size());
        verify(fileService, times(1)).importFromJson("test.json");
    }
}
