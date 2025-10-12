docker run --name postgres --restart=always -e POSTGRES_PASSWORD=postgres -p 5432:5432 -v /root/docker/postgres/data:/var/lib/postgresql  --privileged -d postgres:14.2
