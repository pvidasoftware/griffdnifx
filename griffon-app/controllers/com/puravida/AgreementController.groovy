package com.puravida

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading

import javax.inject.Inject
import java.nio.file.Paths

@ArtifactProviderFor(GriffonController)
class AgreementController {

    AgreementModel model
    AgreementView view
    GriffdnifxController parentController

    @Inject
    SignPdfService signPdfService

    void logout(){
        view.hide()
    }

    void doAction( Dnie dnie) {
        model.dnie = dnie
        view.show()
    }

    @Threading(Threading.Policy.OUTSIDE_UITHREAD)
    void userAgree(){

        signPdfService.signPdf model.dnie,
                getClass().getResourceAsStream("web/${model.agreementFile}"),
                new File("agreements/Agreement_${model.dnie.serialnumber}.pdf")


        parentController.agreementAcepted()
    }
}