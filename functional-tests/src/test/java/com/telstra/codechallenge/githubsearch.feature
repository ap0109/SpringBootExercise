# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: Want to test the git repos search using different criteria

  Scenario: Is the git starred repo search uri available and functioning
    Given url microserviceUrl
    And path '/search/repos'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'

  Scenario: Is the git search uri available and functioning with default values
            for "NUM" number of records per page and "OFFSET" for pagination.
            Default Value for Offset is 1 and for no. of records NUM is 30
    Given url microserviceUrl
    And path '/search/repos'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    * def count = response.length
    * assert count == 30
    * def gitSchemaExpected =
    * match each response ==
    """
      {
        "html_url":'#string',
        "name":'#string',
        "description":'#string',
        "language":'#string',
        "watchers_count":'#number'
      }
    """

  Scenario: Is the git search uri available and functioning with provided values
    Given url microserviceUrl
    And path '/search/repos'
    * param offSet = 1
    * param num = 15
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    * def count = response.length
    * assert count == 15
    * def gitSchemaExpected =
    * match each response ==
    """
      {
        "html_url":'#string',
        "name":'#string',
        "description":'#string',
        "language":'#string',
        "watchers_count":'#number'
      }
    """

  Scenario: Is the git search uri available and functioning with provided values
    Given url microserviceUrl
    And path '/search/repos'
    * param offSet = 1
    * param num = 70
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    * def count = response.length
    * assert count == 70
    * def gitSchemaExpected =
    * match each response ==
    """
      {
        "html_url":'#string',
        "name":'#string',
        "description":'#string',
        "language":'#string',
        "watchers_count":'#number'
      }
    """

  Scenario: Is the git search uri available and functioning with provided values - Maximum 100 records we will get
    Given url microserviceUrl
    And path '/search/repos'
    * param offSet = 1
    * param num = 120
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    * def count = response.length
    * assert count == 100
    * def gitSchemaExpected =
    * match each response ==
    """
      {
        "html_url":'#string',
        "name":'#string',
        "description":'#string',
        "language":'#string',
        "watchers_count":'#number'
      }
    """