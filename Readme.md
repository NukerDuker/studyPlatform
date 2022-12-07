# Run docker postgres db

docker run --rm -dit --name studyPlatformDb -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=studyPlatform -e PGDATA=/var/lib/postgresql/data/pgdata postgres:14    

# Documentation 
[Swagger](http://localhost:8080/swagger-ui/index.html#/)

# Realized functionality
When you make score to student for some lesson, method that counts his rate is invoked, after that method saves rate to db.    
If there is rank for current course, each new score for new lesson will update his rank.
To get rank just use swagger method student/get/{id}.

