import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class JDBCFoodItemDAO implements DAO<JDBCFoodItem> {
    public List<JDBCFoodItem> list() {
        List<JDBCFoodItem> list = new ArrayList<>();
        Database db = new Database();
        try (Connection c = db.connection()) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM menu");
            while (rs.next()) {
                list.add(new JDBCFoodItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("imgURL"),
                    (double) rs.getFloat("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

    public Optional<JDBCFoodItem> get(int id) {
        Optional<JDBCFoodItem> toReturn = Optional.empty();
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM menu WHERE menu.id = ? ");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JDBCFoodItem foodToEdit = new JDBCFoodItem(rs.getInt("id"),
                                                    rs.getString("name"),
                                                    rs.getString("description"),
                                                    rs.getString("imgURL"),
                                                    (double) rs.getFloat("price"));
                toReturn = Optional.of(foodToEdit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public void add(JDBCFoodItem entry) {
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO menu (id, name, description, imgURL, price) VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, entry.getId());
            pstmt.setString(2, entry.getName());
            pstmt.setString(3, entry.getDescription());
            pstmt.setString(4, entry.getImgURL());
            pstmt.setFloat(5, (float) entry.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(JDBCFoodItem entry) {
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("UPDATE menu SET id = ?, name = ?, description = ?, imgURL = ?, price = ? WHERE id = ?");
            pstmt.setInt(1, entry.getId());
            pstmt.setString(2, entry.getName());
            pstmt.setString(3, entry.getDescription());
            pstmt.setString(4, entry.getImgURL());
            pstmt.setFloat(5, (float) entry.getPrice());
            pstmt.setInt(6, entry.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Database db = new Database();
        try (Connection c = db.connection()) {
            PreparedStatement pstmt = c.prepareStatement("DELETE FROM menu WHERE menu.id = ? ");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
