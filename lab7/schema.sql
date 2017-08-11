DROP TABLE IF EXISTS menu;

DROP TABLE IF EXISTS orders;

DROP TABLE IF EXISTS order_foods;

CREATE TABLE menu (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    imgURL TEXT,
    price FLOAT(2),
    quantity INTEGER
);

CREATE TABLE orders (
	order_id INTEGER AUTO_INCREMENT PRIMARY KEY,
	customer_name VARCHAR(255) NOT NULL,
	status VARCHAR(255) NOT NULL,
	order_time TIMESTAMP NOT NULL
);

CREATE TABLE order_foods (
	order_foods_id INTEGER NOT NULL REFERENCES orders(orders_id),
	menu_id INTEGER NOT NULL REFERENCES menu(id),
	food_quantity INTEGER NOT NULL
);


INSERT INTO menu (id, name, description, imgURL, price, quantity) VALUES
(1, "Unholy Water", "Cursed and hexed by numerous witches, this potion has been known to induce aggression and spite in its drinkers.", "images/potion1.png", 6.6, 1),
(2, "Dragon Breath Ale", "Since the days of old, knights have been known to consume the scorched liquor of dragons to acquire immunity to flame.", "images/potion2.png", 8.0, 1),
(3, "Matrix Mead", "What happens if you mix a blue pill and a red pill together? Only the truly brave would dare to find out...", "images/potion3.png", 2.5, 1),
(4, "Stealth Swill", "Want to spy on your mortal enemy? A swig of this swill will turn you invisible. Even the bottle itself is disappearing!", "images/potion4.png", 4.0, 1),
(5, "Gnomish Gin", "This refreshing concoction has been imbued with secretive gnomish medicinal herbs, perfect for all your combat ailments.", "images/potion5.png", 1.5, 1);

INSERT INTO orders (order_id, customer_name, status, order_time) VALUES
(1, "Aaron", "IN PROGRESS", NOW()),
(2, "Fiona the Volatile", "IN QUEUE", NOW());

INSERT INTO order_foods (order_foods_id, menu_id, food_quantity) VALUES 
(1, 2, 5),
(1, 4, 3),
(2, 3, 4);