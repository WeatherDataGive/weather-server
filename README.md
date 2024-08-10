## 전반적인 MySql과 PostgreSql의 성능 비교
---

### 1. 쓰기 성능
| | 시간 | 시간 | 평균 |
| :-: | :-: | :-: | :-: |
| **데이터 조회** | 3.183 sec | 4.166 sec | 3.675 sec |
| **PostgreSql** | 112.953 sec | 120.816 sec | 116.885 sec |
| **MySql** | 199.727 sec | 199.563 sec | 199.645 sec |

데이터 쓰기에서 PostgreSql의 성능이 더 좋았다.

&nbsp;

### 2. 모든 데이터 조회 성능
> SELECT * FROM weather_table;

| | 시간 | 시간 | 시간 | 시간 | 평균 |
| :-: | :-: | :-: | :-: | :-: | :-: |
| **PostgreSql** | 22.434 ms | 22.593 ms | 20.584 ms | 21.793 ms | 21.851 ms |
| **MySql** | 26.887 ms | 22.606 ms | 20.031 ms | 16.732 ms | 21.564 ms |

모든 데이터 조회에서 성능이 많이 차이 나지 않았다.

&nbsp;

### 3. 특정 데이터 조회 성능
> SELECT * FROM weather_table WHERE year=1930;

| | 시간 | 시간 | 시간 | 시간 | 평균 |
| :-: | :-: | :-: | :-: | :-: | :-: |
| **PostgreSql** | 4.697 ms | 6.046 ms | 4.587 ms | 4.273 ms | 4.900 ms |
| **MySql** | 17.923 ms | 13.274 ms | 17.335 ms | 14.389 ms | 15.730 ms |

특정 데이터 조회에서 PostgreSql의 성능이 더 좋았다.

&nbsp;

### 4. 특정 범위 조회 성능
> SELECT * FROM weather_table WHERE day < 20;
> 
> SELECT * FROM weather_table WHERE humidity < 50;

| | 시간 | 시간 | 시간 | 시간 | 평균 |
| :-: | :-: | :-: | :-: | :-: | :-: |
| **PostgreSql** | 20.16 ms | 13.952 ms | 6.982 ms | 7.775 ms | 12.217 ms |
| **MySql** | 16.876 ms | 15.224 ms | 15.52 ms | 16.349 ms | 15.992 ms |

특정 범위 조회에서 PostgreSql의 성능이 더 좋았다.

&nbsp;

### 결과
> 전반적인 성능(데이터 조회)에서는 Mysql 보다 PostgreSql의 성능이 더 좋았다.


&nbsp;

## 현재 비지니스에서의 MySql과 PostgreSql의 성능 비교
---

### 1. 현재 비지니스에서 사용되는 쿼리(INDEX 없음)
> SELECT SUM(temp) FROM weather_table WHERE year = 2000 AND month = 12

| | 시간 | 시간 | 시간 | 시간 | 평균 |
| :-: | :-: | :-: | :-: | :-: | :-: |
| **PostgreSql** | 13.234 ms | 4.511 ms | 3.146 ms | 4.375 ms | 6.316 ms |
| **MySql** | 8.690 ms | 9.413 ms | 8.0195 ms | 11.262 ms | 9.346 ms |

Index가 없는 상황에서 현재 비지니스에서 사용되는 쿼리의 성능은 PostgreSql이 더 좋았다.

&nbsp;

### 2. 현재 비지니스에서 사용되는 쿼리(INDEX 사용)
> SELECT SUM(temp) FROM weather_table WHERE year = 2000 AND month = 12

| | 시간 | 시간 | 시간 | 시간 | 평균 |
| :-: | :-: | :-: | :-: | :-: | :-: |
| **PostgreSql** | 0.542 ms | 0.448 ms | 0.313 ms | 0.458 ms | 0.440 ms |
| **MySql** | 0.528 ms | 0.323 ms | 0.521 ms | 0.527 ms | 0.475 ms |

Index가 있는 상황에서 현재 비지니스에서 사용되는 쿼리의 성능은 많이 차이 나지 않았다.

&nbsp;

### 3. 현재 비지니스에서 사용되는 쿼리(UNIQUE INDEX 사용)
> SELECT SUM(temp) FROM weather_table WHERE year = 2000 AND month = 12

| | 시간 | 시간 | 시간 | 시간 | 평균 |
| :-: | :-: | :-: | :-: | :-: | :-: |
| **PostgreSql** | 0.457 ms | 0.557 ms | 0.355 ms | 0.453 ms | 0.455 ms |
| **MySql** | 0.508 ms | 0.447 ms | 0.349 ms | 0.368 ms | 0.418 ms |

Unique Index가 있는 상황에서 현재 비지니스에서 사용되는 쿼리의 성능은 많이 차이 나지 않았다.

&nbsp;

### 결과
> 해당 비지니스에서 사용되는 쿼리의 성능(데이터 조회)으로는 Mysql보다 PostgreSql의 성능이 더 좋았다.
>
> Index를 사용하는 것과 Index를 사용하는 것은 실제로 성능 적으로 차이가 확연하게 났으며,
>
> Index를 사용 했을 때에는 Mysql과 PostgreSql의 성능적인 차이는 크지 않았다.

