import subprocess

# List of curl PATCH commands from the log
curl_commands = [
    "curl --location --request PATCH http://localhost:8080/order/addNew/1/7/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/1/2/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/2/19/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/2/19/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/3/17/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/3/19/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/4/8/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/4/5/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/5/18/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/5/7/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/6/1/5",
    "curl --location --request PATCH http://localhost:8080/order/remove/6/1/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/7/3/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/7/9/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/8/9/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/8/6/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/9/12/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/9/8/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/10/1/3",
    "curl --location --request PATCH http://localhost:8080/order/remove/10/16/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/1/14/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/2/19/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/3/14/3",
    "curl --location --request PATCH http://localhost:8080/order/remove/4/5/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/5/17/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/6/10/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/7/18/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/8/2/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/9/3/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/10/12/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/1/13/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/2/12/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/3/10/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/4/15/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/5/2/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/6/4/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/7/14/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/8/9/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/9/19/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/10/2/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/1/9/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/2/12/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/3/3/2",
    "curl --location --request PATCH http://localhost:8080/order/remove/4/19/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/5/18/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/6/3/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/7/11/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/8/10/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/9/8/2",
    "curl --location --request PATCH http://localhost:8080/order/remove/10/12/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/1/15/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/2/15/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/3/9/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/4/9/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/5/19/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/6/4/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/7/3/3",
    "curl --location --request PATCH http://localhost:8080/order/remove/8/15/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/9/9/5",
    "curl --location --request PATCH http://localhost:8080/order/remove/10/14/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/1/13/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/2/6/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/3/16/1",
    "curl --location --request PATCH http://localhost:8080/order/addNew/4/18/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/5/11/3",
    "curl --location --request PATCH http://localhost:8080/order/addNew/6/7/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/7/7/4",
    "curl --location --request PATCH http://localhost:8080/order/addNew/8/7/5",
    "curl --location --request PATCH http://localhost:8080/order/addNew/9/19/2",
    "curl --location --request PATCH http://localhost:8080/order/addNew/10/9/3",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/1",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/2",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/3",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/4",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/5",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/6",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/7",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/8",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/9",
    "curl --location --request PATCH http://localhost:8080/order/placeOrder/10"
]

# Function to execute the curl command
def execute_curl(command):
    try:
        subprocess.run(command, shell=True, check=True)
        print(f"\nSuccessfully executed: {command}")
    except subprocess.CalledProcessError as e:
        print(f"\nError executing: {command} - {e}")

# Main loop to go through each curl command one by one
for index, command in enumerate(curl_commands):
    user_input = input(f"\nPress Enter to send request {index + 1}/{len(curl_commands)}: ")
    if user_input.lower() == 'exit':
        print("\nExiting the loop.")
        break
    execute_curl(command)

print("All commands have been executed.")
