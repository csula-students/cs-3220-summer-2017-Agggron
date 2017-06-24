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
        this.order_placed = false;
    }

    init () {
        // Render a list of items under root element
        this.render();
        // TODO: attach remove cart items to rendered HTML
        // All EventListeners are found in render() --> removeButton, removeAllButton, confirmOrderButton. 
    }

    destroy () {
        // TODO: remove all the events attached from init
        let removeButtons = document.querySelectorAll('.remove_button');
        for (var i = 0; i < removeButtons.length; i++) {
            let btn = removeButtons[i];
            btn.removeEventListener('click', () => {
                let item = this.items[parseInt(btn.dataset.index)];
                this.removeItem(item);
            });
        }

        let removeAllButton = document.querySelector('.remove_all_button');
        removeAllButton.removeEventListener('click', () => {
            this.removeAllItems();
        });

        let confirmOrderButton = document.querySelector('.confirm_order_button');
        confirmOrderButton.removeEventListener('click', () => {
            this.placeOrder();
        });
    }

    // remove an item from shopping cart
    removeItem (item) {
        // TODO: logic to remove an item from cart
        /* With Eric's help from Slack...*/
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
        if (this.items !== null) {
            var newQueueItems = [];

            // everything in 
            if (this.store.queue !== null) {
                for (var i = 0; i < this.store.queue.length; i++) {
                    newQueueItems.push(this.store.queue[i]);
                }
            }
        
            for (var i = 0; i < this.items.length; i++) {
                var new_item_added = true;
                for (var j = 0; j < newQueueItems.length; j++) {
                    var existing_queue_item_name = newQueueItems[j][0];
                    if (this.items[i][0] === existing_queue_item_name) {
                        var amount_to_add = Number(this.items[i][2]);
                        newQueueItems[j][2] += amount_to_add;
                        new_item_added = false;
                    }
                }
                if (new_item_added) {
                    newQueueItems.push([this.items[i][0], this.items[i][1], Number(this.items[i][2])]);
                }
            }
            this.store.queue = newQueueItems;
            this.order_placed = true;
            this.removeAllItems();
        }
    }

    // render a list of item under root element
    render () {
        this.items = this.store.cartItems;
        let tbody = this.root.querySelector('tbody');
        // using innerHTML to render a list of table row item under tbody
        tbody.innerHTML = ``;
        if (this.items === null) {
            this.items = [];
        } else if ((this.items.length == 0) && (this.order_placed)) {
            tbody.innerHTML += 
            `<tr class="cart-table">
                <td class="cart-table">
                    <p class="title">Order Placed Successfully!</p>
                    <p>See your order <a href="statuses.html" class="link">here</a>.</p>
                </td>
            </tr>`; 
            this.order_placed = false;
            return;
        } else if (this.items.length == 0) {
            tbody.innerHTML +=
            `<tr class="cart-table">
                <td class="cart-table">
                    <p>Nothing! Go on, buy some more liquor!</p>
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

        let removeAllButton = document.querySelector('.remove_all_button');
        removeAllButton.addEventListener('click', () => {
            this.removeAllItems();
        });

        let confirmOrderButton = document.querySelector('.confirm_order_button');
        confirmOrderButton.addEventListener('click', () => {
            this.placeOrder();
        });
    }
}    

class CheckoutButton {
    constructor(root, store, cart) {
        this.root = root;
        this.store = store;
        this.onClick = () => this.addItemToCart();
        this.init();
        this.cart = cart;
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
        this.cart.render();
    }
}

class StatusTable {
    // take dom element into JavaScript class to attachment events
    constructor(root, store) {
        this.root = root;
        this.store = store;
        this.init();
    }

    init () {
        // attach click event listener to table header row on each column
        this.render();
        // not doing sorting as of now
    }

    destroy () {
        // remove all the events attached from init
        let clearHistoryButton = document.querySelector('.clear_history_button');
        clearHistoryButton.removeEventListener('click', () => {
            this.clearHistory();
        });
    }

    sort (columnName) {
        // after sorting the array of statuses, re render item to update view
        if (columnName === "name_ascending") {
            //debugger;
            var final_sorted_queue = [];
            for (var i = 0; i < this.store.queue.length; i++) {
                var current_item = this.store.queue[i];
                if (i == 0) {
                    final_sorted_queue.push(current_item);
                } else {
                    // second item must compare to all items in new queue
                    var newly_sorted_queue = [];
                    var item_in_sort_position = false;
                    for (var j = 0; j < final_sorted_queue.length; j++) {
                        // item in appropriate position, all remaining items in the final_sorted_queue can go to the end
                        if (item_in_sort_position) {
                            newly_sorted_queue.push(current_item);
                            break;
                        }
                        if (current_item[0] < final_sorted_queue[j][0]) {
                            newly_sorted_queue.push(current_item);
                            newly_sorted_queue.push(final_sorted_queue[j]);
                            item_in_sort_position = true;
                        } 
                    }
                    final_sorted_queue = newly_sorted_queue; 
                }
            }
            this.store.queue = final_sorted_queue;
        }
        this.render();
    }

    clearHistory() {
        this.store.queue = [];
        this.render();
    }

    // render rows of items under table using root.innerHTML
    render () {
        let tbody = this.root.querySelector('tbody');
        // using innerHTML to render a list of table row item under tbody
        tbody.innerHTML = ``;
        if (this.store.queue === null) {
            this.store.queue = [];
        } 
        if (this.store.queue.length == 0) {
            tbody.innerHTML +=
            `<tr class="order_status_table">
                <td class="order_status_table" >
                    <p>You have purchased nothing! <br>
                    Actually buy something <a href="menu.html" class="link">here</a>.</p>
                    <img src="../images/bartender.jpg" width=150px height=120px>
                </td>
            </tr>`; 
            return;
        }

        tbody.innerHTML += 
            `<tr class="order_status_table">
                <td class="order_status_table title">Beverage</td>
                <td class="order_status_table title">Quantity</td>
                <td class="order_status_table title">Status</td>
            </tr>`;

        for (var i = 0; i < this.store.queue.length; i++) {
            // for each item in local storage's QUEUE, create a row with a cell for the item name and image, a cell for quantity, and a cell for status (In Progress for now).
            var item_name = this.store.queue[i][0];
            var image_name = this.store.queue[i][1];
            var item_quantity = Number(this.store.queue[i][2]);

            tbody.innerHTML +=
                `<tr class="order_status_table">
                    <td class="order_status_table">
                        <h4>${item_name}</h4>
                        <img src=${image_name} class="small">
                    </td>
                    <td class="order_status_table">
                        <h4>${item_quantity}</h4>
                    </td>
                    <td class="order_status_table">
                        <h4>In Progress!</h4>
                    </td>
                </tr>`;
        }

        tbody.innerHTML += 
            `<tr class="cart-table">
                <td class="cart-table" colspan="3">
                    <button class="clear_history_button">Clear All History!</button>
                    <br>
                    <br>
                    <button class="sort_button">Sort By Ascending Name!</button>
                </td>
            </tr>`;

        let clearHistoryButton = document.querySelector('.clear_history_button');
        clearHistoryButton.addEventListener('click', () => {
            this.clearHistory();
        });
        let sortButton = document.querySelector('.sort_button');
        sortButton.addEventListener('click', () => {
            this.sort("name_ascending");
        });
    }
}

// DOMContentLoaded event will allow us to run the following function when
// everything is ready. Think of the following code will only be executed by
// the end of document
document.addEventListener('DOMContentLoaded', () => {
    // use querySelector to find the table element (preferably by id selector)
    let statusTable = document.querySelector('.order_status_table');
    // // use querySelector to find the cart element (preferably by id selector)
    let cart = document.querySelector('.cart-table');
    let checkoutButtons = document.querySelectorAll('.checkout-button');

    let store = new Store(window.localStorage);

    if (statusTable) {
        new StatusTable(statusTable, store);
    }
    if (cart) {
        var cartVar = new Cart(cart, store);
    }
    if (checkoutButtons && checkoutButtons.length) {
        for (var i = 0; i < checkoutButtons.length; i++) {
            new CheckoutButton(checkoutButtons[i], store, cartVar);
        }
    }
});