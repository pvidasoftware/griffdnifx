package com.puravida

import griffon.core.artifact.GriffonService
import griffon.metadata.ArtifactProviderFor
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface
import org.bouncycastle.asn1.ASN1Primitive
import org.bouncycastle.cert.X509CertificateHolder
import org.bouncycastle.cert.jcajce.JcaCertStore
import org.bouncycastle.cms.CMSSignedData
import org.bouncycastle.cms.CMSSignedDataGenerator
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder
import org.bouncycastle.operator.ContentSigner
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder
import org.bouncycastle.util.Store

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.security.cert.Certificate

@javax.inject.Singleton
@ArtifactProviderFor(GriffonService)
class SignPdfService {

    void signPdf( final Dnie dnie, final InputStream fSource, final File fDestination){

        fDestination.mkdirs()

        Path destination = Paths.get(fDestination.absolutePath)
        Files.copy(fSource, destination, StandardCopyOption.REPLACE_EXISTING)

        final PDDocument doc = PDDocument.load(fDestination)
        final PDSignature signature = new PDSignature()

        signature.filter = PDSignature.FILTER_ADOBE_PPKLITE
        signature.subFilter = PDSignature.SUBFILTER_ADBE_PKCS7_DETACHED
        signature.signDate = Calendar.instance

        doc.addSignature(signature, new SignatureInterface() {
            @Override
            byte[] sign(InputStream content) throws IOException {
                List<Certificate> certList = [dnie.certificate]
                Store certs = new JcaCertStore(certList)
                CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
                org.bouncycastle.asn1.x509.Certificate cert =
                        org.bouncycastle.asn1.x509.Certificate.getInstance(
                                ASN1Primitive.fromByteArray(dnie.certificate.encoded));

                ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA256WithRSA").build(dnie.privateKey);
                gen.addSignerInfoGenerator(
                        new JcaSignerInfoGeneratorBuilder(
                                new JcaDigestCalculatorProviderBuilder().build()).
                                build(sha1Signer, new X509CertificateHolder(cert)));
                gen.addCertificates(certs);
                CMSProcessableInputStream msg = new CMSProcessableInputStream(content);
                CMSSignedData signedData = gen.generate(msg, false);

                return signedData.encoded
            }
        });
        FileOutputStream fos = new FileOutputStream(fDestination)
        doc.saveIncremental(fos);
    }


}
