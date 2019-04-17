package com.programania.staticgenerator;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

public class AppTest {

  public static final Path SRC = Paths.get("/home/luis/src/yo/static-site-generator-kata/source/");
  public static final Path A_SECTION = Paths.get("/home/luis/src/yo/static-site-generator-kata/source/01-section");
  public static final Path A_FILE = Paths.get("/home/luis/src/yo/static-site-generator-kata/source/section/01-intro.md");

  @Test
  public void shouldAnswerWithTrue() throws IOException {


      String concatednatedMarkdown = concatFolder(A_SECTION);

    System.out.println(concatednatedMarkdown);


    String content = readFile(A_FILE);

    String output = render(content);

    assertEquals(output, "<p>This is <em>Sparta</em></p>\n");


  }

  private String render(String content) {
    Parser parser = Parser.builder().build();
    Node document = parser.parse(content);
    HtmlRenderer renderer = HtmlRenderer.builder().build();
    return renderer.render(document);
  }

  private String concatFolder(Path aSection) {
    //java 8
    try (Stream<Path> paths = Files.walk(aSection, 1)) {

      String collect = paths
          .filter(Files::isRegularFile)
          .map(AppTest::readFile)
          .sorted()
          .collect(joining("\n"));

      return collect;
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(),e.getCause());
    }
  }

  private static String readFile(Path aFile) {
    try {
      return new String(Files.readAllBytes(aFile));
    } catch (IOException e) {
      throw new RuntimeException(e.getCause());
    }
  }
}
