package com.puravida

import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.chart.PieChart

@ArtifactProviderFor(GriffonModel)
class ChartModel {
    @FXObservable Integer clickCount = 0

    @FXObservable
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList()
}