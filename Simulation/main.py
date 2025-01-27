import subprocess
import time
import random
import asyncio

# Function to send a curl request via subprocess
def send_curl(method, url, data=None):
    curl_command = ['curl', '--location', '--request', method, url]

    if data:
        curl_command += ['--header', 'Content-Type: application/json', '--data', data]

    # Capture the output from curl
    result = subprocess.run(curl_command, capture_output=True, text=True)

    # Print out the response for debugging
    print(f"Sent request: {url}")
    print(f"Response: {result.stdout}")
    print(f"Error (if any): {result.stderr}")

# Function to simulate product addition (19 products)
def add_products():
    base_url = "http://localhost:8080/product/add"
    products = [
        '{"name": "Smartphone", "description": "Latest smartphone model", "price": 999.99, "stock": 50, "category": "ELECTRONICS"}',
        '{"name": "Laptop", "description": "High performance laptop", "price": 1299.99, "stock": 30, "category": "ELECTRONICS"}',
        '{"name": "Headphones", "description": "Noise-cancelling headphones", "price": 199.99, "stock": 100, "category": "ELECTRONICS"}',
        '{"name": "Smartwatch", "description": "Wearable fitness tracker", "price": 249.99, "stock": 75, "category": "ELECTRONICS"}',
        '{"name": "Gaming Console", "description": "Next-gen gaming console", "price": 499.99, "stock": 20, "category": "ELECTRONICS"}',
        '{"name": "4K TV", "description": "55 inch 4K Ultra HD TV", "price": 799.99, "stock": 15, "category": "ELECTRONICS"}',
        '{"name": "Bluetooth Speaker", "description": "Portable Bluetooth speaker", "price": 149.99, "stock": 120, "category": "ELECTRONICS"}',
        '{"name": "Tablet", "description": "Latest tablet model", "price": 499.99, "stock": 40, "category": "ELECTRONICS"}',
        '{"name": "Camera", "description": "Digital SLR camera", "price": 899.99, "stock": 25, "category": "ELECTRONICS"}',
        '{"name": "Fitness Tracker", "description": "Wearable fitness tracking band", "price": 99.99, "stock": 200, "category": "ELECTRONICS"}',
        '{"name": "Running Shoes", "description": "Lightweight running shoes", "price": 79.99, "stock": 150, "category": "SHOES"}',
        '{"name": "Office Chair", "description": "Ergonomic office chair", "price": 199.99, "stock": 40, "category": "FURNITURE"}',
        '{"name": "Coffee Machine", "description": "Automatic coffee machine", "price": 249.99, "stock": 60, "category": "HOME_APPLIANCES"}',
        '{"name": "Dog Food", "description": "Grain-free dog food", "price": 39.99, "stock": 200, "category": "PET_SUPPLIES"}',
        '{"name": "Baby Stroller", "description": "Lightweight and foldable stroller", "price": 299.99, "stock": 25, "category": "BABY_PRODUCTS"}',
        '{"name": "Office Desk", "description": "Adjustable standing desk", "price": 499.99, "stock": 30, "category": "OFFICE_SUPPLIES"}',
        '{"name": "Basketball", "description": "Official size basketball", "price": 29.99, "stock": 100, "category": "SPORTS_AND_OUTDOORS"}',
        '{"name": "Winter Jacket", "description": "Waterproof winter jacket", "price": 149.99, "stock": 60, "category": "FASHION"}',
        '{"name": "Grocery Package", "description": "Organic grocery essentials", "price": 89.99, "stock": 120, "category": "GROCERY"}'
    ]

    # Send curl requests to add the products
    for product_data in products:
        send_curl('POST', base_url, product_data)
        time.sleep(0.3) # interval 0.3 secs


