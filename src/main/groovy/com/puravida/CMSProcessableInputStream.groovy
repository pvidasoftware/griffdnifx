package com.puravida

import org.bouncycastle.asn1.ASN1ObjectIdentifier
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers
import org.bouncycastle.cms.CMSException
import org.bouncycastle.cms.CMSTypedData

/**
 * Created by jorge on 13/06/16.
 */
class CMSProcessableInputStream implements CMSTypedData
{

    private InputStream inputStream;
    private final ASN1ObjectIdentifier contentType;

    CMSProcessableInputStream(InputStream is)
    {
        this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), is);
    }

    CMSProcessableInputStream(ASN1ObjectIdentifier type, InputStream is)
    {
        contentType = type;
        inputStream = is;
    }

    @Override
    public Object getContent()
    {
        return inputStream;
    }

    @Override
    public void write(OutputStream out) throws IOException, CMSException
    {
        // read the content only one time
        byte[] buffer = new byte[8 * 1024];
        int read;
        while ((read = inputStream.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
        inputStream.close();
    }

    @Override
    public ASN1ObjectIdentifier getContentType()
    {
        return contentType;
    }

}