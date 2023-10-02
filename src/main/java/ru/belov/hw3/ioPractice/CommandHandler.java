package ru.belov.hw3.ioPractice;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class CommandHandler {

    public void showMenu() {
        try {
            System.out.println("""
                    Доступные операции:\s
                    <абсолютный путь к файлу> -create\s
                                              -delete\s
                                              -read\s
                                              -write "<содержимое>"\s
                    !ex - выйти\s""");
            System.out.println("Введите, что необходимо сделать:");
            executeCommand(readCommand());
            showMenu();
        } catch (IOException e) {
            System.out.println("При выполнении команды произошла ошибка. \nПопробуйте снова.");
        }
    }

    private Command readCommand() {
        Scanner sc = new Scanner(System.in);
        String[] string = sc.nextLine().split(" ");
        if (string.length < 2) {
            if (string.length == 0 || !string[0].equals("!ex")) {
                System.out.println("Вы ввели некорректную команду, попробуйте снова.");
                showMenu();
            } else {
                System.exit(0);
            }
        }
        if (validateCommand(string)) {
            return createCommand(string);
        } else {
            showMenu();
        }
        return null;
    }

    private void executeCommand(Command command) throws IOException {
        switch (command.command()) {
            case CREATE -> createFile(command);
            case DELETE -> deleteFile(command);
            case READ -> readFile(command);
            case WRITE -> writeFile(command);
        }
    }

    private Command createCommand(String[] stringCommand) {
        String path = stringCommand[0];
        CommandEnum command = switch (stringCommand[1]) {
            case "-create" -> CommandEnum.CREATE;
            case "-write" -> CommandEnum.WRITE;
            case "-read" -> CommandEnum.READ;
            case "-delete" -> CommandEnum.DELETE;
            default -> null;
        };
        if (stringCommand.length > 2) {
            String content = String.join(" ", Arrays.copyOfRange(stringCommand, 2, stringCommand.length));
            return new Command(path, command, content);
        }
        return new Command(path, command, null);
    }

    private boolean validateCommand(String[] stringCommand) {
        if (!isValidCommand(stringCommand)) return false;
        return isValidPath(stringCommand);
    }

    private boolean isValidPath(String[] stringCommand) {
        Path path = Paths.get(stringCommand[0]);
        Path parentPath = path.getParent();
        if (stringCommand[1].equals("-create")) {
            if (Files.exists(parentPath) && !Files.exists(path) && stringCommand[0].endsWith(".txt")) {
                return true;
            } else {
                System.out.println("Вы ввели некорректный путь для создания файла, \nпопробуйте снова.");
            }
        } else {
            if (Files.exists(path)) {
                return true;
            } else {
                System.out.println("такого файла не существует, попробуйте снова.");
            }
        }
        return false;
    }

    private boolean isValidCommand(String[] command) {
        String cmd = command[1];
        if (cmd.equals("-create") || cmd.equals("-write") || cmd.equals("-read") || cmd.equals("-delete")) {
            return true;
        } else {
            System.out.println("некорректная команда, попробуйте снова.");
            return false;
        }
    }

    private void readFile(Command command) throws IOException {
        File file = new File(command.path());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
        System.out.println(stringBuilder);
    }
    private void deleteFile(Command command) {
        File file = new File(command.path());
        if(file.delete()) {
            System.out.println("Файл успешно удален.");
        }
    }

    private void writeFile(Command command) throws IOException {
        FileWriter writer = new FileWriter(command.path());
        writer.write(command.content());
        writer.close();
        System.out.println("Информация в файл успешно записана.");
    }

    private void createFile(Command command) throws IOException {
        File file = new File(command.path());
        if(file.createNewFile()) {
            System.out.println("файл успешно создан.");
        }
    }

}
