import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        TreeMap<String, Integer> phoneBook = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        String userName = null;
        Integer phoneNumber;
        phoneBook.put("Иванов", 9999999);
        phoneBook.put("Петров", 7777777);

        do {
            System.out.print("Введите имя: ");
            input = scanner.nextLine().trim();

            String pattern = "^([\\p{L} ]+)?(\\s*([0-9]{7}))?$";

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);
            if (matcher.find()) {
                userName = matcher.group(1);
                String phoneString = matcher.group(2);
                phoneNumber = phoneString != null ? Integer.valueOf(phoneString.trim()) : null;
            } else {
                System.out.println("Некорректный ввод");
                continue;
            }
            if (userName != null && userName.equals("Список")) {
                printPhoneBook(phoneBook);
            } else if (userName != null && phoneBook.containsKey(userName)) {
                System.out.println(phoneBook.get(userName));
            } else if (userName != null && !phoneBook.containsKey(userName)) {
                System.out.println("Контакт не найден. Что бы добавить контакт введите номер телефона :");
                Integer newNumber = Integer.valueOf(scanner.nextLine().trim());
                phoneBook.put(userName, newNumber);
            } else if (phoneBook.containsValue(phoneNumber)) {
                for (Map.Entry<String, Integer> entry : phoneBook.entrySet()) {
                    if (entry.getValue().equals(phoneNumber)) {
                        System.out.println(entry.getKey());
                        break;
                    }
                }
            } else if (!phoneBook.containsValue(phoneNumber)) {
                System.out.println("Контакт не найден. Что бы добавить контакт введите имя :");
                String newName = scanner.nextLine().trim();
                phoneBook.put(newName, phoneNumber);
            }
        } while (!Objects.equals(userName, "КОНЕЦ"));
    }

    public static void printPhoneBook(TreeMap<String, Integer> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }
}
