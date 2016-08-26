package com.puravida

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading

import javax.inject.Inject

@ArtifactProviderFor(GriffonController)
class LoginController {

    LoginModel model
    LoginView view

    GriffdnifxController parentController

    @Inject
    DnieService dnieService

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void doAction() {
        view.showFirst()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void identifyUser() {
        view.showSecond()

        runOutsideUI {
            try {
                model.dnie = dnieService.identifyUser()
                parentController.userLogged(model.dnie)
            } catch (e) {
                model.error = "$e"
                view.showFirst()
            }
        }
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void doFinish() {
        view.showRetire()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void dniRetired() {
        parentController.logout()
    }

}