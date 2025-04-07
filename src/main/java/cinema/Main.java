package cinema;

import cinema.service.FileService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Cinema cinema = new Cinema();
        FileService fileService = new FileService();

        while (true) {
            System.out.println("--- МЕНЮ ---");
            System.out.println("1. Додати сеанс");
            System.out.println("2. Оновити сеанс");
            System.out.println("3. Видалити сеанс");
            System.out.println("4. Переглянути сеанси");
            System.out.println("5. Купити квиток");
            System.out.println("6. Оновити квиток");
            System.out.println("7. Видалити квиток");
            System.out.println("8. Переглянути квитки");
            System.out.println("9. Статистика");
            System.out.println("10. Експорт");
            System.out.println("11. Імпорт");
            System.out.println("0. Вихід");
            System.out.print("> ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.print("Назва сеансу: ");
                    String name = scanner.nextLine();
                    System.out.print("Кількість квитків: ");
                    int count = Integer.parseInt(scanner.nextLine());
                    cinema.addSession(new Session(name, count));
                    break;
                case "2":
                    System.out.print("Стара назва сеансу: ");
                    String oldName = scanner.nextLine();
                    System.out.print("Нова назва сеансу: ");
                    String newName = scanner.nextLine();
                    System.out.print("Нова кількість квитків: ");
                    int newCount = Integer.parseInt(scanner.nextLine());
                    cinema.updateSession(oldName, new Session(newName, newCount));
                    break;
                case "3":
                    System.out.print("Назва сеансу: ");
                    String delName = scanner.nextLine();
                    cinema.removeSession(delName);
                    break;
                case "4":
                    for (Session s : cinema.getAllSessions()) {
                        System.out.println("Сеанс: " + s.getName() + ", Доступно: " + s.getAvailableTickets() + ", Продано: " + s.getSoldTickets());
                    }
                    break;
                case "5":
                    System.out.print("Сеанс: ");
                    String session = scanner.nextLine();
                    System.out.print("Ціна: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    try {
                        Ticket ticket = cinema.createTicket(session, price);
                        System.out.println("Квиток ID " + ticket.getId() + " придбано!");
                    } catch (Exception e) {
                        System.out.println("Помилка: " + e.getMessage());
                    }
                    break;
                case "6":
                    System.out.print("ID квитка: ");
                    int idUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.print("Нова назва сеансу: ");
                    String newSess = scanner.nextLine();
                    System.out.print("Нова ціна: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());
                    cinema.updateTicket(idUpdate, newSess, newPrice);
                    break;
                case "7":
                    System.out.print("ID квитка: ");
                    int idDelete = Integer.parseInt(scanner.nextLine());
                    cinema.deleteTicket(idDelete);
                    break;
                case "8":
                    for (Ticket t : cinema.getAllTickets()) {
                        System.out.println("ID: " + t.getId() + ", Сеанс: " + t.getSessionName() + ", Ціна: " + t.getPrice());
                    }
                    break;
                case "9":
                    System.out.println("Продано квитків: " + cinema.getAllTickets().size());
                    System.out.println("Загальний дохід: " + cinema.getTotalRevenue());
                    break;
                case "10":
                    fileService.exportToJson(cinema, "cinema.json");
                    System.out.println("Експортовано.");
                    break;
                case "11":
                    cinema = fileService.importFromJson("cinema.json");
                    System.out.println("Імпортовано.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Невідома команда.");
            }
        }
    }
}
