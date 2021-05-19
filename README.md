# LOOFAH GRAPH API

## Overview

A Graph QL API which returns a list of skills

## Usage

**Endpoint**: `localhost:8080`

**Method**: `POST`

**Example request body:**

Get a list of skills
```
	skills {
        description
        category {
            title
        }
        grade {
            title
        }
    }
```

**Example response:**

```
{
    "data": {
        "skills": [
            {
                "description": "You write clear stories, bugs and comments which mean they can be picked up by other engineers",
                "category": {
                    "title": "collaboration"
                },
                "grade": {
                    "title": "analystDeveloper"
                }
            }
            {
                ...
            }
        ]
    },
    "errors": [],
    "dataPresent": true,
    "extensions": null
}
```

**Other examples:**

Please refer to the queries in test/resources/testQueries to see examples of 
all the queries supported by the API

**Error responses:**

Currently all responses are returned with HTTP status code 200. 

Any default Graph QL errors (eg. failed query validation) will be provided in the errors object in the response.

Custom error handling to follow. 

## Run

Run using the ./runLoofah.sh script in loofah-tooling.

## Playground

When the api is running, you can interact with it directly by visiting http://localhost:8080/playground

## Contributors
- Harriet Barsham
- Chris Cooksley
- Caitlin Cooling
- Elishka Flint
- Victoria James
- Sophie Brown
