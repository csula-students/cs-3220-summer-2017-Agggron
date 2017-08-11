package jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jaxrs.models.FoodItem;

@Path("")
@Singleton // used to keep resource between requests otherwise request cope
public class FoodResource {
    private FoodItemDAO dao = new FoodItemDAO();

    /* Try /services/fooditems with a GET OR POST request, or /services/fooditem/:id with a GET, PUT, or DELETE request! */

    @GET
    @Path("fooditems")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodItem> getMenu() {
        return dao.list();
    }

    @GET
    @Path("fooditem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FoodItem getFoodItemById(@PathParam("id") int id) {
        return dao.get(id).get();
    }

    @POST
    @Path("fooditems")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /* EXAMPLE TO PUT IN:
    {   "id": 6,
        "name": "Healing Salve",
        "description": "Restores HP!",
        "imgURL": "images/potion6.png",
        "price": 1.5
    } */
    public boolean addFoodItem(FoodItem foodItem) {
        dao.add(foodItem);
        return true;
    }

    @PUT
    @Path("fooditem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateFoodItem(FoodItem foodItem, @PathParam("id") int id) {
        if (id == foodItem.getId()) {
            dao.update(foodItem);
            return true;
        } else {
            return false;
        }
    }

    @DELETE
    @Path("fooditem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteFoodItem(@PathParam("id") int id) {
        dao.delete(id);
        return true;
    }
}
