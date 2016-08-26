package com.puravida

import es.gob.jmulticard.ui.passwordcallback.PasswordCallbackManager
import griffon.core.artifact.GriffonView
import griffon.javafx.support.fontawesome.FontAwesomeIcon
import griffon.metadata.ArtifactProviderFor
import griffon.plugins.fontawesome.FontAwesome
import griffon.transform.Threading
import javafx.embed.swing.SwingNode
import javafx.scene.control.Tab
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.GridPane

import javax.swing.JLabel
import javax.swing.SwingUtilities

@ArtifactProviderFor(GriffonView)
class LoginView implements ShowHideTabTrait{
    FactoryBuilderSupport builder
    LoginModel model

    GriffdnifxView parentView

    Tab tab

    void initUI() {
        FontAwesomeIcon icon = new FontAwesomeIcon(FontAwesome.FA_SIGN_IN)

        builder.with{
            tab(id:'mainTab',
                    text:application.messageSource.getMessage('login'),
                    closable:false, graphic:icon){

                stackPane(id:'stack'){

                    group(id:'login'){
                        borderPane(styleClass: ['panel']){
                            top{
                                pane(styleClass: ['panel-heading']){
                                    label styleClass: ['h1'],
                                            text: application.messageSource.getMessage('insertDNI')
                                }
                            }
                            center{
                                pane(styleClass: ['panel-body']) {
                                    button styleClass: ['btn', 'btn-lg', 'btn-default'],
                                            text: application.messageSource.getMessage('btnInsert'), identifyUserAction
                                }
                            }
                        }
                    }

                    group(id:'espere'){
                        borderPane(styleClass: ['panel']){
                            top{
                                pane(styleClass: ['panel-heading']){
                                }
                            }
                            center{
                                pane(styleClass: ['panel-body']) {
                                    label text: "Identificando usuario"
                                }
                            }
                        }
                    }

                    group(id:'retire'){
                        borderPane(styleClass: ['panel']){
                            top{
                                pane(styleClass: ['panel-heading']){
                                    label styleClass: ['h1'], text: "Extraiga su DNIe del lector"
                                }
                            }
                            center{
                                pane(styleClass: ['panel-body']) {
                                    button styleClass: ['btn', 'btn-lg', 'btn-default'],
                                            text: 'Retirado', dniRetiredAction
                                }
                            }
                        }
                    }

                }
            }
        }
        showFirst()
        parentView.tabPane.tabs.add(tab=builder.mainTab);
    }


    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void showFirst(){
        builder.stack.children.clear()
        builder.stack.children.add( builder.login )
        show()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void showSecond(){
        builder.stack.children.clear()
        builder.stack.children.add( builder.espere )
        show()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void showRetire(){
        builder.stack.children.clear()
        builder.stack.children.add( builder.retire )
        show()
    }
}