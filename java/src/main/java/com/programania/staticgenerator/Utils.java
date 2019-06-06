package com.programania.staticgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

class Utils {
  static List<String> flatmapLines(List<Path> writtenPaths) {
    return writtenPaths.stream()
        .flatMap(path -> {
          try {
            return Files.readAllLines(path).stream();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }).collect(toList());
  }
}