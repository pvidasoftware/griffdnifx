package com.puravida

import griffon.core.artifact.GriffonView
import griffon.metadata.ArtifactProviderFor
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Separator
import javafx.scene.control.TabPane
import javafx.scene.control.ToolBar
import javafx.stage.Stage
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import javax.annotation.Nonnull
import javax.swing.WindowConstants

@ArtifactProviderFor(GriffonView)
class GriffdnifxView extends AbstractJavaFXGriffonView {

    FactoryBuilderSupport builder
    GriffdnifxModel model

    TabPane tabPane

    void initUI() {
        builder.application(title: application.configuration['application.title'],
                sizeToScene: true, centerOnScreen: true, name: 'mainWindow') {
            scene(stylesheets:['bootstrapfx.css']) {
                borderPane{
                    top {
                        toolBar {
                            button text: application.messageSource.getMessage('inicio'),
                                    disable: bind(model.loggedProperty()),
                                    styleClass: ['btn', 'btn-lg', 'btn-default'],
                                    loginAction

                            separator()

                            button text:application.messageSource.getMessage('exit'),
                                    disable: bind(model.cantExitProperty()),
                                    styleClass: ['btn', 'btn-lg'],
                                    exitAction

                            separator()

                            label text: bind(model.nameProperty()),
                                    styleClass: ['lbl','lbl-default']
                        }
                    }
                    center {
                        tabPane id: 'tabPane', styleClass: ['panel'], {

                        }
                    }
                }
            }
        }
        tabPane = builder.tabPane

        Stage stage = builder.application.primaryStage
        stage.maximized = true
        stage.onCloseRequest = { windowEvent ->
             windowEvent.consume()
        }

    }

}