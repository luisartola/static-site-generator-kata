package com.programania.staticgenerator;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class StaticSiteGeneratorTest {


  @Test
  public void test() throws IOException {


    List<Path> paths = StaticSiteGenerator.buildSite(null, null);

    assertTrue(paths == null);

  }
}