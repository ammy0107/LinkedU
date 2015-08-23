
package isu;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the isu package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCarriersResponse_QNAME = new QName("http://ISU/", "getCarriersResponse");
    private final static QName _SendSMSISU_QNAME = new QName("http://ISU/", "sendSMSISU");
    private final static QName _GetCarriers_QNAME = new QName("http://ISU/", "getCarriers");
    private final static QName _SendSMSISUResponse_QNAME = new QName("http://ISU/", "sendSMSISUResponse");
    private final static QName _SendSMS_QNAME = new QName("http://ISU/", "sendSMS");
    private final static QName _SendSMSResponse_QNAME = new QName("http://ISU/", "sendSMSResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: isu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendSMSISUResponse }
     * 
     */
    public SendSMSISUResponse createSendSMSISUResponse() {
        return new SendSMSISUResponse();
    }

    /**
     * Create an instance of {@link SendSMS }
     * 
     */
    public SendSMS createSendSMS() {
        return new SendSMS();
    }

    /**
     * Create an instance of {@link SendSMSResponse }
     * 
     */
    public SendSMSResponse createSendSMSResponse() {
        return new SendSMSResponse();
    }

    /**
     * Create an instance of {@link GetCarriers }
     * 
     */
    public GetCarriers createGetCarriers() {
        return new GetCarriers();
    }

    /**
     * Create an instance of {@link GetCarriersResponse }
     * 
     */
    public GetCarriersResponse createGetCarriersResponse() {
        return new GetCarriersResponse();
    }

    /**
     * Create an instance of {@link SendSMSISU }
     * 
     */
    public SendSMSISU createSendSMSISU() {
        return new SendSMSISU();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarriersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISU/", name = "getCarriersResponse")
    public JAXBElement<GetCarriersResponse> createGetCarriersResponse(GetCarriersResponse value) {
        return new JAXBElement<GetCarriersResponse>(_GetCarriersResponse_QNAME, GetCarriersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSISU }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISU/", name = "sendSMSISU")
    public JAXBElement<SendSMSISU> createSendSMSISU(SendSMSISU value) {
        return new JAXBElement<SendSMSISU>(_SendSMSISU_QNAME, SendSMSISU.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarriers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISU/", name = "getCarriers")
    public JAXBElement<GetCarriers> createGetCarriers(GetCarriers value) {
        return new JAXBElement<GetCarriers>(_GetCarriers_QNAME, GetCarriers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSISUResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISU/", name = "sendSMSISUResponse")
    public JAXBElement<SendSMSISUResponse> createSendSMSISUResponse(SendSMSISUResponse value) {
        return new JAXBElement<SendSMSISUResponse>(_SendSMSISUResponse_QNAME, SendSMSISUResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISU/", name = "sendSMS")
    public JAXBElement<SendSMS> createSendSMS(SendSMS value) {
        return new JAXBElement<SendSMS>(_SendSMS_QNAME, SendSMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSMSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ISU/", name = "sendSMSResponse")
    public JAXBElement<SendSMSResponse> createSendSMSResponse(SendSMSResponse value) {
        return new JAXBElement<SendSMSResponse>(_SendSMSResponse_QNAME, SendSMSResponse.class, null, value);
    }

}
