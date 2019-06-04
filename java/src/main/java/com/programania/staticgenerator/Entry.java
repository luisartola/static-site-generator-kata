package com.programania.staticgenerator;

import java.nio.file.Path;

class Entry {
  private final Path fileName;
  private final String content;

  public Entry(Path fileName, String content) {
    this.fileName = fileName;
    this.content = content;
  }

  public static Entry of(Path fileName, String content) {
    return new Entry(fileName, content);
  }

  public String getContent() {
    return this.content;
  }

  public String getName() {
    return this.fileName.getFileName().toString().replace(".md", "").concat(".html");
  }
}
