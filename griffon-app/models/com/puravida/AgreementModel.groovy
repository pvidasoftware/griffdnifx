package com.puravida

import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonModel)
class AgreementModel {

    Dnie dnie

    String agreementFile = "MadridGUG.pdf"

}