# Function to add customers ( 10 )
def add_customers():
    base_url = "http://localhost:8080/user/add"
    customers = [
        '{"name": "John Doe", "email": "johndoe1@example.com", "phoneNumber": "1234567890", "password": "password123", "userType": "Customer", "address": {"street": "123 Main St", "country": "USA", "state": "NY", "postCode": "10001"}}',
        '{"name": "Jane Doe", "email": "janedoe1@example.com", "phoneNumber": "9876543210", "password": "password123", "userType": "Customer", "address": {"street": "456 Elm St", "country": "Canada", "state": "ON", "postCode": "M5H 2N2"}}',
        '{"name": "Alice Johnson", "email": "alicej1@example.com", "phoneNumber": "1122334455", "password": "password123", "userType": "Customer", "address": {"street": "789 Oak St", "country": "UK", "state": "London", "postCode": "SW1A 1AA"}}',
        '{"name": "Bob Smith", "email": "bobsmith1@example.com", "phoneNumber": "5544332211", "password": "password123", "userType": "Customer", "address": {"street": "101 Pine St", "country": "Australia", "state": "NSW", "postCode": "2000"}}',
        '{"name": "Charlie Brown", "email": "charlieb1@example.com", "phoneNumber": "6655443322", "password": "password123", "userType": "Customer", "address": {"street": "121 Maple St", "country": "Germany", "state": "Berlin", "postCode": "10115"}}',
        '{"name": "David Williams", "email": "davidw1@example.com", "phoneNumber": "2233445566", "password": "password123", "userType": "Customer", "address": {"street": "131 Cedar St", "country": "France", "state": "Paris", "postCode": "75001"}}',
        '{"name": "Eve Davis", "email": "eved1@example.com", "phoneNumber": "7788990011", "password": "password123", "userType": "Customer", "address": {"street": "141 Spruce St", "country": "Japan", "state": "Tokyo", "postCode": "100-0001"}}',
        '{"name": "Frank Miller", "email": "frankm1@example.com", "phoneNumber": "3344556677", "password": "password123", "userType": "Customer", "address": {"street": "151 Birch St", "country": "Brazil", "state": "São Paulo", "postCode": "01000-000"}}',
        '{"name": "Grace Lee", "email": "gracel1@example.com", "phoneNumber": "2233441122", "password": "password123", "userType": "Customer", "address": {"street": "161 Walnut St", "country": "India", "state": "Maharashtra", "postCode": "400001"}}',
        '{"name": "Henry Clark", "email": "henryc1@example.com", "phoneNumber": "9988776655", "password": "password123", "userType": "Customer", "address": {"street": "171 Oakwood St", "country": "South Africa", "state": "Gauteng", "postCode": "2000"}}'
    ]

    # Send curl requests to add the customers
    for customer_data in customers:
        send_curl('POST', base_url, customer_data)
        time.sleep(0.3)  # interval 0.3 secs



# Customer class to handle sending requests
class Customer:
    def __init__(self, number):
        self.number = number

    async def send_order_requests(self):

        product_number = random.randint(1, 19)  # 19 products now
        quantity = random.randint(1, 5)

        # to prevent removing without shoppingcart : first add something to make a shopping cart :
        print(f"(Customer {self.number})")
        url = f"http://localhost:8080/order/addNew/{self.number}/{product_number}/{quantity}"
        send_curl('PATCH', url)

        for i in range(6):
            product_number = random.randint(1, 19)  # 19 products now
            quantity = random.randint(1, 5)
            print(f"Round : {i}")

            if random.random() < 0.8:
                # 80% chance to add product
                print(f"(Customer {self.number}):")
                url = f"http://localhost:8080/order/addNew/{self.number}/{product_number}/{quantity}"
                send_curl('PATCH', url)
            else:
                print(f"(Customer {self.number}):")
                # 20% chance to remove product
                url = f"http://localhost:8080/order/remove/{self.number}/{product_number}/{quantity}"
                send_curl('PATCH', url)

            # Interval of 0.5 seconds
            await asyncio.sleep(0.2)

        # Send placeOrder request
        print(f"(Customer {self.number}):")
        place_order_url = f"http://localhost:8080/order/placeOrder/{self.number}"
        send_curl('PATCH', place_order_url)

        # 30% chance to cancel the order
        if random.random() < 0.3:
            print(f"(Customer {self.number}):")
            cancel_order_url = f"http://localhost:8080/order/cancelOrder/{self.number}"
            send_curl('PATCH', cancel_order_url)


# Function to run customers asynchronously
async def run_customers():
    customers = [Customer(i) for i in range(1, 11)]

    while True:
        tasks = [customer.send_order_requests() for customer in customers]
        await asyncio.gather(*tasks)

# Main function to start the process
def main():

    # First, add 19 products
    print("Adding products...")
    add_products()
    # wait for a while
    time.sleep(3)

    # Add customer
    print("Adding Customers...")
    add_customers()
    time.sleep(3)


    # run customers async
    print("Starting customer requests...")
    asyncio.run(run_customers())

# Starting point
if __name__ == "__main__":
    main()
