
### **3. Mocks vs. InjectMocks in Spring Tests**

#### **`@Mock`**

- Creates a **mock object** of a dependency.
- Behavior of the mock can be customized using `when()` and `thenReturn()`.

#### **`@InjectMocks`**

- Injects mock dependencies (`@Mock`) into the object being tested.
- Automatically initializes the test subject.

---

#### **Example**

```java
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new User(1, "John")));

        User user = userService.findUserById(1);

        assertEquals("John", user.getName());
    }
}
```

---

#### **Key Differences**

|**`@Mock`**|**`@InjectMocks`**|
|---|---|
|Creates a mock of a dependency.|Injects mocked dependencies into the test subject.|
|Requires manual injection into the class under test.|Automatically wires dependencies.|
