[5分钟使用InfluxDB和Telegraf为服务器搭建基础监控[最新] - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/444649783)

`docker network create --driver bridge telegrf-network`

`docker run -d --name=influxdb -p 8086:8086 -v  /tmp/testdata/influx:/root/.influxdb2 --net=telegrf-network influxdb` 

`docker run -d --name=telegraf -P --net=telegrf-network telegraf` 

`docker network inspect telegrf-network`

`192.168.56.10:8086`

![](attachments/2023-05-23-1.png)

![](attachments/2023-05-23-2.png)

