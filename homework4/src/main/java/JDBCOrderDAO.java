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
            ResultSet rs = stmt.executeQuery("select * from orders INNER JOIN order_foods INNER JOIN menu WHERE orders.order_id = order_foods.order_foods_id AND menu.id = order_foods.menu_id;");
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
        return null;
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

    private static java.sql.Timestamp getSQLDate(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public void update(JDBCOrder order) {
        return;
    }

    public void delete(int id) {
        return;
    }
}