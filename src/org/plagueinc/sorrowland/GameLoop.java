package org.plagueinc.sorrowland;

import org.plagueinc.sorrowland.core.entity.Loop;
import org.plagueinc.sorrowland.gui.GUIWindow;
import org.plagueinc.sorrowland.manager.AppManager;

public class GameLoop extends Loop {

  private boolean    isInitialized;
  private float      oneSecondElapsed;
  private GUIWindow  gui;
  private AppManager appManager;

  public GameLoop() {
    super();
    initialize();
  }

  @Override
  public boolean isInitialized() {
    return isInitialized;
  }

  @Override
  public void initialize() {
    if (!isInitialized()) {
      isInitialized = true;
      gui = new GUIWindow(800, 600);
      gui.initialize();
      appManager = new AppManager();
    }
  }

  @Override
  protected void update(float nano) {
    // default code for update window title information
    oneSecondElapsed += nano;
    if (oneSecondElapsed > (ONE_NANO_SECOND / 4) && null != getExecutionInfo()) {
      gui.setTitle(getExecutionInfo());
      oneSecondElapsed = 0;
    }

    // custom code below
    getAppManager().update(nano);
  }

  @Override
  protected void render() {
    getGUI().clearFrame();
    getAppManager().render(getGUI().getG2D());
    getGUI().swapBuffer();
  }

  public AppManager getAppManager() {
    return appManager;
  }

  public GUIWindow getGUI() {
    return gui;
  }
}
