# Run docker postgres db

docker run --rm -dit --name studyPlatformDb -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=studyPlatform -e PGDATA=/var/lib/postgresql/data/pgdata postgres:14