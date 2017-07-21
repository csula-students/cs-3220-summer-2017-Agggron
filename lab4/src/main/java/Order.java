import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Order {

	public enum Status {
		IN_QUEUE, IN_PROGRESS, COMPLETED
	}

	public final int id;
	public final List<FoodItem> items;
	public final String customerName;
	public String status;
	public final Date orderTime;

	public Order (int id, List<FoodItem> items, String customerName, String status, Date orderTime) {
		this.id = id;
		this.items = items;
		this.customerName = customerName;
		this.status = status;
		/*for (Status acceptedValueForStatus : Status.values()) {
			if (acceptedValueForStatus.toString() == status) {
				this.status = status;
			}
		}*/
		this.orderTime = orderTime;
	}
	
	public int getId() {
		return id;
	}

	public List<FoodItem> getItems() {
		return items;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getStatus() {
		return status;
	}

	public Date getOrderTime() {
		return orderTime;
	}
}
