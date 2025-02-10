
> 1. Docker is a containerization platform that packages applications and their dependencies together for it to run seamlessly across multiple environments.
> 2. Contains all the components for running a piece of software: code, system tools, runtime, system libraries, etc. 
> 3. Any software that can be installed on a server can be packaged in a Docker container. 
### Docker image:
- Images are templates containing instructions for creating containers. With Docker images, containers can be created to run on the Docker platform.
- There is no change to the image. It is immutable.
- Creating a Docker image requires writing a script in a Dockerfile.
- Used to package up applications and pre-configured server environments.

### Docker Container:
- Docker Containers are actually Docker Virtual Machines.
- Running instance of Docker image.
- Docker run command can be used