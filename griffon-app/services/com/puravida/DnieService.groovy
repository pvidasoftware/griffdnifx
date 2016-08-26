package com.puravida

import es.gob.jmulticard.jse.provider.DnieProvider
import es.gob.jmulticard.jse.smartcardio.SmartcardIoConnection
import es.gob.jmulticard.ui.passwordcallback.gui.DnieCallbackHandler
import griffon.core.artifact.GriffonService
import griffon.metadata.ArtifactProviderFor
import org.bouncycastle.asn1.ASN1Primitive
import org.bouncycastle.asn1.x500.style.BCStyle

import javax.security.auth.callback.CallbackHandler
import java.security.KeyStore
import java.security.PrivateKey
import java.security.Provider
import java.security.Security
import java.security.cert.X509Certificate

@javax.inject.Singleton
@ArtifactProviderFor(GriffonService)
class DnieService {

    Provider provider

    void loadProvider() {
        provider = new DnieProvider(new SmartcardIoConnection())
        Security.addProvider(provider)
    }


    Dnie identifyUser(){

        if( !provider )
            loadProvider()

        Dnie ret = new Dnie()

        final KeyStore ks = KeyStore.getInstance('DNI')

        final CallbackHandler callbackHandler = new DnieCallbackHandler()

        final KeyStore.LoadStoreParameter lsp = new KeyStore.LoadStoreParameter() {
            @Override
            public KeyStore.ProtectionParameter getProtectionParameter() {
                return new KeyStore.CallbackHandlerProtection(callbackHandler)
            }
        };

        ks.load(lsp)

        ret.certificate = ks.getCertificate('CertAutenticacion')
        ret.privateKey = (PrivateKey) ks.getKey('CertAutenticacion', null);
        ret.chain = ks.getCertificateChain('CertAutenticacion');

        if (ret.certificate && ret.certificate instanceof X509Certificate) {

            final org.bouncycastle.asn1.x509.Certificate x509Certificate =
                    org.bouncycastle.asn1.x509.Certificate.getInstance(ASN1Primitive.fromByteArray(ret.certificate.encoded))

            ret.givenname = x509Certificate.getSubject().getRDNs(BCStyle.GIVENNAME).first().first.value
            ret.surname = x509Certificate.getSubject().getRDNs(BCStyle.SURNAME).first().first.value
            ret.serialnumber = x509Certificate.getSubject().getRDNs(BCStyle.SERIALNUMBER).first().first.value

        }

        ret
    }

}