import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;

public class JDBCOrderDAO implements DAO<JDBCOrder> {

    public List<JDBCOrder> list() {
        List<JDBCOrder> list = new ArrayList<>();
        List<Integer> order_ids_added = new ArrayList<>();
        Database db = new Database();
        try (Connection c = db.connection()) {

            // Get information from orders table
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders INNER JOIN order_foods INNER JOIN menu WHERE orders.order_id = order_foods.order_foods_id AND menu.id = order_foods.menu_id;");
            while (rs.next()) {
                int order_id = rs.getInt("order_id");

                JDBCFoodItem food_item_to_add = new JDBCFoodItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("imgURL"), 
                    (double) rs.getFloat("price")
                );
                food_item_to_add.quantity = rs.getInt("food_quantity");

                // If order has not been seen before, create a new Order
                if (!order_ids_added.contains(order_id)) {
                    
                    order_ids_added.add(order_id);

                    List<JDBCFoodItem> list_with_food_item_to_add = new ArrayList<JDBCFoodItem>();
                    list_with_food_item_to_add.add(food_item_to_add);

                    list.add(new JDBCOrder(
                        order_id,
                        list_with_food_item_to_add,
                        rs.getString("customer_name"),
                        rs.getString("status"),
                        rs.getTimestamp("order_time")
                    ));
                } 

                // If order has been seen before, find that Order and update it (add the new food item to it)
                else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getId() == order_id) {
                            list.get(i).items.add(food_item_to_add);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

    public Optional<JDBCOrder> get(int id) {
        Optional<JDBCOrder> toReturn = Optional.empty();
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM orders INNER JOIN order_foods INNER JOIN menu WHERE orders.order_id = ? AND order_foods.order_foods_id = ? AND order_foods.menu_id = menu.id");
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            ResultSet rs = pstmt.executeQuery();

            boolean createdNewOrder = false;
            JDBCOrder newOrder = null;
            if (rs.next()) {
                
                JDBCFoodItem foodItemToAdd = new JDBCFoodItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("imgURL"), 
                    (double) rs.getFloat("price")
                );
                foodItemToAdd.quantity = rs.getInt("food_quantity");

                if (!createdNewOrder) {
                    List<JDBCFoodItem> listOfFoodItemsInOrder = new ArrayList<JDBCFoodItem>();
                    listOfFoodItemsInOrder.add(foodItemToAdd);
                    newOrder = new JDBCOrder(rs.getInt("order_id"),
                                            listOfFoodItemsInOrder,
                                            rs.getString("customer_name"),
                                            rs.getString("status"),
                                            rs.getTimestamp("order_time")
                                            );
                    createdNewOrder = true;
                } else {
                    newOrder.items.add(foodItemToAdd);
                }

                toReturn = Optional.of(newOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public void add(JDBCOrder order) {

        Database db = new Database();
        try (Connection c = db.connection()) {

            // insert into ORDERS table
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO orders (order_id, customer_name, status, order_time) VALUES (?, ?, ?, ?)");
            pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getCustomerName());
            pstmt.setString(3, order.getStatus());
            pstmt.setTimestamp(4, getSQLDate(order.getOrderTime()));
            pstmt.executeUpdate();

            // for each food item in the order, insert one line into ORDER_FOODS table
            for (int i = 0; i < order.getItems().size(); i++) {
                pstmt = c.prepareStatement("INSERT INTO order_foods (order_foods_id, menu_id, food_quantity) VALUES (?, ?, ?)");
                pstmt.setInt(1, order.getId());
                pstmt.setInt(2, order.getItems().get(i).getId());
                pstmt.setInt(3, order.getItems().get(i).getQuantity());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNextAvailableOrderId() {
        Database db = new Database();
        int highest_id_so_far = 1;
        try (Connection c = db.connection()) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(order_id) FROM orders;");
            while (rs.next()) {
                highest_id_so_far = rs.getInt("max(order_id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(highest_id_so_far);
        return highest_id_so_far + 1;
    }

    private static java.sql.Timestamp getSQLDate(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public void update(JDBCOrder order) {
        return;
    }

    public void delete(int id) {
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("DELETE FROM orders WHERE orders.order_id = ? ");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            pstmt = c.prepareStatement("DELETE FROM order_foods WHERE orders.order_foods_id = ? ");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}