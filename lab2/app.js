// single state store
class Store {
    constructor (storage) {
        this.storage = storage; // assuming local storage will be passed in as storage
        // these are the key name you can use in Store
        this.CART_KEY = 'CART';
        this.QUEUE_KEY = 'QUEUE';
        this.FOODS_KEY = 'FOODS';
    }

    // you can get item by store.cartItems
    get cartItems () {
        return JSON.parse(this.storage.getItem(this.CART_KEY));
    }

    // to call setter, simply "assign" like store.cartItems = something
    set cartItems (cartItems) {
        this.storage.setItem(this.CART_KEY, JSON.stringify(cartItems));
    }

    get queue () {
        return JSON.parse(this.storage.getItem(this.QUEUE_KEY));
    }

    set queue (queue) {
        this.storage.setItem(this.QUEUE_KEY, JSON.stringify(queue));
    }

    get foods () {
        return JSON.parse(this.storage.getItem(this.FOODS_KEY));
    }

    set foods (foods) {
        this.storage.setItem(this.FOODS_KEY, JSON.stringify(foods));
    }
}

class Cart {
    // take dom element into JavaScript class to attachment events
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.items = this.store.cartItems;
        this.init();
    }

    init () {
        // Render a list of items under root element
        this.render();
        // TODO: attach remove cart items to rendered HTML
        
        // Remove buttons located in removeItem(item) function. Creation of click EventListener calling removeItem(item) is found in RemoveButton class.
    }

    destroy () {
        // TODO: remove all the events attached from init
        // Destroying of click EventListener is found in RemoveButton class.
    }

    // remove an item from shopping cart
    removeItem (item) {
        // TODO: logic to remove an item from cart
        /* Works with RemoveButton class
        if (this.items != null) {
            var updated_list = [];
            for (var i = 0; i < this.items.length; i++) {
                if (this.items[i][0] != item) {
                    updated_list.push(this.items[i]);
                }
            }
            this.store.cartItems = updated_list;
            this.items = updated_list;
        }
        // call render method when the item is removed to update view
        this.render();*/
    
        /* Almost entirely similar, works with Eric's help from Slack...*/
        if (this.items != null) {
            var updated_list = [];
            var to_compare = item[0];
            for (var i = 0; i < this.items.length; i++) {
                if (this.items[i][0] != to_compare) {
                    updated_list.push(this.items[i]);
                }
            }
            this.store.cartItems = updated_list;
            this.items = updated_list;
        }
        this.render();
    }

    removeAllItems() {
        this.store.cartItems = [];
        this.items = [];
        this.render();
    }

    placeOrder () {
        // add item to statuses in store as status "in progress"
        console.log("Order placed!");
    }

    // render a list of item under root element
    render () {
        let tbody = this.root.querySelector('tbody');
        // using innerHTML to render a list of table row item under tbody
        tbody.innerHTML = ``;
        if (this.items === null) {
            this.items = [];
        } else if (this.items.length == 0) {
            tbody.innerHTML +=
            `<tr class="cart-table">
                <td class="cart-table">
                    <p>Nothing! Go on, buy some liquor!</p>
                    <img src="../images/bartender.jpg" width=150px height=120px>
                </td>
            </tr>`; 
            return;
        }
        for (var i = 0; i < this.items.length; i++) {
            // for each item in cartItems, create a row with the item name and image in one cell, and then a box of quantity in the other cell.
            var item_name = this.items[i][0];
            var image_name = this.items[i][1];
            var item_quantity = Number(this.items[i][2]);

            tbody.innerHTML +=
                `<tr class="cart-table">
                    <td class="cart-table">
                        <h4>${item_name}</h4>
                        <img src=${image_name} class="small">
                    </td>
                    <td class="cart-table">
                        <h4>${item_quantity}</h4>
                    </td>
                    <td class="cart-table">
                        <button class="remove_button" data-name=${item_name} data-index=${i}>Remove From Inventory!</button>
                    </td>
                </tr>`;
        }

        tbody.innerHTML += 
            `<tr class="cart-table">
                <td class="cart-table" colspan="3">
                    <button class="remove_all_button">Clear All From Inventory!</button>
                    <br><br>
                    <button class="confirm_order_button">Submit Inventory Request!</button>
                </td>
            </tr>`;
        
        let removeButtons = document.querySelectorAll('.remove_button');
        for (var i = 0; i < removeButtons.length; i++) {
            let btn = removeButtons[i];
            btn.addEventListener('click', () => {
                let item = this.items[parseInt(btn.dataset.index)];
                this.removeItem(item);
            });
        }
        /* Eric's help from Slack that works, but I'm currently using my own implementation...
        removeButtons.forEach(btn => {
            btn.addEventListener('click', () => {
                let item = this.items[parseInt(btn.dataset.index)];
                this.removeItem(item);
            });
        });*/

        let removeAllButton = document.querySelector('.remove_all_button');
        removeAllButton.addEventListener('click', () => {
            this.removeAllItems();
        });

        let confirmOrderButton = document.querySelector('.confirm_order_button');
        confirmOrderButton.addEventListener('click', () => {
            this.placeOrder();
        }) 

    }
}    

