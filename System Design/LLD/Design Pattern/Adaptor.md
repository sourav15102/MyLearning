
### Define
**Adapter** is a structural design pattern that allows objects with incompatible interfaces to collaborate.

### Problem
Lets say there is an app which calls different APIs and collects the data and displays it, and now the new API it is dealing with now is sending the data in different format than others. 
Our app only works with previous type of data, it is not recommended to make major changes to the code of the app.

### Solution:
We can have an adaptor which will act as an intermediary between the application and API service, it will do all the work of conversions.
![[Pasted image 20230819210059.png]]

