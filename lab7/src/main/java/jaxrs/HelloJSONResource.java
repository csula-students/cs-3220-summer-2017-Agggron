package jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jaxrs.models.User;
import jaxrs.models.FoodItem;

@Path("hello-json-resource")
@Singleton // used to keep resource between requests otherwise request cope
public class HelloJSONResource {
    private List<User> users = new ArrayList<>(Arrays.asList(new User("Eric", "Liao")));
    private FoodItemDAO dao = new FoodItemDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello() {
        return "Hello, JAX-RS again";
    }

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUser() {
        return users;
    }

    @GET
    @Path("menu")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodItem> getMenu() {
        return dao.list();
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
