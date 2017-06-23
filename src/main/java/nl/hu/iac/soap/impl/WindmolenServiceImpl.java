package nl.hu.iac.soap.impl;

import nl.hu.iac.soap.wsinterface.*;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kvanwijngaarden on 23-6-2017.
 */

@WebService(endpointInterface = "nl.hu.iac.soap.wsinterface.WindmolenServiceInterface")
public class WindmolenServiceImpl implements WindmolenServiceInterface{

    /*
    * De error message word in een andere class gegenereed,
    * aangezien je in deze class geen andere class mag toevoegen
    */
    ErrorHandler error = new ErrorHandler();
    List<Map> alleWindmolen = new ArrayList();
    Map windmolen = new HashMap();


    @Override
    public AddwindmolenResponse addwindmolen(AddwindmolenRequest addrequest) throws Fault
    {
        ObjectFactory factory = new ObjectFactory();
        AddwindmolenResponse response = factory.createAddwindmolenResponse();

        try
        {
            windmolen.put("Fabrikant", addrequest.getFabrikant());
            windmolen.put("ProductID", addrequest.getProductID());
            windmolen.put("ProductieDatum", addrequest.getProductieDatum());

            alleWindmolen.add(windmolen);

            response.setResult(alleWindmolen.toString());
        }
        catch (RuntimeException e)
        {
            throw error.GetErrorMessage("De windmolen kan niet geregistreerd worden. Probeer het nogmaals.");
        }
        return response;
    }

    @Override
    public RemovewindmolenResponse removewindmolen(RemovewindmolenRequest removerequest) throws Fault
    {
        ObjectFactory factory = new ObjectFactory();
        RemovewindmolenResponse response = factory.createRemovewindmolenResponse();

        try
        {
            alleWindmolen.remove(removerequest.getIndex());
            response.setResult(alleWindmolen.toString());
        }
        catch (RuntimeException e)
        {
            throw error.GetErrorMessage("De windmolen kan niet verwijderd worden. Probeer het nogmaals.");
        }
        return response;
    }

    @Override
    public ReadwindmolenResponse readwindmolen(ReadwindmolenRequest readrequest) throws Fault
    {
        ObjectFactory factory = new ObjectFactory();
        ReadwindmolenResponse response = factory.createReadwindmolenResponse();
        String result = null;

        try
        {
            if (readrequest.getCommand().equals("Read"))
            {
                result = alleWindmolen.toString();
            }
            else
            {
                result = "Er is iets mis gegaan. Type 'Read' in om de windmolen uit te lezen.";
            }

            response.setResult(result);
        }
        catch (RuntimeException e)
        {
            throw error.GetErrorMessage("De windmolen kan niet gelezen worden. Probeer het nogmaals.");
        }
        return response;
    }

    @Override
    public UpdatewindmolenResponse updatewindmolen(UpdatewindmolenRequest updaterequest) throws Fault
    {
        ObjectFactory factory = new ObjectFactory();
        UpdatewindmolenResponse response = factory.createUpdatewindmolenResponse();

        try
        {
            windmolen.put("Fabrikant", updaterequest.getFabrikant());
            windmolen.put("ProductID", updaterequest.getProductID());
            windmolen.put("ProductieDatum", updaterequest.getProductieDatum());

            alleWindmolen.set(updaterequest.getIndex().intValue(), windmolen);

            response.setResult(windmolen.toString());
        }
        catch (RuntimeException e)
        {
            throw error.GetErrorMessage("De windmolen kan niet geupdate worden. Probeer het nogmaals.");
        }
        return response;
    }

}
