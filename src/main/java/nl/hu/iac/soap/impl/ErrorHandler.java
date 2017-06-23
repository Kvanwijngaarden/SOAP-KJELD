package nl.hu.iac.soap.impl;

import nl.hu.iac.soap.wsinterface.Fault;
import nl.hu.iac.soap.wsinterface.ObjectFactory;
import nl.hu.iac.soap.wsinterface.WindmolenFault;

/**
 * Created by Kvanwijngaarden on 24-6-2017.
 */
public class ErrorHandler {

    ObjectFactory factory = new ObjectFactory();

    public Fault GetErrorMessage(String message){

        WindmolenFault x = factory.createWindmolenFault();
        x.setErrorCode((short) 1);
        x.setMessage(message);
        Fault fault = new Fault(message, x);

        return fault;

    }

}
