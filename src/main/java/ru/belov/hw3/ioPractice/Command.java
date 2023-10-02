package ru.belov.hw3.ioPractice;

public record Command(String path, CommandEnum command, String content) {
}
