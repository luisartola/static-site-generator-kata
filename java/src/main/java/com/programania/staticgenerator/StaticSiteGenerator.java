package com.programania.staticgenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;


class StaticSiteGenerator {

  static List<Path> buildSite(Path aFolder, Path output) {
    return StaticSiteUtils.transform(aFolder.toAbsolutePath())
        .stream()
        .map(entry -> uncheckedWrite(output, entry))
        .collect(toList());
  }

  //explicar la composicion haciendo una lambda que hace runtime excepciones ioexception????
  private static Path uncheckedWrite(Path output, Entry entry) {
    try {
      //gestionar este error dentro de una lambda es un coñazo
      return Files.write(
          entryToSite(output, entry),
          StaticSiteUtils.render(entry.getContent()).getBytes()
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static Path entryToSite(Path output, Entry entry) {
    return output.toAbsolutePath().resolve(entry.getNameWithHtmlExtension());
  }
}