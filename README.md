# LOOFAH GRAPH API

## Overview

A Graph QL API which returns a list of skills

## Usage

**Endpoint**: `localhost:8080/skills`

**Method**: `POST`

**Example request body:**

Get a list of all skills
```
{
    allSkills {
        id
        title
        description
    }
}
```

Get a skill by id
```
{
    skill(id:"1") {
        id
        title
        description
    }
}
```

**Example response:**

```
{
   "data" : {
      "allSkills" : [
         {
            "title" : "title1",
            "id" : "1",
            "description" : "description1"
         },
         {
            "description" : "description2",
            "id" : "2",
            "title" : "title2"
         },
         {
            "title" : "title3",
            "id" : "3",
            "description" : "description3"
         }
      ]
   },
   "dataPresent" : true,
   "extensions" : null,
   "errors" : []
}
```

**Error responses:**

Currently all responses are returned with HTTP status code 200. 

Any default Graph QL errors (eg. failed query validation) will be provided in the errors object in the response.

Custom error handling to follow. 

## Run

Run using Intellij. 

Dockerisation to follow.

## Contributors
- Harriet Barsham
- Chris Cooksley
- Caitlin Cooling
- Elishka Flint
- Victoria James
