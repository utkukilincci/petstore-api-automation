# Petstore-Api-Automation

To run the test -> ``mvn clean test``

You can also run tests in parallel (TestNG) -> ``mvn clean test -Dparallel="methods"``

### Notes: 

1. I try to write sample CRUD operation cases (1 positive 1 negative)
2. This API has a lot of bugs. With this project, we can write all cases with copy and paste :)
3. One test (shouldNotUpdatePetWithoutId) will fail. I knowingly let the test fail for you can check the error message my matcher returns.