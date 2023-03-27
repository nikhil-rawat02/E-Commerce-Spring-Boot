package com.Ecommers.shopping.Shipment;

import com.Ecommers.shopping.Exception.EasyPostAPIKeyNotFoundException;
import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;
import com.easypost.service.EasyPostClient;

import java.util.HashMap;
import java.util.Map;

public class Delivery {

    public static String createDelivery(String toAddress,String fromAddress, String dimension) throws EasyPostAPIKeyNotFoundException, EasyPostException {
        EasyPostAPIKey apiKey = new EasyPostAPIKey();

        Map<String ,Object> toAddressMap = new HashMap<String, Object>();
        String [] customerAddress = toAddress.split("\\$");
        toAddressMap.put("CompanyName", customerAddress[0]);
        toAddressMap.put("street1", customerAddress[1]);
        toAddressMap.put("street2", customerAddress[2]);
        toAddressMap.put("city", customerAddress[3]);
        toAddressMap.put("state", customerAddress[4]);
        toAddressMap.put("country", customerAddress[5]);
        toAddressMap.put("pinCode", customerAddress[6]);

        Map<String ,Object> fromAddressMap = new HashMap<String, Object>();
        String [] sellerAddress = fromAddress.split("\\$");
        fromAddressMap.put("CompanyName", customerAddress[0]);
        fromAddressMap.put("street1", customerAddress[1]);
        fromAddressMap.put("street2", customerAddress[2]);
        fromAddressMap.put("city", customerAddress[3]);
        fromAddressMap.put("state", customerAddress[4]);
        fromAddressMap.put("country", customerAddress[5]);
        fromAddressMap.put("pinCode", customerAddress[6]);

        Map<String, Object> parcelMap = new HashMap<String, Object>();
        String [] parcelDimension = dimension.split("\\$");

        parcelMap.put("weight", parcelDimension[0]);
        parcelMap.put("height", parcelDimension[1]);
        parcelMap.put("width", parcelDimension[2]);
        parcelMap.put("length", parcelDimension[3]);

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("from_address", fromAddressMap);
        shipmentMap.put("to_address", toAddressMap);
        shipmentMap.put("parcel", parcelMap);

        Shipment shipment = apiKey.easyPostClient.shipment.create(shipmentMap);
        Shipment boughtShipment = apiKey.easyPostClient.shipment.buy(shipment.getId(), shipment.lowestRate());
        System.out.println(boughtShipment.prettyPrint());
        return shipment.getId();
    }
}
