# Spring Boot Coding Exercise

This is a simple coding exercise that will allow you to demonstrate your knowledge
of spring boot by using a microservice to call a downstream service and return
some results.

## Project Structure

This is a multi module maven project with two modules:

- The `micoservice` module produces a spring boot application.
- The `functional-tests` is used to run functional tests using the [karate](https://github.com/intuit/karate) library.

## Instructions

Select one of the two exercises below and add the required behaviour to the spring boot application in the microservice module. You can:

- Add libraries you need.
- Refactor any of the existing code.

You will see that there are already a couple of endpoints in the `microservice` they are fundamentally there to demonstrate the use of the [karate](https://github.com/intuit/karate) library and should not be taken as complete examples.

### Assesment

Your submission will be judge along the following criterea.

- The solution works.
- The solution is maintainable.
- The solution is tested.
- The solution is appropriate.

## The Exercises

Example curl api calls for these exercises are listed in the following gist https://gist.github.com/bartonhammond/0a19da4c24c0f644ae38

**1. Find the hottest repositories created in the last week**

Use the [GitHub API][1] to expose an endpoint in this microservice the will get a list of the
highest starred repositories in the last week.

The endpoint should accept a parameter that sets the number of repositories to return.

The following fields should be returned:

      html_url
      watchers_count
      language
      description
      name


[1]: http://developer.github.com/v3/search/#search-repositories

## Added Solution to Get Starred Repository

For this we need to pass two input

** 1 st : [OFFSET] which is used for pagination - Whatever the offset is it means that which page you are on

** 2 nd : [NUM] of starred repository on that page -  Number of records on that page.

- Example 1: http://localhost:8080/search/repos?offSet=1&num=40
  Here 40 records will come on first page

- Example 2: http://localhost:8080/search/repos?offSet=2&num=40
  From this we will get next 40 records which falls on 2 page

- Example 3 : http://localhost:8080/search/repos?offSet=100&num=40
  Response will be an error :  because there will be only 1000 records we can search

  {
  "timestamp": "2022-05-16T14:58:23.8313767",
  "status": 422,
  "error": "Unprocessable Entity",
  "message": "Only the first 1000 search results are available",
  "path": "/search/repos"
  }