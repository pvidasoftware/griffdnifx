package com.puravida

import griffon.core.GriffonApplication
import griffon.core.ShutdownHandler
import griffon.core.artifact.GriffonController
import griffon.core.mvc.MVCGroup
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading

import javax.annotation.Nonnull

@ArtifactProviderFor(GriffonController)
class GriffdnifxController implements ShutdownHandler{

    GriffdnifxModel model
    GriffdnifxView view

    boolean canShutdown(@Nonnull GriffonApplication application){
        return model.logged == false
    }

    void onShutdown(@Nonnull GriffonApplication application){

    }

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        application.addShutdownHandler(this)
        createMVCGroup("login");
        createMVCGroup("rest");
        createMVCGroup("agreement");
        createMVCGroup("chart");

        logout()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void exitAction(){
        application.shutdown()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void login() {
        MVCGroup group = application.mvcGroupManager.findGroup('login')
        group.controller.doAction()
    }

    void logout() {
        runInsideUIAsync {
            model.userLogout()  // canExit = true

            ['rest', 'agreement'].each { id ->
                MVCGroup group = application.mvcGroupManager.findGroup(id)
                group.controller.logout()
            }

            login()
        }
    }

    void userLogged( Dnie dnie){
        runInsideUIAsync {
            model.userLogged(dnie)  // canExit = false

            MVCGroup group = application.mvcGroupManager.findGroup('rest')
            group.controller.doAction(model.dnie.serialnumber)
        }
    }

    void restConsumed(){
        runInsideUIAsync {
            MVCGroup group = application.mvcGroupManager.findGroup('agreement')
            group.controller.doAction(model.dnie)
        }
    }

    void agreementAcepted(){
        runInsideUIAsync {
            MVCGroup group = application.mvcGroupManager.findGroup('login')
            group.controller.doFinish()
        }
    }
}