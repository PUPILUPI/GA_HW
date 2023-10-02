package ru.belov.hw3.ioPractice;

public class Command {
    private String path;
    private CommandEnum command;
    private String content;

     public Command(String path, CommandEnum command, String content) {
         this.path = path;
         this.command = command;
         this.content = content;
     }

    public String getContent() {
        return content;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public String getPath() {
        return path;
    }
}
