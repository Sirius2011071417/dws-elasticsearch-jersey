# README

标签（空格分隔）： readme

---
## Recursively resolve query params with and/or/not by QueryBuilder
```
POST http://localhost:8080/dws/api/plan/

{"page_size":1,"page":1,"query": {"and": [{"or":[{"title__contains": "阳光"},{"keyword__contains": "九亭"}]}, {"release_date__gte":"2011-12-12"}]}}
```
will translate into

```
{
  "from" : 0,
  "size" : 1,
  "query" : {
    "bool" : {
      "must" : [
        {
          "range" : {
            "release_date" : {
              "from" : "2011-12-12",
              "to" : null,
              "include_lower" : true,
              "include_upper" : true,
              "boost" : 1.0
            }
          }
        }
      ],
      "should" : [
        {
          "match" : {
            "title" : {
              "query" : "阳光",
              "operator" : "OR",
              "analyzer" : "ik_max_word",
              "prefix_length" : 0,
              "max_expansions" : 50,
              "minimum_should_match" : "3<90%",
              "fuzzy_transpositions" : true,
              "lenient" : false,
              "zero_terms_query" : "NONE",
              "boost" : 1.0
            }
          }
        },
        {
          "multi_match" : {
            "query" : "九亭",
            "fields" : [
              "content^1.0",
              "title^1.0"
            ],
            "type" : "best_fields",
            "operator" : "OR",
            "analyzer" : "ik_max_word",
            "slop" : 0,
            "prefix_length" : 0,
            "max_expansions" : 50,
            "minimum_should_match" : "3<90%",
            "lenient" : false,
            "zero_terms_query" : "NONE",
            "boost" : 1.0
          }
        }
      ],
      "minimum_should_match": 1,
      "disable_coord" : false,
      "adjust_pure_negative" : true,
      "boost" : 1.0
    }
  },
  "sort" : [
    {
      "release_date" : {
        "order" : "desc"
      }
    },
    {
      "create_time" : {
        "order" : "desc"
      }
    },
    {
      "_score" : {
        "order" : "desc"
      }
    }
  ]
}

```

```
POST http://localhost:8080/dws/api/plan/010e5b40-2613-6ff5-9aec-3632e82ab6f6
```



