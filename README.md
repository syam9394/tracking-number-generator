#  Tracking Number Generator API

A scalable Spring Boot REST API that generates unique tracking numbers for parcel shipments.

---

##  Features

- Unique 16-character alphanumeric tracking number
- RFC 3339 timestamp support
- High concurrency-safe
- OpenAPI 3.0 with Swagger UI

---

##  API Endpoint

**GET** `/next-tracking-number`

###  Query Parameters

| Name                  | Type     | Required | Example                                    |
|-----------------------|----------|----------|--------------------------------------------|
| origin_country_id     | String   | yes      | `IN`                                       |
| destination_country_id| String   | yes       | `US`                                       |
| weight                | Double   | yes       | `1.234`                                    |
| created_at            | DateTime | yes       | `2023-12-01T10:00:00+05:30`                |
| customer_id           | UUID     | yes       | `de619854-b59b-425e-9db4-943979e1bd49`     |
| customer_name         | String   | yes       | `RedBox Logistics`                         |
| customer_slug         | String   | yes       | `redbox-logistics`                         |

---

##  Sample Request

```http
GET /next-tracking-number?origin_country_id=IN&destination_country_id=US&weight=1.234&created_at=2023-12-01T10:00:00%2B05:30&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics

## Sample Response
{
  "tracking_number": "INUSRED1A2B3C4D",
  "created_at": "2023-12-01T10:00:00+05:30"
}

