package com.puravida

import griffon.core.artifact.GriffonView
import griffon.javafx.support.fontawesome.FontAwesomeIcon
import griffon.metadata.ArtifactProviderFor
import griffon.plugins.fontawesome.FontAwesome
import javafx.scene.control.Tab

import griffon.plugins.glazedlists.javafx.gui.DefaultFXWritableTableFormat
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView;

import static griffon.plugins.glazedlists.javafx.GlazedListsJavaFX.eventTableViewModel;
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option;
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.options;

@ArtifactProviderFor(GriffonView)
class RestView extends AbstractJavaFXGriffonView  implements ShowHideTabTrait{

    FactoryBuilderSupport builder

    RestModel model
    RestController controller

    GriffdnifxView parentView

    void initUI() {
        builder.with{
            content = fxml(resource('/com/puravida/restview.fxml')) {

                bean(labelNif, text: bind(model.nifProperty))

                def tableFormat = fxTableFormat(new DefaultFXWritableTableFormat<>(
                        options(option("name", "name"), option("editable", false), option("title", "Name")),
                        options(option("name", "surname"), option("editable", false), option("title", "Surname")),
                        options(option("name", "gender"), option("editable", false), option("title", "Gender")),
                        options(option("name", "region"), option("editable", false), option("title", "Region")),
                ))
                def tableModel = eventTableViewModel( model.list, tableFormat)

                tableModel.attachTo(tableView)
            }
        }
        connectActions(builder.content, controller)

        Tab tab = new Tab('Verification')
        tab.graphic = new FontAwesomeIcon(FontAwesome.FA_ROCKET)
        tab.content = builder.content
        tab.closable = false
        builder.mainTab = tab
        parentView.tabPane.tabs.add(builder.mainTab);
    }

}