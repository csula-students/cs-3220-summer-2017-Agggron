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

import jaxrs.models.User;
import jaxrs.models.FoodItem;

@Path("")
@Singleton // used to keep resource between requests otherwise request cope
public class FoodResource {
    private List<User> users = new ArrayList<>(Arrays.asList(new User("Eric", "Liao")));
    private FoodItemDAO dao = new FoodItemDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello() {
        return "test";
        /*"GET    /services/fooditems     return a list of food items in the database
        GET     /services/fooditem/:id  return a single food items given by id in the database
        POST    /services/fooditems     Create a new food item and add it to database
        PUT     /services/fooditem/:id  Update a certain food item given its id
        DELETE  /services/fooditem/:id  Delete a food item given id";*/
    }

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUser() {
        return users;
    }

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
    public boolean addFoodItem(FoodItem foodItem) {
        dao.add(foodItem);
        System.out.println(dao.list());
        return true;
    }

    @PUT
    @Path("fooditem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateFoodItem(FoodItem foodItem, @PathParam("id") int id) {
        /*FoodItem foodItemToUpdate = dao.get(id).get();
        dao.update(foodItem);
        System.out.println(dao.list());*/
        return true;
    }

    @DELETE
    @Path("fooditem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteFoodItem(@PathParam("id") int id) {
        dao.delete(id);
        System.out.println(dao.list());
        return true;
    }


    @POST
    @Path("users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addUser(User newUser) {
        System.out.println(newUser);
        users.add(newUser);
        System.out.println(users);
        return true;
    }
}
