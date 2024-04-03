public class Order {
    private int orderId;
    private int username; // Assuming username is an int, adjust if it's another type
    private String orderDetails;
    private String totalAmount;

    public Order(int orderId, int username, String orderDetails, String totalAmount) {
        this.orderId = orderId;
        this.username = username;
        this.orderDetails = orderDetails;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public int getUsername() { return username; }
    public String getOrderDetails() { return orderDetails; }
    public String getTotalAmount() { return totalAmount; }

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setUsername(int username) { this.username = username; }
    public void setOrderDetails(String orderDetails) { this.orderDetails = orderDetails; }
    public void setTotalAmount(String totalAmount) { this.totalAmount = totalAmount; }
}
