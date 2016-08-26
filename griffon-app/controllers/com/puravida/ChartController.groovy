package com.puravida

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javafx.scene.chart.PieChart

@ArtifactProviderFor(GriffonController)
class ChartController {

    ChartModel model

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void nextAction() {

        model.pieChartData.clear()

        switch( model.clickCount % 2){
            case 0 :
                model.pieChartData.addAll(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30)
                )
            break
            case 1:
                model.pieChartData.addAll(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Apples", 30)
                )
            break
        }

    }
}