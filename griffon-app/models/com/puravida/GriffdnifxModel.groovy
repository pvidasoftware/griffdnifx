package com.puravida

import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonModel)
class GriffdnifxModel {

    String version = "GriffDNIe v1.0"

    @FXObservable
    String name = "Welcome"

    @FXObservable
    Boolean logged = false

    @FXObservable
    Boolean cantExit = false

    Dnie dnie

    public void userLogged( Dnie d){
        dnie = d
        name = "$dnie.givenname, $dnie.surname ($dnie.serialnumber)"
        logged = true
        cantExit = true
    }

    public void userLogout(){
        dnie = null
        name = "Welcome"
        logged = false
        cantExit = false
    }


}