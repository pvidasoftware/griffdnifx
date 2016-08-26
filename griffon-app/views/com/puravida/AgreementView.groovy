package com.puravida

import griffon.core.artifact.GriffonView
import griffon.javafx.support.fontawesome.FontAwesomeIcon
import griffon.metadata.ArtifactProviderFor
import griffon.plugins.fontawesome.FontAwesome
import javafx.scene.control.Tab
import javafx.scene.layout.AnchorPane

@ArtifactProviderFor(GriffonView)
class AgreementView implements ShowHideTabTrait{
    FactoryBuilderSupport builder
    AgreementModel model

    GriffdnifxView parentView

    void initUI() {
        FontAwesomeIcon icon = new FontAwesomeIcon(FontAwesome.FA_FILE_PDF_O)
        builder.with{
            tab(id:'mainTab', application.messageSource.getMessage('agreement'),
                    graphic:icon, closable:false){
                borderPane{
                    top(align: 'center', margin: [0, 20, 20, 20]){
                        button text: 'Acepto', userAgreeAction
                    }
                    center(){
                        browser = webView(prefWidth: 200)
                    }
                }
            }
        }
        String url = getClass().getResource("web/viewer.html").toExternalForm() + "?file=MadridGUG.pdf"
        builder.browser.engine.load(url)
        parentView.tabPane.tabs.add(builder.mainTab);
    }

}