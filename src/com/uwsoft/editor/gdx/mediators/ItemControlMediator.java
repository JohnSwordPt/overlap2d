package com.uwsoft.editor.gdx.mediators;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.uwsoft.editor.data.manager.DataManager;
import com.uwsoft.editor.gdx.actors.SelectionRectangle;
import com.uwsoft.editor.renderer.actor.*;
import com.uwsoft.editor.renderer.data.*;

import java.io.File;
import java.util.HashMap;

/**
 * Created by CyberJoe on 3/18/2015.
 */
public class ItemControlMediator {

    private SceneControlMediator sceneControl;

    public ItemControlMediator(SceneControlMediator sceneControl) {
        this.sceneControl = sceneControl;
    }


    private void itemZIndexChange( HashMap<IBaseItem, SelectionRectangle> currentSelection, boolean isUp) {
        for (SelectionRectangle value : currentSelection.values()) {
            sceneControl.getCurrentScene().updateDataVO();

            int ammount = 1;
            if (!isUp) ammount = -1;

            int setting = value.getHostAsActor().getZIndex() + ammount;
            if (setting < 0) setting = 0;
            Group parent = value.getHostAsActor().getParent();
            parent.swapActor(value.getHostAsActor().getZIndex(), setting);

            sceneControl.getCurrentScene().updateDataVO();
        }
    }

    public void moveItemBy(Actor actor, float x, float y) {
        actor.setX(actor.getX() + x);
        actor.setY(actor.getY() + y);
    }

    public void removeItem(Actor actor) {
        IBaseItem item = (IBaseItem) actor;
        actor.remove();
        sceneControl.getCurrentScene().removeItem(item);
        item.dispose();
    }

}
