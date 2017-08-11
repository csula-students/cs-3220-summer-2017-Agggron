import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class JDBCOrder {

	public enum Status {
		IN_QUEUE, IN_PROGRESS, COMPLETED
	}

	public int id;
	public List<JDBCFoodItem> items;
	public String customerName;
	public String status;
	public Date orderTime;

	public JDBCOrder (int id, List<JDBCFoodItem> items, String customerName, String status, Date orderTime) {
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

	public List<JDBCFoodItem> getItems() {
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
