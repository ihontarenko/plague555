package org.impulse1911.startApp.core.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageLoader extends FileResourceLoader {

  public ImageLoader(String filepath) throws FileNotFoundException {
    super(filepath);
  }

  public BufferedImage load() throws IOException {
    return ImageIO.read(this.resource);
  }

}