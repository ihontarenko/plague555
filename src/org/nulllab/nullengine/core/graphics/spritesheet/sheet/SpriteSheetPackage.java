package org.nulllab.nullengine.core.graphics.spritesheet.sheet;

import org.nulllab.nullengine.core.common.Initializable;
import org.nulllab.nullengine.core.common.resource.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

abstract public class SpriteSheetPackage implements Initializable {

  private String                   filename;
  private Map<String, SpriteSheet> spriteSheets;

  public SpriteSheetPackage(String filename) {
    this.filename = filename;
    this.spriteSheets = new HashMap<>();
    initialize();
  }

  public SpriteSheet getSpriteSheet(String name) {
    return spriteSheets.get(name);
  }

  public Map<String, SpriteSheet> getSpriteSheets() {
    return spriteSheets;
  }

  public String getFilename() {
    return filename;
  }

  public String getPackageUniqueName() {
    return getClass().getSimpleName();
  }

  @Override
  public boolean isInitialized() {
    return spriteSheets.size() > 0;
  }

  @Override
  public void initialize() {
    InputStream   inputStream   = new ImageLoader(getFilename()).getFileStream();
    BufferedImage bufferedImage;

    try {
      bufferedImage = ImageIO.read(inputStream);

      for (SpriteSheetMapper mapper : getSpriteSheetMappers()) {
        SpriteSheet sheet = new SpriteSheet(bufferedImage,
            mapper.getSizeX(), mapper.getSizeY(),
            mapper.getOffsetX(), mapper.getOffsetY(),
            mapper.getCountX(), mapper.getCountY()
        );
        spriteSheets.put(mapper.getName(), sheet);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void reinitialize() {

  }

  abstract public SpriteSheetMapper[] getSpriteSheetMappers();

}