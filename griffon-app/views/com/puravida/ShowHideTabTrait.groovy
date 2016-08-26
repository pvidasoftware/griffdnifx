package com.puravida

/**
 * Created by jorge on 23/08/16.
 */
trait ShowHideTabTrait {

    abstract FactoryBuilderSupport getBuilder();

    abstract GriffdnifxView getParentView();

    void show(){
        builder.mainTab.content.visible = true
        parentView.tabPane.selectionModel.select(builder.mainTab)
    }

    void hide(){
        builder.mainTab.content.visible = false
    }
}
