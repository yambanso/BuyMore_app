package com.example.buymore_app.backend;

import com.example.buymore_app.Items;

import java.util.List;

public class order {
    public String orderOwner;
    public String OrdeOwnerCOntact;
    public List<Items> orderItems;

    public order(String orderOwner, String ordeOwnerCOntact, List<Items> orderItems) {
        this.orderOwner = orderOwner;
        OrdeOwnerCOntact = ordeOwnerCOntact;
        this.orderItems = orderItems;
    }
}
