package com.puravida

import griffon.core.artifact.GriffonController
import griffon.metadata.ArtifactProviderFor
import griffon.plugins.wslite.WsliteHandler
import griffon.transform.Threading
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.beans.property.adapter.JavaBeanStringProperty
import wslite.rest.ContentType
import wslite.rest.RESTClient
import wslite.rest.Response

import javax.inject.Inject

@ArtifactProviderFor(GriffonController)
class RestController {

    RestModel model
    RestView view

    GriffdnifxController parentController

    @Inject
    private WsliteHandler wsliteHandler

    private int rowIndex = 1
    private static final int MAX_ROWS = 10

    private String url =  'http://uinames.com/api/'

    void logout(){
        view.hide()
    }

    void doAction( String nif) {
        model.nif = nif
        model.list.clear()
        rowIndex = 1
        next()
        view.show()
    }

    @Threading(Threading.Policy.OUTSIDE_UITHREAD)
    void next(){
        try {
            wsliteHandler.withRest([url: url], { Map<String, Object> params, RESTClient client ->
                Response response = client.get(
                        path: "/",
                        accept: ContentType.JSON)
                runInsideUIAsync {
                    model.list.add(RestModel.fromJson(response.json))
                }
            })
            if( rowIndex++ > MAX_ROWS ){
                parentController.restConsumed()
            }

        }catch(e){
            parentController.restConsumed()
        }
    }


}