## How does a _SOAP API_ differ from a _REST API_?

[](https://github.com/Devinterview-io/api-design-interview-questions#4-how-does-a-soap-api-differ-from-a-rest-api)

Before we delve into the differences between SOAP and REST, let's understand the essence of each.

### Core Concepts

[](https://github.com/Devinterview-io/api-design-interview-questions#core-concepts)

#### SOAP (Simple Object Access Protocol)

[](https://github.com/Devinterview-io/api-design-interview-questions#soap-simple-object-access-protocol)

- **Coordinated by**: World Wide Web Consortium (W3C)
- **Communication Protocol**: Primarily uses HTTP and SMTP
- **Data Format**: XML
- **Main Focus**: Actions and behaviors, comprehensiveness, and security

#### REST (Representational State Transfer)

[](https://github.com/Devinterview-io/api-design-interview-questions#rest-representational-state-transfer)

- **Coordinated by**: No overseeing body; adheres to a set of architectural constraints
- **Communication Protocol**: Utilizes various protocols, often HTTP
- **Data Format**: Initially XML, commonly JSON; not prescriptive
- **Main Focus**: Statelessness, uniform interface, performance, and simplicity

### Key Distinctions

[](https://github.com/Devinterview-io/api-design-interview-questions#key-distinctions)

#### Data Format

[](https://github.com/Devinterview-io/api-design-interview-questions#data-format)

- **SOAP**: Mandates the use of XML.
- **REST**: Initially employed XML but has adapted to popularize JSON due to its simplicity and appropriateness for web-based data exchange.

#### Ease of Consumption

[](https://github.com/Devinterview-io/api-design-interview-questions#ease-of-consumption)

- **SOAP**: Comprehensive, stable, but raises complexity with formal contracts, WSDL usage, and tight coupling.
- **REST**: Promotes loosely coupled, simple interactions that are often easier for developers to understand and employ.

#### Standards and Formalism

[](https://github.com/Devinterview-io/api-design-interview-questions#standards-and-formalism)

- **SOAP**: Insists on adhering to WSDL (Web Services Description Language) to define the structure and behavior of the web service.
- **REST**: Lacks a universal standard or formal contract.

#### Error Handling

[](https://github.com/Devinterview-io/api-design-interview-questions#error-handling)

- **SOAP**: Definitive standards for error representations using dedicated XML elements.
- **REST**: Generally employs HTTP status codes for error communication.

#### State Management

[](https://github.com/Devinterview-io/api-design-interview-questions#state-management)

- **SOAP**: Can be stateful, although it does not enforce it.
- **REST**: Is designed to be stateless.

#### Integrations

[](https://github.com/Devinterview-io/api-design-interview-questions#integrations)

- **SOAP**: Initially built with a focus on integrating remote systems and enabling inter-machine communication.
- **REST**: Evolved within the web for serving web resources, focusing on human-readable URLs for web APIs.

### Code Example: SOAP Request

[](https://github.com/Devinterview-io/api-design-interview-questions#code-example-soap-request)

Here is the Java code:

```java
URL url = new URL("http://webservice.example.com");
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("POST");
connection.setRequestProperty("Content-Type", "text/xml");
connection.setRequestProperty("SOAPAction", "http://webservice.example.com/SomeAction");

String soapRequest = "<soap:Envelope ....></soap:Envelope>";

connection.setDoOutput(true);
try (OutputStream os = connection.getOutputStream()) {
    byte[] input = soapRequest.getBytes(StandardCharsets.UTF_8);
    os.write(input, 0, input.length);
}

try (BufferedReader br = new BufferedReader(new InputStreamReader(
    connection.getInputStream(), StandardCharsets.UTF_8))) {
    StringBuilder response = new StringBuilder();
    String responseLine;
    while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
    }
    System.out.println(response.toString());
}
```

### Code Example: REST Request

[](https://github.com/Devinterview-io/api-design-interview-questions#code-example-rest-request)

Here is a Python example using the popular `requests` library:

```python
import requests

url = "http://api.example.com/some-resource"
headers = {"Content-Type": "application/json", "Authorization": "Bearer YOUR_TOKEN"}
data = {"param1": "value1", "param2": "value2"}

response = requests.post(url, json=data, headers=headers)

if response.status_code == 200:
    # Successful request, process data here
    response_data = response.json()
else:
    print(f"Request failed with status code: {response.status_code}")
```