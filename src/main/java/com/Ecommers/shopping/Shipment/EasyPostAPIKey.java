package com.Ecommers.shopping.Shipment;

import com.easypost.EasyPost;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.service.EasyPostClient;

public class EasyPostAPIKey {
    EasyPostClient easyPostClient;
    EasyPostAPIKey(){
        try {
            easyPostClient = new EasyPostClient("EZTK7d057bdd3d894f109adee630012aa82eq8eqsxxQEQTe1NR6Uzsayg");
        } catch (MissingParameterError e) {
            throw new RuntimeException(e);
        }
    }
}
