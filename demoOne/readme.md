# Demo One

Given Instructions for demo One:
- Rest API that can handle any structured data in Json
    - Specify URIs, status codes, headers, data model, version
- Rest API with support for crd operations
    - Post, Get, Delete
- Rest API with support for validation
    - Json Schema describing the data model for the use case
- Controller validates incoming payloads against json schema
- The semantics with ReST API operations such as update if not changed/read if changed
    - Update not required
    - Conditional read is required
- Storage of data in key/value store
    - Must implement use case provided

## Given Use Case
```
{

	"planCostShares": {
		"deductible": 2000,
		"_org": "example.com",
		"copay": 23,
		"objectId": "1234vxc2324sdf-501",
		"objectType": "membercostshare"
		
	},
	"linkedPlanServices": [{
		"linkedService": {
			"_org": "example.com",
			"objectId": "1234520xvc30asdf-502",
			"objectType": "service",
			"name": "Yearly physical"
		},
		"planserviceCostShares": {
			"deductible": 10,
			"_org": "example.com",
			"copay": 0,
			"objectId": "1234512xvc1314asdfs-503",
			"objectType": "membercostshare"
		},
		"_org": "example.com",
		"objectId": "27283xvx9asdff-504",
		"objectType": "planservice"
	}, {
		"linkedService": {
			"_org": "example.com",
			"objectId": "1234520xvc30sfs-505",
			"objectType": "service",
			"name": "well baby"
		},
		"planserviceCostShares": {
			"deductible": 10,
			"_org": "example.com",
			"copay": 175,
			"objectId": "1234512xvc1314sdfsd-506",
			"objectType": "membercostshare"
		},
		
		"_org": "example.com",
		
		"objectId": "27283xvx9sdf-507",
		"objectType": "planservice"
	}],


	"_org": "example.com",
	"objectId": "12xvxc345ssdsds-508",
	"objectType": "plan",
	"planType": "inNetwork",
	"creationDate": "12-12-2017"
}
```

# DemoOne

Welcome to the **DemoOne** project! This project is built using Spring Boot and utilizes Redis as a NoSQL database to handle JSON data. It provides APIs for creating, reading, and deleting data.

## Features

The **DemoOne** project offers the following features:

1. **Create API**: This API allows you to create JSON data and store it in the Redis database. You can send a JSON payload via an HTTP POST request to the designated endpoint, and the data will be stored in the database.

2. **Read API**: The read API enables you to retrieve the JSON data from the Redis database. By sending an HTTP GET request to the specified endpoint, you can retrieve the stored JSON data.

3. **Delete API**: This API allows you to delete specific JSON data from the Redis database. You need to send an HTTP DELETE request to the designated endpoint, providing the appropriate identifier for the data you wish to delete.
4. **Conditional Read**: Get request follows conditional read principle, and only displays results when the 'if-Match' value and ETag value is same.

## Getting Started

To get started with the **DemoOne** project, follow the steps below:

1. **Clone the repository**: Start by cloning this repository to your local machine using the following command:

   ```
   git clone https://github.com/Pranav014/INFO7255_AdvBigData.git
   ```
   Go to DemoOne and run springboot application

2. **Configure Redis**: Make sure you have Redis installed and running on your system. Update the Redis connection details in the project's configuration file to match your Redis server configuration.

3. **Build and run the project**: Navigate to the project's root directory and build the project using the provided build tool (e.g., Maven or Gradle). Then, run the project using the appropriate command. Ensure that all dependencies are resolved during the build process.

4. **Test the APIs**: Once the project is up and running, you can test the create, read, and delete APIs using your preferred API testing tool (e.g., cURL, Postman, or Insomnia). Send HTTP requests to the respective endpoints to interact with the data stored in the Redis database.

## Endpoints

1. `localhost:8081/api/plans/get` : `GET` Request to get all values
2. `localhost:8081/api/plans/add` : `POST` Request to add a json object to redis
3. `localhost:8081/api/plans/get/{id}` : `GET` Request to get a specific object following conditionsl read principle
4. `localhost:8081/api/plans/delete/{id}` : `DELETE` Request to delete a specific object

## Contributing

Contributions to the **DemoOne** project are welcome. If you find any issues or have suggestions for improvements, please feel free to create a pull request or submit an issue in the repository.

## License

The **DemoOne** project is licensed under the [MIT License](LICENSE).

## Contact

If you have any questions or need further assistance, please feel free to contact the project me or open an issue in the repository.

Happy coding!
