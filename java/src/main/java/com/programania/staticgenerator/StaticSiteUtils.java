package com.programania.staticgenerator;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StaticSiteUtils {
  public static List<Entry> transform(Path path) {

    if (isParentDirectory(path)) {
      return getFolders(path).stream().map(aSection -> Entry.of(
          aSection.getFileName(),
          concatFolder(aSection)
      )).collect(Collectors.toList());
    }

    return Collections.singletonList(Entry.of(path.getFileName(), concatFolder(path)));
  }

  private static List<Path> getFolders(Path aSection) {
    //java 8
    try (Stream<Path> paths = Files.walk(aSection, 1)) {
      return paths.collect(toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean isParentDirectory(Path aSection) {
    //java 8
    try (Stream<Path> paths = Files.walk(aSection, 1)) {
      return paths.allMatch(path -> Files.isDirectory(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String concatFolder(Path aSection) {
    //java 8
    //comentar alternativa forEach Fernando optimización
    try (Stream<Path> paths = Files.walk(aSection, 1)) {
      return paths
          .filter(Files::isRegularFile)
          .map(StaticSiteUtils::readFile)
          //ojo! para ordenar necesita resolver el stream
          .sorted()
          .collect(joining("\n"));
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e.getCause());
    }
  }

  static String render(String content) {
    Parser parser = Parser.builder().build();
    Node document = parser.parse(content);
    HtmlRenderer renderer = HtmlRenderer.builder().build();
    return renderer.render(document);
  }

  private static String readFile(Path path) {
    try {
      return new String(Files.readAllBytes(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}