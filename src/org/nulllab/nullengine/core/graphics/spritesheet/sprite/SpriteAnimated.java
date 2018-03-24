package org.nulllab.nullengine.core.graphics.spritesheet.sprite;

import org.nulllab.nullengine.core.common.time.Timer;
import org.nulllab.nullengine.core.graphics.Canvas;
import org.nulllab.nullengine.core.graphics.spritesheet.sheet.SpriteSheet;

public class SpriteAnimated extends Sprite {

  public enum Direction {
    LOOPED, PING_PONG
  }

  public static final int DEFAULT_FPS = 30;

  private Direction      direction;
  private Timer          timer;
  private int            incrementer;

  public SpriteAnimated(SpriteSheet sheet) {
    this(sheet, DEFAULT_FPS, DEFAULT_SCALE, DEFAULT_INDEX, sheet.count(), Direction.LOOPED);
  }

  public SpriteAnimated(SpriteSheet sheet, int fps) {
    this(sheet, fps, DEFAULT_SCALE, DEFAULT_INDEX, sheet.count(), Direction.LOOPED);
  }

  public SpriteAnimated(SpriteSheet sheet, int fps, double scale) {
    this(sheet, fps, scale, DEFAULT_INDEX, sheet.count(), Direction.LOOPED);
  }

  public SpriteAnimated(SpriteSheet sheet, int fps, double scale, int startIndex, int lastIndex) {
    this(sheet, fps, scale, startIndex, lastIndex, Direction.LOOPED);
  }

  public SpriteAnimated(SpriteSheet sheet, int fps, double scale, int startIndex, int lastIndex, Direction direction) {
    super(sheet.getBufferedImages(startIndex, lastIndex));
    setScale(scale);
    this.incrementer = 1;
    this.direction = direction;
    this.timer = new Timer(1D / fps);
  }

  @Override
  public void draw(Canvas canvas, double x, double y) {
    nextIndex();
    super.draw(canvas, x, y);
  }

  private void nextIndex() {
    if (timer.isElapsedThenPurge()) {
      updateIncrementer();
      setActiveImage(getActiveIndex() + incrementer);
    }
  }

  private void updateIncrementer() {
    switch (direction) {
      case PING_PONG:
        if (getActiveIndex() == countImages() - 1) {
          incrementer = -1;
        } else if (getActiveIndex() == 0) {
          incrementer = 1;
        }
        break;
      case LOOPED:
        // default
    }
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }
}
