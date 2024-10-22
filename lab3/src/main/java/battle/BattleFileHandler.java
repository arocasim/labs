package battle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BattleFileHandler {

    public void saveBattleToFile(Battle battle, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(battle.toString());
            System.out.println("Бій збережено у файл " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні файлу: " + e.getMessage());
        }
    }

    public Battle loadBattleFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            StringBuilder battleContent = new StringBuilder();
            for (String l : lines) {
                battleContent.append(l).append("\n");
            }
            System.out.println("\n--- Відтворений бій ---");
            System.out.println(battleContent.toString());
            return null;
        } catch (IOException e) {
            System.out.println("Помилка при завантаженні файлу: " + e.getMessage());
            return null;
        }
    }
}

