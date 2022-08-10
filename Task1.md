# TASK 1 - API Test

> You are testing a REST API which is intended to interact with a system for keeping track of users and some metadata. [...] Please discuss how you would test this API interaction (high-level test cases). For simplicity's sake, we can assume that authentication and correct configuration of credentials is already handled properly.

## Test Strategy

Before discussing scope of functional test cases I would like to start with planning testing types, layers and test distribution among CI pipeline. Except functional tests API requires doing some security and performance checks as well. So I can define following test layers:
* health check as smoke test
* JSON schema validation as contract test
* functional tests for the REST API
* security checks
* performance checks
* API end-to-end tests

In terms of pipeline distribution among the pipeline smoke and contract tests should be done as early as possible. Then functional suite should stay and finally API end-to-end suite should follow. Security checks might be included to functional suite while performance checks might be the last step of pipeline or even excluded at all. It depends and can be discussed with the team and reorganized.

## Test ideas

Here is a list of test ideas for functional & security checks for provided API:
* expected functionality works
  * POST creates new user (verify that it is really created in DB)
  * PUT updates user data (replaces all fields in DB, not only provided ones as PATCH does)
  * GET returns real data from DB
* check URLs
  * same structure
  * are https requests available over http?
  * are there other requests with the same structure? like DELETE https://api.url.org/user/[userid]
* check headers (there are no headers in examples, but they should be in real API to provide access)
  * with/without required headers
  * all supported headers
  * inconsistency between headers and content
* check request parameters ("id" path parameter for GET, PUT)
  * existing value in the system
  * non-existing/invalid values
  * without parameter
* check request body (for POST, PUT)
  * with/without required fields valid values
  * all fields valid values
  * extra fields are ignored or lead to error
  * missed body
  * missed/empty/null/doublicate values for fields
  * boundary checks (like 0 for "age" or string length for "name")
  * invalid values for fields
  * values not from defined enum ("country" field)
  * multi-entry for array ("country" field)
  * register sensitive?
  * is it possible to set "id" in POST body?
  * input data is validated, filtered, and sanitized (no xss/sql Injection possible)
* check response content and headers
  * all expected headers are returned
  * status code is logical for positive / negative requests
  * response data is according to the schema (fields, types)
  * descriptive errors
  * there is no extra data in response (Excessive Data Exposure)
  * created "id" is primary key from db? (no data leakage about amount of users)
* special checks
  * each user is able to interact with its own data (no Broken Authorization like PUT, GET for other user's "id")
  * is number of requests per client limited? (Rate Limiting)
  * does API produce any logs and include enough detail?
