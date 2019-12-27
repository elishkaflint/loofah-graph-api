# LOOFAH GRAPH API

## Overview

A Graph QL API which returns a list of skills

## Use

Request to get all skills (using curl);

```
curl -s -X GET \
  localhost:8080/skills \
  -H 'Content-Type: text/plain' \
  -d 'query { allSkills { id title description } }' | json_pp
```

Json response:

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

## Run

Run using Intellij (dockerisation to follow)

## Contributors
- Harriet Barsham
- Chris Cooksley
- Caitlin Cooling
- Elishka Flint
- Victoria James
