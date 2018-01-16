/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes;

import dao.PurchaseManagerBean;
import entity.Purchase;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author j-m_d
 */
@Path("/purchases")
public class PurchasesRoutes {

    @Context
    private UriInfo context;
    
    @EJB
    PurchaseManagerBean purchaseManager;

    /**
     * Creates a new instance of PurchasesRoutes
     */
    public PurchasesRoutes() {
    }

    /**
     * Retrieves representation of an instance of routes.PurchasesRoutes
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<Purchase> listPurchase = purchaseManager.getAll();
        StringBuilder response = new StringBuilder("{\"purchases\": [");
        for (int i = 0; i < listPurchase.size(); i++) {
            response.append(listPurchase.get(i).toJSON());
            if (i != listPurchase.size() - 1)
                response.append(",");
        }
        response.append("]}");
        return response.toString();
    }

    /**
     * PUT method for updating or creating an instance of PurchasesRoutes
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
