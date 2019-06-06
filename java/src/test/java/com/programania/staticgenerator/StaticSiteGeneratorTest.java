package com.programania.staticgenerator;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.programania.staticgenerator.StaticSiteGenerator.buildSite;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StaticSiteGeneratorTest {

  @Test
  public void si_pasamos_fichero() {

    Path aFile = Paths.get("../source/01-section/01-intro.md");

    List<Path> writtenPaths = buildSite(aFile, Paths.get("../site"));

    List<String> lines = Utils.flatmapLines(writtenPaths);
    assertEquals(1, lines.size());
    lines.forEach(line -> assertTrue(line.contains("This is <em>Sparta</em>")));
  }


  @Test
  public void si_pasamos_directorio() {

    Path aFolder = Paths.get("../source/01-section/");

    List<Path> writtenPaths = buildSite(aFolder, Paths.get("../site"));

    List<String> lines = Utils.flatmapLines(writtenPaths);
    assertEquals(3, lines.size());
    lines.forEach(line -> assertTrue(line.contains("This is <em>Sparta</em>")));

  }


  @Test
  public void si_pasamos_directorio_padre() {

    Path aFolder = Paths.get("../source/");

    List<Path> writtenPaths = buildSite(aFolder, Paths.get("../site"));

    List<String> lines = Utils.flatmapLines(writtenPaths);
    assertEquals(6, lines.size());
    lines.forEach(line -> assertTrue(line.contains("This is <em>Sparta</em>")));

  }
}