-- 1. Create restaurant food item table
CREATE TABLE food_items (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price FLOAT
);

-- 2. Create restaurant order statuses table
CREATE TABLE orders (
    id INTEGER PRIMARY KEY,
    customer_name VARCHAR(255),
    -- using String for the Date so that I can do MySQL command #12 with the LIKE keyword
    created VARCHAR(255)
);

CREATE TABLE order_foods (
    order_id INTEGER REFERENCES orders(id) ,
    food_id INTEGER REFERENCES food_items(id),
    quantity INTEGER
);

-- 3. Create restaurant cart table
CREATE TABLE shopping_cart (
    id INTEGER,
    customer_name VARCHAR(255) REFERENCES orders(customer_name),
    food_id INTEGER REFERENCES order_foods(food_id),
    quantity INTEGER REFERENCES order_foods(quantity)
);

-- 4. Insert default food items into food items table
INSERT INTO food_items VALUES
(1, "Hamburger", "A hamburglar", 9.99),
(2, "Fries", "Some fries", 4.99),
(3, "Coke", "Coke cola", 2.99);

-- 5. Insert default order statuses into order statuses table
INSERT INTO orders VALUES 
(1, "Eric", "2017-07-20 13:35:55"),
(2, "John", "2017-07-22 10:35:55"),
(3, "Jane", "2017-07-22 15:35:55"), 
(4, "Alice", "2017-07-22 16:35:55");

INSERT INTO order_foods VALUES
(1, 1, 1),
(1, 2, 2),
(2, 2, 2),
(2, 3, 1),
(3, 3, 1),
(4, 2, 1),
(4, 3, 2);

INSERT INTO shopping_cart VALUES
(1, "Anonymous", 1, 2),
(1, "Anonymous", 2, 1),
(1, "Anonymous", 2, 1),
(2, "Mike", 1, 1),
(2, "Mike", 2, 1),
(3, "Bob", 3, 1);

-- 6. Update food item name from "Hamburger" to "Salad"
UPDATE food_items SET name="Salad" WHERE name="Hamburger";

-- 7. Update customer name from "Jane" to "Doe"
UPDATE orders SET customer_name="Doe" WHERE customer_name="Jane";

-- 8. Find out which food item has the most orders
-- Want name of food item (food_items --> name) linked with highest number of orders (order_foods --> quantity)
-- Need to join food_items and order_foods, specifically rows where food_items.id = order_foods.id
-- Call sum on the quantities, but sum in groups (sum all from each of the three food_id's), then do descending order
-- Display the name and quantity of orders for the first one of the descending order list
SELECT food_items.name, SUM(order_foods.quantity) FROM food_items INNER JOIN order_foods WHERE food_items.id = order_foods.food_id GROUP BY food_id ORDER BY SUM(order_foods.quantity) desc limit 1;

-- 9. Find out which food item in least shopping carts
-- Want name of food item (food_items --> name) linked with lowest quantity in shopping cart (shopping_cart --> quantity)
-- INNER JOIN food_items and shopping_cart, WHERE food_items.id matches shopping_cart.food_id
-- Sum in groups based on the food_id, then do ascending order
-- Display the name and quantity in shopping cart for the top result in the ascending order list
SELECT food_items.name, SUM(shopping_cart.quantity) FROM food_items INNER JOIN shopping_cart WHERE food_items.id = shopping_cart.food_id GROUP BY food_id ORDER BY SUM(shopping_cart.quantity) asc limit 1;

-- 10. Find out all restaurant food items using SELECT query
SELECT food_items.name FROM food_items;

-- 11. Find out all restaurant order statuses using SELECT query
-- I assume the status is when it was submitted in this case since nothing else seems to fit
SELECT orders.created FROM orders;

-- 12. Find out the order statuses that is created today
SELECT orders.created FROM orders WHERE orders.created LIKE "2017-07-22%";

-- 13. DELETE restaurant food items table
DROP TABLE food_items;

-- 14. Delete restaurant order statuses table
DROP TABLES orders, order_foods;

-- 15. Delete restaurant cart table
DROP TABLE shopping_cart;