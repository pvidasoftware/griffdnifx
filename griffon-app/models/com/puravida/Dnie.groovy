package com.puravida

import java.security.PrivateKey
import java.security.cert.Certificate

/**
 * Created by jorge on 21/06/16.
 */
class Dnie {

    String givenname
    String surname
    String serialnumber

    Certificate certificate
    PrivateKey privateKey
    Certificate[] chain
}
