DROP TABLE IF EXISTS menu;

CREATE TABLE menu (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    imgURL TEXT,
    price FLOAT(2),
    quantity INTEGER
);

INSERT INTO menu (id, name, description, imgURL, price, quantity) VALUES
(1, "Unholy Water", "Cursed and hexed by numerous witches, this potion has been known to induce aggression and spite in its drinkers.", "images/potion1.png", 6.66, 1),
(2, "Dragon Breath Ale", "Since the days of old, knights have been known to consume the scorched liquor of dragons to acquire immunity to flame.", "images/potion2.png", 8.00, 1),
(3, "Matrix Mead", "What happens if you mix a blue pill and a red pill together? Only the truly brave would dare to find out...", "images/potion3.png", 2.50, 1),
(4, "Stealth Swill", "Want to spy on your mortal enemy? A swig of this swill will turn you invisible. Even the bottle itself is disappearing!", "images/potion4.png", 4.00, 1),
(5, "Gnomish Gin", "This refreshing concoction has been imbued with secretive gnomish medicinal herbs, perfect for all your combat ailments.", "images/potion5.png", 1.50, 1);