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
        view.show()
        view.showFirst()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void identifyUser() {
        view.showSecond()

        runOutsideUI {
            try {
                model.dnie = dnieService.identifyUser()
                parentController.userLogged(model.dnie)
                view.showLogout()
            } catch (e) {
                model.error = "$e"
                view.showFirst()
            }
        }
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void logout() {
        view.showFirst()
        parentController.logout()
    }

}