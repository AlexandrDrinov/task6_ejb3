package by.epamlab.model.impl;

import by.epamlab.exception.DAOException;
import java.util.List;

import by.epamlab.ifaces.IDataDAO;
import by.epamlab.model.beans.AncillaryAirComponent;
import by.epamlab.model.beans.Customer;
import by.epamlab.model.beans.Email;
import by.epamlab.model.beans.FareFamily;
import by.epamlab.model.beans.Payment;
import by.epamlab.model.beans.Phone;
import by.epamlab.model.beans.ResComponent;
import by.epamlab.model.beans.Reservation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlToData implements IDataDAO {

    private static final String TAG_RESERVATION = "Reservation";
    private static final String TAG_RES_COMPONENT = "ResComponent";
    private static final String TAG_FARE_FAMILY = "FareFamily";
    private static final String TAG_CUSTOMER = "Customer";

    private static final String FILE_PATH = "c:\\_dev\\store\\reservation.xml";

    private NodeList createNodeList(String filePath, String tagName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(new File(filePath));
        NodeList nList = document.getElementsByTagName(tagName);
        return nList;
    }

    public Reservation getReservation() throws DAOException {
        try {
            Reservation reservation = new Reservation();
            NodeList nList = createNodeList(FILE_PATH, TAG_RESERVATION);
            Element eElement = (Element) nList.item(0);
            reservation.setCode(eElement.getAttribute("Code"));
            reservation.setDescription(eElement.getAttribute("Description"));
            return reservation;
        } catch (ParserConfigurationException ex) {
            throw new DAOException(ex);
        } catch (SAXException ex) {
            throw new DAOException(ex);
        } catch (IOException ex) {
            throw new DAOException(ex);
        }
    }

    private Date conversionDateTime(String dateTime) {
        Calendar calendar = DatatypeConverter.parseDateTime(dateTime);
        return calendar.getTime();
    }

    public List<ResComponent> getResComponents() throws DAOException {
        try {
            List<ResComponent> resComponents = new ArrayList<ResComponent>();
            NodeList nList = createNodeList(FILE_PATH, TAG_RES_COMPONENT);
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    ResComponent resComponent = new ResComponent();
                    resComponent.setComponentTypeCode(eElement.getAttribute("ComponentTypeCode"));
                    resComponent.setCreateDateTime(conversionDateTime(eElement.getAttribute("CreateDateTime")));
                    resComponent.setInternalStatus(eElement.getAttribute("InternalStatus"));
                    resComponent.setSequence(Integer.parseInt(eElement.getAttribute("Sequence")));
                    resComponents.add(resComponent);
                }
            }
            return resComponents;
        } catch (ParserConfigurationException ex) {
            throw new DAOException(ex);
        } catch (SAXException ex) {
            throw new DAOException(ex);
        } catch (IOException ex) {
            throw new DAOException(ex);
        }
    }

    public List<ResComponent> getResComponents(String resCode) throws DAOException {
        return getResComponents(resCode, null);
    }

    public List<ResComponent> getResComponents(String resCode, String resComponentTypeCode) throws DAOException {
        try {
            List<ResComponent> resComponents = new ArrayList<ResComponent>();
            NodeList nList = createNodeList(FILE_PATH, TAG_RESERVATION);
            if (nList.getLength() == 0) {
                throw new DAOException(TAG_RESERVATION + " " + "not found");
            }
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String code = eElement.getAttribute("Code");
                    if (code.equals(resCode)) {
                        selectResComponentsByTypeCode(resComponents, eElement, resComponentTypeCode);
                    }
                }
            }
            return resComponents;
        } catch (ParserConfigurationException ex) {
            throw new DAOException(ex);
        } catch (SAXException ex) {
            throw new DAOException(ex);
        } catch (IOException ex) {
            throw new DAOException(ex);
        }
    }

    private void selectResComponentsByTypeCode(List<ResComponent> resComponents, Element eElement, String resComponentTypeCode) throws DAOException {
        NodeList nListComponent = eElement.getElementsByTagName(TAG_RES_COMPONENT);
        if (nListComponent.getLength() == 0) {
            throw new DAOException(TAG_RES_COMPONENT + " " + "not found");
        }
        for (int j = 0; j < nListComponent.getLength(); j++) {
            Node nNodeComponent = nListComponent.item(j);
            if (nNodeComponent.getNodeType() == Node.ELEMENT_NODE) {
                Element eElementComponent = (Element) nNodeComponent;
                if (resComponentTypeCode == null) {
                    addComponent(resComponents, eElementComponent);
                } else {
                    String codeComponent = eElementComponent.getAttribute("ComponentTypeCode");
                    if (codeComponent.equals(resComponentTypeCode)) {
                        addComponent(resComponents, eElementComponent);
                    }
                }

            }
        }
    }

    private void addComponent(List<ResComponent> resComponents, Element eElementComponent) {
        ResComponent resComponent = new ResComponent();
        resComponent.setComponentTypeCode(eElementComponent.getAttribute("ComponentTypeCode"));
        resComponent.setCreateDateTime(conversionDateTime(eElementComponent.getAttribute("CreateDateTime")));
        resComponent.setInternalStatus(eElementComponent.getAttribute("InternalStatus"));
        resComponent.setSequence(Integer.parseInt(eElementComponent.getAttribute("Sequence")));
        resComponents.add(resComponent);
    }

    public FareFamily getFareFamily() throws DAOException {
        try {
            FareFamily fareFamily = new FareFamily();
            NodeList nList = createNodeList(FILE_PATH, TAG_FARE_FAMILY);
            Element eElement = (Element) nList.item(0);
            fareFamily.setFareFamilyCode(eElement.getAttribute("FareFamilyCode"));

            NodeList nChildList = eElement.getChildNodes();
            List<AncillaryAirComponent> ancillaryAirComponentList = new ArrayList<AncillaryAirComponent>();
            for (int i = 0; i < nChildList.getLength(); i++) {
                Node nNode = nChildList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eChildElement = (Element) nNode;
                    AncillaryAirComponent ancillaryAirComponent = new AncillaryAirComponent();
                    ancillaryAirComponent.setAncillaryAirComponentCode(eChildElement.getAttribute("AncillaryAirComponentCode"));
                    ancillaryAirComponentList.add(ancillaryAirComponent);
                }
            }
            fareFamily.setAncillaryAirComponentList(ancillaryAirComponentList);
            return fareFamily;
        } catch (ParserConfigurationException ex) {
            throw new DAOException(ex);
        } catch (SAXException ex) {
            throw new DAOException(ex);
        } catch (IOException ex) {
            throw new DAOException(ex);
        }
    }

    public Customer getCustomer() throws DAOException {
        try {
            Customer customer = new Customer();
            NodeList nList = createNodeList(FILE_PATH, TAG_CUSTOMER);
            Element eElement = (Element) nList.item(0);
            customer.setCustomerDocID(eElement.getAttribute("CustomerDocID"));
            customer.setFirstName(eElement.getAttribute("FirstName"));
            customer.setLastName(eElement.getAttribute("LastName"));
            customer.setSequence(Integer.parseInt(eElement.getAttribute("Sequence")));

            NodeList nChildList = eElement.getChildNodes();
            List<Payment> paymentList = new ArrayList<Payment>();
            for (int i = 0; i < nChildList.getLength(); i++) {
                Node nNode = nChildList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eChildElement = (Element) nNode;

                    if (eChildElement.getNodeName().equalsIgnoreCase("Email")) {
                        Email email = new Email();
                        email.setEmailAddress(eChildElement.getAttribute("EmailAddress"));
                        customer.setEmail(email);
                    }
                    if (eChildElement.getNodeName().equalsIgnoreCase("Phone")) {
                        Phone phone = new Phone();
                        phone.setPhoneNumber(eChildElement.getAttribute("PhoneNumber"));
                        customer.setPhone(phone);
                    }
                    if (eChildElement.getNodeName().equalsIgnoreCase("Payment")) {
                        Payment payment = new Payment();
                        payment.setAmountPaid(Double.parseDouble(eChildElement.getAttribute("AmountPaid")));
                        payment.setFormOfPaymentTypeCode(eChildElement.getAttribute("FormOfPaymentTypeCode"));
                        payment.setCurrencyCode(eChildElement.getAttribute("CurrencyCode"));
                        paymentList.add(payment);
                    }
                }
            }
            customer.setPaymentsList(paymentList);
            return customer;
        } catch (ParserConfigurationException ex) {
            throw new DAOException(ex);
        } catch (SAXException ex) {
            throw new DAOException(ex);
        } catch (IOException ex) {
            throw new DAOException(ex);
        }
    }
}