class RemoveButton {
    constructor(root, store, cart) {
            this.root = root;
            this.store = store;
            this.cart = cart;
            this.onClick = () => this.cart.removeItem(this.root.dataset.name);
            this.init();
        }

        init () {
            this.root.addEventListener("click", this.onClick);
        }

        destroy () {
            this.root.removeEventListener("click", this.onClick);
        }

}

class ClearStatusButton {
    constructor(root, store, cart) {
            this.root = root;
            this.store = store;
            this.cart = cart;
            this.onClick = () => this.clearStatuses();
            this.init();
        }

        init () {
            this.root.addEventListener("click", this.onClick);
        }

        destroy () {
            this.root.removeEventListener("click", this.onClick);
        }

        clearStatuses() {
            let status = document.querySelector(".status_table");  
        }
}

class CheckoutButton {
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.onClick = () => this.addItemToCart();
        this.init();
    }

    init () {
        this.root.addEventListener("click", this.onClick);
    }

    destroy () {
        this.root.removeEventListener("click", this.onClick);
    }

    addItemToCart () {
        // hint: you can use `dataset` to access data attributes
        // See passing data from HTML to JavaScript from course note
        let cartItems = this.store.cartItems || [];
        // TODO: replace with actual item
        var new_cart_item = true;
        for (var i = 0; i < cartItems.length; i++) {
            // go through each item name in cartItems. If they match, increase the quantity of existing item in cartItems by 1. Otherwise, add the item as a new entry in cartItems.
            var existing_cart_item_name = cartItems[i][0]
            if (this.root.dataset.name === existing_cart_item_name) {
                var amount_to_add = Number(this.root.dataset.quantity);
                cartItems[i][2] += amount_to_add;
                new_cart_item = false;
            }
        }
        if (new_cart_item) {
            cartItems.push([this.root.dataset.name, this.root.dataset.image, Number(this.root.dataset.quantity)]);
        }
        this.store.cartItems = cartItems;
    }
}

class StatusTable {
    // take dom element into JavaScript class to attachment events
    constructor(root, store) {
        this.root = root;
        this.store = store;
        init();
    }

    init () {
        // attach click event listener to table header row on each column
        render();
    }

    destroy () {
        // remove all the events attached from init
    }

    sort (columnName) {
        // after sorting the array of statuses, re render item to update view
        render();
    }

    // render rows of items under table using root.innerHTML
    render () {

    }
}

// DOMContentLoaded event will allow us to run the following function when
// everything is ready. Think of the following code will only be executed by
// the end of document
document.addEventListener('DOMContentLoaded', () => {
    // use querySelector to find the table element (preferably by id selector)
    // let statusTable = document.querySelector('');
    // // use querySelector to find the cart element (preferably by id selector)
    let cart = document.querySelector('.cart-table');
    let checkoutButtons = document.querySelectorAll('.checkout-button');

    let store = new Store(window.localStorage);
    // if (table) {
    //     new StatusTable(table, store);
    // }
    if (cart) {
        new Cart(cart, store);
    }
    if (checkoutButtons && checkoutButtons.length) {
        for (var i = 0; i < checkoutButtons.length; i ++) {
            new CheckoutButton(checkoutButtons[i], store);
        }
    }
});