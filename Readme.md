About
======

MapRedux is a lightweight library to aggregate data from Map-like data structures.

Motivation
===========

The library is to merge data from Map-like structures into [aggregated] Map structures.

The most useful features:

* Support of custom Reducers
* Support of sorting by multiple columns

How to use
============

Add dependency:
```
<dependency>
  <groupId>io.github.akaliutau</groupId>
  <artifactId>mapredux</artifactId>
  <version>0.0.3</version>
</dependency>
```

Consider the following input:

```json lines
{"country": "Netherlands", "age": 25, "hobbies": "swimming", "region": "Europe", "name": "Alice"}
{"country": "US", "age": 23, "hobbies": "reading", "region": "North America", "name": "Bob"}
{"country": "US", "age": 29, "hobbies": "spying", "region": "North America", "name": "Eve"}
{"country": "South Korea", "age": 31, "hobbies": "walking", ",region": "Asia", "name": "Yuni"}
{"age": 18, "region": "North America", "name": "X"}

```

```
MapRedux mr = new MapRedux.Builder()
        .select(
                column("name", "count", "count"),
                column("age", "avg_age", "avg"),
                column("region"),
                column("country"),
                column("hobbies", "set")
        )
        .where(m -> (int) m.get("age") > 18)
        .groupBy("region", "country")
        .orderBy(new StringColumnComparator("region"))
        .build();
List<Map<String, Object>> result = mr.reduce(records);

```

Aggregated results will look like this:

```json lines
{"country": "South Korea", "avg_age": 31.0, "hobbies": ["walking"], "count": 1, "region": "Asia"}
{"country": "Netherlands", "avg_age": 25.0, "hobbies": ["swimming"], "count": 1, "region": "Europe"}
{"country": "US", "avg_age": 26.0, "hobbies": ["spying", "reading"], "count": 2, "region": "North America"}
```

