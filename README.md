To run the application locally:
1. In the root directory, run:
   ```bash
   docker build -f Dockerfile-local -t gyst-app .
   ```
2. Then run:
   ```bash
    docker run -p 8080:8080 gyst-app
    ```
