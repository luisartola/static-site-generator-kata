package com.programania.staticgenerator;

import java.nio.file.Path;

class Entry {
  private final Path fileName;
  private final String content;

  private Entry(Path fileName, String content) {
    this.fileName = fileName;
    this.content = content;
  }

  static Entry of(Path fileName, String content) {
    return new Entry(fileName, content);
  }

  String getContent() {
    return this.content;
  }

  String getNameWithHtmlExtension() {
    return this.fileName.getFileName().toString().replace(".md", "").concat(".html");
  }
}