package com.puravida

import griffon.core.artifact.GriffonView
import griffon.javafx.support.fontawesome.FontAwesomeIcon
import griffon.metadata.ArtifactProviderFor
import griffon.plugins.fontawesome.FontAwesome
import griffon.plugins.glazedlists.javafx.gui.DefaultFXWritableTableFormat
import javafx.collections.FXCollections
import javafx.scene.chart.PieChart
import javafx.scene.control.Tab
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.option
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.options
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.options
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.options
import static griffon.plugins.glazedlists.javafx.gui.FXTableFormat.options

@ArtifactProviderFor(GriffonView)
class ChartView extends AbstractJavaFXGriffonView  implements ShowHideTabTrait{

    FactoryBuilderSupport builder
    ChartModel model
    ChartController controller

    GriffdnifxView parentView
    Tab tab

    void initUI() {
        builder.with{
            content = fxml(resource('/com/puravida/chart.fxml')) {
                pieChart.data = model.pieChartData
            }
        }
        connectActions(builder.content, controller)

        tab = new Tab('Chart')
        tab.graphic = new FontAwesomeIcon(FontAwesome.FA_AREA_CHART)
        tab.content = builder.content
        tab.closable = false

        parentView.tabPane.tabs.add(tab);
    }
}