package com.puravida

import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.EventList
import ca.odell.glazedlists.GlazedLists
import ca.odell.glazedlists.SortedList
import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor
import javafx.beans.property.SimpleStringProperty

@ArtifactProviderFor(GriffonModel)
class RestModel {

    @FXObservable String nif = ""

    EventList<String> list = new SortedList( new BasicEventList(), { a, b ->
        return a.region <=> b.region
    } as Comparator)

    static class RestPerson{
        @FXObservable String name = ""
        @FXObservable String surname = ""
        @FXObservable String gender = ""
        @FXObservable String region = ""
    }

    static RestPerson fromJson( json ){
        new RestPerson(name:json.name,
                surname:json.surname,
                gender:json.gender,
                region:json.region)
    }
}