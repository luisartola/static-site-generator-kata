package com.programania.staticgenerator;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;


/*
* crear un método generate
* si  pasamos un fichero markdown,
*         genera directorio "site" con ese fichero en html
* si le pasamos un directorio,
*         concatena todos los ficheros interiores y genera un directorio "site con un único fichero html
* si le pasamos un directorio padre que dentro sólo tiene directorios hijos,
*         genera un directorio "site", que dentro tiene un fichero por cada directorio hijo, con todos los ficheros del hijo concatenados
* */

public class AppTest {

  @Test
  public void si_pasamos_fichero() throws IOException {

    Path aFile = Paths.get("../source/01-section/01-intro.md").toAbsolutePath();

    List<Entry> entries = StaticSiteUtils.transform(aFile);

    Entry entry = entries.get(0);
    String html = StaticSiteUtils.render(entry.getContent());
    Path write = Files.write(aFile.getParent().getParent().getParent().resolve("site").resolve(entry.getName()), html.getBytes());

    List<String> lines = Files.readAllLines(write);
    assertEquals(1, lines.size());
    assertEquals("<p>This is <em>Sparta</em> 01</p>", lines.get(0));


  }

  @Test
  public void si_pasamos_directorio() throws IOException {

    Path aFolder = Paths.get("../source/01-section/");

    List<Entry> entries = StaticSiteUtils.transform(aFolder);

    Entry entry = entries.get(0);
    String html = StaticSiteUtils.render(entry.getContent());
    Path write = Files.write(aFolder.getParent().getParent().resolve("site").resolve(entry.getName()), html.getBytes());
    List<String> lines = Files.readAllLines(write);

    assertEquals(3, lines.size());
    assertEquals("<p>This is <em>Sparta</em> 01", lines.get(0));
    assertEquals("This is <em>Sparta</em> 02", lines.get(1));
    assertEquals("This is <em>Sparta</em> 03</p>", lines.get(2));

  }

  @Test
  public void si_pasamos_directorio_padre() throws IOException {

//    Path aFolder = Paths.get("../source/");
//
//    String markdown = StaticSiteUtils.transform(aFolder);
//
//    String html = StaticSiteUtils.render(markdown);
//
//    Path write = Files.write(aFolder.getParent().resolve("coco.html"), html.getBytes());
//
//    List<String> lines = Files.readAllLines(write);
//
//    assertEquals(3, lines.size());
//    assertEquals("<p>This is <em>Sparta</em> 01", lines.get(0));
//    assertEquals("This is <em>Sparta</em> 02", lines.get(1));
//    assertEquals("This is <em>Sparta</em> 03</p>", lines.get(2));

  }

}