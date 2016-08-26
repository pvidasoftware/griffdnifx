package com.puravida

import javafx.scene.control.Tab

/**
 * Created by jorge on 23/08/16.
 */
trait ShowHideTabTrait {

    abstract Tab getTab();

    abstract GriffdnifxView getParentView();

    void show(){
        tab.content.visible = true
        parentView.tabPane.selectionModel.select(tab)
    }

    void hide(){
        tab.content.visible = false
    }
}
